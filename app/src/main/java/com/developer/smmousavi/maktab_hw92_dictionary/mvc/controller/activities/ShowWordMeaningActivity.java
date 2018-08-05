package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst.ShowWordMeaningFragment;

import java.util.UUID;

public class ShowWordMeaningActivity extends _SingleFragmentActivity {

  public static final String WORD_ID_EXTRAS = "com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.word_id_extras";
  public static final String TRASNLATION_ID_EXTRAS = "com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.is_persian_word_extras";
  public static final String FROM_EXTRAS = "com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.from";
  public static final String TO_EXTRAS = "com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.to";


  public static Intent newIntet(Context orgin, UUID wordId, UUID translationId, String from, String to) {
    Intent intent = new Intent(orgin, ShowWordMeaningActivity.class);
    intent.putExtra(WORD_ID_EXTRAS, wordId);
    intent.putExtra(TRASNLATION_ID_EXTRAS, translationId);
    intent.putExtra(FROM_EXTRAS, from);
    intent.putExtra(TO_EXTRAS, to);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public Fragment createFragment() {
    Bundle extras = getIntent().getExtras();
    UUID wordId = (UUID) extras.getSerializable(WORD_ID_EXTRAS);
    UUID traslationId = (UUID) extras.getSerializable(TRASNLATION_ID_EXTRAS);
    String from = extras.getString(FROM_EXTRAS);
    String to = extras.getString(TO_EXTRAS);
    return ShowWordMeaningFragment.newInstance(wordId, traslationId, from, to);
  }


  @Override
  public String getTag() {
    return ShowWordMeaningFragment.FRAGMENT_TAG;
  }
}
