package com.taan.hasani.moein.feedme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="TAG" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadData Downloader=new downloadData();
        Downloader.execute("http://www.bartarinha.ir/fa/rss/allnews");

    }

    //////////////////////
    private class downloadData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String rss=downloadXML(params[0]);
            if(rss==null)
                Log.e(TAG, "doInBackground: Error downloading xml");
            return rss;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Parse_News parseNews=new Parse_News();
            parseNews.parse(s);
        }

    private String downloadXML (String urlpath){
        StringBuilder stringBuilder=new StringBuilder();

       try {
           /////
           URL url=new URL(urlpath);
           HttpURLConnection connection=(HttpURLConnection)url.openConnection();
           InputStream input=connection.getInputStream();
           InputStreamReader inputreader=new InputStreamReader(input);
           BufferedReader reader=new BufferedReader(inputreader);
           ////
           int num_of_chars;

           char[] charbuffered = new char[500];

           while (true) {
               num_of_chars = reader.read(charbuffered);
               if (num_of_chars < 0)
                   break;
               if (num_of_chars > 0)
                   stringBuilder.append(String.copyValueOf(charbuffered, 0, num_of_chars));
           }
           reader.close();
           return stringBuilder.toString();

       }catch (MalformedURLException e){
           Log.e(TAG, "downloadXML: Invalid URL: "+e.getMessage());
       }catch (IOException e){
           Log.e(TAG,"downloadXML: IO Exception reading"+e.getMessage());
       }
    return null;
    }


    }
    //////////////////////


}

