package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/20/15.
 */
public class EnrageAbleBuff extends HeroMonsterBuff {

    public EnrageAbleBuff(){
        super();
    }

    public EnrageAbleBuff( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Enrage-able";
    }

}
