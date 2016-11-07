package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity6 extends AppCompatActivity {

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
                Intent go = new Intent(DescriptionActivity6.this, GameActivity.class);
                startActivity(go);
            }
        });
        textDescription.setText("Move the car in a zig zag, making it go forward and right three times");
        textStage.setText("Stage 6");
        tutorial1Text = (TextView) findViewById(R.id.tutorial1Text);
        tutorial1Text.setText("Remember, you can put more than one method within a For Loop! Good Luck!");
    }
}
