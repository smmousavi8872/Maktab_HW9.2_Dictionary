package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst.WordsListShowFragment;

public class WordShowActivity extends _SingleFragmentActivity {

  private WordsListShowFragment guestFragment;


  public static Intent newIntent(Context orgin) {
    return new Intent(orgin, WordShowActivity.class);
  }


  @Override
  public Fragment createFragment() {
    guestFragment = WordsListShowFragment.newInstance();
    return guestFragment;
  }

  @Override
  public String getTag() {
    return WordsListShowFragment.FRAGMENT_TAG;
  }
}
