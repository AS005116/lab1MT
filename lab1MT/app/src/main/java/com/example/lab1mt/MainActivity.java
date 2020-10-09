package com.example.lab1mt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int CountOp;
    private int[] ResDraw = {
            R.drawable.ic_1,
            R.drawable.ic_2,
            R.drawable.ic_3,
            R.drawable.ic_4,
            R.drawable.ic_5,
            R.drawable.ic_6
    };
    private ArrayList<Integer> IsEq = new ArrayList<Integer>();
    private ArrayList<Integer> ResBtn = new ArrayList<Integer>(){
        {
            add(R.id.btn1); add(R.id.btn2); add(R.id.btn3); add(R.id.btn4);
            add(R.id.btn5); add(R.id.btn6); add(R.id.btn7); add(R.id.btn8);
            add(R.id.btn9); add(R.id.btn10); add(R.id.btn11); add(R.id.btn12);
        }
    };
    private int[] Tags = {
            0,1,2,3,
            4,5,4,5,
            3,2,1,0
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Start();
    }
    private void Start(){
        RandomTags();
        SetBackBtn();
    }
    private void RandomTags(){
        Random rand = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Integer item : Tags){
            list.add(item);
        }
        for (int i = 0; i < Tags.length; i++){
            int count = rand.nextInt(list.size());
            Tags[i] = list.get(count);
            list.remove(count);
        }
    }
    private void SetBackBtn(){
        for (int i = 0; i < ResBtn.size(); i++){
            button = (Button) findViewById(ResBtn.get(i));
            button.setBackgroundResource(R.drawable.ic_base);
        }
    }
    private void Reset(){
        SetBackBtn();
        CountOp = 0;
        IsEq = new ArrayList<Integer>();
    }
    public void OnRestartClick(View v){
        Start();
        for (int i = 0; i < ResBtn.size(); i++){
            button = (Button) findViewById(ResBtn.get(i));
            button.setVisibility(View.VISIBLE);
        }
    }
    public void OnClick(View v) {
        if(CountOp < 2){
            CountOp++;
            button = (Button) findViewById(v.getId());
            button.setBackgroundResource(ResDraw[Tags[ResBtn.indexOf(v.getId())]]);
            IsEq.add(ResBtn.indexOf(v.getId()));
        }
        else return;
        if (CountOp == 2){
            if(ResBtn.get(IsEq.get(0)).equals(ResBtn.get(IsEq.get(1)))){
                Reset();
                return;
            }
            new CountDownTimer(1000,500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(Tags[IsEq.get(0)] == Tags[IsEq.get(1)]){
                        new CountDownTimer(500,500) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                button = (Button) findViewById(ResBtn.get(IsEq.get(0)));
                                button.setVisibility(View.INVISIBLE);
                                button = (Button) findViewById(ResBtn.get(IsEq.get(1)));
                                button.setVisibility(View.INVISIBLE);
                                Reset();
                            }
                        }.start();
                    }
                }
                @Override
                public void onFinish() {
                    Reset();
                }
            }.start();
        }
    }
}