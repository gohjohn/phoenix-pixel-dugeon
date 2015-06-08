package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ElementalResistance;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/6/15.
 */
public class RatSkin extends HeroMonsterArmor {
    public final int SPECIAL_LEVEL = 4;
    {
        name = "Rat Skin";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public RatSkin() {
        super(1);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        if(level>=SPECIAL_LEVEL){
            ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
            newBuffs.add(new ElementalResistance(level + 1 - SPECIAL_LEVEL));
            return newBuffs;
        }
        return super.updatedBuffs();
    }


    @Override
    public String desc() {
        if(level >= SPECIAL_LEVEL){
            return "Woah, something feels different, it feels warmer and more resistant to the elements. "+
                    "Further upgrades should improve your resistance.";
        }

        return
                "What? You don't recognise your own skin? " +
                        "It's nothing special, but you have it since birth. " +
                        "You heard that upgrading it might provide some extra benefits.";

    }
}
