package com.as.minigame;

import android.content.Context;
import android.content.SharedPreferences;


public class DataStore {

    private static final String RECVER_DATA = "com.as.minigame.record_data";
    private static DataStore ds = null;

    private static SharedPreferences sp = null;

    private DataStore(Context context) {
        sp = context.getSharedPreferences(RECVER_DATA, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (ds == null)
            ds = new DataStore(context);
    }

    public static DataStore getInstance() throws Exception {
        if (ds == null) {
            throw new Exception("DataStore has not init");
        }
        return ds;
    }

    public int[][] getCardsNumber() {
        int[][] cardsNumber = new int[4][4];
        for (int y = 0; y < 4; y ++) {
            for (int x = 0; x < 4; x ++) {
                cardsNumber[x][y] = sp.getInt("card" + x + y, 0);
            }
        }
        return cardsNumber;
    }

    public int getScore() {
        int score;
        score = sp.getInt("score", 0);
        return score;
    }

    public void saveData(int score, int[][] cardsNubmber) {
        SharedPreferences.Editor editor = sp.edit();
        for (int y = 0; y < 4; y ++) {
            for (int x = 0; x < 4; x ++) {
                editor.putInt("card" + x + y, cardsNubmber[x][y]);
            }
        }
        editor.putInt("score", score);
        editor.commit();
    }
}
