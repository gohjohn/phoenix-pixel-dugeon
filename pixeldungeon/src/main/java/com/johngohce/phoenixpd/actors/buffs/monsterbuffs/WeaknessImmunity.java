package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.actors.buffs.Weakness;

import java.util.HashSet;

/**
 * Created by johngoh on 6/21/15.
 */
public class WeaknessImmunity extends HeroMonsterBuff{

    public WeaknessImmunity(){
        super();
    }

    @Override
    public String toString() {
        return "Weakness Immunity";
    }

    public static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add( Weakness.class );
    }
}