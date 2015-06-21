package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.WebLaying;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SpiderSkin;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

/**
 * Created by johngoh on 6/17/15.
 */
public class SpiderFangs extends MonsterMeleeWeapon {
    //Future special ability... Spawn multiple tiny spiders.
    //On attack, poison
    //On attack, poop web
    //If you kill an enemy, spawn a friendly spider.
    {
        name = "Spider Fangs";
        image = ItemSpriteSheet.WEAPON;
        isPermanentlyEquipped = true;
    }

    public SpiderFangs() {
        super( 2, 1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        //Standard poison
        int level = Math.max( 0, this.level );
        if (Random.Int( level + 3 ) >= 2) {
            Buff.affect(defender, com.johngohce.phoenixpd.actors.buffs.Poison.class).
                    set(com.johngohce.phoenixpd.actors.buffs.Poison.duration(defender) * (level + 1));

//            Buff.prolong(attacker, WebLaying.class, Random.Float( 1.0f, 1.5f ) );
            if(Dungeon.hero.belongings.armor instanceof SpiderSkin && ((SpiderSkin) Dungeon.hero.belongings.armor).level >= SpiderSkin.SPECIAL_LEVEL)
            Buff.affect(attacker, WebLaying.class, Random.Int((int)(WebLaying.DURATION * level)) + 1);

        }

        super.proc(attacker,defender,damage);
    }

    @Override
    public String desc() {
        return "Your fangs drip with venom, giving you the ability to poison your foes.\n" +
                "The chance for poison and poison damage increases with level.";
    }
}