package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.actors.buffs.Poison;
import com.johngohce.phoenixpd.actors.buffs.Roots;

import java.util.HashSet;

/**
 * Created by johngoh on 6/8/15.
 */
public class SpiderImmunity extends HeroMonsterBuff{

    public SpiderImmunity(){
        super();
    }

    @Override
    public String toString() {
        return "Spider Immunity";
    }

    public static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add( Roots.class );
        IMMUNITIES.add( Poison.class );
    }
}