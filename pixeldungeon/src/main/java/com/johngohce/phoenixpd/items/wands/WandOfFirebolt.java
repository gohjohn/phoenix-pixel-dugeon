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
package com.johngohce.phoenixpd.items.wands;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.ResultDescriptions;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.blobs.Blob;
import com.johngohce.phoenixpd.actors.blobs.Fire;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Burning;
import com.johngohce.phoenixpd.effects.MagicMissile;
import com.johngohce.phoenixpd.effects.particles.FlameParticle;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.mechanics.Ballistica;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.utils.Utils;
import com.johngohce.utils.Callback;
import com.johngohce.utils.Random;

public class WandOfFirebolt extends Wand {

	{
		name = "Wand of Firebolt";
	}
	
	@Override
	protected void onZap( int cell ) {

		int level = level();
		
		for (int i=1; i < Ballistica.distance - 1; i++) {
			int c = Ballistica.trace[i];
			if (Level.flamable[c]) {
				GameScene.add( Blob.seed( c, 1, Fire.class ) );
			}
		}
		
		GameScene.add( Blob.seed( cell, 1, Fire.class ) );
					
		Char ch = Actor.findChar( cell );
		if (ch != null) {	
			
			ch.damage( Random.Int( 1, 8 + level * level ), this );
			Buff.affect( ch, Burning.class ).reignite( ch );

			ch.sprite.emitter().burst( FlameParticle.FACTORY, 5 );
			
			if (ch == curUser && !ch.isAlive()) {
				Dungeon.fail( Utils.format( ResultDescriptions.WAND, name, Dungeon.depth ) );
				GLog.n( "You killed yourself with your own Wand of Firebolt..." );
			}
		}
	}
	
	protected void fx( int cell, Callback callback ) {
		MagicMissile.fire( curUser.sprite.parent, curUser.pos, cell, callback );
		Sample.INSTANCE.play( Assets.SND_ZAP );
	}
	
	@Override
	public String desc() {
		return
			"This wand unleashes bursts of magical fire. It will ignite " +
			"flammable terrain, and will damage and burn a creature it hits.";
	}
}
