package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.PersianWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.PersianWord;

import java.util.UUID;

public class PersianCursorWrapper extends CursorWrapper {

  public PersianCursorWrapper(Cursor cursor) {
    super(cursor);
  }


  public PersianWord getPersianWord() {
    String wordIdStr = getString(getColumnIndex(PersianWordTable.Cols.UUID));
    String wordText = getString(getColumnIndex(PersianWordTable.Cols.WORD_TEXT));
    String initail = getString(getColumnIndex(PersianWordTable.Cols.INITIAL_CHAR));
    String englishTranslationIdStr = getString(getColumnIndex(PersianWordTable.Cols.ENGLISH_TRANSLATION_UUID));
    String spanishTranslationIdStr = getString(getColumnIndex(PersianWordTable.Cols.SPANISH_TRANSLATION_UUID));
    String verbal = getString(getColumnIndex(PersianWordTable.Cols.VERBAL));
    long searchTimes = getLong(getColumnIndex(PersianWordTable.Cols.SEARCH_TIMES));

    PersianWord persianWord = new PersianWord(wordText, UUID.fromString(wordIdStr));
    persianWord.setInitial(initail);
    persianWord.setEnglishTranslationId(UUID.fromString(englishTranslationIdStr));
    persianWord.setSpanishTranslationId(UUID.fromString(spanishTranslationIdStr));
    persianWord.setVerbal(verbal);
    persianWord.setSearchedTimes(searchTimes);

    return persianWord;
  }
}
