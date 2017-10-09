package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Levitation;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.MultiplicityBuff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/7/15.
 */
public class FlySkin extends HeroMonsterArmor implements FlyingArmour{
    public int SPECIAL_LEVEL = 4;
    public static final String AC_FLY	    = "FLY";
    public static final String AC_LAND		= "LAND";

    {
        name = "Fly Skin";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public FlySkin() {
        super(2);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new MultiplicityBuff(level));
        return newBuffs;
    }
    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions(hero);
        if(level >= SPECIAL_LEVEL){
            Levitation levitation = hero.buff(Levitation.class);
            if(levitation==null){
                actions.add (AC_FLY);
            }else{
                actions.add (AC_LAND);
            }
            defaultAction = special();
        }
        return actions;
    }

    @Override
    public Item upgrade(){
        Item item = super.upgrade();
        defaultAction = special();
        return item;
    }

    private String special(){
        if(level < SPECIAL_LEVEL) return null;
        Levitation levitation = null;
        if(Dungeon.hero != null) {
            levitation = Dungeon.hero.buff(Levitation.class);
        }
        if(levitation==null){
            return AC_FLY;
        }else{
            return AC_LAND;
        }
    }

    @Override
    public void execute( Hero hero, String action ) {
        if(action.equals(AC_FLY) || action.equals(AC_LAND)){
            action = special();
        }
        if (action.equals( AC_FLY )) {
            Levitation levitation = hero.buff(Levitation.class);
            if(levitation==null) {
                Buff.affect(hero, Levitation.class, (level + 1 - SPECIAL_LEVEL) * 5);
                GLog.i("WHEEEE");
                hero.spendAndNext(1f);
            }
        } else if (action.equals( AC_LAND )) {
            Levitation levitation = hero.buff(Levitation.class);
            if(levitation!=null){
                levitation.detach();
            }
        } else {
            super.execute( hero, action );
        }
        defaultAction = special();
    }

    @Override
    public String desc() {
        if(level >= SPECIAL_LEVEL){
            return "Small wings don't fly very far. Upgrade more for flying distance! " +
                    "I believe I can flyyyyy!";
        }

        return
                "My wings are too small to fly for now.\n" +
                        "At least it seems that I can make copies of myself when I get hit.\n" +
                        "Your multiplicity chance increases with level.";

    }
}