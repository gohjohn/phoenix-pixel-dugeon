package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.armor.glyphs.Metabolism;
import com.johngohce.phoenixpd.items.weapon.enchantments.Leech;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfHealthEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Health Enchantment";
        mode = WndBag.Mode.ENCHANTABLE;
        enchantment = new Leech();
        glyph = new Metabolism();
        enchantmentText = "vampiric";
        glyphText = "metabolism";
    }
}
