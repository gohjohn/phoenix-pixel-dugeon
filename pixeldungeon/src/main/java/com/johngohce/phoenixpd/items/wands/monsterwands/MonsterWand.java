package com.johngohce.phoenixpd.items.wands.monsterwands;

import android.util.Log;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.MonsterItem;
import com.johngohce.phoenixpd.items.wands.Wand;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/16/15.
 */
public abstract class MonsterWand extends Wand implements MonsterItem {

    protected ArrayList<HeroMonsterBuff> buffs;

    public MonsterWand() {
        calculateDamage();

        image = ItemSpriteSheet.WAND_EBONY;
        wood = "ebony";

        levelKnown = true;
        cursed = false;
        cursedKnown = true;
        unique = true;
        buffs = new ArrayList<>();
    }
    private void calculateDamage() {
        int tier = 1 + level / 3;
        MIN = tier;
        MAX = (tier * tier - tier + 10) / 2 + level;
    }
    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
//		if (hero.heroClass != HeroClass.MAGE) {
//			actions.remove( AC_EQUIP );
//			actions.remove( AC_UNEQUIP );
//		}
        return actions;
    }

    @Override
    public int price() {
        return 0;//Unsellable
    }

    @Override
    public boolean isIdentified(){
        return true;
    }

    @Override
    protected boolean isKnown() {
        return true;
    }

    @Override
    public void setKnown(){}

    @Override
    public Item identify() {
        levelKnown = true;
        cursedKnown = true;
        return this;
    }
    @Override
    public Item upgrade() {
        Item item = super.upgrade();

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
    public void charge( Char owner ) {
        if (charger == null) {
            (charger = new MonsterCharger()).attachTo( owner );
        }
    }

    @Override
    public String desc() {
        return "The thing looks awesome!";
    }

    protected class MonsterCharger extends Charger {

        private static final int INITIAL_CHARGE_TIME = 40;

        @Override
        protected void delay() {
            int time2charge = INITIAL_CHARGE_TIME;
            if(Dungeon.hero != null && Dungeon.hero.belongings != null && Dungeon.hero.belongings.weapon instanceof MonsterWand){
                time2charge -= ((MonsterWand)Dungeon.hero.belongings.weapon).level * 5;
                time2charge = Math.max(time2charge,5);
            }
            Log.i("MonsterCharger","time2charge: " + time2charge);
            spend(time2charge);
        }
    }
}
