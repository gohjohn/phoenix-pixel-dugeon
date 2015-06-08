package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

/**
 * Created by johngoh on 6/9/15.
 */
public class SuccubusLeather extends HeroMonsterArmor {

    public final int THIEF_SPECIAL_LEVEL = 2;
    public final int TENGU_SPECIAL_LEVEL = 3;
    public final int SHOPKEEPER_SPECIAL_LEVEL = 5;
    public final int BLACKSMITH_SPECIAL_LEVEL = 6;
    public final int DWARF_KING_SPECIAL_LEVEL = 8;

    {
        name = "Sexy Leather";
        image = ItemSpriteSheet.ARMOR;
    }

    public SuccubusLeather() {
        super(2);
    }

    @Override
    public int price(){
        return 400 * level;
    }


    @Override
    public String desc() {

        return
                "It's your favorite leather.. " +
                        "Upgrading this should increase your sexiness. " +
                        "But there isn't anybody in this dungeon you want to seduce... Is there? ;)";
    }
}