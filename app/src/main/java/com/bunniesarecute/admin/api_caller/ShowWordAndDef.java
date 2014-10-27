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
    String wordFromAPI;
    String definitionFromAPI;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_and_def);
        wordDisplay = (TextView) findViewById(R.id.randomWord);
        definitionDisplay = (TextView) findViewById(R.id.randomDefinition);

        wordDisplay.setText(wordFromAPI);
        definitionDisplay.setText(definitionFromAPI);

        
    }

}
