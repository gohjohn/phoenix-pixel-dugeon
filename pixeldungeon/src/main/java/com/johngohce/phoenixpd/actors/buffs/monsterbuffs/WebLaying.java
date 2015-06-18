package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/8/15.
 */
public class WebLaying extends HeroMonsterBuff implements MonsterFlavorBuff {

    int counter = 2;

    public WebLaying(){
        super();
        counter = 2;
    }

    public WebLaying(int level){
        super(level);
        counter = Math.max(2,level);
    }

    @Override
    public boolean act() {
        if(counter==0) {
            detach();
        }
        if(counter>=0) counter--;
        return true;
    }

    @Override
    public String toString() {
        return "Web Laying";
    }
}
