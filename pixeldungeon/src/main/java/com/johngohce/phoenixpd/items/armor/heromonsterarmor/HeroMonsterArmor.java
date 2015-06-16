package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.MonsterItem;
import com.johngohce.phoenixpd.items.armor.Armor;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/6/15.
 */
public abstract class HeroMonsterArmor extends Armor implements MonsterItem {

    protected ArrayList<HeroMonsterBuff> buffs;

    public HeroMonsterArmor(int tier) {
        super( tier );

        levelKnown = true;
        cursed = false;
        cursedKnown = true;
        unique = true;
        buffs = new ArrayList<>();
    }

    @Override
    public Item upgrade(){
        Item item = super.safeUpgrade();

        updateBuffs();
        return item;
    }

    public void updateBuffs(){
        if(buffs == null) buffs = new ArrayList<>();
        if(Dungeon.hero != null) {
            for (HeroMonsterBuff buff : buffs) {
                buff.detach();
            }
            ArrayList<HeroMonsterBuff> newBuffs = buffs = updatedBuffs();
            for (HeroMonsterBuff buff : newBuffs) {
                buff.attachTo(Dungeon.hero);
            }
        }
    }

    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        return new ArrayList<>();
    }

    @Override
    public int typicalSTR() {
        return 10;
    }

    @Override
    public int price() {
        return 0;//Unsellable
    }

    @Override
    public String desc() {
        return "The thing looks awesome!";
    }
}
