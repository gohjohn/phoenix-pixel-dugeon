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
package com.johngohce.phoenixpd.actors.blobs;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Badges;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.DungeonTilemap;
import com.johngohce.phoenixpd.Journal;
import com.johngohce.phoenixpd.Journal.Feature;
import com.johngohce.phoenixpd.actors.buffs.Awareness;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.effects.BlobEmitter;
import com.johngohce.phoenixpd.effects.Identification;
import com.johngohce.phoenixpd.effects.Speck;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.levels.Terrain;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.utils.GLog;

public class WaterOfAwareness extends WellWater {

	private static final String TXT_PROCCED =
		"As you take a sip, you feel the knowledge pours into your mind. " +
		"Now you know everything about your equipped items. Also you sense " +
		"all items on the level and know all its secrets.";
	
	@Override
	protected boolean affectHero( Hero hero ) {
		
		Sample.INSTANCE.play( Assets.SND_DRINK );
		emitter.parent.add( new Identification( DungeonTilemap.tileCenterToWorld( pos ) ) );
		
		hero.belongings.observe();
		
		for (int i=0; i < Level.LENGTH; i++) {
			
			int terr = Dungeon.level.map[i];
			if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
				
				Level.set( i, Terrain.discover( terr ) );						
				GameScene.updateMap( i );
				
				if (Dungeon.visible[i]) {
					GameScene.discoverTile( i, terr );
				}
			}
		}
		
		Buff.affect( hero, Awareness.class, Awareness.DURATION );
		Dungeon.observe();

		Dungeon.hero.interrupt();
	
		GLog.p( TXT_PROCCED );
		
		Journal.remove( Feature.WELL_OF_AWARENESS );
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item ) {
		if (item.isIdentified()) {
			return null;
		} else {
			item.identify();
			Badges.validateItemLevelAquired( item );
			
			emitter.parent.add( new Identification( DungeonTilemap.tileCenterToWorld( pos ) ) );
			
			Journal.remove( Feature.WELL_OF_AWARENESS );
			
			return item;
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );	
		emitter.pour( Speck.factory( Speck.QUESTION ), 0.3f );
	}
	
	@Override
	public String tileDesc() {
		return 
			"Power of knowledge radiates from the water of this well. " +
			"Take a sip from it to reveal all secrets of equipped items.";
	}
}
