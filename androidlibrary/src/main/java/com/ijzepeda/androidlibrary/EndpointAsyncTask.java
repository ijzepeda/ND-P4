package com.ijzepeda.androidlibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.ijzepeda.backend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Created by Ivan on 6/11/2016.
 */

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private JokeApi myApiService = null;
    private Context context;
    public ProgressDialog dialog;

    public interface PostAction{
        void processFinish(String output);
    }
    public PostAction delegatePostAction = null;

    EndpointsAsyncTask(Context context) {
        dialog = new ProgressDialog(context);
    }
     EndpointsAsyncTask(PostAction delegate){
        this.delegatePostAction = delegate;
    }
    EndpointsAsyncTask(Context context,PostAction delegate){
        dialog = new ProgressDialog(context);
        this.delegatePostAction = delegate;


    }

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
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://nanodegree-1337.appspot.com/_ah/api/");

            //LOCAL SETTINGS/devappserver
//            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
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
        Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        Log.e("FetchJOke[androidlib]","EndpointsAsyncTask.OnPostExecute: "+ result);
    delegatePostAction.processFinish(result);
    }



//         private GetTaskListener mListener = null;
//         public  EndpointsAsyncTask setListener(GetTaskListener listener) {
//             this.mListener = listener;
//             return this;
//         }
//
//    public static interface GetTaskListener {
//        public void onComplete(String jsonString, Exception e);
//    }


}
