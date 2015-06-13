package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import android.util.Log;

import com.johngohce.noosa.audio.Sample;
import com.johngohce.phoenixpd.Assets;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.buffs.Blindness;
import com.johngohce.phoenixpd.actors.buffs.Invisibility;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.effects.Speck;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.armor.Armor;
import com.johngohce.phoenixpd.items.armor.glyphs.Stench;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfRemoveCurse;
import com.johngohce.phoenixpd.items.weapon.Weapon;
import com.johngohce.phoenixpd.items.weapon.enchantments.Poison;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.windows.WndBag;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/13/15.
 */
public abstract class SpecificEnchantmentScroll extends Item {

    protected static final String TXT_GLOWS	= "your %s glows in the dark";
    private static final String TXT_BLINDED	= "You can't read a scroll while blinded";
    public static final String AC_READ	= "READ";
    public static final float TIME_TO_READ	= 1f;
    protected String inventoryTitle = "Select an enchantable item";

    protected WndBag.Mode mode = WndBag.Mode.ENCHANTABLE;
    protected Weapon.Enchantment enchantment = new Poison();
    protected Armor.Glyph glyph = new Stench();
    protected String enchantmentText = "venom";
    protected String glyphText = "stench";

    {
        stackable = true;
        defaultAction = AC_READ;
        image = ItemSpriteSheet.SCROLL_SOWILO;
    }

    protected void onItemSelected( Item item ) {

        ScrollOfRemoveCurse.uncurse(Dungeon.hero, item);

        if (item instanceof Weapon) {

            ((Weapon)item).enchant(enchantment);

        } else {

            ((Armor)item).inscribe(glyph);

        }

        item.fix();

        curUser.sprite.emitter().start( Speck.factory(Speck.LIGHT), 0.1f, 5 );
        GLog.w( TXT_GLOWS, item.name() );
    }

    protected static WndBag.Listener itemSelector = new WndBag.Listener() {
        @Override
        public void onSelect( Item item ) {
            if (item != null) {
                ((SpecificEnchantmentScroll)curItem).onItemSelected( item );
                curUser.spendAndNext( TIME_TO_READ );

                Sample.INSTANCE.play( Assets.SND_READ );
                Invisibility.dispel();
            } else {
                curItem.collect( curUser.belongings.backpack );
            }
        }
    };

    @Override
    public void execute( Hero hero, String action ) {
        if (action.equals( AC_READ )) {

            if (hero.buff( Blindness.class ) != null) {
                GLog.w(TXT_BLINDED);
            } else {
                curUser = hero;
                curItem = detach( hero.belongings.backpack );
                doRead();
            }

        } else {

            super.execute( hero, action );

        }
    }

    protected void doRead() {
        GameScene.selectItem(itemSelector, mode, inventoryTitle);
    }

    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_READ );
        return actions;
    }

    @Override
    public String info() {
        return desc();
    }

    @Override
    public String desc() {
        switch (mode){
            case ARMOR:
                return "This scroll is able to imbue an armor " +
                        "with a "+glyphText+" enchantment, granting it a special power.";
            case WEAPON:
                return "This scroll is able to imbue a weapon " +
                        "with a "+enchantmentText+" enchantment, granting it a special power.";
            case ENCHANTABLE:
                return "This scroll is able to imbue a weapon/armor " +
                        "with a "+enchantmentText+"/"+glyphText+" enchantment, granting it a special power.";
            default:
                Log.d("SpecificEnchantmentScroll","Desc Error: "+mode);
                return
                        "This scroll is able to imbue a weapon or an armor " +
                                "with an enchantment, granting it a special power.";
        }
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public int price() {
        return 15 * quantity;
    }
}
