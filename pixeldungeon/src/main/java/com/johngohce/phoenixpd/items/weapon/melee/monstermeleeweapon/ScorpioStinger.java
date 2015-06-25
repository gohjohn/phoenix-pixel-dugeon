package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Cripple;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/22/15.
 */
public class ScorpioStinger extends MonsterMeleeWeapon {
    //Special shooting ability

    public static final String AC_SHOOT	    = "SHOOT";

    {
        name = "Scorpio Stinger";
        image = ItemSpriteSheet.SCORPIO_STINGER;
        isPermanentlyEquipped = true;
        defaultAction = AC_SHOOT;
    }

    public ScorpioStinger() {
        super( 3, 1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        //Standard poison
        int level = Math.max( 0, this.level );
        if (Random.Int( level + 3 ) >= 2) {
            Buff.affect(defender, com.johngohce.phoenixpd.actors.buffs.Poison.class).
                    set(com.johngohce.phoenixpd.actors.buffs.Poison.duration(defender) * (level + 1));
        }
        if (Random.Int( 2 ) == 0) {
            Buff.prolong( defender, Cripple.class, Cripple.DURATION );
        }

        super.proc(attacker,defender,damage);
    }
    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions(hero);
        actions.add(AC_SHOOT);
        return actions;
    }


    @Override
    public void execute( Hero hero, String action ) {
        if(action.equals(AC_SHOOT)){
            curUser = hero;
            curItem = this;
            GameScene.selectCell(shooter);
        } else {
            super.execute( hero, action );
        }
    }

    @Override
    protected void onThrow( int cell ) {
        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == curUser) {
//            super.onThrow( cell );
        } else {
            if (!curUser.shoot( enemy, this )) {
//                miss( cell );
            }
        }
    }

    @Override
    public String desc() {
        return "Few who have seen your crippling serrated spikes, live to tell the tale. " +
                "But those who speak of you, only advice to skip the demon halls for fear you.\n\n" +
                "This weapon is a ranged weapon with limitless ammo.. " +
                "The only 'downside' is that it cannot be improved to shoot faster(or slower).";
    }
}