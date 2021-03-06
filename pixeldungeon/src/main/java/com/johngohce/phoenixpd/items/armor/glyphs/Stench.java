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
package com.johngohce.phoenixpd.items.armor.glyphs;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.blobs.Blob;
import com.johngohce.phoenixpd.actors.blobs.ToxicGas;
import com.johngohce.phoenixpd.items.armor.Armor;
import com.johngohce.phoenixpd.items.armor.Armor.Glyph;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSprite;
import com.johngohce.phoenixpd.sprites.ItemSprite.Glowing;
import com.johngohce.utils.Random;

public class Stench extends Glyph {

	private static final String TXT_STENCH	= "%s of stench";
	
	private static ItemSprite.Glowing GREEN = new ItemSprite.Glowing( 0x22CC44 );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage) {

		int level = Math.max( 0, armor.level );
		
		if (Level.adjacent( attacker.pos, defender.pos ) && Random.Int( level + 5 ) >= 4) {
			
			GameScene.add( Blob.seed( attacker.pos, 20, ToxicGas.class ) );
			
		}
		
		return damage;
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_STENCH, weaponName );
	}
	
	@Override
	public Glowing glowing() {
		return GREEN;
	}

}
