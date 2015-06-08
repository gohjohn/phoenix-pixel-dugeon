package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.actors.buffs.Burning;
import com.johngohce.phoenixpd.items.weapon.enchantments.Fire;

import java.util.HashSet;

/**
 * Created by johngoh on 6/8/15.
 */
public class FireImmunity extends HeroMonsterBuff{

    @Override
    public String toString() {
        return "Fire Immunity";
    }

    public static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add( Fire.class );
        IMMUNITIES.add( Burning.class );
    }
}