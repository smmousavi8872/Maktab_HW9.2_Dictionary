package com.developer.smmousavi.maktab_hw92_dictionary.mvc.model;

import java.util.UUID;

public class EnglishWord extends Word {

  private UUID persianTranslationId;
  private UUID spanishTranslationId;


  public EnglishWord(String wordText) {
    super(wordText);
  }

  public EnglishWord(String wordText, UUID id) {
    super(wordText, id);
  }


  public UUID getPersianTranslationId() {
    return persianTranslationId;
  }

  public void setPersianTranslationId(UUID persianTranslationId) {
    this.persianTranslationId = persianTranslationId;
  }

  public UUID getSpanishTranslationId() {
    return spanishTranslationId;
  }

  public void setSpanishTranslationId(UUID spanishTranslationId) {
    this.spanishTranslationId = spanishTranslationId;
  }

}
