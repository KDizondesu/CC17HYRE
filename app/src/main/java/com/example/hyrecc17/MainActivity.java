package com.example.hyrecc17;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button applicantButton;
    private Button recruiterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicantButton = findViewById(R.id.applicant_button);
        recruiterButton = findViewById(R.id.recruiter_button);

        applicantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle applicant button click
                Intent intent = new Intent(MainActivity.this, ApplicantLogin.class);
                startActivity(intent);
            }
        });

        recruiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle recruiter button click
                Intent intent = new Intent(MainActivity.this, RecruiterLogin.class);
                startActivity(intent);
            }
        });
    }
}

