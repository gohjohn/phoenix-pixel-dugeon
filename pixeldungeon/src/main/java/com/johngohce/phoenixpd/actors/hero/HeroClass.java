/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.johngohce.phoenixpd.actors.hero;

import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Badges;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.BatSkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.CrabShell;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.ElementalBody;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.EyeLid;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.FlySkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.GnollSkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.MonkRobe;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.RatSkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.RockSkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.ScorpioShell;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SkeletonBonesArmor;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SpiderSkin;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.SuccubusLeather;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.ThiefCloak;
import com.johngohce.phoenixpd.items.armor.heromonsterarmor.WarlockCloak;
import com.johngohce.phoenixpd.items.bags.Keyring;
import com.johngohce.phoenixpd.items.food.Food;
import com.johngohce.phoenixpd.items.food.MysteryMeat;
import com.johngohce.phoenixpd.items.potions.PotionOfHealing;
import com.johngohce.phoenixpd.items.potions.PotionOfLiquidFlame;
import com.johngohce.phoenixpd.items.potions.PotionOfStrength;
import com.johngohce.phoenixpd.items.rings.RingOfShadows;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfIdentify;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfMagicMapping;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfUpgrade;
import com.johngohce.phoenixpd.items.wands.Wand;
import com.johngohce.phoenixpd.items.wands.WandOfBlink;
import com.johngohce.phoenixpd.items.wands.WandOfDisintegration;
import com.johngohce.phoenixpd.items.wands.WandOfMagicMissile;
import com.johngohce.phoenixpd.items.wands.monsterwands.ShamanStaff;
import com.johngohce.phoenixpd.items.weapon.enchantments.Fire;
import com.johngohce.phoenixpd.items.weapon.enchantments.Poison;
import com.johngohce.phoenixpd.items.weapon.melee.Dagger;
import com.johngohce.phoenixpd.items.weapon.melee.Knuckles;
import com.johngohce.phoenixpd.items.weapon.melee.Mace;
import com.johngohce.phoenixpd.items.weapon.melee.ShortSword;
import com.johngohce.phoenixpd.items.weapon.melee.Spear;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.BatFangs;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.BoneFist;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.Claws;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.CrabClaws;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.FlyStinger;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.SpiderFangs;
import com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon.ThiefDagger;
import com.johngohce.phoenixpd.items.weapon.missiles.Boomerang;
import com.johngohce.phoenixpd.items.weapon.missiles.Dart;
import com.johngohce.phoenixpd.ui.QuickSlot;
import com.johngohce.utils.Bundle;

public enum HeroClass {

	WARRIOR( "warrior" ), MAGE( "mage" ), ROGUE( "rogue" ), HUNTRESS( "huntress" );
	
	private String title;
	
	private HeroClass( String title ) {
		this.title = title;
	}
	
	public static final String[] WAR_PERKS = {
		"Warriors start with 11 points of Strength.",
		"Warriors start with a unique short sword. This sword can be later \"reforged\" to upgrade another melee weapon.",
		"Warriors are less proficient with missile weapons.",
		"Any piece of food restores some health when eaten.",
		"Potions of Strength are identified from the beginning.",
	};
	
	public static final String[] MAG_PERKS = {
		"Mages start with a unique Wand of Magic Missile. This wand can be later \"disenchanted\" to upgrade another wand.",
		"Mages recharge their wands faster.",
		"When eaten, any piece of food restores 1 charge for all wands in the inventory.",
		"Mages can use wands as a melee weapon.",
		"Scrolls of Identify are identified from the beginning."
	};
	
	public static final String[] ROG_PERKS = {
		"Rogues start with a Ring of Shadows+1.",
		"Rogues identify a type of a ring on equipping it.",
		"Rogues are proficient with light armor, dodging better while wearing one.",
		"Rogues are proficient in detecting hidden doors and traps.",
		"Rogues can go without food longer.",
		"Scrolls of Magic Mapping are identified from the beginning."
	};
	
	public static final String[] HUN_PERKS = {
		"Huntresses start with 15 points of Health.",
		"Huntresses start with a unique upgradeable boomerang.",
		"Huntresses are proficient with missile weapons and get a damage bonus for excessive strength when using them.",
		"Huntresses gain more health from dewdrops.",
		"Huntresses sense neighbouring monsters even if they are hidden behind obstacles."
	};
	
