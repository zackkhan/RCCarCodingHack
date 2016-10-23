package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.woxthebox.draglistview.sample.GameActivity.descriptionTextString;
import static com.woxthebox.draglistview.sample.GameActivity.stageTextString;

public class DescriptionActivity extends AppCompatActivity {

    Button nextButton;
    static TextView textStage;
    static TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        textStage = (TextView) findViewById(R.id.text_stage);
        textDescription = (TextView) findViewById(R.id.text_description);
        textDescription.setText("Move the car forwards once");
        textStage.setText("Stage 1");
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(DescriptionActivity.this, GameActivity.class);
                startActivity(go);
            }
        });
    }
}
