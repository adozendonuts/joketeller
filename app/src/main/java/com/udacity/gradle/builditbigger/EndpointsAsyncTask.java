package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.runningoutofbreadth.backend.myApi.MyApi;
import com.runningoutofbreadth.jokedisplayer.JokeActivity;

import java.io.IOException;

/**
 * Created by SandD on 2/8/2016.
 */
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, Pair<Context, String>> {
    private static MyApi myApiService = null;
    private Context context;

    public EndpointsAsyncTask() {
    }

    @Override
    protected Pair<Context, String> doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://jokes-gce-build-it-bigger.appspot.com/_ah/api/");
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return new Pair<Context, String>(context, myApiService.sayHi(name).execute().getData());
        } catch (IOException e) {
            return new Pair <Context, String>(null, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Pair<Context, String> result) {
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Intent mIntent = new Intent(result.first, JokeActivity.class);
        mIntent.putExtra(MainActivity.JOKE_KEY, result.second);
        result.first.startActivity(mIntent);
    }

}
