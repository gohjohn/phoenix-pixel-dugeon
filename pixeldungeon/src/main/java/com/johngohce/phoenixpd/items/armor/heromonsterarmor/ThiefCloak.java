package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.Stealth;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class ThiefCloak extends HeroMonsterArmor {
    {
        name = "Thief Cloak";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public ThiefCloak() {
        super(2);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new Stealth(level));
        return newBuffs;
    }

    @Override
    public String desc() {

        return
                "Your thief's guild has magically bound this cloak to you. " +
                        "There is sadly no way to remove it. " +
                        "This cloak has served you well, helping you stay in the shadows.";

    }
}