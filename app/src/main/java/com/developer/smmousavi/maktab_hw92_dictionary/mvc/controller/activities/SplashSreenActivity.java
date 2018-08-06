package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities;

import android.support.v4.app.Fragment;

import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst.SplashScreenFragment;

public class SplashSreenActivity extends _SingleFragmentActivity {

  @Override
  public Fragment createFragment() {
    return SplashScreenFragment.newInstance();
  }

  @Override
  public String getTag() {
    return SplashScreenFragment.FRAGMENT_TAG;
  }

}
