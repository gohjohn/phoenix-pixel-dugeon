package com.johngohce.phoenixpd.actors.hero;

import com.johngohce.phoenixpd.actors.mobs.Bat;
import com.johngohce.phoenixpd.actors.mobs.Brute;
import com.johngohce.phoenixpd.actors.mobs.Crab;
import com.johngohce.phoenixpd.actors.mobs.Elemental;
import com.johngohce.phoenixpd.actors.mobs.Eye;
import com.johngohce.phoenixpd.actors.mobs.Gnoll;
import com.johngohce.phoenixpd.actors.mobs.Golem;
import com.johngohce.phoenixpd.actors.mobs.Mob;
import com.johngohce.phoenixpd.actors.mobs.Monk;
import com.johngohce.phoenixpd.actors.mobs.Rat;
import com.johngohce.phoenixpd.actors.mobs.Scorpio;
import com.johngohce.phoenixpd.actors.mobs.Shaman;
import com.johngohce.phoenixpd.actors.mobs.Skeleton;
import com.johngohce.phoenixpd.actors.mobs.Spinner;
import com.johngohce.phoenixpd.actors.mobs.Succubus;
import com.johngohce.phoenixpd.actors.mobs.Swarm;
import com.johngohce.phoenixpd.actors.mobs.Thief;
import com.johngohce.phoenixpd.actors.mobs.Warlock;
import com.johngohce.phoenixpd.sprites.BatSprite;
import com.johngohce.phoenixpd.sprites.BruteSprite;
import com.johngohce.phoenixpd.sprites.CrabSprite;
import com.johngohce.phoenixpd.sprites.ElementalSprite;
import com.johngohce.phoenixpd.sprites.EyeSprite;
import com.johngohce.phoenixpd.sprites.GnollSprite;
import com.johngohce.phoenixpd.sprites.GolemSprite;
import com.johngohce.phoenixpd.sprites.MobSprite;
import com.johngohce.phoenixpd.sprites.MonkSprite;
import com.johngohce.phoenixpd.sprites.RatSprite;
import com.johngohce.phoenixpd.sprites.ScorpioSprite;
import com.johngohce.phoenixpd.sprites.ShamanSprite;
import com.johngohce.phoenixpd.sprites.SkeletonSprite;
import com.johngohce.phoenixpd.sprites.SpinnerSprite;
import com.johngohce.phoenixpd.sprites.SuccubusSprite;
import com.johngohce.phoenixpd.sprites.SwarmSprite;
import com.johngohce.phoenixpd.sprites.ThiefSprite;
import com.johngohce.phoenixpd.sprites.WarlockSprite;
import com.johngohce.utils.Bundle;

/**
 * Created by johngoh on 6/3/15.
 */
public enum HeroMonsterClass {

    NONE( null, null ),

    RAT("marsupial rat", new RatSprite()),
    SCOUT("gnoll scout", new GnollSprite()),
    CRAB("sewer crab", new CrabSprite()),

    SKELETON("skeleton", new SkeletonSprite()),
    THIEF("crazy thief", new ThiefSprite()),
    FLIES("swarm of flies", new SwarmSprite()),
    SHAMAN("gnoll shaman", new ShamanSprite()),

    BAT("vampire bat", new BatSprite()),
    BRUTE("gnoll brute", new BruteSprite()),
    SPINNER("cave spinner", new SpinnerSprite()),

    FIRE_ELEMENTAL("fire elemental", new ElementalSprite()),
    WARLOCK("dwarf warlock", new WarlockSprite()),
    MONK("dwark monk", new MonkSprite()),
    GOLEM("golem", new GolemSprite()),

    SUCCUBUS("succubus", new SuccubusSprite()),
    EVIL_EYE("evil eye", new EyeSprite()),
    SCORPIO("scorpio", new ScorpioSprite());


    private String title;
    private MobSprite image;

    private HeroMonsterClass( String title, MobSprite image) {
        this.title = title; this.image = image;
    }

    public String title() {
        return title;
    }

    public MobSprite image() { return image; }

    private static final String MONSTER_CLASS	= "monsterClass";

    public void storeInBundle( Bundle bundle ) {
        bundle.put( MONSTER_CLASS, toString() );
    }

    public static HeroMonsterClass restoreInBundle( Bundle bundle ) {
        String value = bundle.getString( MONSTER_CLASS );
        try {
            return valueOf( value );
        } catch (Exception e) {
            return NONE;
        }
    }

    public static HeroMonsterClass getMonsterClass(Object obj){
        if (obj instanceof Mob){
            if(obj instanceof Rat) return RAT;
            if(obj instanceof Gnoll) return SCOUT;
            if(obj instanceof Crab) return CRAB;

            if(obj instanceof Skeleton) return SKELETON;
            if(obj instanceof Thief) return THIEF;
            if(obj instanceof Shaman) return SHAMAN;
            if(obj instanceof Swarm) return FLIES;

            if(obj instanceof Bat) return BAT;
            if(obj instanceof Brute) return BRUTE;
            if(obj instanceof Spinner) return SPINNER;

            if(obj instanceof Elemental) return FIRE_ELEMENTAL;
            if(obj instanceof Warlock) return WARLOCK;
            if(obj instanceof Golem) return GOLEM;
            if(obj instanceof Monk) return MONK;

            if(obj instanceof Eye) return EVIL_EYE;
            if(obj instanceof Succubus) return SUCCUBUS;
            if(obj instanceof Scorpio) return SCORPIO;
        }
        return null;
    }
}
