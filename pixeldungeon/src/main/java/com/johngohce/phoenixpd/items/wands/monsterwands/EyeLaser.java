package com.johngohce.phoenixpd.items.wands.monsterwands;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.DungeonTilemap;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Terror;
import com.johngohce.phoenixpd.actors.buffs.Vertigo;
import com.johngohce.phoenixpd.effects.CellEmitter;
import com.johngohce.phoenixpd.effects.DeathRay;
import com.johngohce.phoenixpd.effects.particles.PurpleParticle;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfRecharging;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.levels.Terrain;
import com.johngohce.phoenixpd.mechanics.Ballistica;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Callback;
import com.johngohce.utils.Random;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/21/15.
 */
public class EyeLaser extends MonsterWand {
//TODO FIX... currently doesnt go thru enemies
    {
        name = "Evil Eye of Doom";
        image = ItemSpriteSheet.SMTH;
        isPermanentlyEquipped = true;
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        if (curCharges < maxCharges && damage > 0 && Random.IntRange(0, 1) == 0) {
            curCharges++;
            updateQuickslot();

            ScrollOfRecharging.chargeAnimation(Dungeon.hero);
        }
        //Standard Eldritch/Terror/Horror Weapon enchantment
        int level = Math.max( 0, this.level );
        if (Random.Int( level + 5 ) >= 4) {
            if (defender == Dungeon.hero) {
                Buff.affect(defender, Vertigo.class, Vertigo.duration(defender));
            } else {
                Buff.affect(defender, Terror.class, Terror.DURATION).object = attacker.id();
            }
        }
    }


    @Override
    protected void onZap(int cell) {

        boolean terrainAffected = false;

        int level = level();

        int maxDistance = distance();
        Ballistica.distance = Math.min( Ballistica.distance, maxDistance );

        ArrayList<Char> chars = new ArrayList<Char>();

        for (int i=1; i < Ballistica.distance; i++) {

            int c = Ballistica.trace[i];

            Char ch;
            if ((ch = Actor.findChar(c)) != null) {
                chars.add( ch );
            }

            int terr = Dungeon.level.map[c];
            if (terr == Terrain.DOOR || terr == Terrain.BARRICADE) {

                Level.set( c, Terrain.EMBERS );
                GameScene.updateMap( c );
                terrainAffected = true;

            } else if (terr == Terrain.HIGH_GRASS) {

                Level.set( c, Terrain.GRASS );
                GameScene.updateMap( c );
                terrainAffected = true;

            }

            CellEmitter.center( c ).burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
        }

        if (terrainAffected) {
            Dungeon.observe();
        }

        int lvl = level + chars.size();
        int dmgMin = lvl;
        int dmgMax = 8 + lvl * lvl;// wand has /3
        for (Char ch : chars) {
            ch.damage( Random.NormalIntRange( dmgMin, dmgMax ), this );
            ch.sprite.centerEmitter().burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
            ch.sprite.flash();
        }
    }

    private int distance() {
        return level() + 4;
    }

    @Override
    protected void fx(int cell, Callback callback) {
        cell = Ballistica.trace[Math.min( Ballistica.distance, distance() ) - 1];
        curUser.sprite.parent.add( new DeathRay( curUser.sprite.center(), DungeonTilemap.tileCenterToWorld(cell) ) );
        callback.call();
    }

    @Override
    public String desc() {
        return "A common tip among veterans of this dungeon is to skip the last demon halls.. " +
                "You hear that the main reason is you.\n" +
                "Your deadly attacks demolish all in your way, making this possibly the strongest weapon in game.\n\n" +
                "Normal attacks may replenish your laser charges and even cause your foes to fear you.";
    }
}