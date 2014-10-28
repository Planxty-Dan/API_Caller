package com.bunniesarecute.admin.api_caller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by admin on 10/27/14.
 */
public class ShowWordAndDef extends Activity{

    private TextView wordDisplay;
    private TextView definitionDisplay;
    private String wordFromAPI = "";
    private String definitionFromAPI = "";
    DictionaryAPI mDictionaryAPI;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_and_def);
        wordDisplay = (TextView) findViewById(R.id.randomWord);
        definitionDisplay = (TextView) findViewById(R.id.randomDefinition);
        mDictionaryAPI = new DictionaryAPI(this);
        mDictionaryAPI.setWordOrDef("word");
        mDictionaryAPI.execute();
        wordFromAPI = mDictionaryAPI.getWordFromAPI();
//        mDictionaryAPI.setWordOrDef("definition");
//        mDictionaryAPI.setWord(wordFromAPI);
//        mDictionaryAPI.execute();
//        definitionFromAPI = mDictionaryAPI.getDefinitionFromAPI();
//        wordDisplay.setText(wordFromAPI);
//        definitionDisplay.setText(definitionFromAPI);


    }
    public void setWordDisplay(String word) {
        wordDisplay.setText(word);
    }

}
