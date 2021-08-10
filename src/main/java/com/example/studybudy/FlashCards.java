package com.example.studybudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Makes question set for people to be tested on
public class FlashCards extends AppCompatActivity {

    Button generator;
    private FirebaseAuth auth;
    private String TAG = FlashCards.class.getSimpleName();
    private Boolean ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10;
    private String ques1,ques2,ques3,ques4,ques5,ques6,ques7,ques8,ques9,ques10;
    private EditText correct;
    private Button submit;


    @Override
    //Beginiing of on create has all checkboxes for user answer flip
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        generator = findViewById(R.id.button3);
        auth = FirebaseAuth.getInstance();


        generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCollectionName();
            }
        });

        //check box example
        CheckBox chk1 = findViewById(R.id.checkBox1);
        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView1);
                    tview1.setText("the answer was " + ans1+" for #1");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView1);
                    tview1.setText(ques1);
                }

            }
        });

        CheckBox chk2 = findViewById(R.id.checkBox2);
        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview2 = (TextView) findViewById(R.id.textView2);
                    tview2.setText("The answer was " + ans2+" for #2");
                }
                else{
                    TextView tview2 = (TextView) findViewById(R.id.textView2);
                    tview2.setText(ques2);
                }

            }
        });

        CheckBox chk3 = findViewById(R.id.checkBox3);
        chk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView3);
                    tview1.setText("the answer was " + ans3+" for #3");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView3);
                    tview1.setText(ques3);
                }

            }
        });

        CheckBox chk4 = findViewById(R.id.checkBox4);
        chk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView4);
                    tview1.setText("the answer was " + ans4+" for #4");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView4);
                    tview1.setText(ques4);
                }

            }
        });

        CheckBox chk5 = findViewById(R.id.checkBox5);
        chk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView5);
                    tview1.setText("the answer was " + ans5+" for #5");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView5);
                    tview1.setText(ques5);
                }

            }
        });

        CheckBox chk6 = findViewById(R.id.checkBox6);
        chk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView6);
                    tview1.setText("the answer was " + ans6+" for #6");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView6);
                    tview1.setText(ques6);
                }

            }
        });

        CheckBox chk7 = findViewById(R.id.checkBox7);
        chk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView7);
                    tview1.setText("the answer was " + ans7+" for #7");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView7);
                    tview1.setText(ques7);
                }

            }
        });

        CheckBox chk8 = findViewById(R.id.checkBox8);
        chk8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView8);
                    tview1.setText("the answer was " + ans8+" for #8");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView8);
                    tview1.setText(ques8);
                }

            }
        });

        CheckBox chk9 = findViewById(R.id.checkBox9);
        chk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView9);
                    tview1.setText("the answer was " + ans9+" for #9");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView9);
                    tview1.setText(ques9);
                }

            }
        });

        CheckBox chk10 = findViewById(R.id.checkBox10);
        chk10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                // Check which checkbox was clicked
                if(checked){
                    TextView tview1 = (TextView) findViewById(R.id.textView10);
                    tview1.setText("the answer was " + ans10+" for #10");
                }
                else{
                    TextView tview1 = (TextView) findViewById(R.id.textView10);
                    tview1.setText(ques10);
                }

            }
        });

        correct = findViewById(R.id.editTextTextPersonName2);
        submit = findViewById(R.id.submit);
        handleCorrect();
    }

    //handles submision of self tested quiz results to display visual representation in SimpleChart Activity
    public void handleCorrect(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = correct.getText().toString();
                if(input.isEmpty()){
                    correct.setError("Please enter number");
                    correct.requestFocus();
                }
                if(true){try{
                    int num = Integer.parseInt(input);
                    Intent intent = new Intent(getApplicationContext(), SimpleChart.class);
                    intent.putExtra("EXTRA_CORRECT", input);
                    startActivity(intent);
                }catch (NumberFormatException ex) {
                    correct.setError("Please enter valid number");
                    correct.requestFocus();
                }
                }

            }
        });
    }


    //verify firebase is pulling correct data for questions
    private void verifyCollectionName() {
        String authEmail = auth.getCurrentUser().getEmail();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore
                .collection("questions")
                .whereEqualTo("createdBy", authEmail)
                .whereEqualTo("questionsSet", "malik2") //key
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "onComplete: task is complete");

                            String docId = null;

                            // there is only 1 document always, but needs to be iterated in a for loop
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                docId = document.getId();
                            }

                            getData(docId);
                        } else {
                            Log.e(TAG, "onComplete: task could not be completed => " + task.getException());
                        }
                    }
                });
    }

    //fetches data for flash card testing and sets it in activity based off of position
    private void getData(final String docId) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore
                .collection("questions")
                .document(docId)                            // seJ2Z0iMJNAcdXIqiw3j for test@gmail.com
                .collection("questionsData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshots = task.getResult();

                            // put the questions into the arraylist
                            List<Question> questionList = new ArrayList<>();

                            // get the questions
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Question questionTest = new Question();
                                questionTest.setQuestion(Objects.requireNonNull(document.get("question")).toString());
                                questionTest.setAnswer(Objects.requireNonNull(document.get("answer")).toString());
                                questionTest.setAnswerChoices((ArrayList<String>) document.get("answerChoices"));

                                questionList.add(questionTest);
                            }

                            for (int i = 0; i < questionList.size(); i++) {
                                Log.i(TAG, "Questions => " + questionList.get(i).getQuestion());
                                Log.i(TAG, "Answer => " + questionList.get(i).getAnswer());
                            }

                            //1
                            TextView tview1 = (TextView) findViewById(R.id.textView1);
                            tview1.setText(questionList.get(0).getQuestion());
                            ques1 = questionList.get(0).getQuestion();
                            //2
                            TextView tview2 = (TextView) findViewById(R.id.textView2);
                            tview2.setText(questionList.get(1).getQuestion());
                            ques2 = questionList.get(1).getQuestion();
                            //3
                            TextView tview3 = (TextView) findViewById(R.id.textView3);
                            tview3.setText(questionList.get(2).getQuestion());
                            ques3 = questionList.get(2).getQuestion();
                            //4
                            TextView tview4 = (TextView) findViewById(R.id.textView4);
                            tview4.setText(questionList.get(3).getQuestion());
                            ques4 = questionList.get(3).getQuestion();
                            //5
                            TextView tview5 = (TextView) findViewById(R.id.textView5);
                            tview5.setText(questionList.get(4).getQuestion());
                            ques5 = questionList.get(4).getQuestion();
                            //6
                            TextView tview6 = (TextView) findViewById(R.id.textView6);
                            tview6.setText(questionList.get(5).getQuestion());
                            ques6 = questionList.get(5).getQuestion();
                            //7
                            TextView tview7 = (TextView) findViewById(R.id.textView7);
                            tview7.setText(questionList.get(6).getQuestion());
                            ques7 = questionList.get(6).getQuestion();
                            //8
                            TextView tview8 = (TextView) findViewById(R.id.textView8);
                            tview8.setText(questionList.get(7).getQuestion());
                            ques8 = questionList.get(7).getQuestion();
                            //9
                            TextView tview9 = (TextView) findViewById(R.id.textView9);
                            tview9.setText(questionList.get(8).getQuestion());
                            ques9 = questionList.get(8).getQuestion();
                            //10
                            TextView tview10 = (TextView) findViewById(R.id.textView10);
                            tview10.setText(questionList.get(9).getQuestion());
                            ques10 = questionList.get(9).getQuestion();

                            ans1 = Boolean.parseBoolean(questionList.get(0).getAnswer());
                            ans2 = Boolean.parseBoolean(questionList.get(1).getAnswer());
                            ans3 = Boolean.parseBoolean(questionList.get(2).getAnswer());
                            ans4 = Boolean.parseBoolean(questionList.get(3).getAnswer());
                            ans5 = Boolean.parseBoolean(questionList.get(4).getAnswer());
                            ans6 = Boolean.parseBoolean(questionList.get(5).getAnswer());
                            ans7 = Boolean.parseBoolean(questionList.get(6).getAnswer());
                            ans8 = Boolean.parseBoolean(questionList.get(7).getAnswer());
                            ans9 = Boolean.parseBoolean(questionList.get(8).getAnswer());
                            ans10 = Boolean.parseBoolean(questionList.get(9).getAnswer());


                        } else {
                            Log.e(TAG, "onComplete: unsuccessful getting data");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: getting data ");
                    }
                });
    }

}


