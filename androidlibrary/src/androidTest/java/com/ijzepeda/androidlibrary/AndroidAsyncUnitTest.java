package com.ijzepeda.androidlibrary;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;
import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import com.ijzepeda.androidlibrary.FetchJoke;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Ivan on 6/11/2016.
 */


@MediumTest
@RunWith(AndroidJUnit4.class)
public class AndroidAsyncUnitTest {
    @Test
    public void AndroidJokeAsyncTaskTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
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



//   //public class AndroidAsyncUnitTest extends ApplicationTestCase<android.app.Application> {
//
//    String mJsonString = null;
//    Exception mError = null;
//    CountDownLatch signal = null;
//
//    public AndroidAsyncUnitTest() {
//        super(Application.class);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        signal = new CountDownLatch(1);
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        signal.countDown();
//    }
//
//    public void testGetTask() throws InterruptedException {
//
//        FetchJoke task = new FetchJoke();
//        FetchJoke.EndpointsAsyncTask.setListener(new FetchJoke.GetTaskListener() {
//            @Override
//            public void onComplete(String jsonString, Exception e) {
//                mJsonString = jsonString;
//                mError = e;
//                signal.countDown();
//            }
//        }).execute();
//        signal.await();
//
//        assertNull(mError);
//        assertFalse(TextUtils.isEmpty(mJsonString));
//        assertTrue(mJsonString.startsWith("{\"playlist\""));
//        assertTrue(mJsonString.endsWith("}"));
//
//    }
//}
////</application>