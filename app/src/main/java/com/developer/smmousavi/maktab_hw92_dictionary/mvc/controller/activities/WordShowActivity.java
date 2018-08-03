package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities;

import android.support.v4.app.Fragment;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst.WordShowFragment;

public class WordShowActivity extends _SingleFragmentActivity {

  private WordShowFragment guestFragment;

  @Override
  public Fragment createFragment() {
    guestFragment = WordShowFragment.newInstance();
    return guestFragment;
  }


  @Override
  public String getTag() {
    return WordShowFragment.FRAGMENT_TAG;
  }
}
