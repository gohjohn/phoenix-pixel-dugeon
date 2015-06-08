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
package com.johngohce.phoenixpd.actors.mobs.npcs;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;
import com.johngohce.phoenixpd.effects.CellEmitter;
import com.johngohce.phoenixpd.effects.particles.ElmoParticle;
import com.johngohce.phoenixpd.items.Heap;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SuccubusLeather;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ShopkeeperSprite;
import com.johngohce.phoenixpd.windows.WndBag;
import com.johngohce.phoenixpd.windows.WndTradeItem;

public class Shopkeeper extends NPC {

	{
		name = "shopkeeper";
		spriteClass = ShopkeeperSprite.class;
	}
	
	@Override
	protected boolean act() {
		
		throwItem();
		
		sprite.turnTo( pos, Dungeon.hero.pos );
		spend( TICK );
		return true;
	}
	
	@Override
	public void damage( int dmg, Object src ) {
		flee();
	}
	
	@Override
	public void add( Buff buff ) {
		flee();
	}
	
	protected void flee() {
		for (Heap heap: Dungeon.level.heaps.values()) {
			if (heap.type == Heap.Type.FOR_SALE) {
				CellEmitter.get( heap.pos ).burst( ElmoParticle.FACTORY, 4 );
				heap.destroy();
			}
		}
		
		destroy();
		
		sprite.killAndErase();
		CellEmitter.get( pos ).burst( ElmoParticle.FACTORY, 6 );
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public String description() {

        String desc = "This stout guy looks more appropriate for a trade district in some large city " +
                "than for a dungeon. His prices explain why he prefers to do business here. ";
        if(Dungeon.hero.monsterClass == HeroMonsterClass.SUCCUBUS){
            if (Dungeon.hero.belongings.armor instanceof SuccubusLeather){
                SuccubusLeather armor = (SuccubusLeather) Dungeon.hero.belongings.armor;
                if (armor.level >= armor.SHOPKEEPER_SPECIAL_LEVEL){
                    desc += "\n\nHe is practically drooling at you. Looks like you'll get a big discount. ";
                }else{
                    desc += "\n\nYou catch him stealing glances at you. Looks like you'll be able to get slightly cheaper goods. ";
                }
            }else{
                desc += "\n\nYou catch him stealing glances at you. Looks like you'll be able to get slightly cheaper goods. ";
            }
        }

		return desc;
	}
	
	public static WndBag sell() {
		return GameScene.selectItem( itemSelector, WndBag.Mode.FOR_SALE, "Select an item to sell" );
	}
	
	private static WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect( Item item ) {
			if (item != null) {
				WndBag parentWnd = sell();
				GameScene.show( new WndTradeItem( item, parentWnd ) );
			}
		}
	};

	@Override
	public void interact() {
		sell();
	}
}
