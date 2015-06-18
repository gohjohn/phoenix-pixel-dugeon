package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/14/15.
 */
public class BoneFist extends MonsterMeleeWeapon {

    {
        name = "Bone Fist";
        image = ItemSpriteSheet.WEAPON;
        isPermanentlyEquipped = true;
    }

    public BoneFist() {
        super( 2, 1.1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        if(level > 0 && Random.Int( 3 + level) > 3) {
            for (int i = 0; i < Level.NEIGHBOURS8.length; i++) {
                Char ch = attacker.findChar(attacker.pos + Level.NEIGHBOURS8[i]);
                if (ch != null && ch.isAlive() && ch != defender) {
                    int dmg = Math.max(1, Random.Int(damage * level / 10));
                    ch.damage(dmg, attacker);
                    if (!ch.isAlive()) {
                        GLog.i(attacker.TXT_DEFEAT, name, ch.name);
                    }
                }
            }
            if(Random.Int(4+level)>level){
                attacker.damage(Random.IntRange(1,3),this);
                if (!attacker.isAlive()) {
                    GLog.i(name+"'s explosive attack killed you");
                }
            }
        }
        super.proc(attacker,defender,damage);
    }

    @Override
    public String desc() {
        return "This fist was in the service of the Crown of the Ivory King. " +
                "Though it occasionally shatters and damages you, the attack damages nearby foes as well.";
    }
}
