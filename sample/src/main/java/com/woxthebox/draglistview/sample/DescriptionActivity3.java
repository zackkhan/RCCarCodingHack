package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity3 extends AppCompatActivity {

    Button nextButton;
    TextView textDescription;
    TextView textStage;
 TextView tutorial1Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        nextButton = (Button) findViewById(R.id.next_button);
        textDescription = (TextView) findViewById(R.id.text_description);
        textStage = (TextView) findViewById(R.id.text_stage);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(DescriptionActivity3.this, GameActivity.class);
                startActivity(go);
            }
        });
        textDescription.setText("Move the car forwards five times. Be careful, you only have one move() block!");
        textStage.setText("Stage 3");
        tutorial1Text = (TextView) findViewById(R.id.tutorial1Text);
        tutorial1Text.setText("Having to add each method to your program EVERY time you want to use it would take" +
                "a really long time when you want to do the same method over and over again! Luckily," +
                "we have loops in programming that allow you to run the same method multiple times. In this stage," +
                "you will see the use of a For Loop, that looks like this: for(i=0; i<5; i++){. Pay attention to the 5!" +
                "That 5 means that the methods that you add below will run 5 times! Also, pay attention to the { bracket at " +
                "the end of the loop. You need to close that bracket so the program knows when the loop ends! So be sure to add" +
                "the closing bracket } at the end of your program. Good Luck!");
    }
}
