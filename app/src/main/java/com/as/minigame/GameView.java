package com.as.minigame;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends GridLayout {

    private Card cards[][] = new Card[4][4];
    private int[][] cardsNumber = new int[4][4];

    private List<Point> emptyCards = new ArrayList<Point>();
    Random rd = new Random();
    int score = 0;

    public GameView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initGame();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initGame();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initGame();
    }

    private void initGame() {
        setColumnCount(4);
        setBackgroundColor(Color.WHITE);



    }




   public void moveRight() {

        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    // 当同一行为空，不需处理
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            // 将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x++;
                            flage = true;
                            score +=2;
                        } else if (cards[x][y].getNumber() == cards[x1][y].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score +=cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage = true;
                        }
                        break;
                    }

                }
            }
        }

        if (flage) {
            creatRandomCard();
        }
    }

    public  void moveLeft() {

        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    // 当同一行为空，不需处理
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            // 将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x1][y].getNumber());cards[x1][y].setNumber(0);
                            x--;
                            flage = true;
                            score +=2;
                        } else if (cards[x][y].getNumber() == cards[x1][y]
                                .getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score +=cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage = true;
                        }
                        break;
                    }

                }
            }
        }

        if (flage) {
            creatRandomCard();
        }

    }

    public  void moveDown() {
        boolean flage = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    // 当同一行为空，不需处理
                    if (cards[x][y1].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            // 将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y++;
                            flage = true;
                            score +=2;
                        } else if (cards[x][y].getNumber() == cards[x][y1].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score +=cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage = true;
                        }
                        break;
                    }

                }
            }
        }

        if (flage) {
            creatRandomCard();
        }

    }

    public  void moveUp() {
        boolean flage = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {

                    if (cards[x][y1].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {

                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y--;
                            flage = true;
                            score+=2;
                        } else if (cards[x][y].getNumber() == cards[x][y1]
                                .getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score +=cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage = true;
                        }
                        break;
                    }

                }
            }
        }

        if (flage) {
            creatRandomCard();
        }

    }

    private void AddCard(int width, int height) {

        Card c;
        for (int y = 0; y < 4; y++) {

            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                cards[x][y] = c;
                c.setNumber(0);
                addView(c, width, height);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        int width = (w - 10) / 4;
        int height=(h-10)/4;
        AddCard(width, height);
        GameStartWithRecord();
    }


    private void creatRandomCard() {

        emptyCards.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNumber() < 2) {
                    Point point = new Point(x, y);
                    emptyCards.add(point);
                }
            }

        }
        int selat = rd.nextInt(emptyCards.size());
        Point p = emptyCards.get(selat);
        emptyCards.remove(selat);
        int number = 0;
        if (rd.nextInt(4) > 3) {
            number = 4;
        } else
            number = 2;
        cards[p.x][p.y].setNumber(number);
    }

    public void Gameover(){
        boolean OverGame=true;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(cards[x][y].getNumber() <= 0 ||
                        (x >0 && cards[x][y].getNumber() == cards[x-1][y].getNumber())||
                        (x<3 && cards[x][y].getNumber() == cards[x+1][y].getNumber()) ||
                        (y>0 && cards[x][y].getNumber() == cards[x][y-1].getNumber()) ||
                        (y<3 && cards[x][y].getNumber() == cards[x][y+1].getNumber())
                        ){
                    OverGame=false;
                }
            }
        }
        if(OverGame){
            new AlertDialog.Builder(getContext()).setTitle("You have lost!").setMessage("Play Again?").
                    setPositiveButton("yes",new AlertDialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                            GameStart();
                            score = 0;
                        }
                    }).setNegativeButton("No", null).show();
        }
    }

    public void GameStart() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cards[x][y].setNumber(0);
            }
        }
        creatRandomCard();
        creatRandomCard();
    }



    public int[][] getCardsNumber() {
        int[][] numbers = new int[4][4];
        for (int y = 0; y < 4; y ++) {
            for (int x = 0; x < 4; x ++) {
                numbers[x][y] = cards[x][y].getNumber();
            }
        }
        return numbers;
    }

    public void initRecord(int score, int[][] cardsNumber) {
        this.score = score;
        this.cardsNumber = cardsNumber;
    }

    public void GameStartWithRecord() {
        boolean allZero=true;
        for (int y = 0; y < 4; y ++) {
            for (int x = 0; x < 4; x ++) {
                if(cardsNumber[x][y]!=0)
                    allZero=false;
                cards[x][y].setNumber(cardsNumber[x][y]);
            }
        }
        if(allZero==true)
        {
            creatRandomCard();
            creatRandomCard();
        }

    }
}

