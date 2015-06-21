package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.armor.glyphs.Entanglement;
import com.johngohce.phoenixpd.items.weapon.enchantments.Paralysis;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfDisablingEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Disabling Enchantment";
        mode = WndBag.Mode.ENCHANTABLE;
        enchantment = new Paralysis();
        glyph = new Entanglement();
        enchantmentText = "stunning";
        glyphText = "entanglement";
        image = ItemSpriteSheet.SCROLL_NAUDIZ;
    }
}