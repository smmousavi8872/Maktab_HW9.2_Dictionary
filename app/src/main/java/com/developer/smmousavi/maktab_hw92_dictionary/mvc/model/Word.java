package com.developer.smmousavi.maktab_hw92_dictionary.mvc.model;

import java.util.UUID;

public class Word {

  private UUID id;
  private String text;
  private String verbal;
  private String initial;
  private long SearchedTimes;


  public Word(String text) {
    id = UUID.randomUUID();
    this.text = text;
  }

  public Word(String text, UUID id) {
    this.id = id;
    this.text = text;
  }


  public UUID getId() {
    return id;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  public String getVerbal() {
    return verbal;
  }


  public void setVerbal(String verbal) {
    this.verbal = verbal;
  }


  public long getSearchedTimes() {
    return SearchedTimes;
  }


  public String getInitial() {
    return initial;
  }

  public void setInitial(String initial) {
    this.initial = initial;
  }


  public void setSearchedTimes(long searchedTimes) {
    SearchedTimes = searchedTimes;
  }


  public String getTextSubstring(int endIndex) {
    return text.substring(0, endIndex);
  }
}
