package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.SpanishWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.SpanishWord;

import java.util.UUID;

public class SpanishCursorWrapper extends CursorWrapper {
  public SpanishCursorWrapper(Cursor cursor) {
    super(cursor);
  }

  public SpanishWord getSpanishWord() {
    String wordIdStr = getString(getColumnIndex(SpanishWordTable.Cols.UUID));
    String wordText = getString(getColumnIndex(SpanishWordTable.Cols.WORD_TEXT));
    String initail = getString(getColumnIndex(SpanishWordTable.Cols.INITIAL_CHAR));
    String persianTranslationIdStr = getString(getColumnIndex(SpanishWordTable.Cols.PERSIAN_TRANSLATION_UUID));
    String englishTranslationIdStr = getString(getColumnIndex(SpanishWordTable.Cols.ENGLISH_TRANSLATION_UUID));
    String verbal = getString(getColumnIndex(SpanishWordTable.Cols.VERBAL));
    long searchTimes = getLong(getColumnIndex(SpanishWordTable.Cols.SEARCH_TIMES));

    SpanishWord spanishWord = new SpanishWord(wordText, UUID.fromString(wordIdStr));
    spanishWord.setInitial(initail);
    spanishWord.setPersianTranslationId(UUID.fromString(persianTranslationIdStr));
    spanishWord.setEnglishTranslationId(UUID.fromString(englishTranslationIdStr));
    spanishWord.setVerbal(verbal);
    spanishWord.setSearchedTimes(searchTimes);

    return spanishWord;
  }
}
