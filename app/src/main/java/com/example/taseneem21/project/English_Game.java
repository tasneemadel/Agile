package com.example.taseneem21.project;


/**
 * Created by taseneem 21 on 12/24/2016.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.os.Bundle;

        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;


        import static android.graphics.Color.RED;


public class English_Game extends Activity {


    Button c1;
    Button c2;
    Button c3;
    Button c4;
    Button sub;
    TextView result;
    MyBD bd;
    ImageView pic;
    String rightAns;
    String correct = "Correct Answer";
    String wrong = "Wrong Answer";
    boolean created = false;
    int eScore = 0;
    int turn = 1;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = getIntent();
        // Bundle bundle = intent.getExtras();
        // name = bundle.getString("name");




        setContentView(R.layout.eng_game);
        pic = (ImageView) findViewById(R.id.picQuestion);
        c1 = (Button) findViewById(R.id.choice1);
        c2 = (Button) findViewById(R.id.choice2);
        c3 = (Button) findViewById(R.id.choice3);
        c4 = (Button) findViewById(R.id.choice4);
        result = (TextView) findViewById(R.id.result_txt2);
        sub = (Button) findViewById(R.id.Skip);
        bd = new MyBD(English_Game.this);


        bd.deleteteEnglishTable();
        bd.createEnglishTable();
        bd.createRow();
        setGame(turn);
        play();



    }




    private void play() {


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setEnabled(true);
                c2.setEnabled(true);
                c3.setEnabled(true);
                c4.setEnabled(true);
                if (turn >= 4) {
                    finishGame();
                } else {
                    result.setText("");
                    turn++;
                    setGame(turn);
                }
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c1.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    result.setText(correct);
                    result.setTextColor(Color.GREEN);
                    if (turn >= 4) {
                        finishGame();

                    } else {
                      //  turn++;
            //            setGame(turn);
                    }
                } else {


                    result.setText(wrong);
                    result.setTextColor(RED);

                }
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c2.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    result.setText(correct);
                    result.setTextColor(Color.GREEN);

                    if (turn >= 4) {
                        finishGame();
                    } else {
                       // turn++;
              //          setGame(turn);
                    }
                } else {



                    result.setText(wrong);
                    result.setTextColor(RED);

                }
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c3.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    result.setText(correct);
                    result.setTextColor(Color.GREEN);

                    if (turn >= 4) {
                        finishGame();
                    } else {
                 //       turn++;
                //        setGame(turn);
                    }
                } else {



                    result.setText(wrong);
                    result.setTextColor(RED);

                }
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c4.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    result.setText(correct);
                    result.setTextColor(Color.GREEN);

                    if (turn >= 4) {
                        finishGame();
                    } else {
                   //     turn++;
                  //      setGame(turn);
                    }
                    c1.setEnabled(false);
                    c2.setEnabled(false);
                    c3.setEnabled(false);
                    c4.setEnabled(false);
                } else {



                    result.setText(wrong);
                    result.setTextColor(RED);
                    c1.setEnabled(false);
                    c2.setEnabled(false);
                    c3.setEnabled(false);
                    c4.setEnabled(false);
                }
            }
        });





    }

    String choiceA;
    String choiceB;
    String choiceC;
    String choiceD;

    public void finishGame() {
        Intent intent = new Intent(English_Game.this, EnglishScore.class);


        SharedPreferences preferences = getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("engscore", eScore);
        editor.commit();
           finish();
        startActivity(intent);
    }

    public void setGame(int turn) {
        EnglishQuestion e = bd.getSingleQuestion(turn);

        choiceA = e.choice1;
        choiceB = e.choice2;
        choiceC = e.choice3;
        choiceD = e.choice4;


        rightAns = e.getRight();
        result.setText(" ");
        c1.setText(choiceA);
        c2.setText(choiceB);
        c3.setText(choiceC);
        c4.setText(choiceD);
        pic.setImageResource(e.getImageRes());




    }


}

