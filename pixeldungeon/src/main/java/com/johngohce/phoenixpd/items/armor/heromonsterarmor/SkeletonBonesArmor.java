package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ExplosiveThorns;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/7/15.
 */
public class SkeletonBonesArmor extends HeroMonsterArmor{
    {
        name = "Skeleton Bones";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public SkeletonBonesArmor() {
        super(2);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new ExplosiveThorns(level));
        return newBuffs;
    }


    @Override
    public String desc() {
        return
                "I AM ALIVE AGAIN! Oh wait... Does it even make sense to revive as the undead? " +
                        "The necromancer told me that those who hit me get a taste of their own medicine. " +
                        "I wonder what he meant...";
    }
}
