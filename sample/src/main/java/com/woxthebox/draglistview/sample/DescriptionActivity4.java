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
                Intent go = new Intent(DescriptionActivity4.this, GameActivity.class);
                startActivity(go);
            }
        });
        tutorial1Text = (TextView) findViewById(R.id.tutorial1Text);
        tutorial1Text.setText("You can use more than one For Loop in your program too! Remember, you still need to close " +
                "all the For Loops that you use with the } bracket! For this program, you should keep each For Loop seperate" +
                "(Make sure you close the first For Loop before starting the second one). Good Luck!");
        textDescription.setText("Move the car forward 4 times, then backward 4 times. You only have one two move blocks!");
        textStage.setText("Stage 4");
    }
}
