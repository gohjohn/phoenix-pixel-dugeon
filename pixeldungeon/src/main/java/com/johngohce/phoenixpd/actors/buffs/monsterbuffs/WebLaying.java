package com.johngohce.phoenixpd.actors.buffs.monsterbuffs;

import com.johngohce.phoenixpd.actors.buffs.FlavourBuff;

/**
 * Created by johngoh on 6/8/15.
 */
public class WebLaying extends FlavourBuff {

    public static final float DURATION	= 1f;

//
//    @Override
//    public boolean attachTo( Char target ) {
//        if (super.attachTo( target )) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    @Override
//    public int icon() {
//        return BuffIndicator.LEVITATION;
//    }

    @Override
    public String toString() {
        return "Web Laying";
    }
}
