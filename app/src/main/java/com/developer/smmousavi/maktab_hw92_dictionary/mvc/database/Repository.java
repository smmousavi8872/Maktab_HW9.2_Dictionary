package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.EnglishWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.PersianWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.SpanishWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.TranslationTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.vocabulary_bank.EnglishVocabularyBank;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.vocabulary_bank.PersianVocabularyBank;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.vocabulary_bank.SpanishVocabularyBank;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers.EnglishCursorWrapper;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers.PersianCursorWrapper;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers.SpanishCursorWrapper;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers.TranslationCursorWrapper;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.EnglishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.PersianWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.SpanishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Translation;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository {
  // copied from string resources
  public static final String PERSIAN_LANGUAGE_NAME = "Persian";
  public static final String ENGLISH_LANGUAGE_NAME = "English";
  public static final String SPANISH_LANGUAGE_NAME = "Spanish";

  private static Repository instance;
  private SQLiteDatabase sqliteDB;
  private Context context;


  public static Repository getInstance(Context context) {
    if (instance == null) {
      instance = new Repository(context);
    }

    return instance;
  }


  private Repository(Context context) {
    DatabaseHelper database = new DatabaseHelper(context);
    sqliteDB = database.getWritableDatabase();

    // to avoid creating tables every time that the app is launched
    if (getTableCount(PersianWordTable.NAME) < 1)
      initializePersianTable();

    if (getTableCount(EnglishWordTable.NAME) < 1)
      initializeEnglishTable();

    if (getTableCount(SpanishWordTable.NAME) < 1)
      initializeSpanishTable();
  }


  public int getTableCount(String tableName) {
    Cursor c = sqliteDB.query(
      tableName,
      null,
      null,
      null,
      null,
      null
      , null);

    return c.getCount();
  }


  public List<Word> searchPersianWordByInitial(String englishInitial) {
    List<Word> foundWords = new ArrayList<>();
    String whereClause = PersianWordTable.Cols.INITIAL_CHAR + " = ? ";
    String[] whereArgs = {englishInitial};
    PersianCursorWrapper cursorWrapper = (PersianCursorWrapper) getQuery(PersianWordTable.NAME, whereClause, whereArgs);

    if (cursorWrapper.getCount() > 0) {
      try {
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
          foundWords.add(cursorWrapper.getPersianWord());
          cursorWrapper.moveToNext();
        }
      } finally {
        cursorWrapper.close();
      }
    }
    return foundWords;
  }


  public List<Word> searchEnglishWordByInitial(String englishInitial) {
    List<Word> foundWords = new ArrayList<>();
    String whereClause = EnglishWordTable.Cols.INITIAL_CHAR + " = ? ";
    String[] whereArgs = {englishInitial};
    EnglishCursorWrapper cursorWrapper = (EnglishCursorWrapper) getQuery(EnglishWordTable.NAME, whereClause, whereArgs);

    if (cursorWrapper.getCount() > 0) {
      try {
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
          foundWords.add(cursorWrapper.getEnglishWord());
          cursorWrapper.moveToNext();
        }
      } finally {
        cursorWrapper.close();
      }
    }
    return foundWords;
  }

  public List<Word> searchSpanishWordByInitial(String englishInitial) {
    List<Word> foundWords = new ArrayList<>();
    String whereClause = SpanishWordTable.Cols.INITIAL_CHAR + " = ? ";
    String[] whereArgs = {englishInitial};
    SpanishCursorWrapper cursorWrapper = (SpanishCursorWrapper) getQuery(SpanishWordTable.NAME, whereClause, whereArgs);

    if (cursorWrapper.getCount() > 0) {
      try {
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
          foundWords.add(cursorWrapper.getSpanishWord());
          cursorWrapper.moveToNext();
        }
      } finally {
        cursorWrapper.close();
      }
    }
    return foundWords;
  }


  public void addEnglishWord(EnglishWord word) {
    ContentValues values = getEnglishWordContentValues(word);
    sqliteDB.insert(EnglishWordTable.NAME, null, values);
  }


  public void addPersianWord(PersianWord word) {
    ContentValues values = getPersianWordContentValues(word);
    sqliteDB.insert(PersianWordTable.NAME, null, values);
  }


  public void addSpanishWord(SpanishWord word) {
    ContentValues values = getSpanishWordContentValues(word);
    sqliteDB.insert(SpanishWordTable.NAME, null, values);
  }


  public void addTranslation(Translation translation) {
    ContentValues values = getTranslationsContentValue(translation);
    sqliteDB.insert(TranslationTable.NAME, null, values);
  }


  public CursorWrapper getQuery(String tableName, String whereClause, String[] whereArgs) {
    Cursor cursor = sqliteDB.query(
      tableName,
      null,
      whereClause,
      whereArgs,
      null,
      null,
      null,
      null
    );

    switch (tableName) {
      case EnglishWordTable.NAME:
        return new EnglishCursorWrapper(cursor);
      case PersianWordTable.NAME:
        return new PersianCursorWrapper(cursor);
      case SpanishWordTable.NAME:
        return new SpanishCursorWrapper(cursor);
      case TranslationTable.NAME:
        return new TranslationCursorWrapper(cursor);
      default:
        return null;
    }
  }


  public ContentValues getPersianWordContentValues(PersianWord word) {
    ContentValues values = new ContentValues();
    values.put(PersianWordTable.Cols.UUID, word.getId().toString());
    values.put(PersianWordTable.Cols.WORD_TEXT, word.getText());
    values.put(PersianWordTable.Cols.INITIAL_CHAR, word.getTextSubstring(1));
    values.put(PersianWordTable.Cols.VERBAL, word.getVerbal());
    values.put(PersianWordTable.Cols.SEARCH_TIMES, word.getSearchedTimes());
    values.put(PersianWordTable.Cols.SPANISH_TRANSLATION_UUID, word.getEnglishTranslationId().toString());
    values.put(PersianWordTable.Cols.ENGLISH_TRANSLATION_UUID, word.getSpanishTranslationId().toString());

    return values;
  }


  public ContentValues getEnglishWordContentValues(EnglishWord word) {
    ContentValues values = new ContentValues();
    values.put(EnglishWordTable.Cols.UUID, word.getId().toString());
    values.put(EnglishWordTable.Cols.WORD_TEXT, word.getText());
    values.put(EnglishWordTable.Cols.INITIAL_CHAR, word.getTextSubstring(1));
    values.put(EnglishWordTable.Cols.VERBAL, word.getVerbal());
    values.put(EnglishWordTable.Cols.SEARCH_TIMES, word.getSearchedTimes());
    values.put(EnglishWordTable.Cols.PERSIAN_TRANSLATION_UUID, word.getPersianTranslationId().toString());
    values.put(EnglishWordTable.Cols.SPANISH_TRANSLATION_UUID, word.getSpanishTranslationId().toString());

    return values;
  }


  public ContentValues getSpanishWordContentValues(SpanishWord word) {
    ContentValues values = new ContentValues();
    values.put(SpanishWordTable.Cols.UUID, word.getId().toString());
    values.put(SpanishWordTable.Cols.WORD_TEXT, word.getText());
    values.put(SpanishWordTable.Cols.INITIAL_CHAR, word.getTextSubstring(1));
    values.put(SpanishWordTable.Cols.VERBAL, word.getVerbal());
    values.put(SpanishWordTable.Cols.SEARCH_TIMES, word.getSearchedTimes());
    values.put(SpanishWordTable.Cols.PERSIAN_TRANSLATION_UUID, word.getPersianTranslationId().toString());
    values.put(SpanishWordTable.Cols.ENGLISH_TRANSLATION_UUID, word.getEnglishTranslationId().toString());

    return values;
  }


  public ContentValues getTranslationsContentValue(Translation translation) {
    ContentValues values = new ContentValues();
    values.put(TranslationTable.Cols.TRANSLATION_ID, translation.getTranslationId().toString());
    values.put(TranslationTable.Cols.WORD_ID, translation.getWordId().toString());
    values.put(TranslationTable.Cols.TO, translation.getTo());
    values.put(TranslationTable.Cols.FROM, translation.getFrom());
    values.put(TranslationTable.Cols.TRANSLATION_TEXT, translation.getTranslationText());

    return values;

  }


  public void createPersianWord(String wordText, String wordVerbal, String englishTranslation, String spanishTranslation) {
    PersianWord persianWord = new PersianWord(wordText);
    UUID englishTranslationId = createPersianToEnglishTranslation(persianWord.getId(), englishTranslation);
    UUID spanishTranslationId = createPersianToSpanishTranslation(persianWord.getId(), spanishTranslation);
    persianWord.setVerbal(wordVerbal);
    persianWord.setEnglishTranslationId(englishTranslationId);
    persianWord.setSpanishTranslationId(spanishTranslationId);
    addPersianWord(persianWord);
  }


  public UUID createPersianToEnglishTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(PERSIAN_LANGUAGE_NAME, ENGLISH_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }


  public UUID createPersianToSpanishTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(PERSIAN_LANGUAGE_NAME, SPANISH_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }

  public void initializePersianTable() {
    PersianVocabularyBank persianVocab = new PersianVocabularyBank();

    String[][] persianTextsArray = persianVocab.getTextsArray();
    for (String[] aTextsArray : persianTextsArray) {
      createPersianWord(aTextsArray[0], aTextsArray[1], aTextsArray[2], aTextsArray[3]);
    }
  }


  public void createEnglishWord(String wordText, String wordVerbal, String persianTranslation, String spanishTranslation) {
    EnglishWord englishWord = new EnglishWord(wordText);
    UUID persianTranslationId = createEnglishToPersianTranslation(englishWord.getId(), persianTranslation);
    UUID spanishTranslationId = createEnglishToSpanishTranslation(englishWord.getId(), spanishTranslation);
    englishWord.setVerbal(wordVerbal);
    englishWord.setPersianTranslationId(persianTranslationId);
    englishWord.setSpanishTranslationId(spanishTranslationId);
    addEnglishWord(englishWord);
  }


  public UUID createEnglishToPersianTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(ENGLISH_LANGUAGE_NAME, PERSIAN_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }


  public UUID createEnglishToSpanishTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(ENGLISH_LANGUAGE_NAME, SPANISH_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }


  public void initializeEnglishTable() {
    EnglishVocabularyBank englishVocab = new EnglishVocabularyBank();

    String[][] englishTextsArray = englishVocab.getTextsArray();
    for (String[] aTextsArray : englishTextsArray) {
      createEnglishWord(aTextsArray[0], aTextsArray[1], aTextsArray[2], aTextsArray[3]);
    }
  }


  public void createWord(String wordText, String wordVerbal, String persianTranslation, String englishTranslation) {
    SpanishWord espanishWord = new SpanishWord(wordText);
    UUID persianTranslationId = createSpanishToPersianTranslation(espanishWord.getId(), persianTranslation);
    UUID englishTranslationId = createSpanishToEnglishTranslation(espanishWord.getId(), englishTranslation);
    espanishWord.setVerbal(wordVerbal);
    espanishWord.setPersianTranslationId(persianTranslationId);
    espanishWord.setEnglishTranslationId(englishTranslationId);
    addSpanishWord(espanishWord);
  }


  public UUID createSpanishToPersianTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(SPANISH_LANGUAGE_NAME, PERSIAN_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }


  public UUID createSpanishToEnglishTranslation(UUID wordId, String translationText) {
    Translation translation = new Translation(SPANISH_LANGUAGE_NAME, ENGLISH_LANGUAGE_NAME, translationText);
    translation.setWordId(wordId);
    addTranslation(translation);
    return translation.getTranslationId();
  }


  public void initializeSpanishTable() {
    SpanishVocabularyBank spanishVocab = new SpanishVocabularyBank();

    String[][] spanishTextsArray = spanishVocab.getTextsArray();
    for (String[] aTextsArray : spanishTextsArray) {
      createWord(aTextsArray[0], aTextsArray[1], aTextsArray[2], aTextsArray[3]);
    }
  }


}