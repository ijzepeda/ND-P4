package com.ijzepeda.androidlibrary;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AsyncUnitTest  {
    @Test
    public void JokeAsyncTaskTest() throws Exception {
//        The course didn't covered Unit testing that well to understand it.

        Context appContext= new Activity();
        EndpointsAsyncTask asyncT=new EndpointsAsyncTask(appContext, new EndpointsAsyncTask.PostAction(){
            @Override
            public void processFinish(String output) {
                assertEquals("", output);
        assertFalse(TextUtils.isEmpty(output));
            }
        });
        asyncT.execute(appContext);

    }


}