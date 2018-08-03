package com.developer.smmousavi.maktab_hw92_dictionary.mvc.controller.fragmenst;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.developer.smmousavi.maktab_hw92_dictionary.R;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.database.Repository;
import com.developer.smmousavi.maktab_hw92_dictionary.mvc.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordShowFragment extends Fragment {

  public static final String FRAGMENT_TAG = "fragment_tag";

  private Repository repository;
  private EditText searchEdt;
  private RecyclerView resultRecyclerView;
  private WordAdapter adapter;
  private List<Word> showingWords = new ArrayList<>();


  public static WordShowFragment newInstance() {

    Bundle args = new Bundle();

    WordShowFragment fragment = new WordShowFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public WordShowFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repository = Repository.getInstance(getActivity());
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_word_show, container, false);
    searchEdt = view.findViewById(R.id.search_dictionary_edt);
    resultRecyclerView = view.findViewById(R.id.result_recycler_view);
    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    setupWordAdapter();

    searchEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String inputText = searchEdt.getText().toString();
        if (inputText.equals("")) {
          showingWords = new ArrayList<>();
          setupWordAdapter();

        } else if (inputText.length() == 1) {
          showingWords = repository.searchEnglishWordByInitial(inputText);
          Log.e("TAG2", showingWords.size() + "");

          setupWordAdapter();

        } else if (inputText.length() > 1) {

        }

      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    return view;
  }


  public void setupWordAdapter() {
    adapter = new WordAdapter(showingWords);
    resultRecyclerView.setAdapter(adapter);
  }

  private class WordViewHolder extends RecyclerView.ViewHolder {

    private Word currentWord;
    private TextView wordShowTxt;

    public WordViewHolder(@NonNull View itemView) {
      super(itemView);
      wordShowTxt = itemView.findViewById(R.id.txt_word_show);
    }

    public void bindeWord(Word word) {
      currentWord = word;
      wordShowTxt.setText(word.getText());
    }
  }


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
  }

}
