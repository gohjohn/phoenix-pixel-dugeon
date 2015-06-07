package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.MovementHaste;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/7/15.
 */
public class CrabShell extends HeroMonsterArmor{
    {
        name = "Crab Shell";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public CrabShell() {
        super(1);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new MovementHaste(level));
        return newBuffs;
    }


    @Override
    public String desc() {
        return
                "You'd think that crabs would have tougher shells than the rest... NOPE. " +
                        "Seems like your species sacrificed armor for speed.";
    }
}
