package com.johngohce.phoenixpd;

import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.food.Food;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfEnchantment;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfUpgrade;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfAffectionEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfDisablingEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfGrimEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfHealthEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfPoisonEnchantment;
import com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls.ScrollOfUnstableEnchantment;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class ScrollOfDebugging extends Item {

	{
		name = "Scroll of Debugging";
        image = ItemSpriteSheet.SCROLL_SOWILO;
	}
	
	public static final String AC_READ	= "READ";
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_READ );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		if (action.equals( AC_READ )) {
            new ScrollOfEnchantment().collect();
            new ScrollOfAffectionEnchantment().collect();
            new ScrollOfPoisonEnchantment().collect();
            new ScrollOfDisablingEnchantment().collect();
            new ScrollOfGrimEnchantment().collect();
            new ScrollOfHealthEnchantment().collect();
            new ScrollOfUnstableEnchantment().collect();

            new ScrollOfUpgrade().setKnown();
            new ScrollOfEnchantment().setKnown();

            for (int i = 0; i < 10; i++) {
                new Food().collect();
                new ScrollOfUpgrade().collect();
            }
		} else {
		
			super.execute( hero, action );
			
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
    public String desc() {
        return "This item is from debugging purposes only.";
    }
}
