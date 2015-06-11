package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/11/15.
 */
public class AttackHaste extends HeroMonsterBuff {

    public AttackHaste(){
        super();
    }

    public AttackHaste( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Attack Haste";
    }

}
