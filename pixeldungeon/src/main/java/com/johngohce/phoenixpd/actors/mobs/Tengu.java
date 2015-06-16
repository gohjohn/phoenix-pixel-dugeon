/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.johngohce.phoenixpd.actors.mobs;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Badges;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.Statistics;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.blobs.ToxicGas;
import com.johngohce.phoenixpd.actors.buffs.Poison;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;
import com.johngohce.phoenixpd.effects.CellEmitter;
import com.johngohce.phoenixpd.effects.Speck;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SuccubusLeather;
import com.johngohce.phoenixpd.items.keys.SkeletonKey;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfMagicMapping;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfPsionicBlast;
import com.johngohce.phoenixpd.items.weapon.enchantments.Death;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.levels.Terrain;
import com.johngohce.phoenixpd.mechanics.Ballistica;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.TenguSprite;
import com.johngohce.utils.Random;

import java.util.HashSet;

public class Tengu extends Mob {

	private static final int JUMP_DELAY = 5;
	
	{
		name = Dungeon.depth == Statistics.deepestFloor ? "Tengu" : "memory of Tengu";
		spriteClass = TenguSprite.class;
		
		HP = HT = 120;
		EXP = 20;
		defenseSkill = 20;
	}
	
	private int timeToJump = JUMP_DELAY;
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 8, 15 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 20;
	}
	
	@Override
	public int dr() {
		return 5;
	}
	
	@Override
	public void die( Object cause ) {
		
//		Badges.Badge badgeToCheck = null;
//		switch (Dungeon.hero.heroClass) {
//		case WARRIOR:
//			badgeToCheck = Badge.MASTERY_WARRIOR;
//			break;
//		case MAGE:
//			badgeToCheck = Badge.MASTERY_MAGE;
//			break;
//		case ROGUE:
//			badgeToCheck = Badge.MASTERY_ROGUE;
//			break;
//		case HUNTRESS:
//			badgeToCheck = Badge.MASTERY_HUNTRESS;
//			break;
//		}
//		if (!Badges.isUnlocked( badgeToCheck )) {
//			Dungeon.level.drop( new TomeOfMastery(), pos ).sprite.drop();
//		}
		
		GameScene.bossSlain();
		Dungeon.level.drop( new SkeletonKey(), pos ).sprite.drop();
		super.die( cause );
		
		Badges.validateBossSlain();
		
		yell( "Free at last..." );
	}
	
	@Override
	protected boolean getCloser( int target ) {
		if (Level.fieldOfView[target]) {
			jump();
			return true;
		} else {
			return super.getCloser( target );
		}
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return Ballistica.cast( pos, enemy.pos, false, true ) == enemy.pos;
	}
	
	@Override
	protected boolean doAttack( Char enemy ) {
		timeToJump--;
		if (timeToJump <= 0 && Level.adjacent( pos, enemy.pos )) {
			jump();
			return true;
		} else {
			return super.doAttack( enemy );
		}
	}
	
	private void jump() {
		timeToJump = JUMP_DELAY;
		
		for (int i=0; i < 4; i++) {
			int trapPos;
			do {
				trapPos = Random.Int( Level.LENGTH );
			} while (!Level.fieldOfView[trapPos] || !Level.passable[trapPos]);
			
			if (Dungeon.level.map[trapPos] == Terrain.INACTIVE_TRAP) {
				Level.set( trapPos, Terrain.POISON_TRAP );
				GameScene.updateMap( trapPos );
				ScrollOfMagicMapping.discover( trapPos );
			}
		}
		
		int newPos;
		do {
			newPos = Random.Int( Level.LENGTH );
		} while (
			!Level.fieldOfView[newPos] || 
			!Level.passable[newPos] || 
			(enemy != null && Level.adjacent( newPos, enemy.pos )) ||
			Actor.findChar( newPos ) != null);
		
		sprite.move( pos, newPos );
		move( newPos );
		
		if (Dungeon.visible[newPos]) {
			CellEmitter.get( newPos ).burst( Speck.factory( Speck.WOOL ), 6 );
			Sample.INSTANCE.play( Assets.SND_PUFF );
		}
		
		spend( 1 / speed() );
	}
	
	@Override
	public void notice() {
		super.notice();
        Hero hero = Dungeon.hero;



        Boolean succubusBonusFlag = false;
        if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
            if(Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                if(armor.level >= armor.TENGU_SPECIAL_LEVEL){
                    succubusBonusFlag = true;
                }
            }
        }

        if(succubusBonusFlag){
            HP = 1;
            yell( "*drool*" );
        }else if(hero.monsterClass == HeroMonsterClass.SUCCUBUS){
            HP = Math.min((int) (HT * 0.8f), HP);
            yell( "Oh! A " + hero.heroClass.title() + "!" );
        }else{
            yell( "Gotcha, " + hero.heroClass.title() + "!" );
        }
	}
	
	@Override
	public String description() {
        Boolean succubusBonusFlag = false;
        if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
            if(Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                if(armor.level >= armor.DWARF_KING_SPECIAL_LEVEL){
                    succubusBonusFlag = true;
                }
            }
        }

		String desc =
			"Tengu are members of the ancient assassins clan, which is also called Tengu. " +
			"These assassins are noted for extensive use of shuriken and traps.";

        if(succubusBonusFlag) desc += "\n\nThe moment he sees you, he falls to the ground grovelling.";

        return desc;
	}
	
	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add( ToxicGas.class );
		RESISTANCES.add( Poison.class );
		RESISTANCES.add( Death.class );
		RESISTANCES.add( ScrollOfPsionicBlast.class );
	}
	
	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
}
