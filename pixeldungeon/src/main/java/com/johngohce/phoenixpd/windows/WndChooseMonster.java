package com.johngohce.phoenixpd.windows;

import com.johngohce.noosa.BitmapTextMultiline;
import com.johngohce.phoenixpd.actors.hero.HeroMonsterClass;
import com.johngohce.phoenixpd.items.TomeOfMastery;
import com.johngohce.phoenixpd.scenes.PixelScene;
import com.johngohce.phoenixpd.scenes.TitleScene;
import com.johngohce.phoenixpd.sprites.ItemSprite;
import com.johngohce.phoenixpd.ui.RedButton;
import com.johngohce.phoenixpd.ui.Window;

import java.util.ArrayList;

/**
 * Created by johngoh on 6/22/15.
 */
public class WndChooseMonster extends Window {

    private static final String TXT_MESSAGE	= "This feature is meant for testers only.\n";
    private static final String TXT_CANCEL	= "I'll decide later";

    private static final int WIDTH		= 120;
    private static final int BTN_HEIGHT	= 18;
    private static final float GAP		= 2;


    public WndChooseMonster( int page ) {

        super();

        HeroMonsterClass[] t1 = new HeroMonsterClass[] {HeroMonsterClass.RAT,HeroMonsterClass.SCOUT,HeroMonsterClass.CRAB};
        HeroMonsterClass[] t2 = new HeroMonsterClass[] {HeroMonsterClass.SKELETON,HeroMonsterClass.THIEF,HeroMonsterClass.FLIES,HeroMonsterClass.SHAMAN};
        HeroMonsterClass[] t3 = new HeroMonsterClass[] {HeroMonsterClass.BAT,HeroMonsterClass.SPINNER,HeroMonsterClass.BRUTE};
        HeroMonsterClass[] t4 = new HeroMonsterClass[] {HeroMonsterClass.FIRE_ELEMENTAL,HeroMonsterClass.WARLOCK,HeroMonsterClass.GOLEM,HeroMonsterClass.MONK};
        HeroMonsterClass[] t5 = new HeroMonsterClass[] {HeroMonsterClass.EVIL_EYE,HeroMonsterClass.SUCCUBUS,HeroMonsterClass.SCORPIO};

        final HeroMonsterClass classes[][] = new HeroMonsterClass[][]{t1,t2,t3,t4,t5};

        while (page < 0) page += classes.length;
        page = page % classes.length;
        final int actualPage = page;

        IconTitle titlebar = new IconTitle();
        titlebar.icon( new ItemSprite( new TomeOfMastery().image(), null ) );
        titlebar.label( "Choose your starting class" );
        titlebar.setRect( 0, 0, WIDTH, 0 );
        add( titlebar );

        String text = TXT_MESSAGE + "Current starting class = "+ HeroMonsterClass.defaultClass().title() +
                "\n\ntier: "+(page+1);

        BitmapTextMultiline normal = PixelScene.createMultiline(text, 6);
        normal.maxWidth = WIDTH;
        normal.measure();
        normal.x = titlebar.left();
        normal.y = titlebar.bottom() + GAP;
        add( normal );

        RedButton btnLeft = new RedButton( "Previous" ){
            @Override
            protected void onClick() {
                hide();
                TitleScene.scene.add(new WndChooseMonster(actualPage - 1));
            }
        };
        btnLeft.setRect( 0, normal.y + normal.height() + GAP, (WIDTH - GAP) / 2, BTN_HEIGHT );
        add( btnLeft );

        RedButton btnRight = new RedButton( "Next" ){
            @Override
            protected void onClick() {
                hide();
                TitleScene.scene.add(new WndChooseMonster(actualPage + 1));
            }
        };
        btnRight.setRect( btnLeft.right() + GAP, btnLeft.top(), btnLeft.width(), BTN_HEIGHT );
        add( btnRight );

        ArrayList<RedButton> monsterButtons = new ArrayList<>();
        for(int i=0;i<classes[actualPage].length;i++){
            final int newI = i;
            RedButton btnMonster = new RedButton( classes[actualPage][i].title() ) {
                @Override
                protected void onClick() {
                    hide();
                    HeroMonsterClass.testerDefaultClass = classes[actualPage][newI];
                }
            };
            if(monsterButtons.isEmpty()){
                btnMonster.setRect( 0, btnRight.bottom() + GAP, WIDTH, BTN_HEIGHT );
            }else{
                btnMonster.setRect( 0, monsterButtons.get(monsterButtons.size() - 1).bottom() + GAP, WIDTH, BTN_HEIGHT );
            }
            add(btnMonster);
            monsterButtons.add(btnMonster);

        }

        resize(WIDTH, (int) monsterButtons.get(monsterButtons.size() - 1).bottom());
    }
}
