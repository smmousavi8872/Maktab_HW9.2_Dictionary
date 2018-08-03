package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.EnglishWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.EnglishWord;

import java.util.UUID;

public class EnglishCursorWrapper extends CursorWrapper {
  public EnglishCursorWrapper(Cursor cursor) {
    super(cursor);
  }


  public EnglishWord getEnglishWord() {
    String wordIdStr = getString(getColumnIndex(EnglishWordTable.Cols.UUID));
    String wordText = getString(getColumnIndex(EnglishWordTable.Cols.WORD_TEXT));
    String initail = getString(getColumnIndex(EnglishWordTable.Cols.INITIAL_CHAR));
    String persianTranslationIdStr = getString(getColumnIndex(EnglishWordTable.Cols.PERSIAN_TRANSLATION_UUID));
    String spanishTranslationIdStr = getString(getColumnIndex(EnglishWordTable.Cols.SPANISH_TRANSLATION_UUID));
    String verbal = getString(getColumnIndex(EnglishWordTable.Cols.VERBAL));
    long searchTimes = getLong(getColumnIndex(EnglishWordTable.Cols.SEARCH_TIMES));

    EnglishWord englishWord = new EnglishWord(wordText, UUID.fromString(wordIdStr));
    englishWord.setInitial(initail);
    englishWord.setPersianTranslationId(UUID.fromString(persianTranslationIdStr));
    englishWord.setSpanishTranslationId(UUID.fromString(spanishTranslationIdStr));
    englishWord.setVerbal(verbal);
    englishWord.setSearchedTimes(searchTimes);

    return englishWord;
  }
}
