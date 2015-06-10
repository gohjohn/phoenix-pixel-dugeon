package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.SpiderImmunity;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class SpiderSkin extends HeroMonsterArmor{
    // TODO Poops web after successful poison attack and level>special_level.(Requires Spider Weapon to proceed)

    public final int SPECIAL_LEVEL = 3;
    {
        name = "Spider Skin";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public SpiderSkin() {
        super(1);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new SpiderImmunity());
        return newBuffs;
    }


    @Override
    public String desc() {
        if(level >= SPECIAL_LEVEL){
            return "I am immune to poison and webs and more importantly... " +
                    "I can poop webs after a successful poison(Not done yet. Need to work on weapon). Ain't that cool? " +
                    "No? NO?!? Erm... ok :(";
        }

        return "I am immune to poison and webs. " +
                "Just being in this dungeon makes my skin tingle with the potential for greater powers...";

    }
}
