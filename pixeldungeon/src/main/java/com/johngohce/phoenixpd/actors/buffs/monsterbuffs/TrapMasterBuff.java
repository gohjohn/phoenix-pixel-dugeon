package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

/**
 * Created by johngoh on 6/9/15.
 */
public class TrapMasterBuff extends HeroMonsterBuff {

    public TrapMasterBuff(){
        super();
    }

    public TrapMasterBuff(int level){
        super(level);
    }

    @Override
    public String toString() {
        return "Trap Master";
    }
}
