package com.example.pepperluchapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pepperluchapplication.DTO.CUSTOMER;
import com.example.pepperluchapplication.DTO.MyApplication;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityLogin extends AppCompatActivity {

    private Button buttonLogin, buttonSignup;
    private ImageView logo;
    private ArrayList<CUSTOMER> customers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default" +
                "-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("Database/Customer");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    CUSTOMER item = data.getValue(CUSTOMER.class);
                    customers.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); //Don't ignore errors
            }
        });

        logo = findViewById(R.id.pepperlunch_logo);
        // Using Glide to fix error 'Canvas: trying to draw too large bitmap'
        Glide.with(this)
                .load(R.drawable.pepperlunch_logo)
                .into(logo);

        buttonLogin = findViewById(R.id.button_open_modal_login);
        // Handle event login
        buttonLogin.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    this);
            View bottomSheetLoginView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.modal_bottom_sheet_login,
                            (LinearLayout) findViewById(R.id.modal_bottom_sheet_login_container));

            // Handle user authentication for login
            bottomSheetLoginView.findViewById(R.id.button_login)
                    .setOnClickListener(view -> {
                        TextView username = bottomSheetLoginView.findViewById(R.id.txt_usr_login);
                        TextView password = bottomSheetLoginView.findViewById(R.id.txt_pwd_login);

                        // Required information field
                        if (isValidLogin(username, password)) {
                            CUSTOMER userLogin = Login(username.getText().toString(),
                                    password.getText().toString());
                            if (userLogin != null) {
                                Intent intent = new Intent(ActivityLogin.this, ActivityHomePage.class);
                                MyApplication.setCustomer(userLogin);

                                startActivity(intent);
                                bottomSheetDialog.dismiss();
                            } else {
                                Toast.makeText(ActivityLogin.this, "Tên đăng nhập hoặc mật khẩu " +
                                                        "không đúng",
                                                Toast.LENGTH_SHORT).
                                        show();
                            }
                        }

                    });

            bottomSheetDialog.setContentView(bottomSheetLoginView);
            bottomSheetDialog.show();
        });

        buttonSignup =
                findViewById(R.id.button_open_modal_sigup);
        // Handle event signup
        buttonSignup.setOnClickListener(v ->
        {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    this);
            View bottomSheetSignUpView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.modal_bottom_sheet_signup,
                            (LinearLayout) findViewById(R.id.modal_bottom_sheet_sigup_container));

            // Handle show date picker dialog
            EditText txtBirthday = bottomSheetSignUpView.findViewById(R.id.txt_birthday);
            txtBirthday.setOnClickListener(v1 -> {
                // Date Select Listener.
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };

                // Create DatePickerDialog (Spinner Mode):
                Calendar calendar = Calendar.getInstance();
                @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog =
                        new DatePickerDialog(this,
                                dateSetListener, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            });

            txtBirthday.setOnFocusChangeListener((v1, event) -> {
                txtBirthday.setInputType(InputType.TYPE_NULL);
                // Date Select Listener.
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };

                // Create DatePickerDialog (Spinner Mode):
                Calendar calendar = Calendar.getInstance();
                @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog =
                        new DatePickerDialog(this,
                                dateSetListener, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            });


            bottomSheetSignUpView.findViewById(R.id.button_signup)
                    .setOnClickListener(view -> {
                        TextView fullName =
                                bottomSheetSignUpView.findViewById(R.id.txt_fullname_signup),
                                birthday = bottomSheetSignUpView.findViewById(R.id.txt_birthday),
                                phoneNumber = bottomSheetSignUpView.findViewById(R.id.txt_phone_number),
                                password = bottomSheetSignUpView.findViewById(R.id.txt_pwd_signup),
                                rePassword = bottomSheetSignUpView.findViewById(R.id.txt_repwd);
                        Spinner spinnerSex = bottomSheetSignUpView.findViewById(R.id.spinner_sex);
                        boolean a = isValidSignUp(fullName, phoneNumber, password, rePassword);
                        boolean b = isMatchConfirmPassword(password.getText()
                                .toString(), rePassword.getText().toString());
//                        if (isValidSignUp(fullName, username, password, rePassword) && isMatchConfirmPassword(password.getText()
//                                .toString(), rePassword.getText().toString())) {
                        // Write a message to the database
                        FirebaseDatabase firebase = FirebaseDatabase.getInstance("https" +
                                "://dbpepperlunch-default" +
                                "-rtdb.asia-southeast1.firebasedatabase.app/");
                        DatabaseReference reference = database.getReference("Database/Customer");
                        String key = reference.push().getKey();

                        String[] arrName = fullName.getText().toString().split(" ");
                        String SURNAME_CUSTOMER = "";
                        for (int i = 0; i < arrName.length - 1; i++) {
                            SURNAME_CUSTOMER += arrName[i];
                        }
                        String NAME = arrName[arrName.length - 1];
                        String DATE_OF_BIRTH = birthday.getText().toString();
                        int GENDER_CUSTOMER = spinnerSex.getSelectedItem().toString() == "Nam" ?
                                1 : 0;
                        String MAIL_CUSTOMER = phoneNumber.getText().toString();
                        String PASSWORD_CUSTOMER = password.getText().toString();
                        int POINT = 0;
                        String DATE_CREATE =
                                Calendar.getInstance().toString();
                        CUSTOMER customer = new CUSTOMER(key, SURNAME_CUSTOMER, NAME,
                                DATE_OF_BIRTH, GENDER_CUSTOMER, "", "", MAIL_CUSTOMER,
                                PASSWORD_CUSTOMER, POINT, "", DATE_CREATE);
                        reference.child(key).setValue(customer);
//                        }
                        bottomSheetDialog.dismiss();
                    });

            bottomSheetDialog.setContentView(bottomSheetSignUpView);
            bottomSheetDialog.show();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CUSTOMER Login(String username, String password) {
        return customers.stream().parallel()
                .filter(customer -> customer.getMAIL_CUSTOMER()
                        .equals(username) && customer.getPASSWORD_CUSTOMER().equals(password))
                .findFirst().orElse(null);
    }

    /**
     * Validate password and confirm password
     * @param password Password
     * @param confirmPassword Confirm password
     * @return
     */
    private boolean isMatchConfirmPassword(String password, String confirmPassword) {
        return password.compareTo(confirmPassword) == 0;
    }

    /**
     * Validate required fields for the sign up form
     * @param fullName Full name text view
     * @param username Username text view
     * @param password Password text view
     * @param rePassword Re-password text view
     * @return
     */
    private boolean isValidSignUp(TextView fullName, TextView username,
                                  TextView password, TextView rePassword) {
        if (Strings.isEmptyOrWhitespace(fullName.getText().toString())) {
            fullName.setError("Nhập họ tên");
            return false;
        } else {
            fullName.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(username.getText().toString())) {
            username.setError("Nhập tên đăng nhâp");
            return false;
        } else {
            username.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(password.getText().toString())) {
            password.setError("Nhập mật khẩu");
            return false;
        } else {
            password.setError(null);
        }

        if (isMatchConfirmPassword(password.getText().toString(), rePassword.getText()
                .toString())) {
            rePassword.setError("Mật khẩu không khớp");
            return false;
        } else {
            rePassword.setError(null);
        }

        return true;
    }

    /**
     * Validate required fields for the login form
     * @param username Username field
     * @param password Password field
     * @return True if fields is valid, otherwise false
     */
    private boolean isValidLogin(TextView username, TextView password) {
        if (Strings.isEmptyOrWhitespace(username.getText().toString())) {
            username.setError("Nhập tên đăng nhâp");
            return false;
        } else {
            username.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(password.getText().toString())) {
            username.setError("Nhập mật khẩu");
            return false;
        } else {
            password.setError(null);
        }
        return true;
    }
}