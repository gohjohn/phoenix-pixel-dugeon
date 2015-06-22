package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

/**
 * Created by johngoh on 6/9/15.
 */
public class SuccubusLeather extends HeroMonsterArmor {

    public final int THIEF_SPECIAL_LEVEL = 3;
    public final int TENGU_SPECIAL_LEVEL = 4;
    public final int BLACKSMITH_SPECIAL_LEVEL = 6;
    public final int DWARF_KING_SPECIAL_LEVEL = 8;

    {
        name = "Succubus Leather";
        image = ItemSpriteSheet.ARMOR;
    }

    public SuccubusLeather() {
        super(2);
    }

    public static int shopkeeperSpecialLevel(int tier){
        return tier * 2 + 1; //3,5,7
    }

    @Override
    public int price(){
        return 400 * level;
    }


    @Override
    public String desc() {

        return
                "It's your favorite leather. " +
                        "Upgrading this should increase your sexiness.\n" +
                        "But there isn't anybody in this dungeon you want to seduce... Is there? ;)";
    }
}