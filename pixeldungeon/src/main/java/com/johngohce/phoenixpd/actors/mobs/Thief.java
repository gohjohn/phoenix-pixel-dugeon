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

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Terror;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;
import com.johngohce.phoenixpd.items.Gold;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SuccubusLeather;
import com.johngohce.phoenixpd.items.rings.RingOfHaggler;
import com.johngohce.phoenixpd.sprites.CharSprite;
import com.johngohce.phoenixpd.sprites.ThiefSprite;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.utils.Utils;
import com.johngohce.utils.Bundle;
import com.johngohce.utils.Random;

public class Thief extends Mob {

	protected static final String TXT_STOLE	= "%s stole %s from you!";
	protected static final String TXT_CARRIES	= "\n\n%s is carrying a _%s_. Stolen obviously.";
	
	public Item item;
	
	{
		name = "crazy thief";
		spriteClass = ThiefSprite.class;
		
		HP = HT = 20;
		defenseSkill = 12;
		
		EXP = 5;
		maxLvl = 10;
		
		loot = RingOfHaggler.class;
		lootChance = 0.01f;
		
		FLEEING = new Fleeing();
	}
	
	private static final String ITEM = "item";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( ITEM, item );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		item = (Item)bundle.get( ITEM );
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 7 );
	}
	
	@Override
	protected float attackDelay() {
		return 0.5f;
	}
	
	@Override
	public void die( Object cause ) {

		super.die( cause );
		
		if (item != null) {
			Dungeon.level.drop( item, pos ).sprite.drop();
		}
	}
	
	@Override
	public int attackSkill( Char target ) {
        if(enemy == Dungeon.hero){
            Boolean succubusBonusFlag = false;
            if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
                if(Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                    SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                    if(armor.level >= armor.THIEF_SPECIAL_LEVEL){
                        succubusBonusFlag = true;
                    }
                }
            }
            if (succubusBonusFlag) return 1;
        }
        return 12;
	}
	
	@Override
	public int dr() {
		return 3;
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {

		if (item == null && enemy instanceof Hero && steal( (Hero)enemy )) {
			state = FLEEING;
		}
		
		return damage;
	}
	
	@Override
	public int defenseProc(Char enemy, int damage) {
        if(enemy == Dungeon.hero){
            Boolean succubusBonusFlag = false;
            if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
                if(Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                    SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                    if(armor.level >= armor.THIEF_SPECIAL_LEVEL){
                        succubusBonusFlag = true;
                    }
                }
            }
            if (succubusBonusFlag) HP = 1;
        }
		if (state == FLEEING) {
			Dungeon.level.drop( new Gold(), pos ).sprite.drop();
		}
		
		return damage;
	}
	
	protected boolean steal( Hero hero ) {
		
		Item item = hero.belongings.randomUnequipped();
		if (item != null) {
			
			GLog.w( TXT_STOLE, this.name, item.name() );
			
			item.detachAll( hero.belongings.backpack );
			this.item = item;
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String description() {
		String desc =
			"Deeper levels of the dungeon have always been a hiding place for all kinds of criminals. " +
			"Not all of them could keep a clear mind during their extended periods so far from daylight. Long ago, " +
			"these crazy thieves and bandits have forgotten who they are and why they steal.";
		
		if (item != null) {
			desc += String.format( TXT_CARRIES, Utils.capitalize( this.name ), item.name() );
		}

        if(enemy == Dungeon.hero){
            Boolean succubusBonusFlag = false;
            if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
                if(Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                    SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                    if(armor.level >= armor.THIEF_SPECIAL_LEVEL){
                        succubusBonusFlag = true;
                    }
                }
            }
            if (succubusBonusFlag) desc += "\n\nDrooling all over the place, he can't seem to fight you properly.";
        }

		return desc;
	}
	
	private class Fleeing extends Mob.Fleeing {
		@Override
		protected void nowhereToRun() {
			if (buff( Terror.class ) == null) {
				sprite.showStatus( CharSprite.NEGATIVE, TXT_RAGE );
				state = HUNTING;
			} else {
				super.nowhereToRun();
			}
		}
	}
}
