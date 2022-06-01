package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperluchapplication.ActivityLogin;
import com.example.pepperluchapplication.DTO.CUSTOMER;
import com.example.pepperluchapplication.R;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

public class fragment_change_password extends Fragment {
    private Button buttonChangePassword, buttonOTP;
    private EditText editTextOldPassword, editTextNewPassword, editTextConfirmPassword, editTextOTP;
    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;
    // string for storing our verification ID
    private String verificationId;

    public fragment_change_password() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextOldPassword = view.findViewById(R.id.txt_pwd_old_change);
        editTextNewPassword = view.findViewById(R.id.txt_pwd_new_change);
        editTextConfirmPassword = view.findViewById(R.id.txt_confirm_password_change);
        editTextOTP = view.findViewById(R.id.txt_otp);
        // Handle event sent OTP
        buttonOTP = view.findViewById(R.id.button_send_otp);
        buttonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();

                // Get customer information from shared preferences
                Gson gson = new Gson();
                String json =
                        view.getContext().getSharedPreferences("USER", Context.MODE_PRIVATE)
                                .getString("CUSTOMER", "");
                CUSTOMER customer = gson.fromJson(json, CUSTOMER.class);
                String phone = "+84" + customer.getPHONE_CUSTOMER();
                sendVerificationCode(phone);
            }
        });

        buttonChangePassword = view.findViewById(R.id.button_change_password);
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer information from shared preferences
                Gson gson = new Gson();
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(
                        "USER", Context.MODE_PRIVATE);
                String json = sharedPreferences.getString("CUSTOMER", "");
                CUSTOMER customer = gson.fromJson(json, CUSTOMER.class);
                if (validFormChangePassword(editTextOldPassword, editTextNewPassword,
                        editTextConfirmPassword, editTextOTP, customer)) {
                    verifyCode(editTextOTP.getText().toString());
                    if (isValidOTP) {
                        try {
                            // Update password of customer
                            customer.setPASSWORD_CUSTOMER(ActivityLogin.sha256(editTextNewPassword.getText()
                                    .toString()));
                            FirebaseDatabase firebase = FirebaseDatabase.getInstance("https" +
                                    "://dbpepperlunch-default" +
                                    "-rtdb.asia-southeast1.firebasedatabase.app/");
                            DatabaseReference reference = firebase.getReference("Database/Customer");
                            reference.child(customer.getID_CUSTOMER()).setValue(customer);

                            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                            json = gson.toJson(customer);
                            prefsEditor.putString("CUSTOMER", json).apply();

                            isValidOTP = false;
                            Toast.makeText(getActivity(), "Bạn đã cập nhật mật khẩu " +
                                            "thành công",
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {
                            Toast.makeText(view.getContext(), "Something went wrong: " + ex.getMessage(),
                                    Toast.LENGTH_LONG);
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Mã xác thực không hợp lệ", Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }

    private boolean validFormChangePassword(EditText oldPassword, EditText newPassword,
                                            EditText confirmPassword, EditText otp, CUSTOMER customer) {
        if (Strings.isEmptyOrWhitespace(oldPassword.getText().toString())) {
            oldPassword.setError("Nhập mật khẩu hiện tại");
            return false;
        } else if (customer.getPASSWORD_CUSTOMER()
                .compareTo(ActivityLogin.sha256(oldPassword.getText().toString())) != 0) {
            oldPassword.setError("Mật khẩu hiện tại không hợp lệ");
            return false;
        } else {
            oldPassword.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(newPassword.getText().toString())) {
            newPassword.setError("Nhập mật khẩu mới");
            return false;
        } else {
            newPassword.setError(null);
        }

        if (!ActivityLogin.isMatchConfirmPassword(newPassword.getText().toString(),
                confirmPassword.getText().toString())) {
            confirmPassword.setError("Mật khẩu không khớp");
            return false;
        } else {
            oldPassword.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(otp.getText().toString())) {
            otp.setError("Nhập mật mã xác thực OTP");
            return false;
        } else {
            otp.setError(null);
        }
        return true;
    }

    private void sendVerificationCode(String phoneNumber) {
        // OTP on user phone number.
        mAuth.setLanguageCode("vi");
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)         // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())        // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            // when we receive the OTP it contains a unique id which we are storing in our string
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                editTextOTP.setText(code);
                verifyCode(code);
            } else {
                Toast.makeText(getContext(), "Lỗi xác thực", Toast.LENGTH_LONG);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
    boolean isValidOTP = false;

    private void verifyCode(String code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            isValidOTP = true;
                        }
                    });
        } catch (Exception ex) {
            Toast.makeText(getContext(), "Mã xác thực không hợp lệ", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}