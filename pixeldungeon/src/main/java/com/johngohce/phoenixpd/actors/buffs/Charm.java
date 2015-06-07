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

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.SkinResistance;
import com.johngohce.phoenixpd.items.rings.RingOfElements.Resistance;
import com.johngohce.phoenixpd.ui.BuffIndicator;
import com.johngohce.utils.Bundle;

public class Charm extends FlavourBuff {
	
	public int object = 0;
	
	private static final String OBJECT	= "object";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( OBJECT, object );
		
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		object = bundle.getInt( OBJECT );
	}
	
	@Override
	public int icon() {
		return BuffIndicator.HEART;
	}
	
	@Override
	public String toString() {
		return "Charmed";
	}

    public static float duration( Char ch ) {
        Resistance r = ch.buff( Resistance.class );
        SkinResistance r2 = ch.buff( SkinResistance.class );
        float durationFactor = 1f;
        if(r != null) durationFactor *= r.durationFactor();
        if(r2 != null) durationFactor *= r2.resistanceReductionDurationFactor();

        return durationFactor;
    }
}
