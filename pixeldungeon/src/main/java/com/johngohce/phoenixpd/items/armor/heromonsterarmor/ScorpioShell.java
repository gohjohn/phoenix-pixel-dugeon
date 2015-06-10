package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.TrapMasterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/9/15.
 */
public class ScorpioShell extends HeroMonsterArmor {
    {
        name = "Scorpio Shell";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public ScorpioShell() {
        super(3);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new TrapMasterBuff(level));
        return newBuffs;
    }

    @Override
    public String desc() {

        return
                "Looks can be deceiving, your shell isn't as hard as scale armor. " +
                        "But your legs react extremely quickly, " +
                        "giving you a chance to disarm traps when you step on them.";
    }
}