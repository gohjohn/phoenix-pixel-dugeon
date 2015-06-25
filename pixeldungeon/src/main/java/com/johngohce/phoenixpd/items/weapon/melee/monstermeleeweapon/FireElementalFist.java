package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Burning;
import com.johngohce.phoenixpd.effects.particles.FlameParticle;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/21/15.
 */
public class FireElementalFist extends MonsterMeleeWeapon {
    //Future.. AOE Fire Blast
    {
        name = "Fire Ball";
        image = ItemSpriteSheet.FIRE_ELE_FIST;
        isPermanentlyEquipped = true;
    }

    public FireElementalFist() {
        super( 3, 1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        // Standard burning enchantment
        // lvl 0 - 33%
        // lvl 1 - 50%
        // lvl 2 - 60%
        int level = Math.max( 0, this.level );

        if (Random.Int( level + 3 ) >= 2) {
            if (Random.Int( 2 ) == 0) {
                Buff.affect( defender, Burning.class ).reignite( defender );
            }
            defender.damage( Random.Int( 1, level + 2 ), this );
            defender.sprite.emitter().burst( FlameParticle.FACTORY, level + 1 );
        }
        super.proc(attacker,defender,damage);
    }

    @Override
    public String desc() {
        return "Your touch sets everything ablaze.\n" +
                "The chance for ignition increases with level.";
    }
}