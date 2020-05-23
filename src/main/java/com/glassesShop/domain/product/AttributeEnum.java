package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public enum AttributeEnum {
    //убедиться, что id не повторяются
    BLACK(Color.BLACK.getId(), Color.BLACK.getCode(), Color.class, 0),
    WHITE(Color.WHITE.getId(), Color.WHITE.getCode(), Color.class, 0),
    RED(Color.RED.getId(), Color.RED.getCode(), Color.class, 0),
    BLUE(Color.BLUE.getId(), Color.BLUE.getCode(), Color.class, 0),
    BROWN(Color.BROWN.getId(), Color.BROWN.getCode(), Color.class, 0),
    PINK(Color.PINK.getId(), Color.PINK.getCode(), Color.class, 0),
    SILVER(Color.SILVER.getId(), Color.SILVER.getCode(), Color.class, 0),
    GREY(Color.GREY.getId(), Color.GREY.getCode(), Color.class, 0),
    GREEN(Color.GREEN.getId(), Color.GREEN.getCode(), Color.class, 0),
    GOLD(Color.GOLD.getId(), Color.GOLD.getCode(), Color.class, 0),
    PURPLE(Color.PURPLE.getId(), Color.PURPLE.getCode(), Color.class, 0),
    BEIGE(Color.BEIGE.getId(), Color.BEIGE.getCode(), Color.class, 0),
    MEN(Gender.MEN.getId(), Gender.MEN.getCode(), Gender.class, 1),
    WOMEN(Gender.WOMEN.getId(), Gender.WOMEN.getCode(), Gender.class, 1),
    UNISEX(Gender.UNISEX.getId(), Gender.UNISEX.getCode(), Gender.class, 1),
    CHILDREN(Gender.CHILDREN.getId(), Gender.CHILDREN.getCode(), Gender.class, 1),
    BABOCHKA(FrameForm.BABOCHKA.getId(), FrameForm.BABOCHKA.getCode(), FrameForm.class, 2),
    KOSHACHIJ_GLAZ(FrameForm.KOSHACHIJ_GLAZ.getId(), FrameForm.KOSHACHIJ_GLAZ.getCode(), FrameForm.class, 2),
    OVALNAYA(FrameForm.OVALNAYA.getId(), FrameForm.OVALNAYA.getCode(), FrameForm.class, 2),
    PANTO(FrameForm.PANTO.getId(), FrameForm.PANTO.getCode(), FrameForm.class, 2),
    PILOT(FrameForm.PILOT.getId(), FrameForm.PILOT.getCode(), FrameForm.class, 2),
    KRUGLAYA(FrameForm.KRUGLAYA.getId(), FrameForm.KRUGLAYA.getCode(), FrameForm.class, 2),
    KVADRATNAYA(FrameForm.KVADRATNAYA.getId(), FrameForm.KVADRATNAYA.getCode(), FrameForm.class, 2),
    PRYAMOUGOLNAYA(FrameForm.PRYAMOUGOLNAYA.getId(), FrameForm.PRYAMOUGOLNAYA.getCode(), FrameForm.class, 2),
    TRAPECIYA(FrameForm.TRAPECIYA.getId(), FrameForm.TRAPECIYA.getCode(), FrameForm.class, 2),
    BROULAJNER(FrameForm.BROULAJNER.getId(), FrameForm.BROULAJNER.getCode(), FrameForm.class, 2),
    VAJFARER(FrameForm.VAJFARER.getId(), FrameForm.VAJFARER.getCode(), FrameForm.class, 2),
    NESTANDARTNAYA(FrameForm.NESTANDARTNAYA.getId(), FrameForm.NESTANDARTNAYA.getCode(), FrameForm.class, 2),

    AVENUE_5TH(Brand.AVENUE_5TH.getId(), Brand.AVENUE_5TH.getCode(), Brand.class, 3),
    ACTIV(Brand.ACTIV.getId(), Brand.ACTIV.getCode(), Brand.class, 3),
    ARMANI_EXCHANGE(Brand.ARMANI_EXCHANGE.getId(), Brand.ARMANI_EXCHANGE.getCode(), Brand.class, 3),
    BURBERRY(Brand.BURBERRY.getId(), Brand.BURBERRY.getCode(), Brand.class, 3),
    BVLGARI(Brand.BVLGARI.getId(), Brand.BVLGARI.getCode(), Brand.class, 3),
    C_LINE(Brand.C_LINE.getId(), Brand.C_LINE.getCode(), Brand.class, 3),
    CARRERA(Brand.CARRERA.getId(), Brand.CARRERA.getCode(), Brand.class, 3),
    CHLOE(Brand.CHLOE.getId(), Brand.CHLOE.getCode(), Brand.class, 3),
    DBYD(Brand.DBYD.getId(), Brand.DBYD.getCode(), Brand.class, 3),
    DIESEL(Brand.DIESEL.getId(), Brand.DIESEL.getCode(), Brand.class, 3),
    DIOR(Brand.DIOR.getId(), Brand.DIOR.getCode(), Brand.class, 3),
    DOLCE_AND_GABBANA(Brand.DOLCE_AND_GABBANA.getId(), Brand.DOLCE_AND_GABBANA.getCode(), Brand.class, 3),
    DUPONT(Brand.DUPONT.getId(), Brand.DUPONT.getCode(), Brand.class, 3),
    EMPORIO_ARMANI(Brand.EMPORIO_ARMANI.getId(), Brand.EMPORIO_ARMANI.getCode(), Brand.class, 3),
    ENZZO(Brand.ENZZO.getId(), Brand.ENZZO.getCode(), Brand.class, 3),
    FENDI(Brand.FENDI.getId(), Brand.FENDI.getCode(), Brand.class, 3),
    FOSSIL(Brand.FOSSIL.getId(), Brand.FOSSIL.getCode(), Brand.class, 3),
    FUZION(Brand.FUZION.getId(), Brand.FUZION.getCode(), Brand.class, 3),
    GIORGIO_ARMANI(Brand.GIORGIO_ARMANI.getId(), Brand.GIORGIO_ARMANI.getCode(), Brand.class, 3),
    GIVENCHY(Brand.GIVENCHY.getId(), Brand.GIVENCHY.getCode(), Brand.class, 3),
    GUCCI(Brand.GUCCI.getId(), Brand.GUCCI.getCode(), Brand.class, 3),
    HUGO(Brand.HUGO.getId(), Brand.HUGO.getCode(), Brand.class, 3),
    HERITAGE(Brand.HERITAGE.getId(), Brand.HERITAGE.getCode(), Brand.class, 3),
    HUGO_BOSS(Brand.HUGO_BOSS.getId(), Brand.HUGO_BOSS.getCode(), Brand.class, 3),
    IN_STYLE(Brand.IN_STYLE.getId(), Brand.IN_STYLE.getCode(), Brand.class, 3),
    JIMMY_CHOO(Brand.JIMMY_CHOO.getId(), Brand.JIMMY_CHOO.getCode(), Brand.class, 3),
    JULIUS(Brand.JULIUS.getId(), Brand.JULIUS.getCode(), Brand.class, 3),
    JUST_CAVALLI(Brand.JUST_CAVALLI.getId(), Brand.JUST_CAVALLI.getCode(), Brand.class, 3),
    LEVIS(Brand.LEVIS.getId(), Brand.LEVIS.getCode(), Brand.class, 3),
    MARC_JACOBS(Brand.MARC_JACOBS.getId(), Brand.MARC_JACOBS.getCode(), Brand.class, 3),
    MAXMARA(Brand.MAXMARA.getId(), Brand.MAXMARA.getCode(), Brand.class, 3),
    MCQ(Brand.MCQ.getId(), Brand.MCQ.getCode(), Brand.class, 3),
    MICHAEL_KORS(Brand.MICHAEL_KORS.getId(), Brand.MICHAEL_KORS.getCode(), Brand.class, 3),
    MIKI_NINN(Brand.MIKI_NINN.getId(), Brand.MIKI_NINN.getCode(), Brand.class, 3),
    MONTBLANC(Brand.MONTBLANC.getId(), Brand.MONTBLANC.getCode(), Brand.class, 3),
    MOSCHINO(Brand.MOSCHINO.getId(), Brand.MOSCHINO.getCode(), Brand.class, 3),
    PIERRE_CARDIN(Brand.PIERRE_CARDIN.getId(), Brand.PIERRE_CARDIN.getCode(), Brand.class, 3),
    PLAY(Brand.PLAY.getId(), Brand.PLAY.getCode(), Brand.class, 3),
    POLAROID(Brand.POLAROID.getId(), Brand.POLAROID.getCode(), Brand.class, 3),
    PRADA(Brand.PRADA.getId(), Brand.PRADA.getCode(), Brand.class, 3),
    RALPH(Brand.RALPH.getId(), Brand.RALPH.getCode(), Brand.class, 3),
    RAY_BAN(Brand.RAY_BAN.getId(), Brand.RAY_BAN.getCode(), Brand.class, 3),
    ROBERTO_CAVALLI(Brand.ROBERTO_CAVALLI.getId(), Brand.ROBERTO_CAVALLI.getCode(), Brand.class, 3),
    SAINT_LAURENT(Brand.SAINT_LAURENT.getId(), Brand.SAINT_LAURENT.getCode(), Brand.class, 3),
    SEEN(Brand.SEEN.getId(), Brand.SEEN.getCode(), Brand.class, 3),
    SENSAYA(Brand.SENSAYA.getId(), Brand.SENSAYA.getCode(), Brand.class, 3),
    SILHOUETTE(Brand.SILHOUETTE.getId(), Brand.SILHOUETTE.getCode(), Brand.class, 3),
    THE_ONE(Brand.THE_ONE.getId(), Brand.THE_ONE.getCode(), Brand.class, 3),
    TIFFANY_AND_CO(Brand.TIFFANY_AND_CO.getId(), Brand.TIFFANY_AND_CO.getCode(), Brand.class, 3),
    TOM_FORD(Brand.TOM_FORD.getId(), Brand.TOM_FORD.getCode(), Brand.class, 3),
    TOMMY_HILFIGER(Brand.TOMMY_HILFIGER.getId(), Brand.TOMMY_HILFIGER.getCode(), Brand.class, 3),
    TWIINS(Brand.TWIINS.getId(), Brand.TWIINS.getCode(), Brand.class, 3),
    UNOFFICIAL(Brand.UNOFFICIAL.getId(), Brand.UNOFFICIAL.getCode(), Brand.class, 3),
    VOGUE(Brand.VOGUE.getId(), Brand.VOGUE.getCode(), Brand.class, 3);

    private final Integer id;
    private final String code;
    private final Class type;
    private final Integer groupId;//разделяем атрибуты на группы для корректного поиска

    public static AttributeEnum findById(Integer id) {
        if (id != null) {
            AttributeEnum[] attributes = AttributeEnum.values();
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].getId().equals(id)) {
                    return attributes[i];
                }
            }
        }
        return null;
    }

    public static AttributeEnum findByCode(String code) {
        if (!StringUtils.isEmpty(code)) {
            AttributeEnum[] attributes = AttributeEnum.values();
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].getCode().equals(code)) {
                    return attributes[i];
                }
            }
        }
        return null;
    }


    //    private Set<Attribute> getAttributesEnumSetByAttrStrSet(Set<String> attributes){
//        HashSet<Attribute> result =new HashSet();
//        for(String attr: attributes){
//            Attribute attribute = lookupByCode(attr);
//            if(attribute != null){
//                result.add(attribute);
//            }
//        }
//        return result;
//    }
//
//    public static Attribute lookupByCode(String code) {
//        if(code != null) {
//            Attribute[] attributes = Attribute.values();
//            for (int i = 0; i < attributes.length; i++) {
//                if (attributes[i].getCode().equals(code)) {
//                    return attributes[i];
//                }
//            }
//        }
//        return null;
//    }
}