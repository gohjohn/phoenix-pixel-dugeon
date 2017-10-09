package com.johngohce.phoenixpd.items.armor.heromonsterarmor;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.Buff;
import com.johngohce.phoenixpd.actors.buffs.Levitation;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.TearBuff;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/9/15.
 */
public class EyeLid extends HeroMonsterArmor{
    public static final String AC_FLY	    = "FLY";
    public static final String AC_LAND		= "LAND";

    {
        name = "Evil EyeLid";
        image = ItemSpriteSheet.ARMOR;
        isPermanentlyEquipped = true;
        defaultAction = AC_FLY;
    }

    public EyeLid() {
        super(2);
    }

    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        newBuffs.add(new TearBuff(level));
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
                Buff.affect(hero, Levitation.class, (level + 1) * 5);
                GLog.i("Whoooosh");
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
        return
                "The eye is delicate, easily susceptible to all sorts of injuries. " +
                        "Without, the eyelid, the eyes dry up. " +
                        "Strangely, upgrading this makes the world wetter.";
//They say beauty is in the eye of the beholder... Look deep into my eye and find your solace!
    }
}