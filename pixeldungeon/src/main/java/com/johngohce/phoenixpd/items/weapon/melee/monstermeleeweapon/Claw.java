package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Bleeding;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/10/15.
 */
public class Claw extends MonsterMeleeWeapon {
    public final int SPECIAL_LEVEL = 4;

    {
        name = "Claw";
        image = ItemSpriteSheet.WEAPON;
    }

    public Claw() {
        super( 1, 1.1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        if (level>=SPECIAL_LEVEL && Random.Int(2) == 0) {
            Buff.affect(defender, Bleeding.class).set( (level-SPECIAL_LEVEL + 1) * damage / 5 + 1 );
        }

        super.proc(attacker,defender,damage);
    }

    @Override
    public String desc() {
        if (level>=SPECIAL_LEVEL) {
            return "It's that blood on your hands? Looks like your claws are sharp enough to tear skin.";
        }
        return "It's your claws. Slightly more accurate because part of your arms. " +
                "It's almost as good as a typical dagger..\n" +
                "You hear that upgrading your claws may cause bleeding.";
    }
}
