package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MathScore extends Activity {
private Button btn1;
    private TextView txt;
    //Math_Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.mathscore);
       // g=new Math_Game();
        txt=(TextView) findViewById(R.id.score_txt);
        //txt.setText(g.MathScore);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //String outlet_no= bundle.getString("MathScore");
       int s = bundle.getInt("key");
       String  s1 = bundle.getString("User");
        txt.setText(""+s);
        String d="tasneem";

        MyBD db2 = new MyBD(MathScore.this);

        db2.insertmathscore(db2,d,s,0);


        btn1=(Button) findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MathScore.this, Account.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
