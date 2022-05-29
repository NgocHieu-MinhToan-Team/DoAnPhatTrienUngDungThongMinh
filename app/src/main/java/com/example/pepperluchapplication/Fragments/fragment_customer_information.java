package com.example.pepperluchapplication.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperluchapplication.ActivityLogin;
import com.example.pepperluchapplication.DTO.CUSTOMER;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class fragment_customer_information extends Fragment {

    private CUSTOMER customer;
    private EditText editTextFullName, editTextPhoneNumber, editTextBirthday;
    private Spinner spinnerSex;
    private EditText editTextAddress;

    public CUSTOMER getCustomer() {
        return customer;
    }

    public void setCustomer(CUSTOMER customer) {
        this.customer = customer;
    }

    public fragment_customer_information() {
        // Required empty public constructor
    }

    public fragment_customer_information(CUSTOMER customer) {
        setCustomer(customer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextFullName = view.findViewById(R.id.txt_fullname_info);
        editTextFullName.setText(String.join(" ", new String[]{customer.getSURNAME_CUSTOMER(),
                customer.getNAME_CUSTOMER()}));

        editTextPhoneNumber = view.findViewById(R.id.txt_phone_number_info);
        editTextPhoneNumber.setText(customer.getPHONE_CUSTOMER());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        editTextBirthday = view.findViewById(R.id.txt_birthday_info);
        Date birthday = null;
        try {
            birthday = formatter.parse(customer.getDATE_OF_BIRTH());
            editTextBirthday.setText(new SimpleDateFormat("dd/mm/yyyy").format(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editTextBirthday.setOnClickListener(v1 -> {
            // Date Select Listener.
            DatePickerDialog.OnDateSetListener dateSetListener = (view1, year, month, dayOfMonth) -> {
                editTextBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            };

            // Create DatePickerDialog (Spinner Mode):
            Calendar calendar = Calendar.getInstance();
            @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog =
                    new DatePickerDialog(view.getContext(),
                            dateSetListener, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
        });

        editTextBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextBirthday.setInputType(InputType.TYPE_NULL);
                    // Date Select Listener.
                    DatePickerDialog.OnDateSetListener dateSetListener = (view12, year, month, dayOfMonth) -> editTextBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    // Create DatePickerDialog (Spinner Mode):
                    Calendar calendar = Calendar.getInstance();
                    @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog =
                            new DatePickerDialog(view.getContext(),
                                    dateSetListener, calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

                    datePickerDialog.show();
                }
            }
        });

        spinnerSex = view.findViewById(R.id.spinner_sex_info);
        spinnerSex.setSelection(customer.getGENDER_CUSTOMER() == 0 ? 0 : 1);

        editTextAddress = view.findViewById(R.id.txt_address_info);
        editTextAddress.setText(customer.getADDRESS_CUSTOMER());

        Button buttonUpdate = view.findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(v -> {
            // Write a message to the database
            FirebaseDatabase firebase = FirebaseDatabase.getInstance("https" +
                    "://dbpepperlunch-default" +
                    "-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference reference = firebase.getReference("Database/Customer");


            String[] splitFullName = ActivityLogin.splitFullName(editTextFullName.getText()
                    .toString());
            customer.setSURNAME_CUSTOMER(splitFullName[0]);
            customer.setNAME_CUSTOMER(splitFullName[1]);
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                customer.setDATE_OF_BIRTH(formatter1.format(new SimpleDateFormat("dd/mm/yyyy").parse(editTextBirthday.getText()
                        .toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            customer.setGENDER_CUSTOMER(spinnerSex.getSelectedItem().toString() == "Nam" ?
                    1 : 0);
            customer.setADDRESS_CUSTOMER(editTextAddress.getText().toString());

            try {
                reference.child(customer.getID_CUSTOMER()).setValue(customer);
                SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(
                        "USER", MODE_PRIVATE);

                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(customer);
                prefsEditor.putString("CUSTOMER", json);
                prefsEditor.commit();

                Toast.makeText(getActivity(), "Bạn đã cập nhật thông tin tài khoản " +
                                "thành công",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(view.getContext(), "Something went wrong: " + ex.getMessage(),
                        Toast.LENGTH_LONG);
            }
        });
    }
}