	public void initHero( Hero hero ) {
		
		hero.heroClass = this;
		
		initCommon( hero );
        initMonsterClass(hero);

        /*
		switch (this) {
		case WARRIOR:
			initWarrior( hero );
			break;

		case MAGE:
			initMage( hero );
			break;

		case ROGUE:
			initRogue( hero );
			break;

		case HUNTRESS:
			initHuntress( hero );
			break;
		}

		if (Badges.isUnlocked( masteryBadge() )) {
			new TomeOfMastery().collect();
		}
		*/
		
		hero.updateAwareness();
        hero.updateAllBuffs();
	}
    private static void initMonsterClass( Hero hero ){
        if(hero.monsterClass==null) hero.monsterClass=HeroMonsterClass.defaultClass();
        switch (hero.monsterClass){
            case RAT: initRat(hero);break;
            case SCOUT: initScout(hero);break;
            case CRAB: initCrab(hero);break;

            case SKELETON: initSkeleton(hero);break;
            case FLIES: initFlies(hero);break;
            case THIEF: initThief(hero);break;
            case SHAMAN: initShaman(hero);break;

            case SPINNER: initSpinner(hero);break;
            case BAT: initBat(hero);break;
            case BRUTE: initBrute(hero);break;

            case FIRE_ELEMENTAL: initFireElemental(hero);break;
            case MONK: initMonk(hero);break;
            case GOLEM: initGolem(hero);break;
            case WARLOCK: initWarlock(hero);break;

            case SUCCUBUS: initSuccubus(hero);break;
            case SCORPIO: initScorpio(hero);break;
            case EVIL_EYE: initEvilEye(hero);break;

            default:
                hero.monsterClass=HeroMonsterClass.defaultClass();
                initMonsterClass(hero);
                break;
        }
    }
	
	private static void initCommon( Hero hero ) {
		new Food().identify().collect();
		new Keyring().collect();

        for (int i = 0; i < 10; i++) {
            new Food().collect();
            new ScrollOfUpgrade().collect();
        }
        new ScrollOfUpgrade().setKnown();
	}
	
	public Badges.Badge masteryBadge() {
		switch (this) {
		case WARRIOR:
			return Badges.Badge.MASTERY_WARRIOR;
		case MAGE:
			return Badges.Badge.MASTERY_MAGE;
		case ROGUE:
			return Badges.Badge.MASTERY_ROGUE;
		case HUNTRESS:
			return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}
	
	private static void initWarrior( Hero hero ) {
		hero.STR = hero.STR + 1;
		
		(hero.belongings.weapon = new ShortSword()).identify();
		new Dart( 8 ).identify().collect();
		
		QuickSlot.primaryValue = Dart.class;
		
		new PotionOfStrength().setKnown();
	}
	
	private static void initMage( Hero hero ) {	
		(hero.belongings.weapon = new Knuckles()).identify();
		
		WandOfMagicMissile wand = new WandOfMagicMissile();
		wand.identify().collect();
		
		QuickSlot.primaryValue = wand;
		
		new ScrollOfIdentify().setKnown();
	}
	
	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();
		(hero.belongings.ring1 = new RingOfShadows()).upgrade().identify();
		new Dart( 8 ).identify().collect();
		
		hero.belongings.ring1.activate( hero );
		
		QuickSlot.primaryValue = Dart.class;
		
		new ScrollOfMagicMapping().setKnown();
	}
	
