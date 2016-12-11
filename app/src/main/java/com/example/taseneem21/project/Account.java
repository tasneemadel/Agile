package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by taseneem 21 on 11/22/2016.
 */
public class Account extends ActionBarActivity {

   private  Button btn3;
   private Button btn4;

    private static final int RESULT_LOAD_IMG=1;
    private  Button btn1;
    private Button btn2;
    ImageView profileimg;
    Button uploadimg;
private TextView txt;
private RatingBar ratingbar1;
    int totalscore;

    MyBD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        ratingbar1=(RatingBar)findViewById(R.id.ratingBar1);
        profileimg=(ImageView)findViewById(R.id.profileImg);
       // uploadimg=(Button)findViewById(R.id.uploadImg);
       txt=(TextView) findViewById(R.id.welcomeuser);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //String outlet_no= bundle.getString("MathScore");
        String s = bundle.getString("User");

        txt.setText("Welcome "+s);

                profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        bd=new MyBD(Account.this);

       /* uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
*/


        //profileimg.setOnClickListener(this);
        //uploadimg.setOnClickListener(this);




  btn1=(Button) findViewById(R.id.button);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, Game_window.class);
                startActivity(intent);
            }
        });


        btn3=(Button) findViewById(R.id.leaderbtn);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, leaderboard.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profileimg.setImageURI(selectedImage);

        }
    }

    /*
    public void countratingbar(){

        Cursor cr = bd.getmathscore(bd,"tasneem");
        if (cr.getCount() > 0)
        {
            cr.moveToFirst();

            totalscore = cr.getInt(cr.getColumnIndex(MyBD.tabletotalscore));

        }

        if(totalscore<=5){
            ratingbar1.setRating((float)0.5);

        }
        else if(totalscore<=10)

        {
            ratingbar1.setRating((float)1);

        }

        else if(totalscore<=15){

            ratingbar1.setRating((float)1.5);
        }

        else if(totalscore<=20){

            ratingbar1.setRating((float)2);
        }

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), Login.class);
            startActivity(in);
            return true;
        }


        if (id == R.id.change_pass) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), changepassword.class);
            startActivity(in);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }


 /*
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.profileImg:
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
                break;


        }
    }

*/
}
