package com.as.minigame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends Activity implements OnClickListener{
    TextView score_show;
    GameView gv;
    Button btn_restart;
    Button btn_up,btn_down,btn_left,btn_right;
    DataStore dataStore;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int num =msg.arg1;
            score_show.setText(num+"");

        }


    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        dataStore.saveData(gv.score,gv.getCardsNumber());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        dataStore.saveData(gv.score,gv.getCardsNumber());
        super.onDestroy();

    }

    @Override


    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btn_restart.setOnClickListener(this);
        gv.initRecord(dataStore.getScore(),dataStore.getCardsNumber());




        Timer timer =new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.arg1 = gv.score ;
                handler.sendMessage(msg);
            }
        }, 80, 150);
        score_show.setText(dataStore.getScore()+"");
    }

    public void initView(){
        score_show = (TextView) findViewById(R.id.tv_score_show);
        gv =(GameView) findViewById(R.id.gv_show);
        btn_restart=(Button)findViewById(R.id.btn_restart);
        btn_up=(Button)findViewById(R.id.btn_up);
        btn_down=(Button)findViewById(R.id.btn_down);
        btn_left=(Button)findViewById(R.id.btn_left);
        btn_right=(Button)findViewById(R.id.btn_right);
        btn_down.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_up.setOnClickListener(this);
        dataStore.init(this);
        try {
            dataStore=DataStore.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_down:
                gv.moveDown();
                break;
            case R.id.btn_right:
                gv.moveRight();
                break;
            case R.id.btn_left:
                gv.moveLeft();
                break;
            case R.id.btn_up:
                gv.moveUp();
                break;
            case R.id.btn_restart:
                gv.GameStart();
                gv.score = 0;
                break;


        }
        gv.Gameover();

    }

    @Override
    protected void onStop() {
        dataStore.saveData(gv.score,gv.getCardsNumber());
        super.onStop();
    }
}
