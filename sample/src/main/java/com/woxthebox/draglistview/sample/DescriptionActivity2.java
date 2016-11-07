package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity2 extends AppCompatActivity {

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
                Intent go = new Intent(DescriptionActivity2.this, GameActivity.class);
                startActivity(go);
            }
        });
        textDescription.setText("Move the car forwards twice, and then turn left!");
        textStage.setText("Stage 2");
        tutorial1Text = (TextView) findViewById(R.id.tutorial1Text);
        tutorial1Text.setText("Remember that methods like moveForward(); are mini-programs that perform tasks." +
                "You can have more than one method in your program! For this stage, you will need three methods." +
                "Good luck!");
    }
}
