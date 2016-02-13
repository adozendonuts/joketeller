package com.runningoutofbreadth.jokedisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SandD on 2/6/2016.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);

//        AdView mAdView = (AdView) root.findViewById(R.id.adView);
//        // Create an ad request. Check logcat output for the hashed device ID to
//        // get test ads on a physical device. e.g.
//        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        mAdView.loadAd(adRequest);
        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_textview);
        String jokeString;

        Intent intent = getActivity().getIntent();
        if (intent.getStringExtra(JokeActivity.JOKE_STRING_KEY) != null){
            jokeString = intent.getStringExtra(JokeActivity.JOKE_STRING_KEY);
        }else {
            jokeString = "no strings attached";
        }
        jokeTextView.setText(jokeString);



        return root;
    }
}
