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
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ElementalResistance;
import com.johngohce.phoenixpd.items.rings.RingOfElements.Resistance;
import com.johngohce.phoenixpd.ui.BuffIndicator;

public class Vertigo extends FlavourBuff {
	
	public static final float DURATION	= 10f;
	
	@Override
	public int icon() {
		return BuffIndicator.VERTIGO;
	}
	
	@Override
	public String toString() {
		return "Vertigo";
	}


    public static float duration( Char ch ) {
        Resistance r = ch.buff( Resistance.class );
        ElementalResistance r2 = ch.buff( ElementalResistance.class );
        float durationFactor = 1f;
        if(r != null) durationFactor *= r.durationFactor();
        if(r2 != null) durationFactor *= r2.resistanceReductionDurationFactor();

        return durationFactor * DURATION;
    }
}
