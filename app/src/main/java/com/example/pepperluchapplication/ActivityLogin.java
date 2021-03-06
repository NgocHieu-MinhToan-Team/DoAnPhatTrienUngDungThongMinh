package com.example.pepperluchapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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
import com.google.android.gms.common.util.Strings;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class ActivityLogin extends AppCompatActivity {

    private Button buttonLogin, buttonSignup;
    private ImageView logo;
    private ArrayList<CUSTOMER> customers = new ArrayList<>();

    @SuppressLint("NewApi")
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

                                // Save customer information
                                //MyApplication.setCustomer(userLogin);
                                SharedPreferences sharedPreferences = getSharedPreferences(
                                        "USER",
                                        MODE_PRIVATE);

                                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(userLogin);
                                prefsEditor.putString("CUSTOMER", json);
                                prefsEditor.commit();


                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                                bottomSheetDialog.dismiss();
                            } else {
                                Toast.makeText(ActivityLogin.this, "T??n ????ng nh???p ho???c m???t kh???u " +
                                                        "kh??ng ????ng",
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
                DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> txtBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                // Create DatePickerDialog (Spinner Mode):
                Calendar calendar = Calendar.getInstance();
                @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog =
                        new DatePickerDialog(this,
                                dateSetListener, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            });

            txtBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
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
                                new DatePickerDialog(ActivityLogin.this,
                                        dateSetListener, calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

                        datePickerDialog.show();
                    }
                }
            });


            bottomSheetSignUpView.findViewById(R.id.button_signup)
                    .setOnClickListener(view -> {
                        TextView fullName =
                                bottomSheetSignUpView.findViewById(R.id.txt_fullname_signup),
                                birthday = bottomSheetSignUpView.findViewById(R.id.txt_birthday),
                                address = bottomSheetSignUpView.findViewById(R.id.txt_address),
                                phoneNumber = bottomSheetSignUpView.findViewById(R.id.txt_phone_number),
                                password = bottomSheetSignUpView.findViewById(R.id.txt_pwd_signup),
                                confirmPassword = bottomSheetSignUpView.findViewById(R.id.txt_confirm_password);
                        Spinner spinnerSex = bottomSheetSignUpView.findViewById(R.id.spinner_sex);
                        boolean a = isValidSignUp(fullName, phoneNumber, password, confirmPassword);
                        boolean b = isMatchConfirmPassword(password.getText()
                                .toString(), confirmPassword.getText().toString());
                        if (isValidSignUp(fullName, phoneNumber, password, confirmPassword)) {
                            DatabaseReference reference = database.getReference("Database/Customer");

                            // Get the information to be filled in
                            String key = reference.push().getKey();
                            String[] splitFullName = splitFullName(fullName.getText().toString());
                            String SURNAME_CUSTOMER = splitFullName[0];
                            String NAME_CUSTOMER = splitFullName[1];
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String DATE_OF_BIRTH =
                                    null;
                            try {
                                DATE_OF_BIRTH = formatter.format(new SimpleDateFormat("dd/mm/yyyy").parse(birthday.getText()
                                        .toString()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            int GENDER_CUSTOMER = spinnerSex.getSelectedItem().toString() == "Nam" ?
                                    1 : 0;
                            String ADDRESS_CUSTOMER = address.getText().toString();
                            String PHONE_CUSTOMER = phoneNumber.getText().toString();
                            String PASSWORD_CUSTOMER = sha256(password.getText().toString());
                            int POINT = 0;
                            String DATE_CREATE = formatter.format(new Date());
                            CUSTOMER customer = new CUSTOMER(key, SURNAME_CUSTOMER, NAME_CUSTOMER,
                                    DATE_OF_BIRTH, GENDER_CUSTOMER, ADDRESS_CUSTOMER, PHONE_CUSTOMER,
                                    PASSWORD_CUSTOMER, POINT, DATE_CREATE);

                            try {
                                reference.child(key).setValue(customer);
                                bottomSheetDialog.dismiss();
                                Toast.makeText(ActivityLogin.this, "????ng k?? t??i kho???n th??nh c??ng",
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception ex) {
                                Toast.makeText(this, "Something went wrong: " + ex.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                    });

            bottomSheetDialog.setContentView(bottomSheetSignUpView);
            bottomSheetDialog.show();
        });


        // Check if customer information exists, then automatically log in
        Gson gson = new Gson();
        String json = getSharedPreferences("USER", MODE_PRIVATE).getString("CUSTOMER", "");
        if (!json.equals("")) {
            Intent intent = new Intent(ActivityLogin.this, ActivityHomePage.class);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CUSTOMER Login(String phoneNumber, String password) {
        return customers.stream().parallel()
                .filter(customer -> customer.getPHONE_CUSTOMER()
                        .equals(phoneNumber) && customer.getPASSWORD_CUSTOMER()
                        .equals(sha256(password)))
                .findFirst().orElse(null);
    }

    /**
     * Split the full name string into last name and first name
     *
     * @param fullName
     * @return String[0] is Last name, String[1] is First name
     */
    public static String[] splitFullName(String fullName) {
        String surname = "", name = "";
        if (fullName.split(" ").length == 1) {
            name = fullName.split(" ")[0];
        } else {
            surname = fullName.substring(0, fullName.lastIndexOf(" "));
            name = fullName.substring(fullName.lastIndexOf(" ") + 1);
        }

        return new String[]{surname, name};
    }

    /**
     * Hashing the string using the SHA-256 . algorithm
     *
     * @param s Input
     * @return
     */
    public static String sha256(String s) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes(Charset.forName("UTF-8")));

            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            result = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Validate password and confirm password
     *
     * @param password        Password
     * @param confirmPassword Confirm password
     * @return
     */
    public static boolean isMatchConfirmPassword(String password, String confirmPassword) {
        return password.compareTo(confirmPassword) == 0;
    }

    /**
     * Validate Vietnam phone number
     *
     * @param phoneNumber Phone number
     * @return True if phone number is valid, otherwise false
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(0[3|5|7|8|9])+([0-9]{8})\\b");
        return pattern.matcher(phoneNumber).find();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isExistPhoneNumber(String phoneNumber) {
        return customers.stream().parallel()
                .filter(customer -> customer.getPHONE_CUSTOMER()
                        .equals(phoneNumber))
                .findFirst().orElse(null) != null;
    }

    /**
     * Validate required fields for the sign up form
     *
     * @param fullName   Full name text view
     * @param username   Username text view
     * @param password   Password text view
     * @param rePassword Re-password text view
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isValidSignUp(TextView fullName, TextView username,
                                  TextView password, TextView rePassword) {
        if (Strings.isEmptyOrWhitespace(fullName.getText().toString())) {
            fullName.setError("Nh???p h??? t??n");
            return false;
        } else {
            fullName.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(username.getText().toString())) {
            username.setError("Nh???p t??n ????ng nh??p");
            return false;
        } else if (!isValidPhoneNumber(username.getText().toString())) {
            username.setError("S??? ??i???n tho???i kh??ng h???p l???");
            return false;
        } else if (isExistPhoneNumber(username.getText().toString())) {
            username.setError("S??? ??i???n tho???i ???? t???n t???i");
            return false;
        } else {
            username.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(password.getText().toString())) {
            password.setError("Nh???p m???t kh???u");
            return false;
        } else {
            password.setError(null);
        }

        if (!isMatchConfirmPassword(password.getText().toString(), rePassword.getText()
                .toString())) {
            rePassword.setError("M???t kh???u kh??ng kh???p");
            return false;
        } else {
            rePassword.setError(null);
        }

        return true;
    }

    /**
     * Validate required fields for the login form
     *
     * @param username Username field
     * @param password Password field
     * @return True if fields is valid, otherwise false
     */
    private boolean isValidLogin(TextView username, TextView password) {
        if (Strings.isEmptyOrWhitespace(username.getText().toString())) {
            username.setError("Nh???p t??n ????ng nh??p");
            return false;
        } else {
            username.setError(null);
        }

        if (Strings.isEmptyOrWhitespace(password.getText().toString())) {
            password.setError("Nh???p m???t kh???u");
            return false;
        } else {
            password.setError(null);
        }
        return true;
    }
}