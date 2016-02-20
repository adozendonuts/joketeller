package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.runningoutofbreadth.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by SandD on 2/15/2016.
 */
public class MyTests extends AndroidTestCase {

    String joke = null;
    CountDownLatch countDownLatch;

    public void testMyApi() throws IOException {
        MyApi myApiService = null;
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokes-gce-build-it-bigger.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        assertNotNull(myApiService.getJoke().execute().getData());
    }

    /* Used to test whether or not onPostExecute successfully kicks off "sendResult"
     */
    @UiThreadTest
    public void testEndpointsAsyncTask() throws Exception {
        countDownLatch = new CountDownLatch(1);
        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.response = new EndpointsAsyncResponse() {
                @Override
                public void sendResult(String result) {
                    assertNotNull(result);
                    joke = "result";
                }
            };
            endpointsAsyncTask.execute();
            countDownLatch.await(30, TimeUnit.SECONDS);
            assertTrue("joke was null", joke != null);
        } catch (Exception e) {
            fail("Timed out");
        }
    }
}
