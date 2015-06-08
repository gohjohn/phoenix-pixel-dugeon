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

import com.johngohce.input.Touchscreen.Touch;
import com.johngohce.noosa.BitmapTextMultiline;
import com.johngohce.noosa.Game;
import com.johngohce.noosa.TouchArea;
import com.johngohce.phoenixpd.Chrome;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.scenes.PixelScene;
import com.johngohce.phoenixpd.ui.Window;
import com.johngohce.utils.SparseArray;

public class WndStory extends Window {

	private static final int WIDTH = 120;
	private static final int MARGIN = 6;
	
	private static final float bgR	= 0.77f;
	private static final float bgG	= 0.73f;
	private static final float bgB	= 0.62f;
	
	public static final int ID_SEWERS		= 0;
	public static final int ID_PRISON		= 1;
	public static final int ID_CAVES		= 2;
	public static final int ID_METROPOLIS	= 3;
	public static final int ID_HALLS		= 4;
	
	private static final SparseArray<String> CHAPTERS = new SparseArray<String>();
	
	static {
		CHAPTERS.put( ID_SEWERS,
        "This place looks familiar yet so distant.. The world feels so.. so.. different.. " +
		"The walls. They tower above me like never before. They feel different from the ones in the real world. " +
		"The real world? When is the last time I've seen the sun? It all seems like a distant dream. " +
		"My head hurts. Ah, one thing I remember, the sensation of death... My head. My head hurts." );
		
		CHAPTERS.put( ID_PRISON,
        "Who am I? Why am I here? What year is this? How did I come here? Why does my head hurt so much when I try to remember? " +
        "The world is so familiar, I've been down these halls, oh so many times. But they twist differently every single time.. " +
        "My foes seem the same, but they look different in height somehow... This miasma is corrupting my mind and senses. " +
        "Telling me that maybe.. just maybe.. it is okay to die..." );
		
		CHAPTERS.put( ID_CAVES, 
		"Ah I remember. I had kids. They hugged me before I left. Oh but that seems like a dream... So so far away. " +
        "Why did I leave? Why did I come here? They say loneliness crushes the heart, corrupting both mind and soul. " +
        "But I believe my heart was corrupted from the start.. Pushing me deeper and deeper into this God forsaken place. " +
        "Never one time have I gone back up to safety. It's always down down down. My heart telling me to never give up." );
		
		CHAPTERS.put( ID_METROPOLIS, 
		"What a strange rat. Just before I went down from DM-300's room, a rat whispered something to me." +
        "It said it was waiting for me... to remind me of something. Something I once knew..." +
        "That this world is not real. To take a leap of faith, yes, to come back... so I can be a human again. Come back..." );
		
		CHAPTERS.put( ID_HALLS,
		"I remember now. I made it down all the way to the bottom once. I was at the brink of death. " +
        "My weapons and armor were broken and full of holes. I knew I would not make it out alive. " +
        "But the amulet, it spoke. It promised eternal life. It promised power beyond my imagination. " +
        "I wore it and everything went black... " +
        "One thing I know. I must reach it again for my true freedom!" );
	};
	
	private BitmapTextMultiline tf;
	
	private float delay;
	
	public WndStory( String text ) {
		super( 0, 0, Chrome.get( Chrome.Type.SCROLL ) );
		
		tf = PixelScene.createMultiline( text, 7 );
		tf.maxWidth = WIDTH - MARGIN * 2;
		tf.measure();
		tf.ra = bgR;
		tf.ga = bgG;
		tf.ba = bgB;
		tf.rm = -bgR;
		tf.gm = -bgG;
		tf.bm = -bgB;
		tf.x = MARGIN;
		add( tf );
		
		add( new TouchArea( chrome ) {
			@Override
			protected void onClick( Touch touch ) {
				hide();
			}
		} );
		
		resize( (int)(tf.width() + MARGIN * 2), (int)Math.min( tf.height(), 180 ) );
	}
	
	@Override
	public void update() {
		super.update();
		
		if (delay > 0 && (delay -= Game.elapsed) <= 0) {
			shadow.visible = chrome.visible = tf.visible = true;
		}
	}
	
	public static void showChapter( int id ) {
		
		if (Dungeon.chapters.contains( id )) {
			return;
		}
		
		String text = CHAPTERS.get( id );
		if (text != null) {
			WndStory wnd = new WndStory( text );
			if ((wnd.delay = 0.6f) > 0) {
				wnd.shadow.visible = wnd.chrome.visible = wnd.tf.visible = false;
			}
			
			Game.scene().add( wnd );
			
			Dungeon.chapters.add( id );
		}
	}
}
