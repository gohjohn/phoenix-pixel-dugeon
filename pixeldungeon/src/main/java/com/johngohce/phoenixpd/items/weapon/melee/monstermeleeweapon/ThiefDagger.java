package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.mobs.Bat;
import com.johngohce.phoenixpd.actors.mobs.Brute;
import com.johngohce.phoenixpd.actors.mobs.Crab;
import com.johngohce.phoenixpd.actors.mobs.DM300;
import com.johngohce.phoenixpd.actors.mobs.Elemental;
import com.johngohce.phoenixpd.actors.mobs.Eye;
import com.johngohce.phoenixpd.actors.mobs.Gnoll;
import com.johngohce.phoenixpd.actors.mobs.Golem;
import com.johngohce.phoenixpd.actors.mobs.Goo;
import com.johngohce.phoenixpd.actors.mobs.King;
import com.johngohce.phoenixpd.actors.mobs.Mimic;
import com.johngohce.phoenixpd.actors.mobs.Monk;
import com.johngohce.phoenixpd.actors.mobs.Piranha;
import com.johngohce.phoenixpd.actors.mobs.Rat;
import com.johngohce.phoenixpd.actors.mobs.Scorpio;
import com.johngohce.phoenixpd.actors.mobs.Shaman;
import com.johngohce.phoenixpd.actors.mobs.Skeleton;
import com.johngohce.phoenixpd.actors.mobs.Spinner;
import com.johngohce.phoenixpd.actors.mobs.Statue;
import com.johngohce.phoenixpd.actors.mobs.Succubus;
import com.johngohce.phoenixpd.actors.mobs.Swarm;
import com.johngohce.phoenixpd.actors.mobs.Tengu;
import com.johngohce.phoenixpd.actors.mobs.Thief;
import com.johngohce.phoenixpd.actors.mobs.Warlock;
import com.johngohce.phoenixpd.actors.mobs.Wraith;
import com.johngohce.phoenixpd.actors.mobs.npcs.Ghost;
import com.johngohce.phoenixpd.items.Gold;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.LloydsBeacon;
import com.johngohce.phoenixpd.items.armor.Armor;
import com.johngohce.phoenixpd.items.food.Food;
import com.johngohce.phoenixpd.items.food.MysteryMeat;
import com.johngohce.phoenixpd.items.potions.PotionOfHealing;
import com.johngohce.phoenixpd.items.potions.PotionOfLevitation;
import com.johngohce.phoenixpd.items.potions.PotionOfLiquidFlame;
import com.johngohce.phoenixpd.items.potions.PotionOfMight;
import com.johngohce.phoenixpd.items.potions.PotionOfParalyticGas;
import com.johngohce.phoenixpd.items.potions.PotionOfPurity;
import com.johngohce.phoenixpd.items.potions.PotionOfStrength;
import com.johngohce.phoenixpd.items.rings.Ring;
import com.johngohce.phoenixpd.items.rings.RingOfHaggler;
import com.johngohce.phoenixpd.items.rings.RingOfHaste;
import com.johngohce.phoenixpd.items.rings.RingOfShadows;
import com.johngohce.phoenixpd.items.rings.RingOfThorns;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfUpgrade;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfAffectionEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfDisablingEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfGrimEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfHealthEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfPoisonEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfUnstableEnchantment;
import com.johngohce.phoenixpd.items.wands.Wand;
import com.johngohce.phoenixpd.items.wands.WandOfAvalanche;
import com.johngohce.phoenixpd.items.wands.WandOfBlink;
import com.johngohce.phoenixpd.items.wands.WandOfDisintegration;
import com.johngohce.phoenixpd.items.wands.WandOfFirebolt;
import com.johngohce.phoenixpd.items.wands.WandOfLightning;
import com.johngohce.phoenixpd.items.wands.WandOfTelekinesis;
import com.johngohce.phoenixpd.items.wands.WandOfTeleportation;
import com.johngohce.phoenixpd.items.weapon.Weapon;
import com.johngohce.phoenixpd.items.weapon.enchantments.Poison;
import com.johngohce.phoenixpd.items.weapon.missiles.Boomerang;
import com.johngohce.phoenixpd.items.weapon.missiles.Shuriken;
import com.johngohce.phoenixpd.plants.Earthroot;
import com.johngohce.phoenixpd.plants.Sungrass;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;

