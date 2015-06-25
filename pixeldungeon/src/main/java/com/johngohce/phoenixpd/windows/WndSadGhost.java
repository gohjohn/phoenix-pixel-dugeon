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
package com.johngohce.phoenixpd.windows;

import com.johngohce.noosa.BitmapTextMultiline;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.actors.mobs.npcs.Ghost;
import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.potions.PotionOfMight;
import com.johngohce.phoenixpd.items.quest.DriedRose;
import com.johngohce.phoenixpd.items.scrolls.ScrollOfUpgrade;
import com.johngohce.phoenixpd.scenes.PixelScene;
import com.johngohce.phoenixpd.sprites.ItemSprite;
import com.johngohce.phoenixpd.ui.RedButton;
import com.johngohce.phoenixpd.ui.Window;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.phoenixpd.utils.Utils;

public class WndSadGhost extends Window {
	
	private static final String TXT_ROSE	= 
		"Yes! Yes!!! This is it! Please give it to me! " +
		"And you can take one of these items, maybe they " +
		"will be useful to you in your journey...";
	private static final String TXT_RAT	= 
		"Yes! The ugly creature is slain and I can finally rest... " +
		"Please take one of these items, maybe they " +
		"will be useful to you in your journey...";
	private static final String TXT_WEAPON	= "Ghost's weapon";
	private static final String TXT_ARMOR	= "Ghost's armor";

    private static final String TXT_SCROLL	= "Scroll of Upgrade";
    private static final String TXT_POTION	= "Potion of Might";

    private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 20;
	private static final float GAP		= 2;
	
	public WndSadGhost( final Ghost ghost, final Item item ) {
		
		super();
		
		IconTitle titlebar = new IconTitle();
		titlebar.icon( new ItemSprite( item.image(), null ) );
		titlebar.label( Utils.capitalize( item.name() ) );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );
		
		BitmapTextMultiline message = PixelScene.createMultiline( item instanceof DriedRose ? TXT_ROSE : TXT_RAT, 6 );
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add( message );
        boolean giveScroll = (Dungeon.hero.belongings.weapon != null && Dungeon.hero.belongings.weapon.isPermanentlyEquipped);
        boolean givePotion = (Dungeon.hero.belongings.armor != null && Dungeon.hero.belongings.armor.isPermanentlyEquipped);

		RedButton btn1;
        if(giveScroll) {
            btn1= new RedButton( TXT_SCROLL ) {
                @Override
                protected void onClick() {
                    new ScrollOfUpgrade().setKnown();
                    selectReward( ghost, item, new ScrollOfUpgrade() );
                }
            };
        }else{
            btn1= new RedButton( TXT_WEAPON ) {
                @Override
                protected void onClick() {
                    selectReward( ghost, item, Ghost.Quest.weapon );
                }
            };
        }
		btn1.setRect(0, message.y + message.height() + GAP, WIDTH, BTN_HEIGHT);
		add( btn1 );
        
		RedButton btn2;
        if(givePotion){
            btn2 = new RedButton( TXT_POTION ) {
                @Override
                protected void onClick() {
                    new PotionOfMight().setKnown();
                    selectReward( ghost, item, new PotionOfMight());
                }
            };
        }else{
            btn2 = new RedButton( TXT_ARMOR ) {
                @Override
                protected void onClick() {
                    selectReward( ghost, item, Ghost.Quest.armor );
                }
            };
        }
		btn2.setRect(0, btn1.bottom() + GAP, WIDTH, BTN_HEIGHT);
		add( btn2 );
		
		resize( WIDTH, (int)btn2.bottom() );
	}
	
	private void selectReward( Ghost ghost, Item item, Item reward ) {
		
		hide();
		
		item.detach( Dungeon.hero.belongings.backpack );
		
		if (reward.doPickUp( Dungeon.hero )) {
			GLog.i( Hero.TXT_YOU_NOW_HAVE, reward.name() );
		} else {
			Dungeon.level.drop( reward, ghost.pos ).sprite.drop();
		}
		
		ghost.yell( "Farewell, adventurer!" );
		ghost.die( null );
		
		Ghost.Quest.complete();
	}
}
