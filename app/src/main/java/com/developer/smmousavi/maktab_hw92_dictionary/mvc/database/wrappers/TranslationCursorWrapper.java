package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.TranslationTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Translation;

import java.util.UUID;

public class TranslationCursorWrapper extends CursorWrapper {
  public TranslationCursorWrapper(Cursor cursor) {
    super(cursor);
  }


  public Translation getTranslation() {
    String translationIdStr = getString(getColumnIndex(TranslationTable.Cols.TRANSLATION_ID));
    String wordIdStr = getString(getColumnIndex(TranslationTable.Cols.WORD_ID));
    String from = getString(getColumnIndex(TranslationTable.Cols.FROM));
    String to = getString(getColumnIndex(TranslationTable.Cols.TO));
    String translationText = getString(getColumnIndex(TranslationTable.Cols.TRANSLATION_TEXT));

    Translation translation = new Translation(UUID.fromString(translationIdStr), from, to, translationText);
    translation.setWordId(UUID.fromString(wordIdStr));

    return translation;
  }

}
