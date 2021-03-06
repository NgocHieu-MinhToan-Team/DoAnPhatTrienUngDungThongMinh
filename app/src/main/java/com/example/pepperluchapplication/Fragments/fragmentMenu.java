package com.example.pepperluchapplication.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


public class fragmentMenu extends Fragment implements View.OnClickListener {

    Context context;
    ProgressDialog progressDialog;

    public fragmentMenu(Context context)
    {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);

    }

    CardView cardMain,cardSide,cardCombo;
    Fragment frag;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // anh xa
        cardMain=view.findViewById(R.id.card_main);
        cardSide=view.findViewById(R.id.card_side);
        cardCombo=view.findViewById(R.id.card_combo);

        cardMain.setOnClickListener(this);
        cardSide.setOnClickListener(this);
        cardCombo.setOnClickListener(this);
        progressDialog =new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        // Write a message to the database

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.card_main:{
                // x??? l?? l???c data cate
                //progressDialog.show();
                //ArrayList<CATEGORY> arrMain = filterByCondition(dataOfCATEGORY,"MAIN");
                frag =new fragment_category("MAIN");
                openFragmentFromFragment(frag,"CateMain");
            };break;
            case R.id.card_side:{
                // x??? l?? l???c data cate
                //ArrayList<CATEGORY> arrSide = filterByCondition(dataOfCATEGORY,"SIDE");
                frag =new fragment_category("SIDE");
                openFragmentFromFragment(frag,"CateSide");

            };break;
            case R.id.card_combo:{
                // x??? l?? l???c data cate
                //ArrayList<CATEGORY> arrCombo = filterByCondition(dataOfCATEGORY,"COMBO");
                frag =new fragment_category("COMBO");
                openFragmentFromFragment(frag,"CateCombo");
            };break;
        }

    }

    void openFragmentFromFragment(Fragment fragment,String backStackName){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frmMain, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack(backStackName) // name can be null
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    ArrayList<CATEGORY> filterByCondition(ArrayList<CATEGORY> data, String condition){
        return new ArrayList<CATEGORY>(data.parallelStream().filter(entry->entry.getGROUP_CATEGORY().equals(condition)).collect(Collectors.toList()));
    }

}