package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.levels.traps.LightningTrap;

import java.util.HashSet;

/**
 * Created by johngoh on 6/16/15.
 */
public class LightningImmunity extends HeroMonsterBuff{

    @Override
    public String toString() {
        return "Lightning Immunity";
    }

    public static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add( LightningTrap.Electricity.class );
        IMMUNITIES.add( LightningTrap.ShamanElectricity.class );
    }
}