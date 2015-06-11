package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.MultiplicityBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/11/15.
 */
public class CrabClaw extends MonsterMeleeWeapon {

    {
        name = "Crab Claw";
        image = ItemSpriteSheet.WEAPON;
    }

    public CrabClaw() {
        super(1, 1f, 0.9f);
    }


    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new MultiplicityBuff(level));
        return newBuffs;
    }


    @Override
    public String desc() {
        return "Nobody would expect a claw this big to punch this fast. " +
                "The only problem is that the damage isn't as much as other species.";
    }
}
