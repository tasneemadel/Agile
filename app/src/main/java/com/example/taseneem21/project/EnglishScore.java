package com.example.taseneem21.project;

/**
 * Created by taseneem 21 on 12/24/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EnglishScore extends Activity {
    private Button btn1;
    private TextView txt;
    String name;
    MyBD db;
Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.englishscore);
        txt = (TextView) findViewById(R.id.eng_score_txt);
        btn1=(Button) findViewById(R.id.back);
        db=new MyBD(this);
        //Intent intent = getIntent();

       // Bundle bundle = intent.getExtras();

        SharedPreferences myprefs = getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);
        String s3= myprefs.getString("Username", null);
        int s=myprefs.getInt("engscore",0);

        String text = "" + s;
        txt.setText(text);
        db.insertEnglishScore(db , s3,s);
        db.insertTotalScore(db,s3);
        //Toast.makeText(EnglishScore.this, "Inserted", Toast.LENGTH_LONG).show();




    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(EnglishScore.this, Account.class);
            startActivity(intent);
            finish();
        }
    });

}
}