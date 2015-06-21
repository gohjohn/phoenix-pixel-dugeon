package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.Dungeon;
import com.johngohce.phoenixpd.actors.Char;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.AttackHaste;
import com.johngohce.phoenixpd.actors.buffs.monsterbuffs.HeroMonsterBuff;
import com.johngohce.phoenixpd.effects.Speck;
import com.johngohce.phoenixpd.sprites.CharSprite;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.utils.Random;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/17/15.
 */
public class BatFangs extends MonsterMeleeWeapon {
    //Future special ability... Transform into vampire.
    {
        name = "Bat Fangs";
        image = ItemSpriteSheet.WEAPON;
        isPermanentlyEquipped = true;
    }

    public BatFangs() {
        super( 2, 1f, 1f );
    }

    @Override
    public void proc( Char attacker, Char defender, int damage ) {
        //Standard leech
        int level = Math.max( 0, this.level );

        // lvl 0 - 33%
        // lvl 1 - 43%
        // lvl 2 - 50%
        int maxValue = damage * (level + 2) / (level + 6);
        int effValue = Math.min( Random.IntRange( 0, maxValue ), attacker.HT - attacker.HP );

        if (effValue > 0) {
            attacker.HP += effValue;
            attacker.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 1);
            attacker.sprite.showStatus(CharSprite.POSITIVE, Integer.toString(effValue));
        }

        super.proc(attacker,defender,damage);
    }
    @Override
    protected ArrayList<HeroMonsterBuff> updatedBuffs(){
        ArrayList<HeroMonsterBuff> newBuffs = new ArrayList<>();
        if(Dungeon.nightMode){
            newBuffs.add(new AttackHaste(4));
        }
        return newBuffs;
    }

    @Override
    public String desc() {
        if(Dungeon.nightMode) {
            return "It’s a nice night to grab a bite to drink..\n" +
                    "Oh and REAL vampires don't sparkle.";
        }else{
            return "I know there isn't any sun.. But I hate daytime.\n" +
                    "And no, I don't sparkle.";
        }
    }
}