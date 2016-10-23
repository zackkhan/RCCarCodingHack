/**
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView stageText;
    TextView descriptionText;
    String stageTextString = "";
    String descriptionTextString = "";
    Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showFragment(BoardFragment.newInstance());
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_color)));
        generateLevelInformation(StageActivity.whichStage);
        runButton = (Button) findViewById(R.id.runButton);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("are we running3");
                for (String stringData : BoardFragment.methodValuesRight){
                    System.out.println("run " + stringData);
                }
                //BoardFragment.sendDataArduino();

            }
        });
        /*stageText = (TextView) findViewById(R.id.text_stage_number);
        descriptionText = (TextView) findViewById(R.id.text_stage_directions);
        stageText.setText(stageTextString);
        descriptionText.setText(descriptionTextString);*/
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "fragment").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean listFragment = getSupportFragmentManager().findFragmentByTag("fragment") instanceof ListFragment;
        menu.findItem(R.id.action_lists).setVisible(!listFragment);
        menu.findItem(R.id.action_board).setVisible(listFragment);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lists:
                showFragment(ListFragment.newInstance());
                return true;
            case R.id.action_board:
                showFragment(BoardFragment.newInstance());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void generateLevelInformation(int whichStage){
        Log.d("myTag", "Playing level " + whichStage);
        switch (whichStage){
            case 1:
                stageTextString = "Stage 1";
                descriptionTextString = "For this stage, move the car forwards five times. Be careful, you only have one move() block!";
                break;
        }
    }
}
