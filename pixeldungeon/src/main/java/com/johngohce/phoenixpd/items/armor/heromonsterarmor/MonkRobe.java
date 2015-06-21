package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.Dodge;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class MonkRobe extends HeroMonsterArmor {
    {
        name = "Monk Robe";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public MonkRobe() {
        super(3);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new Dodge(level/2));
        return newBuffs;
    }

    @Override
    public int price(){
        return 30 * level;
    }


    @Override
    public String desc() {

        return
                "You must be shapeless, formless, like water. When you pour water in a cup, it becomes the cup. " +
                        "When you pour water in a bottle, it becomes the bottle. When you pour water in a teapot, " +
                        "it becomes the teapot. Water can drip and it can crash. Become like water my friend.\n\n" +
                        "Monks don't wear armor, so you can't remove this.";
    }
}
