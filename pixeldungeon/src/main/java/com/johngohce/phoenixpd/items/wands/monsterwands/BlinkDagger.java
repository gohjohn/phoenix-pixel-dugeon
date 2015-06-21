package com.johngohce.phoenixpd.items.wands.monsterwands;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.noosa.tweeners.AlphaTweener;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Invisibility;
import com.johngohce.phoenixpd.effects.MagicMissile;
import com.johngohce.phoenixpd.effects.Speck;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfRecharging;
import com.johngohce.phoenixpd.mechanics.Ballistica;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Callback;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/22/15.
 */
public class BlinkDagger extends MonsterWand {

    {
        name = "Succubus Blink Dagger";
        image = ItemSpriteSheet.DAGGER;
        isPermanentlyEquipped = true;
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        if (curCharges < maxCharges && damage > 0 && Random.IntRange(0, 2) == 0) {
            curCharges++;
            updateQuickslot();

            ScrollOfRecharging.chargeAnimation(Dungeon.hero);
        }
    }

    @Override
    protected void calculateDamage() {
        //Sword (tier 3)
        MIN = 3 + level;
        MAX = 16 + 3 * level;
    }

    @Override
    protected void onZap(int cell) {

        int level = level();
        boolean charFound = false;
        int cellCharFound = -1;

        if (Ballistica.distance > level + 4) {
            cell = Ballistica.trace[level + 3];
        } else if (Actor.findChar( cell ) != null && Ballistica.distance > 1) {
            cellCharFound = cell;
            charFound = true;
            cell = Ballistica.trace[Ballistica.distance - 2];
        }

        curUser.sprite.visible = true;
        appear(Dungeon.hero, cell);
        Dungeon.observe();

        if(charFound && Actor.findChar(cellCharFound) != null && Actor.findChar(cellCharFound) != Dungeon.hero){
            Dungeon.hero.attack(Actor.findChar(cellCharFound));
            Invisibility.dispel();
        }
        curUser.spendAndNext(TIME_TO_ZAP);
    }

    @Override
    protected void wandUsed() {

        curCharges--;
        updateQuickslot();
//        use();
//        curUser.spendAndNext( TIME_TO_ZAP );
    }

    public static void appear( Char ch, int pos ) {

        ch.sprite.interruptMotion();

        ch.move( pos );
        ch.sprite.place( pos );

        if (ch.invisible == 0) {
            ch.sprite.alpha( 0 );
            ch.sprite.parent.add( new AlphaTweener( ch.sprite, 1, 0.4f ) );
        }

        ch.sprite.emitter().start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
        Sample.INSTANCE.play( Assets.SND_TELEPORT );
    }

    @Override
    protected void fx( int cell, Callback callback ) {
        MagicMissile.whiteLight( curUser.sprite.parent, curUser.pos, cell, callback );
        Sample.INSTANCE.play( Assets.SND_ZAP );
        curUser.sprite.visible = false;
    }


    @Override
    public String desc() {
        return "The fabled dagger used by Kelen, the fastest assassin ever to walk the lands.\n" +
                "Kelen was said to be able to escape every trap. Luckily for us, he wasn't able to escape yours.\n\n" +
                "It has a chance of replenishing charges on successful hit.";
    }
}