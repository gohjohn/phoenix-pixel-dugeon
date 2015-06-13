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
package com.johngohce.phoenixpd.levels.features;

import com.johngohce.phoenixpd.Challenges;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Barkskin;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.TearBuff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.actors.hero.HeroSubClass;
import com.johngohce.phoenixpd.effects.CellEmitter;
import com.johngohce.phoenixpd.effects.particles.LeafParticle;
import com.johngohce.phoenixpd.items.Dewdrop;
import com.johngohce.phoenixpd.items.Generator;
import com.johngohce.phoenixpd.items.rings.RingOfHerbalism.Herbalism;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.levels.Terrain;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.utils.Random;

public class HighGrass {

	public static void trample( Level level, int pos, Char ch ) {
		
		Level.set( pos, Terrain.GRASS );
		GameScene.updateMap( pos );
		if(ch == null) return;

		if (!Dungeon.isChallenged( Challenges.NO_HERBALISM )) {
			int herbalismLevel = 0;
			if (ch != null) {
				Herbalism herbalism = ch.buff( Herbalism.class );
				if (herbalism != null) {
					herbalismLevel = herbalism.level;
				}
			}
            int tearBuffLevel = 0;

            TearBuff tearBuff = ch.buff( TearBuff.class );
            if (tearBuff != null) {
                tearBuffLevel = tearBuff.level;
            }
			// Seed
			if (herbalismLevel >= 0 && Random.Int( 18 ) <= Random.Int( herbalismLevel + 1 )) {
				level.drop( Generator.random( Generator.Category.SEED ), pos ).sprite.drop();
			}
			
			// Dew
			if (herbalismLevel + tearBuffLevel >= 0 && Random.Int( 6 ) <= Random.Int( herbalismLevel + tearBuffLevel/2 + 1 )) {
				level.drop( new Dewdrop(), pos ).sprite.drop();
			}
		}
		
		int leaves = 4;
		
		// Warlock's barkskin
		if (ch instanceof Hero && ((Hero)ch).subClass == HeroSubClass.WARDEN) {
			Buff.affect( ch, Barkskin.class ).level( ch.HT / 3 );
			leaves = 8;
		}
		
		CellEmitter.get( pos ).burst( LeafParticle.LEVEL_SPECIFIC, leaves );
		Dungeon.observe();
	}
}
