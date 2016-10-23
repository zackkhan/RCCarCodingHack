package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity4 extends AppCompatActivity {

    Button nextButton;
    TextView textDescription;
    TextView textStage;

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
                Intent go = new Intent(DescriptionActivity4.this, GameActivity.class);
                startActivity(go);
            }
        });
        textDescription.setText("Move the car forwards 4 times, then move backwards 4 times. Be careful, you only have one two move blocks!");
        textStage.setText("Stage 4");
    }
}
