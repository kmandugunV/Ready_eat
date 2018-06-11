package com.example.kmand.v2app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by kmand on 2018-05-30.
 */

public class Adfragment extends Fragment {

    ImageView adimg;
    int[] adRes = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
    boolean adStopFlag = false;

    public Adfragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_ad, container, false);
        adimg = (ImageView)v.findViewById(R.id.ad1);

        return v;
    }

    public void changeimage()
    {
        adStopFlag = false;
        Thread adThread = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    while(!adStopFlag)
                    {
                        for(int i=0; i<3; i++) {
                            adimg.setImageResource(adRes[i]);
                            Thread.sleep(2000);
                        }
                    }
                }catch(Exception e){}
            }
        });

        adThread.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        adStopFlag = true;
    }
}
