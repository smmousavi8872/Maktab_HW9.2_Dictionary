package com.developer.smmousavi.maktab_hw92_dictionary.mvc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.PersianWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.SpanishWordTable;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.TranslationTable;

import static com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.DBSchema.EnglishWordTable;

public class DatabaseHelper extends SQLiteOpenHelper {

  public static boolean isDatabaseCreated;


  public DatabaseHelper(Context context) {
    super(context.getApplicationContext(), DBSchema.NAME, null, DBSchema.VERSION);
  }

  public static final String CREATE_PERSIAN_WORD_TABLE = "CREATE TABLE " + PersianWordTable.NAME + "("
    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
    + PersianWordTable.Cols.UUID + ", "
    + PersianWordTable.Cols.WORD_TEXT + ", "
    + PersianWordTable.Cols.INITIAL_CHAR + ", "
    + PersianWordTable.Cols.VERBAL + ", "
    + PersianWordTable.Cols.SEARCH_TIMES + ", "
    + PersianWordTable.Cols.ENGLISH_TRANSLATION_UUID + ", "
    + PersianWordTable.Cols.SPANISH_TRANSLATION_UUID
    + ")";


  public static final String CREATE_ENGLISH_WORD_TABLE = "CREATE TABLE " + EnglishWordTable.NAME + "("
    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
    + EnglishWordTable.Cols.UUID + ", "
    + EnglishWordTable.Cols.WORD_TEXT + ", "
    + EnglishWordTable.Cols.INITIAL_CHAR + ", "
    + EnglishWordTable.Cols.VERBAL + ", "
    + EnglishWordTable.Cols.SEARCH_TIMES + ", "
    + EnglishWordTable.Cols.PERSIAN_TRANSLATION_UUID + ", "
    + EnglishWordTable.Cols.SPANISH_TRANSLATION_UUID
    + ")";


  public static final String CREATE_SPANISH_WORD_TABLE = "CREATE TABLE " + SpanishWordTable.NAME + "("
    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
    + SpanishWordTable.Cols.UUID + ", "
    + SpanishWordTable.Cols.WORD_TEXT + ", "
    + SpanishWordTable.Cols.INITIAL_CHAR + ", "
    + SpanishWordTable.Cols.VERBAL + ", "
    + SpanishWordTable.Cols.SEARCH_TIMES + ", "
    + SpanishWordTable.Cols.PERSIAN_TRANSLATION_UUID + ", "
    + SpanishWordTable.Cols.ENGLISH_TRANSLATION_UUID
    + ")";


  public static final String CREATE_TRANSLATION_TABLE = "CREATE TABLE " + TranslationTable.NAME + "("
    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
    + TranslationTable.Cols.FROM + ", "
    + TranslationTable.Cols.TO + ", "
    + TranslationTable.Cols.WORD_ID + ", "
    + TranslationTable.Cols.TRANSLATION_ID + ", "
    + TranslationTable.Cols.TRANSLATION_TEXT
    + ")";


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_PERSIAN_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_SPANISH_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_ENGLISH_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_TRANSLATION_TABLE);
    isDatabaseCreated = true;
  }


  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL(CREATE_PERSIAN_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_SPANISH_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_ENGLISH_WORD_TABLE);
    sqLiteDatabase.execSQL(CREATE_TRANSLATION_TABLE);
    onCreate(sqLiteDatabase);
  }

}
