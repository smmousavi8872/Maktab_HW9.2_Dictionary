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

public class EditWordDialogFragment extends DialogFragment {

  public static final String WORD_ID_ARGS = "word_id_args";
  public static final String FROM_ARGS = "from_args";
  public static final String TO_ARGS = "to_args";

  public static final String PERSIAN_NAME = "Persian";
  public static final String ENGLISH_NAME = "English";
  public static final String SPANISH_NAME = "Spanish";

  private RadioGroup radioGroupFrom;
  private RadioGroup radioGroupTo;
  private TextView wordTextTxt;
  private EditText wordTextEdt;
  private EditText wordPhonetic;
  private EditText wordVerbalEdt;
  private TextView firstTranslationTxt;
  private EditText firstTranslationEdt;
  private EditText secondTranslationEdt;
  private TextView secondTranslationTxt;

  private String fromLanguage;
  private String toLanguage;
  private UUID currentWordId;

  private Repository repository;

  public EditWordDialogFragment() {
    // Required empty public constructor
  }


  public static EditWordDialogFragment newInstance(UUID wordId, String from, String to) {

    Bundle args = new Bundle();
    args.putSerializable(WORD_ID_ARGS, wordId);
    args.putString(FROM_ARGS, from);
    args.putString(TO_ARGS, to);

    EditWordDialogFragment fragment = new EditWordDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View view = inflater.inflate(R.layout.fragment_edit_word_dialog, null, false);

    findViews(view);

    Bundle args = getArguments();

    currentWordId = (UUID) args.getSerializable(WORD_ID_ARGS);
    fromLanguage = args.getString(FROM_ARGS);
    toLanguage = args.getString(TO_ARGS);
    repository = Repository.getInstance(getActivity());

    radioGroupTo.setEnabled(false);
    radioGroupFrom.setEnabled(false);

    switch (fromLanguage) {
      case PERSIAN_NAME:
        PersianWord persianWord = repository.getPersianWord(currentWordId);
        fillForPersianWord(persianWord);
        break;
      case ENGLISH_NAME:
        EnglishWord englishWord = repository.getEnglishWord(currentWordId);
        fillForEnglishWord(englishWord);
        break;
      case SPANISH_NAME:
        SpanishWord spanishWord = repository.getSpanishWord(currentWordId);
        fillForSpanishWord(spanishWord);
        break;
    }

    AlertDialog dialog = new AlertDialog.Builder(getActivity())
      .setView(view)
      .setNegativeButton(android.R.string.cancel, null)
      .setPositiveButton("update", null)
      .create();


    return dialog;
  }


  private void setTranslationTitles(int checkedId) {
    switch (checkedId) {
      case R.id.add_in_english:
        firstTranslationTxt.setText("Persian Translation: ");
        secondTranslationTxt.setText("Spanish Translation: ");
        break;
      case R.id.add_in_persian:
        firstTranslationTxt.setText("English Translation: ");
        secondTranslationTxt.setText("Spanish Translation: ");
        break;
      case R.id.add_in_spanish:
        firstTranslationTxt.setText("Persian Translation: ");
        secondTranslationTxt.setText("English Translation: ");
        break;
    }
  }


