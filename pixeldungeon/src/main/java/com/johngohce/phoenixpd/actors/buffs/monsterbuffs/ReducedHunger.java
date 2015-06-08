package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/8/15.
 */
public class ReducedHunger extends HeroMonsterBuff {

    public ReducedHunger( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Reduced Hunger";
    }

}
