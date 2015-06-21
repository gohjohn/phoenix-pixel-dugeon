package com.johngohce.phoenixpd.items.wands.monsterwands;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.ResultDescriptions;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.LightningImmunity;
import com.johngohce.phoenixpd.effects.CellEmitter;
import com.johngohce.phoenixpd.effects.Lightning;
import com.johngohce.phoenixpd.effects.particles.SparkParticle;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfRecharging;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.levels.traps.LightningTrap;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.utils.Utils;
import com.johngohce.utils.Callback;
import com.johngohce.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by johngoh on 6/16/15.
 */
public class ShamanStaff extends MonsterWand {
    public final int SPECIAL_LEVEL = 4;

    {
        name = "Shaman Staff of Lightning";
        image = ItemSpriteSheet.WAND_EBONY;
        isPermanentlyEquipped = true;
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {

        if (curCharges < maxCharges && damage > 0 && Random.IntRange(0,1) == 0) {
            curCharges++;
            updateQuickslot();

            ScrollOfRecharging.chargeAnimation(Dungeon.hero);
        }
    }


    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        if(level>=SPECIAL_LEVEL) {
            newBuffs.add(new LightningImmunity());
        }
        return newBuffs;
    }

    private ArrayList<Char> affected = new ArrayList<Char>();

    private int[] points = new int[20];
    private int nPoints;

    @Override
    protected void onZap( int cell ) {
        // Everything is processed in fx() method
        if (!curUser.isAlive()) {
            Dungeon.fail(Utils.format(ResultDescriptions.WAND, name, Dungeon.depth));
            GLog.n("You killed yourself with your own "+name+"...");
        }
    }

    private void hit( Char ch, int damage ) {

        if (damage < 1) {
            return;
        }

//        if (ch == Dungeon.hero) {
//            Camera.main.shake( 2, 0.3f );
//        }

        affected.add( ch );
        ch.damage( Level.water[ch.pos] && !ch.flying ? (int)(damage * 2) : damage, LightningTrap.SHAMAN_LIGHTNING  );

        ch.sprite.centerEmitter().burst( SparkParticle.FACTORY, 3 );
        ch.sprite.flash();

        points[nPoints++] = ch.pos;

        HashSet<Char> ns = new HashSet<Char>();
        for (int i=0; i < Level.NEIGHBOURS8.length; i++) {
            Char n = Actor.findChar(ch.pos + Level.NEIGHBOURS8[i]);
            if (n != null && !affected.contains( n )) {
                ns.add( n );
            }
        }

        if (ns.size() > 0) {
            hit( Random.element(ns), Random.Int( damage / 2, damage ) );
        }
    }

    @Override
    protected void fx( int cell, Callback callback ) {

        nPoints = 0;
        points[nPoints++] = Dungeon.hero.pos;

        Char ch = Actor.findChar( cell );
        if (ch != null) {

            affected.clear();
            int lvl = level();
            hit( ch, Random.Int( 5 + lvl / 2, 10 + lvl ) );

        } else {

            points[nPoints++] = cell;
            CellEmitter.center(cell).burst( SparkParticle.FACTORY, 3 );

        }
        curUser.sprite.parent.add( new Lightning( points, nPoints, callback ) );
    }

    @Override
    public String desc() {
        return "This is your trusty staff of lightning. Presented to you during the Gnoll tribe's shaman ritual, " +
                "you have bound your soul with this staff and cannot part with it.\n\n" +
               "It charges faster than normal wands when upgraded and has a chance to gain charges when attacking with it.\n\n" +
                ((level >= SPECIAL_LEVEL)?
                        "It pulses with electricity, dissipating all excess lightning around you.":
                        "At higher upgrades, it absorbs all lightning near you, making you immune to lightning.");
    }
}