package com.ijzepeda.androidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ijzepeda.androidlibrary.EndpointsAsyncTask.PostAction;

public class FetchJoke extends AppCompatActivity  {
public String fetchedJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fetch_joke);

//        new EndpointsAsyncTask(this).execute(this);
        //I did it this way (separate Asynctask) to be able to Test the result.
        //I send the context for the dialogprogress and the interface to retrieve the OnPostExecute result
        //I dont use it here, but It will be needed for the Test
        EndpointsAsyncTask asyncT=new EndpointsAsyncTask(this, new PostAction(){
            @Override
            public void processFinish(String output) {
                fetchedJoke=output;
                finish();
            }
        });
        asyncT.execute(this);
    }


//    @Override
//    public void processFinish(String output) {
//    }
}