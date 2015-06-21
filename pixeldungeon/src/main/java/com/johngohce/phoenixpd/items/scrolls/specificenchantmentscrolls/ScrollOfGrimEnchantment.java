package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.weapon.enchantments.Death;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfGrimEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Grim Enchantment";
        mode = WndBag.Mode.WEAPON;
        enchantment = new Death();
        enchantmentText = "grim";
        image = ItemSpriteSheet.SCROLL_GYFU;

    }
}