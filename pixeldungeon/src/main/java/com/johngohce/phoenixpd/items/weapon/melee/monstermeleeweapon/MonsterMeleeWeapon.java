package com.johngohce.phoenixpd.items.weapon.melee.monstermeleeweapon;

import com.johngohce.phoenixpd.items.Item;
import com.johngohce.phoenixpd.items.weapon.melee.MeleeWeapon;

/**
 * Created by johngoh on 6/10/15.
 */
abstract class MonsterMeleeWeapon extends MeleeWeapon {

    public MonsterMeleeWeapon( int tier, float acu, float dly ) {
        super(tier, acu, dly);

        isPermanentlyEquipped = true;
        levelKnown = true;
        cursed = false;
        cursedKnown = true;
        unique = true;
    }


    @Override
    public Item upgrade() {
        return super.safeUpgrade();
    }

    @Override
    public int typicalSTR() {
        return 10;
    }

    @Override
    public int price(){
        return 0;
    }
}
