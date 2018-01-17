package com.veve.flowreader.model.impl;

import com.veve.flowreader.model.BookPage;
import com.veve.flowreader.model.PageGlyph;

import java.util.Random;

/**
 * Created by ddreval on 15.01.2018.
 */

public class MockPageImpl implements BookPage {

    private static final int MAX_GLYPHS = 1000;

    private static int position = 0;

    public MockPageImpl() {
        position = 0;
    }

    @Override
    public PageGlyph getNextGlyph() {
        if (++position < SAGA.length())
            return new MockPageGlyphImpl(SAGA.substring(position, position+1));
        return null;
    }

    final static String SAGA = "Úlfur hét maður, sonur Bjálfa og Hallberu, dóttur Úlfs hins óarga. " +
            "Hún var systir Hallbjarnar hálftrölls í Hrafnistu, föður Ketils hængs. Úlfur var maður " +
            "svo mikill og sterkur, að eigi voru hans jafningjar, en er hann var á unga aldri, lá " +
            "hann í víkingu og herjaði. Með honum var í félagsskap sá maður, er kallaður var " +
            "Berðlu-Kári, göfugur maður og hinn mesti afreksmaður að afli og áræði; hann var " +
            "berserkur. Þeir Úlfur áttu einn sjóð báðir, og var með þeim hin kærsta vinátta.\n" +
            "En er þeir réðust úr hernaði, fór Kári til bús síns í Berðlu; hann var maður stórauðugur. " +
            "Kári átti þrjú börn; hét sonur hans Eyvindur lambi, annar Ölvir hnúfa; dóttir hans hét " +
            "Salbjörg; hún var kvenna vænst og skörungur mikill; hennar fékk Úlfur; fór hann þá og " +
            "til búa sinna. Úlfur var maður auðugur bæði að löndum og lausum aurum; hann tók lends " +
            "manns rétt, svo sem haft höfðu langfeðgar hans, og gerðist maður ríkur.\n" +
            "Svo er sagt, að Úlfur var búsýslumaður mikill. Var það siður hans að rísa upp árdegis " +
            "og ganga þá um sýslur manna eða þar er smiðir voru og sjá yfir fénað sinn og akra, en " +
            "stundum var hann á tali við menn, þá er ráða hans þurftu. Kunni hann til alls góð ráð " +
            "að leggja, því að hann var forvitri. En dag hvern, er að kveldi leið, þá gerðist hann " +
            "styggur, svo að fáir menn máttu orðum við hann koma; var hann kveldsvæfur. Það var mál " +
            "manna, að hann væri mjög hamrammur; hann var kallaður Kveld-Úlfur.\n" +
            "Þau Kveld-Úlfur áttu tvo sonu; hét hinn eldri Þórólfur, en hinn yngri Grímur. En er " +
            "þeir óxu upp, þá voru þeir báðir miklir menn og sterkir, svo sem faðir þeirra var. Var " +
            "Þórólfur manna vænstur og gjörvilegastur; hann var líkur móðurfrændum sínum, gleðimaður " +
            "mikill, ör og ákafamaður mikill í öllu og hinn mesti kappsmaður; var hann vinsæll af " +
            "öllum mönnum. Grímur var svartur maður og ljótur, líkur föður sínum, bæði yfirlits og " +
            "að skaplyndi; gerðist hann umsýslumaður mikill; hann var hagur maður á tré og járn og " +
            "gerðist hinn mesti smiður; hann fór og oft um vetrum í síldfiski með lagnarskútu og með " +
            "honum húskarlar margir.\n" +
            "En er Þórólfur var á tvítugs aldri, þá bjóst hann í hernað. Fékk Kveld-Úlfur honum langskip. " +
            "Til þeirrar ferðar réðust synir Berðlu-Kára, Eyvindur og Ölvir, - þeir höfðu lið mikið " +
            "og annað langskip, - og fóru um sumarið í víking og öfluðu sér fjár og höfðu hlutskipti mikið. " +
            "Það var nokkur sumur, er þeir lágu í víking, en voru heima um vetrum með feðrum sínum. " +
            "Hafði Þórólfur heim marga dýrgripi og færði föður sínum og móður. Var þá bæði gott til " +
            "fjár og mannvirðingar. Kveld-Úlfur var þá mjög á efra aldri, en synir hans voru rosknir.";

}
