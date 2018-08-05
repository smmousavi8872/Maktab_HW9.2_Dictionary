package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.smmousavi.maktab_hw92_dictionary.R;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.Repository;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.EnglishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.PersianWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.SpanishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Translation;

/**
 * A simple {@link Fragment} subclass.
 */

enum AddIn {
  ENGLISH,
  PERSIAN,
  SPANISH
}


public class AddWordDialogFragment extends DialogFragment {

  private RadioGroup radioGroupAddIn;
  private TextView newWordTxt;
  private EditText newWordEdt;
  private EditText phoneticEdt;
  private EditText partOfSpeachEdt;
  private TextView firstTranslationTxt;
  private TextView secondTranslationTxt;
  private EditText firstTranslationEdt;
  private EditText secondTranslationEdt;

  private String newWordText;
  private String phoneticText;
  private String partOfSpeachText;
  private String firstTranslationText;
  private String secondTranslationText;
  private Repository repository;

  private AddIn addIn = AddIn.ENGLISH;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    repository = Repository.getInstance(getActivity());

    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View view = inflater.inflate(R.layout.fragment_add_word_dialog, null, false);

    findViews(view);

    radioGroupAddIn.check(R.id.add_in_english);

    radioGroupAddIn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        setTranslationTitles(checkedId);
      }
    });

    AlertDialog dialog = new AlertDialog.Builder(getActivity())
      .setView(view)
      .setPositiveButton(android.R.string.ok, null)
      .setNegativeButton(android.R.string.cancel, null)
      .create();

    return dialog;


  }


  @Override
  public void onResume() {
    super.onResume();

    AlertDialog alertDialog = (AlertDialog) getDialog();
    Button okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        newWordText = newWordEdt.getText().toString();
        phoneticText = phoneticEdt.getText().toString();
        partOfSpeachText = partOfSpeachEdt.getText().toString();
        firstTranslationText = firstTranslationEdt.getText().toString();
        secondTranslationText = secondTranslationEdt.getText().toString();

        if (!newWordText.equals("")) {
          createWord();
          Toast.makeText(getActivity(), "New word added", Toast.LENGTH_SHORT).show();
          dismiss();

        } else {
          newWordTxt.setTextColor(Color.RED);
        }
      }
    });
  }

  private void createWord() {
    final String ENGLISH_NANE = getString(R.string.english_language_name);
    final String PERSIAN_NAME = getString(R.string.persian_language_name);
    final String SPANISH_NAME = getString(R.string.spanish_language_name);
    switch (addIn) {
      case ENGLISH:
        EnglishWord englishWord = new EnglishWord(newWordText);
        Translation engToPerTranslation = new Translation(ENGLISH_NANE, PERSIAN_NAME, firstTranslationText);
        Translation engToSpaTranslation = new Translation(ENGLISH_NANE, SPANISH_NAME, secondTranslationText);
        englishWord.setVerbal(partOfSpeachText);
        englishWord.setPersianTranslationId(engToPerTranslation.getTranslationId());
        englishWord.setSpanishTranslationId(engToSpaTranslation.getTranslationId());
        engToPerTranslation.setWordId(englishWord.getId());
        engToSpaTranslation.setWordId(englishWord.getId());
        repository.addTranslation(engToPerTranslation);
        repository.addTranslation(engToSpaTranslation);
        repository.addEnglishWord(englishWord);
        break;
      case PERSIAN:
        PersianWord persianWord = new PersianWord(newWordText);
        Translation perToEnglTranslation = new Translation(PERSIAN_NAME, ENGLISH_NANE, firstTranslationText);
        Translation perToSpaTranslation = new Translation(PERSIAN_NAME, SPANISH_NAME, secondTranslationText);
        persianWord.setVerbal(partOfSpeachText);
        persianWord.setEnglishTranslationId(perToEnglTranslation.getTranslationId());
        persianWord.setSpanishTranslationId(perToSpaTranslation.getTranslationId());
        perToEnglTranslation.setWordId(persianWord.getId());
        perToSpaTranslation.setWordId(persianWord.getId());
        repository.addTranslation(perToEnglTranslation);
        repository.addTranslation(perToSpaTranslation);
        repository.addPersianWord(persianWord);
        break;
      case SPANISH:
        SpanishWord spanishWord = new SpanishWord(newWordText);
        Translation spaToPerTranslation = new Translation(SPANISH_NAME, PERSIAN_NAME, firstTranslationText);
        Translation spaToEngTranslation = new Translation(SPANISH_NAME, ENGLISH_NANE, secondTranslationText);
        spanishWord.setVerbal(partOfSpeachText);
        spanishWord.setPersianTranslationId(spaToPerTranslation.getTranslationId());
        spanishWord.setEnglishTranslationId(spaToEngTranslation.getTranslationId());
        spaToPerTranslation.setWordId(spanishWord.getId());
        spaToEngTranslation.setWordId(spanishWord.getId());
        repository.addTranslation(spaToPerTranslation);
        repository.addTranslation(spaToEngTranslation);
        repository.addSpanishWord(spanishWord);
    }
  }


  private void setTranslationTitles(int checkedId) {
    switch (checkedId) {
      case R.id.add_in_english:
        firstTranslationTxt.setText("Persian Translation: ");
        secondTranslationTxt.setText("Spanish Translation: ");
        addIn = AddIn.ENGLISH;
        break;
      case R.id.add_in_persian:
        firstTranslationTxt.setText("English Translation: ");
        secondTranslationTxt.setText("Spanish Translation: ");
        addIn = AddIn.PERSIAN;
        break;
      case R.id.add_in_spanish:
        firstTranslationTxt.setText("Persian Translation: ");
        secondTranslationTxt.setText("English Translation: ");
        addIn = AddIn.SPANISH;
        break;
    }
  }

  private void findViews(View view) {
    radioGroupAddIn = view.findViewById(R.id.radio_group_add_in);
    newWordTxt = view.findViewById(R.id.new_word_txt);
    newWordEdt = view.findViewById(R.id.new_word_edt);
    phoneticEdt = view.findViewById(R.id.phonetic_edt);
    partOfSpeachEdt = view.findViewById(R.id.part_of_speach_edt);
    firstTranslationTxt = view.findViewById(R.id.first_translation_txt);
    firstTranslationEdt = view.findViewById(R.id.first_translation_edt);
    secondTranslationTxt = view.findViewById(R.id.second_translation_txt);
    secondTranslationEdt = view.findViewById(R.id.second_translation_edt);
  }
}