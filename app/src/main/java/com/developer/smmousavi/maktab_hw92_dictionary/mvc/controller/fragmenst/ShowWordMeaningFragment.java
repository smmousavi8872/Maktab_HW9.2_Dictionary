package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.smmousavi.maktab_hw92_dictionary.R;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.Repository;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.EnglishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.PersianWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.SpanishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Translation;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowWordMeaningFragment extends Fragment {

  public static final String FRAGMENT_TAG = "show_word_meaning_fragment_tag";
  public static final String WORD_ID_ARG = "word_id_args";
  public static final String TRANSLATION_ID_ARG = "translation_id_args";
  public static final String FROM_ARGS = "from_args";
  public static final String TO_ARGS = "to_args";


  private LinearLayout wordTextLayout;
  private TextView showWordTxt;
  private TextView phonoticTxt;
  private TextView verbalTxt;
  private LinearLayout definitionLayout;
  private TextView definitionTextTxt;
  private TextView definitionNumberTxt;

  private UUID wordId;
  private Repository repository;
  private String fromLanguage;
  private String toLanguage;
  private UUID translationId;


  public ShowWordMeaningFragment() {
    // Required empty public constructor
  }

  public static ShowWordMeaningFragment newInstance(UUID wordId, UUID translationId, String from, String to) {

    Bundle args = new Bundle();
    args.putSerializable(WORD_ID_ARG, wordId);
    args.putSerializable(TRANSLATION_ID_ARG, translationId);
    args.putString(FROM_ARGS, from);
    args.putString(TO_ARGS, to);

    ShowWordMeaningFragment fragment = new ShowWordMeaningFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repository = Repository.getInstance(getActivity());
    Bundle args = getArguments();
    wordId = (UUID) args.getSerializable(WORD_ID_ARG);
    translationId = (UUID) args.getSerializable(TRANSLATION_ID_ARG);
    fromLanguage = args.getString(FROM_ARGS);
    toLanguage = args.getString(TO_ARGS);

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_show_word_meaning, container, false);
    findViews(view);

    //copied from string resources
    final String persian = "Persian";
    final String english = "English";
    final String spanish = "Spanish";

    switch (fromLanguage) {
      case persian:
        persianWordSettings();
        PersianWord persianWord = repository.getPersianWord(wordId);
        showWordTxt.setText(persianWord.getText());
        verbalTxt.setText(persianWord.getVerbal());
        switch (toLanguage) {
          case persian:
            definitionLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            definitionTextTxt.setText(persianWord.getText());
            break;
          case english:
            Translation englishTranslation = repository.getTranslation(persianWord.getEnglishTranslationId());
            definitionTextTxt.setText(englishTranslation.getTranslationText());
            break;
          case spanish:
            Translation spanishTranslation = repository.getTranslation(persianWord.getSpanishTranslationId());
            definitionTextTxt.setText(spanishTranslation.getTranslationText());
        }
        break;
      case english:
        EnglishWord englishWord = repository.getEnglishWord(wordId);
        showWordTxt.setText(englishWord.getText());
        verbalTxt.setText(englishWord.getVerbal());
        switch (toLanguage) {
          case persian:
            definitionLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            Translation persianTranslation = repository.getTranslation(englishWord.getPersianTranslationId());
            definitionTextTxt.setText(persianTranslation.getTranslationText());
            break;
          case english:
            definitionTextTxt.setText(englishWord.getText());
            break;
          case spanish:
            Translation spanishTranslation = repository.getTranslation(englishWord.getSpanishTranslationId());
            definitionTextTxt.setText(spanishTranslation.getTranslationText());
        }
        break;
      case spanish:
        SpanishWord spanishWord = repository.getSpanishWord(wordId);
        showWordTxt.setText(spanishWord.getText());
        verbalTxt.setText(spanishWord.getVerbal());
        switch (toLanguage) {
          case persian:
            definitionLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            Translation persianTranslation = repository.getTranslation(spanishWord.getPersianTranslationId());
            definitionTextTxt.setText(persianTranslation.getTranslationText());
            break;
          case english:
            Translation englishTranslation = repository.getTranslation(spanishWord.getEnglishTranslationId());
            definitionTextTxt.setText(englishTranslation.getTranslationText());
            break;
          case spanish:
            definitionTextTxt.setText(spanishWord.getText());
        }
    }
    return view;
  }


  private void persianWordSettings() {
    wordTextLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    verbalTxt.setTextDirection(View.TEXT_DIRECTION_RTL);
    phonoticTxt.setVisibility(View.GONE);
  } // end of persianWordSettings()


  private void findViews(View view) {
    wordTextLayout = view.findViewById(R.id.word_text_layout);
    showWordTxt = view.findViewById(R.id.txt_word);
    phonoticTxt = view.findViewById(R.id.txt_phonotics);
    verbalTxt = view.findViewById(R.id.txt_verbal);
    definitionLayout = view.findViewById(R.id.definition_layout);
    definitionNumberTxt = view.findViewById(R.id.txt_definition_number);
    definitionTextTxt = view.findViewById(R.id.txt_definition);
  }// end of findViews()


}