import java.util.Random;

/**
 * Created by johngoh on 6/13/15.
 */
public class ThiefDagger extends MonsterMeleeWeapon {
    private Random random = new Random();

    public static final String TXT_YOU_STOLE	= "You stole %s";

    {
        name = "Thief Dagger";
        image = ItemSpriteSheet.DAGGER;
        isPermanentlyEquipped = true;
    }

    public ThiefDagger() {
        super( 1, 1.1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        super.proc(attacker, defender, damage);
        if(damage>0) {
            if (defender instanceof Rat) {
                obtainItem(new Gold(5), 0.5f, 1, 0, 0);
            } else if (defender instanceof Gnoll) {
                obtainItem(new Gold(10), 0.5f, 1, 0, 0);
            } else if (defender instanceof Crab) {
                obtainItem(new RingOfHaste(), 0.05f, 2, 1, 0.35f);
                obtainItem(new MysteryMeat(), 0.3f, 1, 0, 0);
            } else if (defender instanceof Goo) {
                obtainItem(new LloydsBeacon(), 0.2f, 2, 0, 0);
            } else if (defender instanceof Ghost.FetidRat) {
                obtainItem(new PotionOfParalyticGas(), 0.3f, 2, 0, 0);
                obtainItem(new PotionOfPurity(), 0.3f, 2, 0, 0);

            } else if (defender instanceof Swarm) {
                obtainItem(new PotionOfHealing(),0.1f, 3, 0, 0);
                obtainItem(new PotionOfLevitation(),0.1f, 2, 0, 0);
            } else if (defender instanceof Skeleton) {
                obtainItem(new ScrollOfUnstableEnchantment(), 0.2f, 2, 0, 0);
                obtainItem(new ScrollOfGrimEnchantment(), 0.05f, 3, 0, 0);
            } else if (defender instanceof Thief) {
                obtainItem(new RingOfHaggler(), 0.1f, 3, 1, 1);
            } else if (defender instanceof Shaman) {
                obtainItem(new WandOfLightning(), 0.1f, 2, 1, 0);
            } else if (defender instanceof Tengu) {
                obtainItem(new Shuriken(2), 0.5f, 3, 0, 0);

            } else if (defender instanceof Bat) {
                obtainItem(new ScrollOfHealthEnchantment(), 0.05f, 4, 0, 0);
            } else if (defender instanceof Spinner) {
                obtainItem(new ScrollOfPoisonEnchantment(), 0.2f, 4, 0, 0);
                obtainItem(new MysteryMeat(), 0.3f, 4, 0, 0);
            } else if (defender instanceof Brute) {
                obtainItem(new PotionOfStrength(), 0.05f, 4, 0, 0);
            } else if (defender instanceof DM300) {
                obtainItem(new RingOfThorns(), 0.1f, 4, 0, 1);
                obtainItem(new WandOfAvalanche(), 0.2f, 4, 3, 0);

            } else if (defender instanceof Monk) {
                obtainItem(new Food(), 0.3f, 5, 0, 1);
                obtainItem(new ScrollOfDisablingEnchantment(), 0.05f, 6, 0, 0);
            } else if (defender instanceof Golem) {
                obtainItem(new Earthroot.Seed(), 0.3f, 5, 0, 0);
                obtainItem(new WandOfAvalanche(), 0.1f, 5, 3, 0);
                obtainItem(new ScrollOfDisablingEnchantment(), 0.05f, 6, 0, 0);
            } else if (defender instanceof Elemental) {
                obtainItem(new WandOfFirebolt(), 0.1f, 6, 3, 0);
                obtainItem(new PotionOfLiquidFlame(), 0.3f, 5, 0, 0);
            } else if (defender instanceof Warlock) {
                obtainItem(new ScrollOfDisablingEnchantment(), 0.1f, 6, 0, 0);
                obtainItem(new ScrollOfGrimEnchantment(), 0.05f, 6, 0, 0);
            } else if (defender instanceof King) {
                obtainItem(new ScrollOfDisablingEnchantment(), 0.02f, 6, 0, 0);
            } else if (defender instanceof King.Undead) {
                obtainItem(new ScrollOfDisablingEnchantment(), 0.02f, 6, 0, 0);
                obtainItem(new ScrollOfUnstableEnchantment(), 0.1f, 5, 0, 0);

            } else if (defender instanceof Succubus) {
                obtainItem(new WandOfBlink(), 0.05f, 7, 3, 0);
                obtainItem(new WandOfTeleportation(), 0.05f, 7, 3, 0);
                obtainItem(new WandOfTelekinesis(), 0.05f, 7, 3, 0);
                obtainItem(new ScrollOfAffectionEnchantment(), 0.1f, 7, 0, 0);
            } else if (defender instanceof Eye) {
                obtainItem(new WandOfDisintegration(), 0.1f, 7, 3, 0);
                obtainItem(new PotionOfHealing(), 0.15f, 7, 0, 0);
            } else if (defender instanceof Scorpio) {
                obtainItem(new ScrollOfPoisonEnchantment(), 0.3f, 7, 0, 0);
                obtainItem(new Boomerang().enchant(new Poison()), 0.1f, 8, 3, 0.5f);

            } else if (defender instanceof Mimic) {
                obtainItem(new PotionOfHealing(), 0.1f, 3, 0, 0);
                obtainItem(new Sungrass.Seed(), 0.15f, 3, 0, 0);
                obtainItem(new Earthroot.Seed(), 0.15f, 3, 0, 0);
                obtainItem(new PotionOfStrength(), 0.05f, 4, 0, 0);
                obtainItem(new ScrollOfUpgrade(), 0.05f, 4, 0, 0);
            } else if (defender instanceof Statue) {
                obtainItem(new ScrollOfGrimEnchantment(), 0.2f, 3, 0, 0);
            } else if (defender instanceof Piranha) {
                obtainItem(new MysteryMeat(), 0.5f, 2, 0, 0);
                obtainItem(new PotionOfMight(), 0.3f, 4, 0, 0);
                obtainItem(new ScrollOfUpgrade(), 0.3f, 4, 0, 0);
            } else if (defender instanceof Wraith) {
                obtainItem(new RingOfShadows(), 0.1f, 4, level/2 + 1, 0.5f);
            } else {
                obtainItem(new Gold(5), 0.5f, 1, 0, 0);
            }
        }
    }

    private void obtainItem(Item item, float dropChance, float requiredLevel, int maxUpgrades, float positiveEquipmentChance){
        float dropChanceModifier = 1.0f + 0.5f * Math.max(level - requiredLevel, 0);
        if(level>=requiredLevel && random.nextFloat() < dropChance * dropChanceModifier){
            if(item instanceof Wand){
                int upgrades = 0;
                if(maxUpgrades>0) upgrades = random.nextInt(maxUpgrades);
                item.upgrade(upgrades);
            }else if(item instanceof Ring){
                int upgrades = 1;
                if(maxUpgrades>1) upgrades = random.nextInt(maxUpgrades-1)+1;//1 to max
                if(random.nextFloat()>positiveEquipmentChance){
                    item.upgrade(upgrades);
                }else{
                    for(int i=0;i<upgrades;i++){
                        item.degrade();
                    }
                    item.cursed = true;
                }
            }else if(item instanceof Armor || item instanceof Weapon){
                int upgrades = 0;
                if(maxUpgrades>0) upgrades = random.nextInt(maxUpgrades);
                if(random.nextFloat()>positiveEquipmentChance){
                    for(int i=0;i<upgrades;i++){
                        if(item instanceof Armor) {
                            ((Armor) item).safeUpgrade();
                        }else{
                            ((Weapon) item).upgrade(((Weapon) item).isEnchanted());
                        }
                    }
                }else{
                    for(int i=0;i<upgrades;i++){
                        item.degrade();
                    }
                    item.cursed = true;
                }
            }
            if(item instanceof Gold){
                Dungeon.gold += item.quantity();
                GLog.i(TXT_YOU_STOLE, item.quantity() + " " + item.name());
            }else if(item.collect(Dungeon.hero.belongings.backpack)) {
                GameScene.pickUp(item);
                GLog.i(TXT_YOU_STOLE, item.name());
            } else {
                Dungeon.level.drop( item, Dungeon.hero.pos ).sprite.drop();
            }
        }
    }

    @Override
    public String desc() {
        return "It looks like a normal dagger. " +
                "But this dagger was specially crafted by your guild to provide you with thieving abilities.\n" +
                "You are able to steal items upon hit. Different mobs will have varying required levels.\n" +
                "This is your most valued possession. You swore to never let go of it. ";
    }
}
