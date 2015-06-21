package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Levitation;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.FireImmunity;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/8/15.
 */
public class FireElementalBody extends HeroMonsterArmor{
    public static final String AC_FLY	    = "FLY";
    public static final String AC_LAND		= "LAND";

    {
        name = "Fire Elemental Body";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
    }

    public FireElementalBody() {
        super(4);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new FireImmunity());
        return newBuffs;
    }
    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions(hero);
        Levitation levitation = hero.buff(Levitation.class);
        if(levitation==null){
            actions.add (AC_FLY);
        }else{
            actions.add (AC_LAND);
        }
        defaultAction = special();
        return actions;
    }

    @Override
    public Item upgrade(){
        Item item = super.upgrade();
        defaultAction = special();
        return item;
    }

    private String special(){
        Levitation levitation = Dungeon.hero.buff(Levitation.class);
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
                Buff.affect(hero, Levitation.class, (level + 1) * 5);
                GLog.i("MUHAHAHAHAHA!");
                hero.spend(1f);
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
        if(level < 3){
            return "TOO SOON. YOU HAVE AWAKEN ME TOO SOON. (Immune to fire. Flight distance can be increased by upgrades.) ";
        }else if(level % 2 == 0){
            return "THIS WORLD AND ALL THAT YOU HOLD DEAR EXIST ONLY TO BURN!";
        }else{
            return "YOU AND YOUR KIND THOUGHT THAT THE FIRES OF DOOM HAD BEEN QUENCHED... " +
                    "BUT I WILL SHOW YOU THE TRUTH!";
        }

    }
}