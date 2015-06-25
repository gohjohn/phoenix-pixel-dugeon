package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.EnrageAbleBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/20/15.
 */
public class BruteClaws extends MonsterMeleeWeapon {
    //High DPS low defence melee class.

    //Future: auto equip if there is no item in the weapon slot.
    //Future: Getting hit increases your rage. Hitting enemies controls it. When in rage deal more damage.


    {
        name = "Brute Claws";
        image = ItemSpriteSheet.BRUTE_FIST;
        isPermanentlyEquipped = true;
    }

    public BruteClaws() {
        super( 4, 1f, 0.8f );
    }


    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new EnrageAbleBuff(level));
        return newBuffs;
    }


    @Override
    public String desc() {
        return "Salvage in nature, you don't use weapons. You claws are usually enough to kill anything.\n" +
                "When in rage, you deal increased damage. Upgrades increase the damage dealt.";
    }
}
