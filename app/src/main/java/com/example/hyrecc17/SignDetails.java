package com.example.hyrecc17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignDetails extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signdetails);

        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Add validation logic here
                if(validateInput(firstName, lastName, email, password)) {

                    // Create a new user object
                    User newUser = new User(firstName, lastName, email, password);

                    // Show loading indicator
                    ProgressDialog progressDialog = new ProgressDialog(SignDetails.this);
                    progressDialog.setMessage("Creating account...");
                    progressDialog.show();

                    // Call signup API
                    ApiClient.getInstance().signup(newUser, new ApiCallback<SignupResponse>() {
                        @Override
                        public void onSuccess(SignupResponse response) {
                            progressDialog.dismiss();

                            // Save user data to shared preferences
                            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("user_id", response.getUserId());
                            editor.putString("token", response.getToken());
                            editor.apply();

                            // Navigate to main activity
                            Intent intent = new Intent(SignDetails.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            progressDialog.dismiss();
                            Toast.makeText(SignDetails.this,
                                    "Signup failed: " + error,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
