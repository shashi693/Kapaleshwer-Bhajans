package com.avenueinfotech.navigationdrawericon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shashikant on 20-02-2017.
 */

public class KhadavvajeeFragment extends Fragment {

    private static View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_khadavvajee,container,false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        return view;
    }
}
