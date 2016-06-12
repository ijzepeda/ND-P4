package com.ijzepeda.builditbigger.free;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ijzepeda.androidlibrary.FetchJoke;
import com.udacity.gradle.builditbigger.R;

import static com.udacity.gradle.builditbigger.R.id.adView;


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

//Setup Interstitial AD
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
            }
        });
        requestNewInterstitial();


        Button jokeBtn=(Button) root.findViewById(R.id.fetchJokeBtn);
        jokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //if free, place the interstitial ad, if not, just ask for the joke
                    Toast.makeText(getActivity(), "CouldNot Load Ad", Toast.LENGTH_SHORT).show();
                    tellJoke();
                }
            }
        });


        AdView mAdView = (AdView) root.findViewById(adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void tellJoke(){
 Intent intent=new Intent(getActivity(),FetchJoke.class);
 startActivity(intent);
 }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .addTestDevice("B6ED393F1A706ACC7E640193CA19C76A")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
//    idB3EEABB8EE11C2BE770B684D95219ECB
//    Ads: Use AdRequest.Builder.addTestDevice("B6ED393F1A706ACC7E640193CA19C76A") to get test ads on this device

}