	private static void initHuntress( Hero hero ) {
		
		hero.HP = (hero.HT -= 5);
		
		(hero.belongings.weapon = new Dagger()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();
		
		QuickSlot.primaryValue = boomerang;
	}

    private static void initRat( Hero hero ) {
        hero.belongings.weapon = new Claws();
        hero.belongings.armor = new RatSkin();
    }
    private static void initScout( Hero hero ) {
        hero.belongings.weapon = new Claws();
        hero.belongings.armor = new GnollSkin();

        Dungeon.gold = 500;
    }
    private static void initCrab( Hero hero ) {
        hero.belongings.weapon = new CrabClaws();
        hero.belongings.armor = new CrabShell();

        new MysteryMeat().collect();
        new MysteryMeat().collect();
        new MysteryMeat().collect();
    }

    private static void initSkeleton( Hero hero ) {
        hero.belongings.weapon = new BoneFist();
        hero.belongings.armor = new SkeletonBonesArmor();
    }
    private static void initThief( Hero hero ) {
        hero.belongings.weapon = new ThiefDagger();
        hero.belongings.armor = new ThiefCloak();

        Dungeon.gold = 42;
    }
    private static void initFlies( Hero hero ) {
        hero.belongings.weapon = new FlyStinger();
        hero.belongings.armor = new FlySkin();

        new PotionOfHealing().setKnown();
    }
    private static void initShaman( Hero hero ) {
        hero.belongings.weapon = new ShamanStaff();
        ((ShamanStaff)hero.belongings.weapon).charge(hero);
        hero.belongings.armor = new GnollSkin();
        
        QuickSlot.primaryValue = hero.belongings.weapon;

        new ScrollOfIdentify().setKnown();
    }

    private static void initBat( Hero hero ) {
        hero.belongings.weapon = new BatFangs();
        hero.belongings.armor = new BatSkin();

        new PotionOfHealing().setKnown();
    }
    private static void initBrute( Hero hero ) {
        (hero.belongings.weapon = new Spear()).upgrade().identify();
        hero.belongings.armor = new GnollSkin();

        new PotionOfStrength().setKnown();
    }
    private static void initSpinner( Hero hero ) {
        hero.belongings.weapon = new SpiderFangs();
        hero.belongings.armor = new SpiderSkin();

        new MysteryMeat().collect();
        new MysteryMeat().collect();
        new MysteryMeat().collect();
    }

    private static void initFireElemental( Hero hero ) {
        (hero.belongings.weapon = new Dagger().enchant(new Fire())).upgrade().identify();
        hero.belongings.armor = new ElementalBody();

        new PotionOfLiquidFlame().setKnown();
    }
    private static void initWarlock( Hero hero ) {
        (hero.belongings.weapon = new Dagger()).identify();
        hero.belongings.armor = new WarlockCloak();

        Wand wand = new WandOfMagicMissile();
        wand.upgrade(5).identify().collect();
        QuickSlot.primaryValue = wand;

        new ScrollOfIdentify().setKnown();
    }
    private static void initMonk( Hero hero ) {
        (hero.belongings.weapon = new Knuckles()).identify().upgrade(3);
        hero.belongings.armor = new MonkRobe();

        new Food().collect();
    }
    private static void initGolem( Hero hero ) {
        (hero.belongings.weapon = new Mace()).identify().upgrade(2);
        hero.belongings.armor = new RockSkin();

        new PotionOfStrength().setKnown();
    }

    private static void initSuccubus( Hero hero ) {
        (hero.belongings.weapon = new Dagger()).identify();
        (hero.belongings.armor = new SuccubusLeather()).upgrade(15);

        Wand wand = new WandOfBlink();
        wand.upgrade().identify().collect();
        QuickSlot.primaryValue = wand;
    }
    private static void initEvilEye( Hero hero ) {
        (hero.belongings.weapon = new Dagger()).identify();
        hero.belongings.armor = new EyeLid();

        Wand wand = new WandOfDisintegration();
        wand.upgrade(2).identify().collect();
        QuickSlot.primaryValue = wand;

    }
    private static void initScorpio( Hero hero ) {
        (hero.belongings.weapon = new Dagger()).identify();
        hero.belongings.armor = new ScorpioShell();

        Boomerang boomerang = new Boomerang();
        boomerang.enchant(new Poison()).upgrade().identify().collect();
        QuickSlot.primaryValue = boomerang;

        new MysteryMeat().collect();
        new MysteryMeat().collect();
        new MysteryMeat().collect();
        new PotionOfHealing().setKnown();
    }

	
	public String title() {
		return title;
	}
	
	public String spritesheet() {
		
		switch (this) {
		case WARRIOR:
			return Assets.WARRIOR;
		case MAGE:
			return Assets.MAGE;
		case ROGUE:
			return Assets.ROGUE;
		case HUNTRESS:
			return Assets.HUNTRESS;
		}
		
		return null;
	}
	
	public String[] perks() {
		
		switch (this) {
		case WARRIOR:
			return WAR_PERKS;
		case MAGE:
			return MAG_PERKS;
		case ROGUE:
			return ROG_PERKS;
		case HUNTRESS:
			return HUN_PERKS;
		}
		
		return null;
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
