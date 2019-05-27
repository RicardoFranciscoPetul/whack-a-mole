package com.example.toppo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    int count = 0, count2 = 0, count3 = 0, target = 0, target2 = 0, target3 = 0, score = 0, time = 60,
            maxPosition1 = 0, minPosition2 = 0, maxPosition2 = 0;
    TextView tv_score, tv_timer, txt_user;
    Vibrator vibrate;
    Button[] button = new Button[10];
    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_score = findViewById(R.id.score);
        tv_timer = findViewById(R.id.tv_timer);
        txt_user = findViewById(R.id.txt_user);
        vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        user = "";
        user = getIntent().getStringExtra("userName");
        tv_score.setText("0");
        txt_user.setText(user);

        declaringButtons();
        timer();
        lessSixtySeconds();
        lessThirtyFiveSeconds();
        lessTwentySeconds();
    }

    public void declaringButtons() {
        for (int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            button[i] = findViewById(id);
        }
    }

    private void lessSixtySeconds() {
        new Thread() {
            @Override
            public void run() {
                while (count < 1000) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (time > 35) {
                                    maxPosition1 = 9;
                                    target = randomNumber(1, maxPosition1);
                                } else if (time > 20 && time <= 35) {
                                    maxPosition1 = 4;
                                    target = randomNumber(1, maxPosition1);
                                } else if (time > 0 && time <= 20) {
                                    maxPosition1 = 3;
                                    target = randomNumber(1, maxPosition1);
                                } else if (time == 0) {
                                    target = 0;
                                    //changeImage(target, maxPosition1);
                                    count = 1000;
                                }
                                changeImage(target, maxPosition1);
                                count++;
                            }
                        });
                        Thread.sleep(randomNumber(900, 1000));
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    private void lessThirtyFiveSeconds() {
        new Thread() {
            @Override
            public void run() {
                while (count3 < 1000) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (time > 35) {
                                    target2 = 10;
                                } else if (time > 20 && time <= 35) {
                                    minPosition2 = 5;
                                    maxPosition2 = 9;
                                    target2 = randomNumber(minPosition2, maxPosition2);
                                } else if (time > 0 && time <= 20) {
                                    maxPosition2 = 6;
                                    minPosition2 = 4;
                                    target2 = randomNumber(minPosition2, maxPosition2);
                                } else if (time == 0) {
                                    count3 = 1000;
                                    target2 = 0;
                                }
                                changeImage2(target2, minPosition2, maxPosition2);
                                count3++;
                            }
                        });
                        Thread.sleep(randomNumber(300, 600));
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    private void lessTwentySeconds() {
        new Thread() {
            @Override
            public void run() {
                while (count2 < 1000) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (time > 20) {
                                    target3 = 10;
                                } else if (time > 0 && time <= 20) {
                                    target3 = randomNumber(7, 9);
                                } else if (time == 0) {
                                    count2 = 1000;
                                    target3 = 0;
                                }
                                changeImage3(target3);
                                count2++;
                            }
                        });
                        Thread.sleep(randomNumber(300, 600));
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    public void changeImage(int target, int maxPosition1) {
        for (int i = 1; i <= maxPosition1; i++) {
            if (target != 0) {
                button[i].setBackgroundResource(R.drawable.topo_gone);
                button[target].setBackgroundResource(R.drawable.topo);
            } else {
                button[i].setBackgroundResource(R.drawable.topo_gone);
            }
        }
    }

    public void changeImage2(int target2, int minPosition2, int maxPosition2) {
        if (target2 < 10) {
            for (int i = minPosition2; i <= maxPosition2; i++) {
                if (target2 != 0) {
                    button[i].setBackgroundResource(R.drawable.topo_gone);
                    button[target2].setBackgroundResource(R.drawable.topo);
                } else {
                    button[i].setBackgroundResource(R.drawable.topo_gone);
                }
            }
        }
    }

    public void changeImage3(int target3) {
        if (target3 < 10) {
            for (int i = 7; i <= 9; i++) {
                if (target3 != 0) {
                    button[i].setBackgroundResource(R.drawable.topo_gone);
                    button[target3].setBackgroundResource(R.drawable.topo);
                } else {
                    button[i].setBackgroundResource(R.drawable.topo_gone);
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void increaseScore(View v) {
        if (target == 1 && v.getId() == R.id.btn_1 && button[1].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            button[1].setBackgroundResource(R.drawable.topo_gone);
            tv_score.setText("" + score);
        } else if (target == 2 && v.getId() == R.id.btn_2  && button[2].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[2].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 3 && v.getId() == R.id.btn_3 && button[3].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[3].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 4 && v.getId() == R.id.btn_4 && button[4].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[4].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 5 && v.getId() == R.id.btn_5 && button[5].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[5].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 6 && v.getId() == R.id.btn_6 && button[6].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[6].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 7 && v.getId() == R.id.btn_7 && button[7].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[7].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 8 && v.getId() == R.id.btn_8 && button[8].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[8].setBackgroundResource(R.drawable.topo_gone);
        } else if (target == 9 && v.getId() == R.id.btn_9 && button[9].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[9].setBackgroundResource(R.drawable.topo_gone);
        }

        if (target2 == 5 && v.getId() == R.id.btn_5 && button[5].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[5].setBackgroundResource(R.drawable.topo_gone);
        } else if (target2 == 6 && v.getId() == R.id.btn_6 && button[6].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[6].setBackgroundResource(R.drawable.topo_gone);
        } else if (target2 == 7 && v.getId() == R.id.btn_7 && button[7].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[7].setBackgroundResource(R.drawable.topo_gone);
        } else if (target2 == 8 && v.getId() == R.id.btn_8 && button[8].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[8].setBackgroundResource(R.drawable.topo_gone);
        } else if (target2 == 9 && v.getId() == R.id.btn_9 && button[9].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[9].setBackgroundResource(R.drawable.topo_gone);
        }
        if (target3 == 7 && v.getId() == R.id.btn_7 && button[7].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[7].setBackgroundResource(R.drawable.topo_gone);
        } else if (target3 == 8 && v.getId() == R.id.btn_8 && button[8].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[8].setBackgroundResource(R.drawable.topo_gone);
        } else if (target3 == 9 && v.getId() == R.id.btn_9 && button[9].getBackground().getConstantState() == getResources().getDrawable(R.drawable.topo).getConstantState()) {
            score++;
            vibrate.vibrate(100);
            tv_score.setText("" + score);
            button[9].setBackgroundResource(R.drawable.topo_gone);
        }
    }

    public void timer() {
        new Thread() {
            @Override
            public void run() {
                while (time >= 1) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time--;
                                tv_timer.setText("" + time);
                                if (time == 0) {
                                    saveScore();
                                    Toast.makeText(Game.this, "Registro Guardado :D", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    public int randomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }

    public void saveScore() {
        SQL sqlObject = new SQL(this, "gameRecords", null, 1);
        SQLiteDatabase database = sqlObject.getWritableDatabase();

        String user = txt_user.getText().toString();
        String score = tv_score.getText().toString();

        ContentValues register = new ContentValues();
        register.put("user", "Jugador: " + user + "        Puntaje: " + score);
        register.put("score", score);

        database.insert("score", null, register);

        database.close();

    }

    public void delete() {
        SQL sqlObject = new SQL(this, "gameRecords", null, 1);
        SQLiteDatabase database = sqlObject.getWritableDatabase();

        database.delete("score", null, null);
        database.close();
    }

    protected void onStop() {
        super.onStop();
        time = -1;
    }

    public void restartGame() {
        time = 60;
        score = 0;
        tv_score.setText("0");
    }

    public void dialogRestartGame(View v) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Reiniciar Partida")
                .setMessage("¿Estas seguro de querer empezar de nuevo?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
