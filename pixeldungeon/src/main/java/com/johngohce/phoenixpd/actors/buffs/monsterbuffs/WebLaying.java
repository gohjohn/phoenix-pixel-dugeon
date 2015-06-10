package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/8/15.
 */
public class WebLaying extends HeroMonsterBuff {

    public WebLaying(){ super(); }

    public WebLaying(int level){
        super(level);
    }

    @Override
    public String toString() {
        return "Web Laying";
    }
}
