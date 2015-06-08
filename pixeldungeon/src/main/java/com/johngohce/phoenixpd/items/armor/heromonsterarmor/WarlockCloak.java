package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ElementalResistance;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class WarlockCloak extends HeroMonsterArmor {
    {
        name = "Warlock Cloak";
        image = ItemSpriteSheet.ARMOR;
    }

    public WarlockCloak() {
        super(1);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new ElementalResistance(level));
        return newBuffs;
    }

    @Override
    public int price(){
        return 10 * level;
    }


    @Override
    public String desc() {

        return
                "Focused on demonology and necromancy, these warlocks have long forgotten the art of defensive magic. " +
                "This cloak is equivalent to elemental resistant cloth armor. You may remove it if you wish.";
    }
}