  private void fillForPersianWord(PersianWord persianWord) {
    radioGroupFrom.check(R.id.edit_dialog_from_persian_radio);

    switch (toLanguage) {
      case PERSIAN_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_persian_radio);
        break;
      case ENGLISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_english_radio);
        break;
      case SPANISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_spanish_radio);
    }
    wordTextEdt.setText(persianWord.getText());
    wordVerbalEdt.setText(persianWord.getVerbal());
    firstTranslationTxt.setText("English Translation: ");
    secondTranslationTxt.setText("Spanish Translatin: ");
    Translation englishTranslation = repository.getTranslation(persianWord.getEnglishTranslationId());
    Translation spanishTranslation = repository.getTranslation(persianWord.getSpanishTranslationId());
    firstTranslationEdt.setText(englishTranslation.getTranslationText());
    secondTranslationEdt.setText(spanishTranslation.getTranslationText());
  }


  private void fillForEnglishWord(EnglishWord englishWord) {
    radioGroupFrom.check(R.id.edit_dialog_from_english_radio);

    switch (toLanguage) {
      case PERSIAN_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_persian_radio);
        break;
      case ENGLISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_english_radio);
        break;
      case SPANISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_spanish_radio);
    }
    wordTextEdt.setText(englishWord.getText());
    wordVerbalEdt.setText(englishWord.getVerbal());
    firstTranslationTxt.setText("Persian Translation: ");
    secondTranslationTxt.setText("Spanish Translatin: ");
    Translation persianTranslation = repository.getTranslation(englishWord.getPersianTranslationId());
    Translation spanishTranslation = repository.getTranslation(englishWord.getSpanishTranslationId());
    firstTranslationEdt.setText(persianTranslation.getTranslationText());
    secondTranslationEdt.setText(spanishTranslation.getTranslationText());
  }


  private void fillForSpanishWord(SpanishWord spanishWord) {
    radioGroupFrom.check(R.id.edit_dialog_from_spanish_radio);

    switch (toLanguage) {
      case PERSIAN_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_persian_radio);
        break;
      case ENGLISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_english_radio);
        break;
      case SPANISH_NAME:
        radioGroupTo.check(R.id.edit_dialog_to_spanish_radio);
    }
    wordTextEdt.setText(spanishWord.getText());
    wordVerbalEdt.setText(spanishWord.getVerbal());
    firstTranslationTxt.setText("Persian Translation: ");
    secondTranslationTxt.setText("Englsih Translatin: ");
    Translation persianTranslation = repository.getTranslation(spanishWord.getPersianTranslationId());
    Translation englishTranslation = repository.getTranslation(spanishWord.getEnglishTranslationId());
    firstTranslationEdt.setText(persianTranslation.getTranslationText());
    secondTranslationEdt.setText(englishTranslation.getTranslationText());
  }


  private void findViews(View view) {
    radioGroupFrom = view.findViewById(R.id.edit_dialog_radio_group_from);
    radioGroupTo = view.findViewById(R.id.edit_dialog_radio_group_to);
    wordTextEdt = view.findViewById(R.id.edit_dialog_new_word_edt);
    wordTextTxt = view.findViewById(R.id.edit_dialog_new_word_txt);
    wordPhonetic = view.findViewById(R.id.edit_dialog_phonetic_edt);
    wordVerbalEdt = view.findViewById(R.id.edit_dialog_part_of_speach_edt);
    firstTranslationTxt = view.findViewById(R.id.edit_dialog_first_translation_txt);
    secondTranslationTxt = view.findViewById(R.id.edit_dialog_second_translation_txt);
    firstTranslationEdt = view.findViewById(R.id.edit_dialog_first_translation_edt);
    secondTranslationEdt = view.findViewById(R.id.edit_dialog_second_translation_edt);
  }


  @Override
  public void onResume() {
    super.onResume();
    AlertDialog dialog = (AlertDialog) getDialog();
    Button okBtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    okBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switch (fromLanguage) {
          case PERSIAN_NAME:
            if (!wordTextEdt.getText().toString().equals("")) {
              updatePersianWord();

            } else
              wordTextTxt.setTextColor(Color.RED);

            break;
          case ENGLISH_NAME:
            if (!wordTextEdt.getText().toString().equals("")) {
              updateEnglishWord();

            } else
              wordTextTxt.setTextColor(Color.RED);

            break;
          case SPANISH_NAME:
            if (!wordTextEdt.getText().toString().equals("")) {
              updateSpanishWord();

            } else
              wordTextTxt.setTextColor(Color.RED);
        }
        dismiss();
      }
    });
  }


  private void updateEnglishWord() {
    EnglishWord lastWord = repository.getEnglishWord(currentWordId);
    EnglishWord englishWord = new EnglishWord(wordTextEdt.getText().toString(), currentWordId);
    Translation persianTranslation = new Translation(lastWord.getPersianTranslationId(), ENGLISH_NAME, PERSIAN_NAME, firstTranslationEdt.getText().toString());
    Translation spanishTranslation = new Translation(lastWord.getSpanishTranslationId(), ENGLISH_NAME, SPANISH_NAME, secondTranslationEdt.getText().toString());
    englishWord.setVerbal(wordVerbalEdt.getText().toString());
    englishWord.setPersianTranslationId(persianTranslation.getTranslationId());
    englishWord.setSpanishTranslationId(spanishTranslation.getTranslationId());
    persianTranslation.setWordId(englishWord.getId());
    spanishTranslation.setWordId(englishWord.getId());
    repository.updateEnglishWord(englishWord);
    repository.updateTranslation(persianTranslation);
    repository.updateTranslation(spanishTranslation);
  }


  private void updatePersianWord() {
    PersianWord lastWord = repository.getPersianWord(currentWordId);
    PersianWord persianWord = new PersianWord(wordTextEdt.getText().toString(), currentWordId);
    Translation englishTranslation = new Translation(lastWord.getEnglishTranslationId(), PERSIAN_NAME, ENGLISH_NAME, firstTranslationEdt.getText().toString());
    Translation spanishTranslation = new Translation(lastWord.getSpanishTranslationId(), PERSIAN_NAME, SPANISH_NAME, secondTranslationEdt.getText().toString());
    persianWord.setVerbal(wordVerbalEdt.getText().toString());
    persianWord.setEnglishTranslationId(englishTranslation.getTranslationId());
    persianWord.setSpanishTranslationId(spanishTranslation.getTranslationId());
    englishTranslation.setWordId(persianWord.getId());
    spanishTranslation.setWordId(persianWord.getId());
    repository.updatePersainWord(persianWord);
    repository.updateTranslation(englishTranslation);
    repository.updateTranslation(spanishTranslation);

  }

  private void updateSpanishWord() {
    SpanishWord lastWord = repository.getSpanishWord(currentWordId);
    SpanishWord spanishWord = new SpanishWord(wordTextEdt.getText().toString(), currentWordId);
    Translation persianTranslation = new Translation(lastWord.getPersianTranslationId(), SPANISH_NAME, PERSIAN_NAME, firstTranslationEdt.getText().toString());
    Translation englishTranslation = new Translation(lastWord.getEnglishTranslationId(), SPANISH_NAME, ENGLISH_NAME, secondTranslationEdt.getText().toString());
    spanishWord.setVerbal(wordVerbalEdt.getText().toString());
    spanishWord.setPersianTranslationId(persianTranslation.getTranslationId());
    spanishWord.setEnglishTranslationId(englishTranslation.getTranslationId());
    persianTranslation.setWordId(spanishWord.getId());
    englishTranslation.setWordId(spanishWord.getId());
    repository.updateSpanishWord(spanishWord);
    repository.updateTranslation(persianTranslation);
    repository.updateTranslation(englishTranslation);

  }


}

