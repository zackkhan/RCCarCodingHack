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

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.woxthebox.draglistview.sample.StageActivity.whichStage;

public class GameActivity extends AppCompatActivity {
    public final String TAG = "Main";

    private SeekBar elevation;
    private TextView debug;
    private TextView status;
    private Bluetooth bt;
    TextView stageText;
    TextView descriptionText;
  public static String stageTextString = "";
    public static String descriptionTextString = "";
    Button runButton;


    public void connectService(){
        try {
           // status.setText("Connecting...");
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("HC-06");
                Log.d(TAG, "Btservice started - listening");
             //   status.setText("Connected");
            } else {
                Log.w(TAG, "Btservice started - bluetooth is not enabled");
             //   status.setText("Bluetooth Not enabled");
            }
        } catch(Exception e){
            Log.e(TAG, "Unable to start bt ",e);
            //status.setText("Unable to connect " +e);
        }
    }


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d(TAG, "MESSAGE_WRITE ");
                    break;
                case Bluetooth.MESSAGE_READ:
                    Log.d(TAG, "MESSAGE_READ ");
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d(TAG, "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d(TAG, "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };

    public void readIn()
    {
        for (String element: BoardFragment.methodValuesRight)
        {
if (element.contains("Forward()"))
    stepForward();
 else if (element.contains("Backward()"))
     stepBackward();
 else  if (element.contains("Left()"))
       turnLeft();
 else if (element.contains("Right()"))
     turnRight();
else if (element.contains ("for{"))
{
    String Loops = element.substring(element.indexOf('<'), element.indexOf(';'));
    int numofLoops = Integer.parseInt(Loops);
    String nextelement = BoardFragment.methodValuesRight.get(BoardFragment.methodValuesRight.indexOf(element));
    for (int i =0; i<5; i++)
    {
      readIn(nextelement);
    }
//"for(int i = 0;i < 5;i++){"

        }

    }}

    private void executeForLoop() {
        int numofloops;
    }
    private void stepForward() {
       bt.sendMessage("1");
        System.out.println("ITS WORKING");
    }
    private void stepBackward()
    {
        bt.sendMessage("2");
    }
    private void turnLeft() {
        bt.sendMessage("3");
    }
    private void turnRight()
    {

    }
    public void readIn(String s)
    {
        if (s.contains("Forward()"))
            stepForward();
        else if (s.contains("Backward()"))
            stepBackward();
        else  if (s.contains("Left()"))
            turnLeft();
        else if (s.contains("Right()"))
            turnRight();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showFragment(BoardFragment.newInstance());
        }
        bt = new Bluetooth(this, mHandler);
        connectService();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_color)));
        generateLevelInformation(whichStage);
        runButton = (Button) findViewById(R.id.runButton);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("are we running3");
                for (String stringData : BoardFragment.methodValuesRight) {
                    System.out.println("run " + stringData);
                }
                readIn();
                isCorrect();
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

    public static String [] generateLevelInformation(int whichStage) {
        Log.d("myTag", "Playing level " + whichStage);

        switch (whichStage) {
            case 1:
                stageTextString = "Stage 1";
                descriptionTextString = "Move the car forwards once!";
                String [] methodValues = {"stepForward();", "stepBackward();", "turnLeft();", "turnRight();"};
                return methodValues;

            case 2:
                stageTextString = "Stage 2" ;
                descriptionTextString = "Move the car forward twice, and then turn left!";
                String [] methodValues2 = {"stepForward();", "stepForward();","stepBackward();", "turnLeft();", "turnRight();"};
                return methodValues2;

            case 3:
                stageTextString = "Stage 3";
                descriptionTextString = "Move the car forwards five times. Be careful, you only have one move() block!";
                String [] methodValues3 = {"stepForward();", "stepBackward();", "turnLeft();", "turnRight();",  "for(i=0; i<5; i++)"};
                return methodValues3;
            case 4:
                stageTextString = "Stage 4";
                descriptionTextString = "Move the car forwards 4 times, then move left 4 times. Be careful, you only have one two move blocks!";
                String [] methodValues4 = {"stepForward();", "turnLeft();", "for(i=0; i<4; i++", "for(i=0; i<4; i++" };
                return methodValues4;

            case 5:
                stageTextString = "Stage 5";
                descriptionTextString = "Move the car forwards 5 times, then move left 4 times. Be careful, you only have one two move blocks!";
                String [] methodValues5 = {"stepForward();", "turnLeft();", "for(i=0; i<5; i++", "for(i=0; i<4; i++" };
                return methodValues5;



            case 6:
                stageTextString = "Stage 6" ;
                descriptionTextString = "Move the car in a square, making four steps for each side of the square";
                String [] methodValues6 = {"stepForward();", "stepBackward();", "turnLeft();", "turnRight(); for(i=0; i<4; i++",
                "for(i=0; i<4; i++"}; //needs a nested for loop lol

                return methodValues6;





        }
        return new String[0];
    }

        public boolean isCorrect()
    {
        ArrayList<String> correctAnswer = new ArrayList<String>();
        switch (whichStage) {
            case 1:
           correctAnswer.add("stepForward();");
if (correctAnswer.equals(BoardFragment.methodValuesRight))
    Toast.makeText(GameActivity.this,"You are correct! Great job!", Toast.LENGTH_LONG).show();
                else
    Toast.makeText(GameActivity.this,"You are incorrect! Please try again!", Toast.LENGTH_LONG).show();

        return true;

            case 2:
                correctAnswer.add("stepForward();");
                correctAnswer.add ("stepForward();");
                correctAnswer.add ("turnLeft();");

                if (correctAnswer.equals(BoardFragment.methodValuesRight))
                    Toast.makeText(GameActivity.this,"You are correct! Great job!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(GameActivity.this,"You are incorrect! Please try again!", Toast.LENGTH_LONG).show();

            case 3:
                correctAnswer.add("for(i=0; i<5; i++)");
                correctAnswer.add ("stepForward();");

                if (correctAnswer.equals(BoardFragment.methodValuesRight))
                    Toast.makeText(GameActivity.this,"You are correct! Great job!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(GameActivity.this,"You are incorrect! Please try again!", Toast.LENGTH_LONG).show();
        }
        return false;
    }




}

