package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/9/15.
 */
public class TearBuff extends HeroMonsterBuff {

    public TearBuff(){
        super();
    }

    public TearBuff(int level){
        super(level);
    }

    @Override
    public String toString() {
        return "Tearing";
    }
}
