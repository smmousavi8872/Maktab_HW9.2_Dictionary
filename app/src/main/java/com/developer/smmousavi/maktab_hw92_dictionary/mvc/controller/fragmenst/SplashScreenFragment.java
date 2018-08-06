package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.maktab_hw92_dictionary.R;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.WordShowActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {

  public static final String FRAGMENT_TAG = "splash_screen_fragment_tag";
  private final int SPLASH_DISPLAY_LENGTH = 2000;

  public SplashScreenFragment() {
    // Required empty public constructor
  }

  public static SplashScreenFragment newInstance() {

    Bundle args = new Bundle();

    SplashScreenFragment fragment = new SplashScreenFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
    final Activity activity = getActivity();

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent mainIntent = WordShowActivity.newIntent(activity);
        startActivity(mainIntent);
        getActivity().finish();
      }
    }, SPLASH_DISPLAY_LENGTH);

    return view;
  }
}

