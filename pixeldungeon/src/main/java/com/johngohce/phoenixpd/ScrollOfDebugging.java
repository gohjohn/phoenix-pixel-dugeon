package com.johngohce.phoenixpd;

import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.food.Food;
import com.johngohce.phoenixpd.items.potions.PotionOfHealing;
import com.johngohce.phoenixpd.items.potions.PotionOfMight;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfMagicMapping;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfUpgrade;
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
            new ScrollOfUpgrade().setKnown();
            new PotionOfHealing().setKnown();
            new ScrollOfMagicMapping().setKnown();
            new PotionOfMight().setKnown();

            for (int i = 0; i < 10; i++) {
                new Food().collect();
                new ScrollOfUpgrade().collect();
                new PotionOfMight().collect();
                new PotionOfHealing().collect();
                new ScrollOfMagicMapping().collect();
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
        return "This item is given to testers only.\n" +
                "Upon use, you get items to make gameplay easier.\n" +
                "It has unlimited use.";
    }
}
