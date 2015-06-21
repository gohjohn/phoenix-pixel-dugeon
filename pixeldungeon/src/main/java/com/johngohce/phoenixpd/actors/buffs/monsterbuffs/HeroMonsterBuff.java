package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import android.util.Log;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;

/**
 * Created by johngoh on 6/6/15.
 */
public class HeroMonsterBuff extends Buff {
    // TODO Buff logic needs work
    // Buffs should be attached to the hero for as long as the item is equipped. Currently doesn't work properly.

    public int level = 1;

    public HeroMonsterBuff(){
        level = 1;
    }

    public HeroMonsterBuff( int level ){
        this.level = level;
    }

    public int level() {
        return level;
    }

    public void level( int value ) {
        level = value;
    }

    @Override
    public void detach() {
        Log.d("HeroMonsterBuff.detach()",toString()+".detach()");
        super.detach();
    }


    @Override
    public boolean attachTo( Char target ) {
        Log.d("HeroMonsterBuff.attachTo()",toString()+".attachTo("+target.toString()+")");
        if (super.attachTo( target )) {
            return true;
        } else {
            return false;
        }
    }
}
