package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.developer.smmousavi.maktab_hw92_dictionary.R;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.activities.ShowWordMeaningActivity;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.Repository;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.EnglishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.PersianWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.SpanishWord;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */

enum FromStatus {
  ENGLISH,
  PERSIAN,
  SPANISH
}

enum ToStatus {
  ENGLISH,
  PERSIAN,
  SPANISH
}

public class WordsListShowFragment extends Fragment {

  public static final String FRAGMENT_TAG = "fragment_tag";
  public static final String ADD_WORD_DIALOG_TAG = "add_word_dialog_fragment_tag";
  public static final String CHOOSE_ON_LONG_PRESS_DIALOG_TAG = "choose_on_long_press_dialog_tag";

  private Repository repository;
  private EditText searchEdt;
  private TextView wordShowTxt;
  private RecyclerView resultRecyclerView;
  private WordAdapter adapter;
  private RadioGroup radioGroupFrom;
  private RadioGroup radioGroupTo;
  private FloatingActionButton addWordFab;

  private List<Word> showingWordList = new ArrayList<>();
  private String from;
  private String to;
  private FromStatus fromStatus = FromStatus.ENGLISH;
  private ToStatus toStatus = ToStatus.PERSIAN;


  public static WordsListShowFragment newInstance() {

    Bundle args = new Bundle();

    WordsListShowFragment fragment = new WordsListShowFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public WordsListShowFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repository = Repository.getInstance(getActivity());
  }// end of onCreate()


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_word_show, container, false);
    findViews(view);

    from = getString(R.string.english_language_name);
    to = getString(R.string.persian_language_name);
    radioGroupFrom.check(R.id.from_english_radio);
    radioGroupTo.check(R.id.to_persian_radio);

    addWordFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        AddWordDialogFragment dialog = new AddWordDialogFragment();
        dialog.show(fm, ADD_WORD_DIALOG_TAG);
      }
    });

    radioGroupFrom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        String inputText = searchEdt.getText().toString().toLowerCase();
        switch (checkedId) {
          case R.id.from_english_radio:
            fromStatus = FromStatus.ENGLISH;
            from = getString(R.string.english_language_name);
            onCheckedRadioChangeSearch(inputText);
            break;
          case R.id.from_persian_radio:
            fromStatus = FromStatus.PERSIAN;
            from = getString(R.string.persian_language_name);
            onCheckedRadioChangeSearch(inputText);
            break;
          case R.id.from_spanish_radio:
            fromStatus = FromStatus.SPANISH;
            from = getString(R.string.spanish_language_name);
            onCheckedRadioChangeSearch(inputText);
            break;
        }
      }
    });

    radioGroupTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
          case R.id.to_english_radio:
            toStatus = ToStatus.ENGLISH;
            to = getString(R.string.english_language_name);
            break;
          case R.id.to_persian_radio:
            toStatus = ToStatus.PERSIAN;
            to = getString(R.string.persian_language_name);
            break;
          case R.id.to_spanish_radio:
            toStatus = ToStatus.SPANISH;
            to = getString(R.string.spanish_language_name);
        }
      }
    });

    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    searchEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String inputText = searchEdt.getText().toString().toLowerCase();
        if (inputText.equals("")) {
          showingWordList = new ArrayList<>();
          setupWordAdapter();

        } else if (inputText.length() == 1) {
          onCheckedRadioChangeSearch(inputText);

        } else if (inputText.length() > 1) {
          showingWordList = searchTheWordList(inputText);
          Collections.sort(showingWordList, new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
              String s1 = word1.getText();
              String s2 = word2.getText();
              return s1.compareToIgnoreCase(s2);
            }
          });
          setupWordAdapter();
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    return view;
  }


  private void onCheckedRadioChangeSearch(String inputText) {
    switch (fromStatus) {
      case ENGLISH:
        showingWordList = repository.searchEnglishWordByInitial(inputText);
        Collections.sort(showingWordList, new Comparator<Word>() {
          @Override
          public int compare(Word word1, Word word2) {
            String s1 = word1.getText();
            String s2 = word2.getText();
            return s1.compareToIgnoreCase(s2);
          }
        });
        break;
      case PERSIAN:
        showingWordList = repository.searchPersianWordByInitial(inputText);
        Collections.sort(showingWordList, new Comparator<Word>() {
          @Override
          public int compare(Word word1, Word word2) {
            String s1 = word1.getText();
            String s2 = word2.getText();
            return s1.compareToIgnoreCase(s2);
          }
        });
        break;
      case SPANISH:
        showingWordList = repository.searchSpanishWordByInitial(inputText);
        Collections.sort(showingWordList, new Comparator<Word>() {
          @Override
          public int compare(Word word1, Word word2) {
            String s1 = word1.getText();
            String s2 = word2.getText();
            return s1.compareToIgnoreCase(s2);
          }
        });

    }
    setupWordAdapter();
  }// end of onCheckedRadioChangeSearch()


  private void findViews(View view) {
    searchEdt = view.findViewById(R.id.search_dictionary_edt);
    resultRecyclerView = view.findViewById(R.id.result_recycler_view);
    radioGroupFrom = view.findViewById(R.id.radio_group_from);
    radioGroupTo = view.findViewById(R.id.radio_group_to);
    addWordFab = view.findViewById(R.id.add_word_fab);
  }// end of fide views


  private List<Word> searchTheWordList(String searchString) {
    int length = searchString.length();
    List<Word> words = new ArrayList<>();
    for (Word word : showingWordList) {
      String subString = word.getTextSubstring(length);
      if (subString.equals(searchString.toLowerCase())) {
        words.add(word);
      }
    }

    return words;
  }// end of searchTheWordList()


  public void setupWordAdapter() {
    adapter = new WordAdapter(showingWordList);
    resultRecyclerView.setAdapter(adapter);
  }// end of setupWordAdapter()


  private class WordViewHolder extends RecyclerView.ViewHolder {

    private Word currentWord;

    public WordViewHolder(@NonNull View itemView) {
      super(itemView);
      wordShowTxt = itemView.findViewById(R.id.txt_word_show);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          UUID wordId = currentWord.getId();
          UUID translationId = getToStatusUUID();
          Intent intent = ShowWordMeaningActivity.newIntet(getActivity(), wordId, translationId, from, to);
          startActivity(intent);
        }
      });

      itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          ChooseOnLongPressDialogFragment dialog = ChooseOnLongPressDialogFragment.newInstance(currentWord.getId(), from, to);
          FragmentManager fm = getFragmentManager();
          dialog.show(fm, CHOOSE_ON_LONG_PRESS_DIALOG_TAG);

          return true;
        }
      });
    }


    private UUID getToStatusUUID() {
      UUID translationId = null;

      if (fromStatus.equals(FromStatus.ENGLISH) && !toStatus.equals(ToStatus.ENGLISH)) {
        EnglishWord word = (EnglishWord) currentWord;
        if (toStatus.equals(ToStatus.PERSIAN)) {
          translationId = word.getPersianTranslationId();

        } else if (toStatus.equals(ToStatus.SPANISH)) {
          translationId = word.getSpanishTranslationId();

        }
      } else if (fromStatus.equals(FromStatus.ENGLISH) && toStatus.equals(ToStatus.ENGLISH)) {
        EnglishWord word = (EnglishWord) currentWord;

      } else if (fromStatus.equals(FromStatus.PERSIAN) && !toStatus.equals(ToStatus.PERSIAN)) {
        PersianWord word = (PersianWord) currentWord;
        if (toStatus.equals(ToStatus.ENGLISH)) {
          translationId = word.getEnglishTranslationId();

        } else if (toStatus.equals(ToStatus.SPANISH)) {
          translationId = word.getSpanishTranslationId();

        }
      } else if (fromStatus.equals(FromStatus.PERSIAN) && toStatus.equals(ToStatus.PERSIAN)) {
        PersianWord word = (PersianWord) currentWord;

      } else if (fromStatus.equals(FromStatus.SPANISH) && !toStatus.equals(ToStatus.SPANISH)) {
        SpanishWord word = (SpanishWord) currentWord;
        if (toStatus.equals(ToStatus.PERSIAN)) {
          translationId = word.getPersianTranslationId();

        } else if (toStatus.equals(ToStatus.ENGLISH)) {
          translationId = word.getEnglishTranslationId();

        }
      } else if (fromStatus.equals(FromStatus.SPANISH) && toStatus.equals(ToStatus.SPANISH)) {
        SpanishWord word = (SpanishWord) currentWord;
      }
      return translationId;
    }// end of getToStatusUUID()


    public void bindeWord(Word word) {
      currentWord = word;
      wordShowTxt.setText(word.getText());
    }
  }// end of WordViewHolder{}


  private class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    List<Word> wordList;

    public WordAdapter(List<Word> wordList) {
      this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View view = inflater.inflate(R.layout.word_item_view, viewGroup, false);
      LinearLayout layout = view.findViewById(R.id.word_item_view_layout);
      if (fromStatus.equals(FromStatus.PERSIAN))
        layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

      return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
      Word word = wordList.get(i);
      wordViewHolder.bindeWord(word);
    }

    @Override
    public int getItemCount() {
      return wordList.size();
    }
  }// end of WordAdapter{}

}
