package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
public class ChooseOnLongPressDialogFragment extends DialogFragment {

  //copied from strings resource
  public static final String PERSIAN_NAME = "Persian";
  public static final String ENGLISH_NANE = "English";
  public static final String SPANISH_NAME = "Spanish";

  public static final String EDIT_WORD_DIALOG_TAG = "edit_word_dialog_tag";

  public static final String WORD_ID_ARGS = "word_id_args";
  public static final String FROM_ARGS = "from_args";
  public static final String TO_ARGS = "to_args";

  private Button deleteWordBtn;
  private Button editWordBtn;
  private Button shareWordBtn;

  private Repository repository = Repository.getInstance(getActivity());

  private UUID wordId;
  private String fromLanguage;
  private String toLanguage;

  public ChooseOnLongPressDialogFragment() {
    // Required empty public constructor
  }

  public static ChooseOnLongPressDialogFragment newInstance(UUID wordId, String from, String to) {

    Bundle args = new Bundle();
    args.putSerializable(WORD_ID_ARGS, wordId);
    args.putString(FROM_ARGS, from);
    args.putString(TO_ARGS, to);

    ChooseOnLongPressDialogFragment fragment = new ChooseOnLongPressDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View view = inflater.inflate(R.layout.fragment_choose_on_long_press_dialog, null, false);
    deleteWordBtn = view.findViewById(R.id.btn_delete_word);
    editWordBtn = view.findViewById(R.id.btn_edit_word);
    shareWordBtn = view.findViewById(R.id.btn_share_word);

    wordId = (UUID) getArguments().getSerializable(WORD_ID_ARGS);
    fromLanguage = getArguments().getString(FROM_ARGS);
    toLanguage = getArguments().getString(TO_ARGS);

    AlertDialog dialog = new AlertDialog.Builder(getActivity())
      .setNegativeButton(android.R.string.cancel, null)
      .setView(view)
      .create();

    deleteWordBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
        new AlertDialog.Builder(getActivity())
          .setTitle("Are you sure of deleting this word?")
          .setNegativeButton(android.R.string.cancel, null)
          .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              switch (fromLanguage) {
                case ENGLISH_NANE:
                  EnglishWord englishWord = repository.getEnglishWord(wordId);
                  repository.removeEnglishWord(englishWord);
                  break;
                case PERSIAN_NAME:
                  PersianWord persianWord = repository.getPersianWord(wordId);
                  repository.removePersianWord(persianWord);
                  break;
                case SPANISH_NAME:
                  SpanishWord spanishWord = repository.getSpanishWord(wordId);
                  repository.removeSpanishWord(spanishWord);
              }
            }
          }).show();
      }
    });


    editWordBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        EditWordDialogFragment dialog = EditWordDialogFragment.newInstance(wordId, fromLanguage, toLanguage);
        FragmentManager fm = getFragmentManager();
        dialog.show(fm, EDIT_WORD_DIALOG_TAG);
        dismiss();
      }
    });

    shareWordBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getIntentText());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        dismiss();
      }
    });

    return dialog;
  }


  public String getIntentText() {
    String textToShare = "";
    switch (fromLanguage) {
      case ENGLISH_NANE:
        EnglishWord englishWord = repository.getEnglishWord(wordId);
        Translation engToPerTranslation = repository.getTranslation(englishWord.getPersianTranslationId());
        Translation engToSpaTranslation = repository.getTranslation(englishWord.getSpanishTranslationId());
        textToShare = "Hi there,"
          + "\nI just wanted to share this word with you."
          + "\n\"" + englishWord.getText() + "\""
          + "\nPart of speech: " + englishWord.getVerbal()
          + "\nPersian Translation: " + engToPerTranslation.getTranslationText()
          + "\nSpanish Translation: " + engToSpaTranslation.getTranslationText();
        break;
      case PERSIAN_NAME:
        PersianWord persianWord = repository.getPersianWord(wordId);
        Translation perToEngTranslation = repository.getTranslation(persianWord.getEnglishTranslationId());
        Translation perToSpaTranslation = repository.getTranslation(persianWord.getSpanishTranslationId());
        textToShare = "سلام،"
          + "\nخواستم این کلمه را با شما به اشتراک بگذارم."
          + "\n\"" + persianWord.getText() + "\""
          + "\nنقش دستوری: " + persianWord.getVerbal()
          + "\nترجمه انگلیسی: " + perToEngTranslation.getTranslationText()
          + "\nترجمه اسپانیایی: " + perToSpaTranslation.getTranslationText();
        break;
      case SPANISH_NAME:
        SpanishWord spanishWord = repository.getSpanishWord(wordId);
        Translation spaToPerTranslation = repository.getTranslation(spanishWord.getPersianTranslationId());
        Translation spaToEngTranslation = repository.getTranslation(spanishWord.getEnglishTranslationId());
        textToShare = "Hola,"
          + "\nsolo quería compartir esta palabra contigo."
          + "\n \"" + spanishWord.getText() + "\""
          + "\nParte del discurso: " + spanishWord.getVerbal()
          + "\nTraducción Persa: " + spaToPerTranslation.getTranslationText()
          + "\nTraducción en inglés: " + spaToEngTranslation.getTranslationText();
    }
    return textToShare;
  }
}
