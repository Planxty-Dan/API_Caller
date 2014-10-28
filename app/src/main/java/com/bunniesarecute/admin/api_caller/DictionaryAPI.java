package com.bunniesarecute.admin.api_caller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 10/27/14.
 */
public class DictionaryAPI extends AsyncTask{

    Context mCurrentContext;
    private ProgressDialog mProgressDialog;
    private String mWordOrDef = "";

    public DictionaryAPI(Context context) {
        mCurrentContext = context;
        mProgressDialog = new ProgressDialog(mCurrentContext);
    }

    public void setWordOrDef(String wordOrDef) {
        mWordOrDef = wordOrDef;
    }

    public void setWord(String word) {
        mWord = word;
    }

    private final String API_key = "6aa015c0d84b01a6c205f6848a6dea42bcb91d757d4341dde";
    InputStream mInputStream = null;
    String mSearchResult = "";
    private String mDictionaryURL = "http://api.wordnik.com:80/v4/words.json/";
    private String mRandomWordURL = "randomWord?hasDictionaryDef=true&minLength=1&api_key=";
    private String mDefinitionURL = "/definitions?limit=1&includeRelated=false&sourceDictionaries=webster&useCanonical=false&includeTags=false&api_key=";
    private String mWord = "";
    private String mSearchURL = "";
    HttpURLConnection mURLConnection = null;

    @Override
    protected void onPreExecute() {
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                DictionaryAPI.this.cancel(true);
            }
        });
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            StringBuilder mJSONResult = new StringBuilder();
            stringBuild();
            URL mWordURL = new URL(mSearchURL);
            mURLConnection = (HttpURLConnection) mWordURL.openConnection();
            mURLConnection.setRequestMethod("GET");
            mInputStream = mURLConnection.getInputStream();
            BufferedReader mBufffer = new BufferedReader(new InputStreamReader(mInputStream));
            String line;
            while ((line = mBufffer.readLine()) != null) {
                mJSONResult.append(line);
            }
            mSearchResult = mJSONResult.toString();
        } catch (MalformedURLException e) {
            Log.e("TAG URL", e.getMessage());
        } catch (IOException e) {
            Log.e("TAG HTTP", e.getMessage());
        }

        return mSearchResult;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    public String stringBuild() {
        if (mWordOrDef == "word") {
            mSearchURL = mDictionaryURL + mRandomWordURL + API_key;
        } else if (mWordOrDef == "definition") {
            mSearchURL = mDictionaryURL + mWord + mDefinitionURL + API_key;
        } else {
            Log.v("TAG IFELSE", "Problem with if/else in DictionaryAPI.stringBuild");
        }
        return mSearchURL;
    }
}
