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
package com.johngohce.phoenixpd.sprites;

import com.johngohce.noosa.TextureFilm;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;

public class MirrorSprite extends MobSprite {

    protected static final int RUN_FRAMERATE= 20;
    private TextureFilm tiers;
    MobSprite mobSprite;
	public MirrorSprite() {
		super();

        mobSprite = HeroMonsterClass.getSprite(Dungeon.hero.monsterClass);
        texture(mobSprite.texture);


        TextureFilm film = frames();

        if(mobSprite.idle!=null){
            idle = mobSprite.idle.clone();
        }else{
            idle = new Animation( 1, true );
            idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );
        }

        if(mobSprite.run!= null){
            run = new Animation( RUN_FRAMERATE, true );
            run.frames = mobSprite.run.frames.clone();
        }else{
            run = new Animation( RUN_FRAMERATE, true );
            run.frames = idle.frames.clone();
        }

        if(mobSprite.die!= null){
            die = mobSprite.die.clone();
        }else{
            die = idle.clone();
        }

        if(mobSprite.attack!= null){
            attack = mobSprite.attack.clone();
        }else{
            attack = idle.clone();
        }

        if(mobSprite.die!= null){
            die = mobSprite.die.clone();
        }else{
            die = idle.clone();
        }

        if(mobSprite.zap!= null){
            zap = mobSprite.zap.clone();
        }else{
            zap = attack.clone();
        }

        if(mobSprite.operate!= null){
            operate = mobSprite.operate.clone();
        }else{
            operate = attack.clone();
        }

		idle();
	}
    TextureFilm frames() {
        if (tiers == null) {
            tiers = mobSprite.frames;
        }

        return tiers;
    }
}
