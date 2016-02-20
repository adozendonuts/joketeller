package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.runningoutofbreadth.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by SandD on 2/8/2016.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String>{
    private static MyApi myApiService = null;
    EndpointsAsyncResponse response = null;

    public EndpointsAsyncTask() {
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokes-gce-build-it-bigger.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        response.sendResult(result);
    }

}
