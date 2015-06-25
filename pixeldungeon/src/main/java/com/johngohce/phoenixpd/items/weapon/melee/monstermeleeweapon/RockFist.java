package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.AttackHaste;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/21/15.
 */
public class RockFist extends MonsterMeleeWeapon {
    public final int SPECIAL_LEVEL = 7;

    {
        name = "Rock Fists";
        image = ItemSpriteSheet.ROCK_FIST;
        isPermanentlyEquipped = true;
    }

    public RockFist() {
        super( 5, 0.8f, 1.1f );
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        if(level<SPECIAL_LEVEL){
            newBuffs.add(new AttackHaste(level-SPECIAL_LEVEL));
        }
        return newBuffs;
    }

    @Override
    public String desc() {
        return "You have always wondered why the world moved faster than you...\n" +
                "But in the end it doesn't matter because they get squished by you anyway.";
    }
}
