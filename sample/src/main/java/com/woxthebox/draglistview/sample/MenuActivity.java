package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button stageSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        stageSelectBtn = (Button) findViewById(R.id.stage_select_btn);
        stageSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StageIntent = new Intent(MenuActivity.this, StageActivity.class);
                MenuActivity.this.startActivity(StageIntent);

            }
        });
    }
}
