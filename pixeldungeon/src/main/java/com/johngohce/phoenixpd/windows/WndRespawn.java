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
import com.johngohce.noosa.Game;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.Rankings;
import com.johngohce.phoenixpd.actors.hero.Hero;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;
import com.johngohce.phoenixpd.scenes.InterlevelScene;
import com.johngohce.phoenixpd.scenes.PixelScene;
import com.johngohce.phoenixpd.ui.RedButton;
import com.johngohce.phoenixpd.ui.Window;

public class WndRespawn extends Window {

	private static String TXT_MESSAGE	= "You died, and your phoenix blood cannot resurrect you";
	private static final String TXT_YES		= "Yes, I will fight!";
	private static final String TXT_NO		= "No, I give up";

	private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 20;
	private static final float GAP		= 2;

	public static WndRespawn instance;
	public static HeroMonsterClass monsterClass;
    public static Object causeOfDeath;

	public WndRespawn(final HeroMonsterClass monsterClass, boolean isNewMonster, Object causeOfDeath) {
		
		super();
		
		instance = this;
		WndRespawn.monsterClass = monsterClass;
        WndRespawn.causeOfDeath = causeOfDeath;

        if(isNewMonster){
            TXT_MESSAGE = "You died, but you can resurrect as "+monsterClass.title()+". Do you wish to do so?";
        }else{
            TXT_MESSAGE = "You died, and cannot resurrect as your cause of death. However, you can resurrect as your previous class: "+monsterClass.title()+". Do you wish to do so?";
        }
		
		IconTitle titlebar = new IconTitle();
        titlebar.icon( monsterClass.image() );
		titlebar.label( monsterClass.title() );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );
		
		BitmapTextMultiline message = PixelScene.createMultiline( TXT_MESSAGE, 6 );
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add( message );
		
		RedButton btnYes = new RedButton( TXT_YES ) {
			@Override
			protected void onClick() {
				hide();

                Dungeon.hero.monsterClass = monsterClass;
				InterlevelScene.mode = InterlevelScene.Mode.RESPAWN;
				Game.switchScene( InterlevelScene.class );
			}
		};
		btnYes.setRect( 0, message.y + message.height() + GAP, WIDTH, BTN_HEIGHT );
		add( btnYes );
		
		RedButton btnNo = new RedButton( TXT_NO ) {
			@Override
			protected void onClick() {
				hide();

				Rankings.INSTANCE.submit( false );
				Hero.reallyDie( WndRespawn.causeOfDeath );
			}
		};
		btnNo.setRect( 0, btnYes.bottom() + GAP, WIDTH, BTN_HEIGHT );
		add( btnNo );
		
		resize( WIDTH, (int)btnNo.bottom() );
	}
	
	@Override
	public void destroy() {
		super.destroy();
		instance = null;
	}
	
	@Override
	public void onBackPressed() {
	}
}
