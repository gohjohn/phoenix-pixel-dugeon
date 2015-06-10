package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/7/15.
 */
public class MovementHaste extends HeroMonsterBuff {

    public MovementHaste(){
        super();
    }

    public MovementHaste( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Movement Haste";
    }

}
