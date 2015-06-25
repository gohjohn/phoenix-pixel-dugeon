package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.SpiderImmunity;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class SpiderSkin extends HeroMonsterArmor{

    public static final int SPECIAL_LEVEL = 3;
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
            return "You are immune to poison and webs, but more importantly... " +
                    "You lay webs after a successful poison. Ain't that cool?\n";
        }

        return "You are immune to poison and webs. \n" +
                "Your skin tingles with the potential for greater powers...";

    }
}
