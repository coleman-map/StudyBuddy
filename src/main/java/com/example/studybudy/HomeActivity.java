package com.example.studybudy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

//Home for user to select where they want to go
public class HomeActivity extends AppCompatActivity {
    Button btnLogout;
    Button questMaker;
    Button flashCard;
    FirebaseAuth mFirebaseAuth;
    Button set;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout = findViewById(R.id.logout);
        questMaker = findViewById(R.id.makeQuestions);
        flashCard = findViewById(R.id.FlashCard);
        set = findViewById(R.id.settingButton);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("");
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

        questMaker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuestionMaker.class);
                startActivity(intent);
            }
        });

        flashCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FlashCards.class);
                startActivity(intent);
            }
        });

        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}