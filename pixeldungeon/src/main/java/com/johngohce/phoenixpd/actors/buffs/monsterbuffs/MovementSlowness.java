package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/8/15.
 */
public class MovementSlowness extends HeroMonsterBuff {

    public MovementSlowness(){
        super();
    }

    public MovementSlowness(int level){
        super(level);
    }

    @Override
    public String toString() {
        return "Slowness";
    }

    public float slownessFactor() {
        return 1.0f * level / 10 + 1;
    }

}
