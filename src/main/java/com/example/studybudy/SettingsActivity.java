package com.example.studybudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//Handles help,about, and password change for user
public class SettingsActivity extends AppCompatActivity {
    private Button about;
    private Button help;
    private TextView settingView;
    EditText  password;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        about = findViewById(R.id.aboutButton);
        help = findViewById(R.id.helpButton);
        password = findViewById(R.id.editText2);
        change = findViewById(R.id.passChange);

        //password change for user
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String pwd = password.getText().toString();
                user.updatePassword(pwd);
                Toast.makeText(SettingsActivity.this,"Password Changed!",Toast.LENGTH_SHORT).show();
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tview1 = (TextView) findViewById(R.id.settingTextView);
                tview1.setText("This is a study application meant for busy students so that they can quickly " +
                        "make flash card quizzes on the go");
            }
        });

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tview1 = (TextView) findViewById(R.id.settingTextView);
                tview1.setText("Sorry since this is a unpaid project I can offer you no help, only a 'Good luck!'");
            }
        });
    }
}