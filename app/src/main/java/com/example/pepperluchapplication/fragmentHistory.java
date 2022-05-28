package com.example.pepperluchapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

<<<<<<< HEAD:app/src/main/java/com/example/pepperluchapplication/fragmentHistory.java
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentHistory extends Fragment {
=======
public class fragmentProfile extends Fragment {
>>>>>>> origin/minhtoan:app/src/main/java/com/example/pepperluchapplication/Fragments/fragmentProfile.java

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