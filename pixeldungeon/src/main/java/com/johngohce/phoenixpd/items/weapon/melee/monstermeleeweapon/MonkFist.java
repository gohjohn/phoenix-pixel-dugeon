package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.AttackHaste;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/21/15.
 */
public class MonkFist extends MonsterMeleeWeapon {

    {
        name = "Monk Fists";
        image = ItemSpriteSheet.MONK_FIST;
        isPermanentlyEquipped = true;
    }

    public MonkFist() {
        super( 3, 1f, 0.8f );
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new AttackHaste((int)(level*1.5)));
        return newBuffs;
    }

    @Override
    public String desc() {
        return "Passed down from generation to generation, the art of Shaolin kung fu was taught to you by your shifu, " +
                "who claims the skill was from the legendary 'NYC Shaolin School'.\n" +
                "Upgrading your fists makes your attacks lightning quick.";
    }
}
