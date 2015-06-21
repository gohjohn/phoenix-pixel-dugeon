package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.MovementHaste;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.ReducedHunger;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.RegenerationBuff;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class RockSkin extends HeroMonsterArmor {
    public final int SPECIAL_LEVEL = 7;
    {
        name = "Rock Skin";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public RockSkin() {
        super(5);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        if(level<SPECIAL_LEVEL){
            newBuffs.add(new MovementHaste(level-SPECIAL_LEVEL));
        }
        newBuffs.add(new ReducedHunger(3));
        newBuffs.add(new RegenerationBuff(level/2+1));
        return newBuffs;
    }

    @Override
    public String desc() {

        return
                "You are extremely slow. Moving slower than anything in this dungeon. " +
                        "Fortunately, your armor is thicker than most. " +
                        "You also get less hungry and heal faster. " +
                        "Upgrading this will increase your speed.";
    }
}