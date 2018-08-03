package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.vocabulary_bank;

public class EnglishVocabularyBank {

  // copied from string resources
  public static final String VERBAL_VERB = "verb";
  public static final String VERBAL_ADJECTIVE = "adjective";
  public static final String VERBAL_NOUN = "noun";


  public String[][] getTextsArray() {
    return textsArray;
  }

  public static final String W1_TEXT = "abandon";
  public static final String W1_PERSIAN_TRANSLATION = "رها کردن";
  public static final String W1_SPANISH_TRANSLATION = "abandonar";
  public static final String W1_VERBAL = VERBAL_VERB;

  public static final String W2_TEXT = "bake";
  public static final String W2_PERSIAN_TRANSLATION = "پختن";
  public static final String W2_SPANISH_TRANSLATION = "endurecer";
  public static final String W2_VERBAL = VERBAL_VERB;

  public static final String W3_TEXT = "call";
  public static final String W3_PERSIAN_TRANSLATION = "زنگ زدن";
  public static final String W3_SPANISH_TRANSLATION = "llamar";
  public static final String W3_VERBAL = VERBAL_VERB;

  public static final String W4_TEXT = "army";
  public static final String W4_PERSIAN_TRANSLATION = "ارتش";
  public static final String W4_SPANISH_TRANSLATION = "ejército";
  public static final String W4_VERBAL = VERBAL_NOUN;

  public static final String W5_TEXT = "boss";
  public static final String W5_PERSIAN_TRANSLATION = "رئيس";
  public static final String W5_SPANISH_TRANSLATION = "patrón";
  public static final String W5_VERBAL = VERBAL_NOUN;

  public static final String W6_TEXT = "cachet";
  public static final String W6_PERSIAN_TRANSLATION = "مهر";
  public static final String W6_SPANISH_TRANSLATION = "caché";
  public static final String W6_VERBAL = VERBAL_NOUN;

  public static final String W7_TEXT = "abnormal";
  public static final String W7_PERSIAN_TRANSLATION = "ناهنجار";
  public static final String W7_SPANISH_TRANSLATION = "contranatural";
  public static final String W7_VERBAL = VERBAL_ADJECTIVE;

  public static final String W8_TEXT = "beautiful";
  public static final String W8_PERSIAN_TRANSLATION = "خوشگل";
  public static final String W8_SPANISH_TRANSLATION = "magnífico";
  public static final String W8_VERBAL = VERBAL_ADJECTIVE;

  public static final String W9_TEXT = "crucial";
  public static final String W9_PERSIAN_TRANSLATION = "بسیار سخت";
  public static final String W9_SPANISH_TRANSLATION = "crucial";
  public static final String W9_VERBAL = VERBAL_ADJECTIVE;


  private String[][] textsArray = {
    {W1_TEXT, W1_VERBAL, W1_PERSIAN_TRANSLATION, W1_SPANISH_TRANSLATION},
    {W2_TEXT, W2_VERBAL, W2_PERSIAN_TRANSLATION, W2_SPANISH_TRANSLATION},
    {W3_TEXT, W3_VERBAL, W3_PERSIAN_TRANSLATION, W3_SPANISH_TRANSLATION},
    {W4_TEXT, W4_VERBAL, W4_PERSIAN_TRANSLATION, W4_SPANISH_TRANSLATION},
    {W5_TEXT, W5_VERBAL, W5_PERSIAN_TRANSLATION, W5_SPANISH_TRANSLATION},
    {W6_TEXT, W6_VERBAL, W6_PERSIAN_TRANSLATION, W6_SPANISH_TRANSLATION},
    {W7_TEXT, W7_VERBAL, W7_PERSIAN_TRANSLATION, W7_SPANISH_TRANSLATION},
    {W8_TEXT, W8_VERBAL, W8_PERSIAN_TRANSLATION, W8_SPANISH_TRANSLATION},
    {W9_TEXT, W9_VERBAL, W9_PERSIAN_TRANSLATION, W9_SPANISH_TRANSLATION}
  };
}
