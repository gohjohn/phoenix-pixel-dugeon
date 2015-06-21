package com.johngohce.phoenixpd.items.scrolls.specificenchantmentscrolls;

import com.johngohce.phoenixpd.items.armor.glyphs.Affection;
import com.johngohce.phoenixpd.sprites.ItemSpriteSheet;
import com.johngohce.phoenixpd.windows.WndBag;

/**
 * Created by johngoh on 6/13/15.
 */
public class ScrollOfAffectionEnchantment extends SpecificEnchantmentScroll {
    {
        name = "Scroll of Affection Enchantment";
        mode = WndBag.Mode.ARMOR;
        glyph = new Affection();
        glyphText = "affection";
        image = ItemSpriteSheet.SCROLL_ODAL;
    }
}