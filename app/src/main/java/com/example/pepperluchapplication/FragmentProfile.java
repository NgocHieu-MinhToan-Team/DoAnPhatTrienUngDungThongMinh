package com.example.pepperluchapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.pepperluchapplication.Model.ItemProfileMenu;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentProfile extends Fragment {

    private Context context;

    private ArrayList<ItemProfileMenu> lstItemProfileMenu =
            new ArrayList<ItemProfileMenu>(Arrays.asList(
                    new ItemProfileMenu(R.drawable.ic_baseline_contact_emergency_24,
                            "Thông tin tài khoản"),
                    new ItemProfileMenu(R.drawable.ic_baseline_lock_24,
                            "Đổi mật khẩu"),
                    new ItemProfileMenu(R.drawable.ic_baseline_loyalty_24,
                            "Điểm thưởng"),
                    new ItemProfileMenu(R.drawable.ic_baseline_card_giftcard_24,
                            "Voucher | Coupon")
            ));

    public FragmentProfile(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the adapter and set it to list view
//        ProfileMenuAdapter profileMenuAdapter = new ProfileMenuAdapter(getActivity(), lstItemProfileMenu);
//        ListView listView = rootView.findViewById(R.id.lstViewProfileMenu);
//        listView.setAdapter(profileMenuAdapter);

        return rootView;
    }
}