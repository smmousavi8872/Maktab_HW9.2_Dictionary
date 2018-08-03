package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database;

public class DBSchema {
  public static final String NAME = "database";
  public static final int VERSION = 1;

  public static final class EnglishWordTable {
    public static final String NAME = "english_word_table";

    public static final class Cols {
      public static final String UUID = "uuid";
      public static final String WORD_TEXT = "word_text";
      public static final String INITIAL_CHAR = "initial_char";
      public static final String VERBAL = "verbal";
      public static final String SEARCH_TIMES = "search_times";
      public static final String PERSIAN_TRANSLATION_UUID = "persian_translation_uuid";
      public static final String SPANISH_TRANSLATION_UUID = "spanish_translation_uuid";
    }

  }


  public static final class PersianWordTable {
    public static final String NAME = "persian_word_table";

    public static final class Cols {
      public static final String UUID = "uuid";
      public static final String WORD_TEXT = "word_text";
      public static final String INITIAL_CHAR = "initial_char";
      public static final String VERBAL = "verbal";
      public static final String SEARCH_TIMES = "search_times";
      public static final String ENGLISH_TRANSLATION_UUID = "english_translation_uuid";
      public static final String SPANISH_TRANSLATION_UUID = "spanish_translation_uuid";
    }
  }


  public static final class SpanishWordTable {
    public static final String NAME = "spanish_word_table";

    public static final class Cols {
      public static final String UUID = "uuid";
      public static final String WORD_TEXT = "word_text";
      public static final String INITIAL_CHAR = "initial_char";
      public static final String VERBAL = "verbal";
      public static final String SEARCH_TIMES = "search_times";
      public static final String PERSIAN_TRANSLATION_UUID = "persian_translation_uuid";
      public static final String ENGLISH_TRANSLATION_UUID = "english_translation_uuid";
    }

  }


  public static final class TranslationTable {
    public static final String NAME = "translation_table";

    public static final class Cols {
      public static final String TRANSLATION_ID = "translation_id";
      public static final String FROM = "from_language";
      public static final String TO = "to_language";
      public static final String WORD_ID = "word_id";
      public static final String TRANSLATION_TEXT = "translation_text";
    }

  }


}
