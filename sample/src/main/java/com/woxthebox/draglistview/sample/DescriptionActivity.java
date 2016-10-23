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
TextView stageName;
    TextView stageDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        stageName = (TextView) findViewById(R.id.stageName);
        stageName.setText(stageTextString);
        stageDescription = (TextView) findViewById(R.id.stageDescription);
        stageDescription.setText(descriptionTextString);

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
