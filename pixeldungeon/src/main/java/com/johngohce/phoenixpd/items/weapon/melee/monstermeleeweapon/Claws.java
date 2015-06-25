package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Bleeding;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/10/15.
 */
public class Claws extends MonsterMeleeWeapon {
    //TODO Split rat from scout claw
    public final int SPECIAL_LEVEL = 4;

    {
        name = "Claws";
        image = ItemSpriteSheet.RAT_CLAW;
        isPermanentlyEquipped = true;
    }

    public Claws() {
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
            return "You claws look somewhat the same but they feel different.\n" +
                    "They faintly pulse with magical energy, granting your attacks the ability to cause your foes to bleed.\n\n" +
                    "You suspect that this same phenomenon occurs for other equipment as well.";
        }
        return "Your dirt-crusted claws have seen better days. Well worn from scratching signs and doors, they are faded and dull in color\n." +
                "Yet you feel the presence of priceless artifacts deep within the dungeon that can improve your attack.";
    }
}
