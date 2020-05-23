package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Brand {
    AVENUE_5TH(28, "5th_avenue", "5th Avenue"),
    ACTIV(29, "activ", "Activ"),
    ARMANI_EXCHANGE(30, "armani_exchange", "Armani Exchange"),
    BURBERRY(31, "burberry", "Burberry"),
    BVLGARI(32, "bvlgari", "Bvlgari"),
    C_LINE(33, "c_line", "C-Line"),
    CARRERA(34, "carrera", "Carrera"),
    CHLOE(35, "chloe", "Chloé"),
    DBYD(36, "dbyd", "DbyD"),
    DIESEL(37, "diesel", "Diesel"),
    DIOR(38, "dior", "Dior"),
    DOLCE_AND_GABBANA(39, "dolce_and_gabbana", "Dolce&Gabbana"),
    DUPONT(40, "dupont", "Dupont"),
    EMPORIO_ARMANI(41, "emporio_armani", "Emporio Armani"),
    ENZZO(42, "enzzo", "Enzzo"),
    FENDI(43, "fendi", "Fendi"),
    FOSSIL(44, "fossil", "Fossil"),
    FUZION(45, "fuzion", "Fuzion"),
    GIORGIO_ARMANI(46, "giorgio_armani", "Giorgio Armani"),
    GIVENCHY(47, "givenchy", "Givenchy"),
    GUCCI(48, "gucci", "Gucci"),
    HUGO(49, "hugo", "HUGO"),
    HERITAGE(50, "heritage", "Heritage"),
    HUGO_BOSS(51, "hugo_boss", "Hugo Boss"),
    IN_STYLE(52, "in_style", "In Style"),
    JIMMY_CHOO(53, "jimmy_choo", "Jimmy Choo"),
    JULIUS(54, "julius", "Julius"),
    JUST_CAVALLI(55, "just_cavalli", "Just Cavalli"),
    LEVIS(56, "levis", "Levi\'s"),
    MARC_JACOBS(57, "marc_jacobs", "Marc Jacobs"),
    MAXMARA(58, "maxmara", "MaxMara"),
    MCQ(59, "mcq", "McQ"),
    MICHAEL_KORS(60, "michael_kors", "Michael Kors"),
    MIKI_NINN(61, "miki_ninn", "Miki Ninn"),
    MONTBLANC(62, "mont_blanc", "MontBlanc"),
    MOSCHINO(63, "moschino", "Moschino"),
    PIERRE_CARDIN(64, "pierre_cardin", "Pierre Cardin"),
    PLAY(65, "play", "Play"),
    POLAROID(66, "polaroid", "Polaroid"),
    PRADA(67, "prada", "Prada"),
    RALPH(68, "ralph", "Ralph"),
    RAY_BAN(69, "ray_ban", "Ray-Ban"),
    ROBERTO_CAVALLI(70, "roberto_cavalli", "Roberto Cavalli"),
    SAINT_LAURENT(71, "saint_laurent", "Saint Laurent"),
    SEEN(72, "seen", "Seen"),
    SENSAYA(73, "sensaya", "Sensaya"),
    SILHOUETTE(74, "silhouette", "Silhouette"),
    THE_ONE(75, "the_one", "The One"),
    TIFFANY_AND_CO(76, "tiffany_and_co", "Tiffany&Co"),
    TOM_FORD(77, "tom_ford", "Tom Ford"),
    TOMMY_HILFIGER(78, "tommy_hilfiger", "Tommy Hilfiger"),
    TWIINS(79, "twiins", "Twiins"),
    UNOFFICIAL(80, "unofficial", "Unofficial"),
    VERSACE(81, "versace", "Versace"),
    VOGUE(82, "vogue", "Vogue");


    private final Integer id;
    private final String code;
    private final String rusName;//просто name вызовет ошибку в ftl
}