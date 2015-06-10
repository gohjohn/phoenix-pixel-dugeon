package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/7/15.
 */
public class MultiplicityBuff extends HeroMonsterBuff {

    public MultiplicityBuff(){
        super();
    }

    public MultiplicityBuff( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Multiplicity";
    }

}
