package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD:app/src/main/java/com/example/pepperluchapplication/fragmentHistory.java
import androidx.fragment.app.Fragment;
=======
import com.example.pepperluchapplication.R;
>>>>>>> f9f290355de84b4d2cd648d84f54e48016f36b21:app/src/main/java/com/example/pepperluchapplication/Fragments/fragmentHistory.java

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentHistory extends Fragment {

    Context context;

    public fragmentHistory(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }
}