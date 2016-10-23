package com.woxthebox.draglistview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class StageActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<StageButton> stageButtonList = new ArrayList<StageButton>();
    public static int whichStage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
        makeLevels();
        recyclerView = (RecyclerView)findViewById(R.id.cards);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        StageHolder adapter = new StageHolder(getApplicationContext(),stageButtonList);
        recyclerView.setAdapter(adapter);
    }

    private void makeLevels(){
        for (int i = 1;i <= 20;i++){
            stageButtonList.add(new StageButton(i + ""));
        }
    }
}
