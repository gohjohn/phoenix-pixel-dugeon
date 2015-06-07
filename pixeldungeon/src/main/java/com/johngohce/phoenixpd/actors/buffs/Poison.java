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
package com.johngohce.phoenixpd.actors.buffs;

import com.johngohce.phoenixpd.Badges;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.ResultDescriptions;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.SkinResistance;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.rings.RingOfElements.Resistance;
import com.johngohce.phoenixpd.ui.BuffIndicator;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.utils.Utils;
import com.johngohce.utils.Bundle;

public class Poison extends Buff implements Hero.Doom {
	
	protected float left;
	
	private static final String LEFT	= "left";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEFT, left );
		
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		left = bundle.getFloat( LEFT );
	}
	
	public void set( float duration ) {
		this.left = duration;
	};
	
	@Override
	public int icon() {
		return BuffIndicator.POISON;
	}
	
	@Override
	public String toString() {
		return "Poisoned";
	}
	
	@Override
	public boolean act() {
		if (target.isAlive()) {
			
			target.damage( (int)(left / 3) + 1, this );
			spend( TICK );
			
			if ((left -= TICK) <= 0) {
				detach();
			}
			
		} else {
			
			detach();
			
		}

		return true;
	}


    public static float duration( Char ch ) {
        Resistance r = ch.buff( Resistance.class );
        SkinResistance r2 = ch.buff( SkinResistance.class );
        float durationFactor = 1f;
        if(r != null) durationFactor *= r.durationFactor();
        if(r2 != null) durationFactor *= r2.resistanceReductionDurationFactor();

        return durationFactor;
    }

	@Override
	public void onDeath() {
		Badges.validateDeathFromPoison();
		
		Dungeon.fail( Utils.format( ResultDescriptions.POISON, Dungeon.depth ) );
		GLog.n( "You died from poison..." );
	}
}
