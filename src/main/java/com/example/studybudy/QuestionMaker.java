package com.example.studybudy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//makes questions and puts them in firebase collections
public class QuestionMaker extends AppCompatActivity {

    private ArrayList<String> questionList;
    String[] answers;
    private Button question_submit;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_maker);

        RecyclerView recyclerView = findViewById(R.id.question_maker_rc);
        recyclerView.setHasFixedSize(true);
        questionList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();


        question_submit = findViewById(R.id.question_submit);

        for (int i = 0; i < 10; i++) {
            questionList.add("");
        }



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        final QuizMakerAdapter adapter = new QuizMakerAdapter(questionList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewCacheSize(questionList.size());

//Refrence stack exchange search "Collection data creation for firebase" then altered and added accordingly
        //submits data to firebase so it can be accessed in flashcards for quizing
        question_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers = adapter.getSelectedAnswer();
                questionList = adapter.getQuestionList();
                System.out.println("Questions");
                for (int i = 0; i < questionList.size(); i ++) {
                    System.out.println(i + ") => " + questionList.get(i));

                }

                System.out.println("Answers");
                for (int i = 0; i < questionList.size(); i ++) {
                    System.out.println(i + ") => " + answers[i]);
                }

                String authEmail = auth.getCurrentUser().getEmail();

                final Map<String, Object> docData = new HashMap<>();
                docData.put("questionsSet", "malik2"); // name of question set make a another textedit button, key
                docData.put("createdBy", authEmail);

                final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                final String docRef = firestore.collection("questions").document().getId();

                firestore
                        .collection("questions")
                        .document(docRef) // create a unique key
                        .set(docData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i("firestore", "onSuccess: docData added successfully into users collection");

                                    ArrayList<Question> questionsFirebase = new ArrayList<>();
                                    ArrayList<String> answerChoices = new ArrayList<>();

                                    answerChoices.add("True");
                                    answerChoices.add("False");

                                    for (int i = 0; i < questionList.size(); i++) {
                                        Question questionObject = new Question(questionList.get(i), answerChoices, answers[i]);
                                        questionsFirebase.add(questionObject);
                                    }


                                    WriteBatch batch = firestore.batch();

                                    for (int i = 0; i < questionsFirebase.size(); i++) {
                                        DocumentReference documentReference = firestore
                                                .collection("questions")
                                                .document(docRef)
                                                .collection("questionsData")
                                                .document();
                                        batch.set(documentReference, questionsFirebase.get(i));
                                    }

                                    batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Log.i("lisrtenr", "onComplete: batch complete, questions added ");
                                            // start next activity
                                            Intent intent = new Intent(getApplicationContext(),FlashCards.class);
                                            startActivity(intent);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("failure", "onFailure: docData did not add");
                                        }
                                    });
                                }
                            }
                        });


            }

        });

    }

    public static class QuizMakerAdapter extends RecyclerView.Adapter<QuizMakerAdapter.QuizMakerViewHolder> {

        private String TAG = QuizMakerAdapter.class.getSimpleName();
        private String[] selectedAnswer;
        private ArrayList<String> questionList;

        public String[] getSelectedAnswer(){
            return selectedAnswer;
        }

        public ArrayList<String> getQuestionList(){
            return questionList;
        }

        QuizMakerAdapter(ArrayList<String> questionList) {
            this.questionList = questionList;
            this.selectedAnswer = new String[this.questionList.size()];
        }

        @NonNull
        @Override
        public QuizMakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.question_maker_view, parent, false);
            return new QuizMakerViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull final QuizMakerViewHolder holder, final int position) {

            // update the position of the get the questions entered
            holder.questionInputTextListener.updatePosition(position);
            // get answer (T/F)
            holder.radioButtonSelectedListener.updatePosition(position);
        }

        @Override
        public int getItemCount() {
            return this.questionList.size();
        }

        private class QuizMakerViewHolder extends RecyclerView.ViewHolder {

            private EditText question_editText;
            private QuestionInputTextListener questionInputTextListener;
            private RadioButtonSelectedListener radioButtonSelectedListener;

            QuizMakerViewHolder(@NonNull View itemView) {
                super(itemView);

                // edit text for the questions
                question_editText = itemView.findViewById(R.id.editTextTextPersonName);
                questionInputTextListener = new QuestionInputTextListener();
                question_editText.addTextChangedListener(questionInputTextListener);

                // radio group for the answers.questionViewTrueFalse_radioGroup
                RadioGroup trueFalse_radioGroup = itemView.findViewById(R.id.question_maker_rg);
                radioButtonSelectedListener = new RadioButtonSelectedListener();
                trueFalse_radioGroup.setOnCheckedChangeListener(radioButtonSelectedListener);
            }
        }

        private class QuestionInputTextListener implements TextWatcher {

            private int position;   // the position of the recycler

            private void updatePosition(int position) {
                this.position = position;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // need this
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // update question position
                questionList.set(position,s.toString());
                Log.e(TAG, "text watcher - position: " + position + " => " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // need this
            }
        }

        //radio group listener position
        private class RadioButtonSelectedListener implements RadioGroup.OnCheckedChangeListener {

            private int position;   // the position of the recycler

            private void updatePosition(int position) {
                this.position = position;
            }

            @Override
            //Get selected answer from the radio group for tracking
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                try {
                    int selectedRadioButton = group.getCheckedRadioButtonId();
                    View view = group.getRootView();
                    RadioButton radioButton = view.findViewById(selectedRadioButton);
                    String selectedValue = radioButton.getText().toString();

                    // set the answer for the question
                    selectedAnswer[this.position] = selectedValue;
                    Log.e(TAG, "Radio group: position: " + position + " => " + selectedValue);
                } catch (Exception e) {
                    Log.e(TAG, "onCheckedChanged: no value select yet");
                }
            }
        }
    }
}