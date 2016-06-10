package com.ijzepeda.androidlibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ijzepeda.backend.jokeApi.JokeApi;

import java.io.IOException;

public class FetchJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_joke);

//        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, joke));
        new EndpointsAsyncTask().execute(this);
//        new EndpointsAsyncTask().execute();
    }



     class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
        private  JokeApi myApiService = null;
        private Context context;
        private ProgressDialog dialog = new ProgressDialog(FetchJoke.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Fetching joke...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(Context... params) {
            if(myApiService == null) {  // Only do this once
                //REMOTE SETTINGS
//                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("https://nanodegree-1337.appspot.com/_ah/api/");

                //LOCAL SETTINGS/devappserver
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for local settings/devappserver

                myApiService = builder.build();
            }

            context = params[0];

            try {


                return myApiService.retrieveJoke().execute().getData();


            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Toast.makeText(context,"Here's your joke:"+ result, Toast.LENGTH_LONG).show();
            Log.e("FetchJOke[androidlib]","EndpointsAsyncTask.OnPostExecute: "+ result);
        }
    }



}