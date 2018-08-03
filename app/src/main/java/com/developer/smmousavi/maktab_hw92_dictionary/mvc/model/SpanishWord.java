package com.developer.smmousavi.maktab_hw92_dictionary.mvc.model;

import java.util.UUID;

public class SpanishWord extends Word {

  private UUID persianTranslationId;
  private UUID englishTranslationId;

  public SpanishWord(String wordText) {
    super(wordText);
  }

  public SpanishWord(String wordText, UUID id) {
    super(wordText, id);
  }

  public UUID getPersianTranslationId() {
    return persianTranslationId;
  }

  public void setPersianTranslationId(UUID persianTranslationId) {
    this.persianTranslationId = persianTranslationId;
  }

  public UUID getEnglishTranslationId() {
    return englishTranslationId;
  }

  public void setEnglishTranslationId(UUID englishTranslationId) {
    this.englishTranslationId = englishTranslationId;
  }
}
