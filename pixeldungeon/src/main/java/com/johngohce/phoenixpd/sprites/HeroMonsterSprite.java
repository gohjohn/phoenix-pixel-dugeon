package com.johngohce.phoenixpd.sprites;

import android.graphics.RectF;

import com.johngohce.noosa.Image;
import com.johngohce.noosa.TextureFilm;
import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.hero.HeroClass;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;

/**
 * Created by johngoh on 6/4/15.
 */
public class HeroMonsterSprite extends HeroSprite {

    private static MobSprite lastMobSprite = null;
    private MobSprite mobSprite;
    public HeroMonsterSprite(MobSprite mobSprite){
        if(mobSprite==null) mobSprite = new RatSprite();
        this.mobSprite = mobSprite;
        lastMobSprite = mobSprite;
        link( Dungeon.hero );
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

        fly = new Animation( 1, true );
        fly.frames( run.frames[(run.frames.length+1)/2] );


        idle();
    }
    @Override
    public void updateArmor() {}

    TextureFilm frames() {
        if (tiers == null) {
            tiers = mobSprite.frames;
        }

        return tiers;
    }

    public static TextureFilm tiers() {
        if (tiers == null) {
            tiers = new RatSprite().frames;
        }

        return tiers;

        /*
        if(lastMobSprite != null) tiers = lastMobSprite.frames;
        else tiers = new RatSprite().frames;

        return tiers;
         */
    }

    public static Image avatar( HeroClass cl, int armorTier ) {

//        RectF patch = tiers().get( armorTier );
        RectF patch = tiers().get( 0 );
        Image avatar = new Image( HeroMonsterClass.defaultClass().image() );

        if(Dungeon.hero.monsterClass!=null) avatar = new Image(Dungeon.hero.monsterClass.image());
        RectF frame = avatar.texture.uvRect( 1, 0, 16, 15 );

        if(lastMobSprite != null){
            frame = avatar.texture.uvRect( 0, 0,
                    (int)(lastMobSprite.idle.frames[0].width()*lastMobSprite.texture.width),
                    (int)(lastMobSprite.idle.frames[0].height()*lastMobSprite.texture.height) );
//            Log.i("Test", "W: "+(int)(lastMobSprite.idle.frames[0].width()*lastMobSprite.texture.width));
//            Log.i("Test", "H: "+(int)(lastMobSprite.idle.frames[0].height()*lastMobSprite.texture.height));
//            Log.i("Test", "width: "+lastMobSprite.idle.frames[0].width());
//            Log.i("Test", "height: "+lastMobSprite.idle.frames[0].height());
//            Log.i("Test", "left: "+lastMobSprite.idle.frames[0].left);
//            Log.i("Test", "right: "+lastMobSprite.idle.frames[0].right);
//            Log.i("Test", "top: "+lastMobSprite.idle.frames[0].top);
//            Log.i("Test", "bottom: "+lastMobSprite.idle.frames[0].bottom);
//            Log.i("Test", "frame: "+lastMobSprite.idle.frames[0]);
//            Log.i("Test", "patchleft: "+patch.left);
//            Log.i("Test", "patchtop: "+patch.top);
//            Log.i("Test", "patch: "+patch);


        }


        frame.offset( patch.left, patch.top );
        avatar.frame( frame );

        return avatar;
    }
}
