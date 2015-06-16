package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.MonsterItem;
import com.johngohce.phoenixpd.items.weapon.melee.MeleeWeapon;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/10/15.
 */
public abstract class MonsterMeleeWeapon extends MeleeWeapon implements MonsterItem {

    protected ArrayList<HeroMonsterBuff> buffs;

    public MonsterMeleeWeapon( int tier, float acu, float dly ) {
        super(tier, acu, dly);

        levelKnown = true;
        cursed = false;
        cursedKnown = true;
        unique = true;
        buffs = new ArrayList<>();
    }


    @Override
    public Item upgrade() {
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
