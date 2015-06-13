package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.armor.glyphs.Multiplicity;
import com.johngohce.phoenixpd.items.weapon.enchantments.Instability;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfUnstableEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Unstable Enchantment";
        mode = WndBag.Mode.ENCHANTABLE;
        enchantment = new Instability();
        glyph = new Multiplicity();
        enchantmentText = "unstable";
        glyphText = "multiplicity";
    }
}
