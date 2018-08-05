package com.developer.smmousavi.maktab_hw92_dictionary.mvc.model;

import java.util.UUID;

public class Translation {

  private UUID translationId;
  private String from;
  private String to;
  private UUID wordId;
  private String translationText;

  public Translation(String from, String to, String translationText) {
    translationId = UUID.randomUUID();
    this.from = from;
    this.to = to;
    this.translationText = translationText;
  }


  public Translation(UUID translationId, String from, String to, String translationText) {
    this.translationId = translationId;
    this.from = from;
    this.to = to;
    this.translationText = translationText;
  }


  public UUID getTranslationId() {
    return translationId;
  }


  public String getFrom() {
    return from;
  }


  public String getTo() {
    return to;
  }


  public String getTranslationText() {
    return translationText;
  }


  public UUID getWordId() {
    return wordId;
  }


  public void setWordId(UUID wordId) {
    this.wordId = wordId;
  }

}

