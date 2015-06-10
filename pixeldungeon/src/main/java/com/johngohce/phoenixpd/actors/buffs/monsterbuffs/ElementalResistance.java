package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.actors.blobs.ToxicGas;
import com.johngohce.phoenixpd.actors.buffs.Burning;
import com.johngohce.phoenixpd.actors.buffs.Poison;
import com.johngohce.phoenixpd.actors.mobs.Eye;
import com.johngohce.phoenixpd.actors.mobs.Warlock;
import com.johngohce.phoenixpd.actors.mobs.Yog;
import com.johngohce.phoenixpd.levels.traps.LightningTrap;
import com.johngohce.utils.Random;

import java.util.HashSet;

/**
 * Created by johngoh on 6/6/15.
 */
public class ElementalResistance extends HeroMonsterBuff {

    public ElementalResistance(int level){
        super(level);
    }

    @Override
    public String toString() {
        return "Skin Resistance";
    }

    private static final HashSet<Class<?>> EMPTY = new HashSet<Class<?>>();
    private static final HashSet<Class<?>> FULL;
    static {
        FULL = new HashSet<>();
        FULL.add( Burning.class );
        FULL.add( ToxicGas.class );
        FULL.add( Poison.class );
        FULL.add( LightningTrap.Electricity.class );
        FULL.add( LightningTrap.ShamanElectricity.class );
        FULL.add( Warlock.class );
        FULL.add( Eye.class );
        FULL.add( Yog.BurningFist.class );
    }

    public HashSet<Class<?>> resistances() {
        if (Random.Int(level + 3) >= 3) {
            return FULL;
        } else {
            return EMPTY;
        }
    }

    public float resistanceReductionDurationFactor() {
        return level < 0 ? 1 : (2 + 0.5f * level) / (2 + level);
    }

}
