package com.developer.smmousavi.maktab_hw92_dictionary.mvc.model;

import java.util.UUID;

public class PersianWord extends Word {

  private UUID englishTranslationId;
  private UUID spanishTranslationId;

  public PersianWord(String wordText) {
    super(wordText);

  }


  public PersianWord(String wordText, UUID id) {
    super(wordText, id);
  }


  public UUID getEnglishTranslationId() {
    return englishTranslationId;
  }


  public void setEnglishTranslationId(UUID englishTranslationId) {
    this.englishTranslationId = englishTranslationId;
  }


  public UUID getSpanishTranslationId() {
    return spanishTranslationId;
  }


  public void setSpanishTranslationId(UUID spanishTranslationId) {
    this.spanishTranslationId = spanishTranslationId;
  }
}
