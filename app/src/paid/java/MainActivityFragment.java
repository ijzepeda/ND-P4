package com.ijzepeda.builditbigger.paid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.ads.InterstitialAd;
import com.ijzepeda.androidlibrary.FetchJoke;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    InterstitialAd mInterstitialAd;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeBtn=(Button) root.findViewById(R.id.fetchJokeBtn);
        jokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tellJoke();
            }
        });


        return root;
    }

    public void tellJoke(){
 Intent intent=new Intent(getActivity(),FetchJoke.class);
 startActivity(intent);
 }



}
