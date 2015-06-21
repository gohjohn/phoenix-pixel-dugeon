package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/21/15.
 */
public class RegenerationBuff extends HeroMonsterBuff {

    public RegenerationBuff(){ super(); }

    public RegenerationBuff( int level ){
        super(level);
    }

    @Override
    public String toString() {
        return "Regeneration";
    }

}
