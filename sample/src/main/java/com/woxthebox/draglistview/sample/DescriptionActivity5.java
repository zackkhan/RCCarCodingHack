package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity5 extends AppCompatActivity {

    Button nextButton;
    TextView textDescription;
    TextView textStage;
    static TextView tutorial1Text;
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
                Intent go = new Intent(DescriptionActivity5.this, GameActivity.class);
                startActivity(go);
            }
        });
        textDescription.setText("Move the car forwards 5 times, then two times backwards, then left once. You can only use three move blocks!");
        textStage.setText("Stage 5");
        tutorial1Text = (TextView) findViewById(R.id.tutorial1Text);
        tutorial1Text.setText("Pay attention to each For Loop, they are different this time! For example, for(i=0; i<2; i++)" +
                " will run each method inside the brackets twice. The numbers are important! Also, dont forget that you can also still" +
                "have methods in your program that are outside the For Loops. Good Luck!");
    }
}
