package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Actor;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.mobs.npcs.MirrorImage;
import com.johngohce.phoenixpd.items.wands.WandOfBlink;
import com.johngohce.phoenixpd.levels.Level;
import com.johngohce.phoenixpd.scenes.GameScene;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.utils.GLog;
import com.johngohce.utils.Random;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/14/15.
 */
public class FlyStinger extends MonsterMeleeWeapon {
    public final int SPECIAL_LEVEL = 3;

    {
        name = "Fly Stinger";
        image = ItemSpriteSheet.WEAPON;
        isPermanentlyEquipped = true;
    }

    public FlyStinger() {
        super( 2, 1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        if(damage > 0 && level >= SPECIAL_LEVEL){
            int maxImages = Random.Int(level + 3) / 3;
            int images = maxImages;
            if( images > 0 && Random.Int( level / 2 + 6 ) > 5 ){
                ArrayList<Integer> respawnPoints = new ArrayList<>();
                for (int i=0; i < Level.NEIGHBOURS8.length; i++) {
                    int p = defender.pos + Level.NEIGHBOURS8[i];
                    if (Actor.findChar(p) == null && (Level.passable[p] || Level.avoid[p])) {
                        respawnPoints.add( p );
                    }
                }
                while (images > 0 && respawnPoints.size() > 0) {
                    int index = Random.index( respawnPoints );

                    MirrorImage mob = new MirrorImage();
                    mob.duplicate( Dungeon.hero );
                    GameScene.add(mob);
                    WandOfBlink.appear(mob, respawnPoints.get(index));

                    respawnPoints.remove( index );
                    images--;
                }
                if(maxImages>images){
                    GLog.i("Your attack spawned " + (maxImages - images) + "images");
                }
            }

        }

        super.proc(attacker,defender,damage);
    }

    @Override
    public String desc() {
        return "I am the Swarm. Armies will be shattered. Worlds will burn. " +
                "Now at last, on this world, vengeance shall be mine.";
    }
}