package com.johngohce.phoenixpd.items.wands.monsterwands;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Weakness;
import com.johngohce.phoenixpd.effects.MagicMissile;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfRecharging;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Callback;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/21/15.
 */
public class WarlockStaff extends MonsterWand {

    {
        name = "Warlock Staff of Weakening";
        image = ItemSpriteSheet.WARLOCK_STAFF;
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        if (curCharges < maxCharges && damage > 0 && Random.IntRange(0, 1) == 0) {
            curCharges++;
            updateQuickslot();

            ScrollOfRecharging.chargeAnimation(Dungeon.hero);
        }
    }


    @Override
    protected void onZap(int cell) {

        Char ch = Actor.findChar(cell);
        if (ch != null) {
            hit(ch, Random.Int(1, 8 + level * level)); //WandOfFirebolt damage
        }
    }

    private void hit(Char ch, int damage) {
        if (damage < 1) {
            return;
        }

        if (Random.Int(2) == 0) {
            Buff.prolong(ch, Weakness.class, Weakness.duration(ch));
        }
        ch.damage(damage, this);
    }

    @Override
    protected void fx(int cell, Callback callback) {
        MagicMissile.shadow(curUser.sprite.parent, curUser.pos, cell, callback);
        Sample.INSTANCE.play( Assets.SND_ZAP );
    }

    @Override
    public int price() {
        int price = 100;
        if (levelKnown) {
            if (level > 0) {
                price *= (level + 1);
            } else if (level < 0) {
                price /= (1 - level);
            }
        }
        if (price < 1) {
            price = 1;
        }
        return price;
    }


    @Override
    public String desc() {
        return "This is your trusty staff of weakening. Made of holly, it has a phoenix feather core, " +
                "and it is deadly against all enemies, second only to Wand of Firebolt.\n\n" +
                "It charges faster than normal wands when upgraded and has a chance to gain charges when used in melee attack.";
    }
}