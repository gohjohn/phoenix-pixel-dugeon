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

import android.util.Log;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ElementalResistance;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.rings.RingOfElements.Resistance;
import com.johngohce.phoenixpd.ui.BuffIndicator;

public class Weakness extends FlavourBuff {

	private static final float DURATION = 40f;

	@Override
	public int icon() {
		return BuffIndicator.WEAKNESS;
	}
	
	@Override
	public String toString() {
		return "Weakened";
	}
	
	@Override
	public boolean attachTo( Char target ) {
		if (super.attachTo( target )) {
            if(target == Dungeon.hero){
                Hero hero = (Hero)target;
                hero.weakened = true;
                hero.belongings.discharge();
            }
            Log.i("Weakness",target.name + " is weakened.");

			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void detach() {
		super.detach();
        if(target == Dungeon.hero) {
            ((Hero) target).weakened = false;
        }
	}


    public static float duration( Char ch ) {
        Resistance r = ch.buff( Resistance.class );
        ElementalResistance r2 = ch.buff( ElementalResistance.class );
        float durationFactor = 1f;
        if(r != null) durationFactor *= r.durationFactor();
        if(r2 != null) durationFactor *= r2.resistanceReductionDurationFactor();

        return durationFactor * DURATION;
    }

    public int weakenedDamage(int dmg) {
        return (int) (dmg * 0.8f);
    }
}
