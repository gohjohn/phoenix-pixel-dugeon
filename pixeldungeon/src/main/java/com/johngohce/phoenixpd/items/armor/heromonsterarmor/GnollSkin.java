package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.SkinResistance;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/7/15.
 */
public class GnollSkin extends HeroMonsterArmor{
    public final int SPECIAL_LEVEL = 3;
    {
        name = "Gnoll Skin";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public GnollSkin() {
        super(1);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        if(level>=SPECIAL_LEVEL){
            ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
            newBuffs.add(new SkinResistance(level + 1 - SPECIAL_LEVEL));
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
                "Gnolls have delicate skin. " +
                        "At least it is slightly more resistant than rat skins. " +
                        "You heard that upgrading it might provide some extra benefits.";

    }
}
