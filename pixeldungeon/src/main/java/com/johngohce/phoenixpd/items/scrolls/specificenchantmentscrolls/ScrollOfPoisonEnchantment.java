package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.armor.glyphs.Stench;
import com.johngohce.phoenixpd.items.weapon.enchantments.Poison;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfPoisonEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Poison Enchantment";
        mode = WndBag.Mode.ENCHANTABLE;
        enchantment = new Poison();
        glyph = new Stench();
        enchantmentText = "venom";
        glyphText = "stench";
    }
}
