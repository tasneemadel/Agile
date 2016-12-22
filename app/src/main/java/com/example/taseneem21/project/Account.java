package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;

import it.sephiroth.android.library.picasso.Picasso;


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
    private TextView emailtxt;
    private TextView agetext;
    int totalscore;
    String imageUrl;
    ImageView profile_img;
    //public static userInfo usInfo;
    SharedPreferences myprefs;
    String s3;
    MyBD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        myprefs= getSharedPreferences("userInfo", MODE_WORLD_READABLE);
        profile_img=(ImageView)findViewById(R.id.profileImg);
        ratingbar1=(RatingBar)findViewById(R.id.ratingBar1);
        profileimg=(ImageView)findViewById(R.id.profileImg);
        s3= myprefs.getString("username", null);
//usInfo=new userInfo();
        LayerDrawable stars = (LayerDrawable) ratingbar1.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    getEmail_Age_Image();




  btn1=(Button) findViewById(R.id.button);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, Game_window.class);
                startActivity(intent);
            }
        });


        btn3=(Button) findViewById(R.id.leaderbtn);
countratingbar();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, leaderboard.class);
                startActivity(intent);
            }
        });
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

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
            imageUrl=data.getData().toString();
            SharedPreferences myprefs= getSharedPreferences("userInfo", MODE_WORLD_READABLE);
            String s3= myprefs.getString("username", null);
            bd.editImage(bd,s3,imageUrl);

        }
    }


    public void countratingbar(){

        Cursor cr = bd.getTotalscore(bd, s3);

 if (cr.getCount() > 0)
        {
            cr.moveToFirst();
            do{
                totalscore = cr.getInt(cr.getColumnIndex(MyBD.tabletotalscore));

            }while (cr.moveToNext());

            cr.close();
            bd.close();
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

    }
public void getEmail_Age_Image(){
    txt=(TextView) findViewById(R.id.welcomeuser);
    emailtxt=(TextView)findViewById(R.id.emailInfo);
    agetext=(TextView)findViewById(R.id.ageInfo);
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();



    SharedPreferences preferences = getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();

    //String s = bundle.getString("User");
    //String e=bundle.getString("Email");
   // int age=bundle.getInt("Age");
    // countratingbar();
    txt.setText("Welcome " + s3);
    editor.putString("Username", s3);
    //editor.commit();
bd=new MyBD(Account.this);

 String urI=bd.getURI(bd,s3);
//usInfo.setusername(s3);
    //usInfo.setuserimage(urI);
    Uri uri;
    uri = Uri.parse(urI);

    File f = new File(uri.getPath());

    Picasso.with(this)
            .load(uri)
            .fit()
            .skipMemoryCache()
            .into(profile_img);
    MyBD db2 = new MyBD(Account.this);
    Cursor cr = db2.Age_Email(db2,s3);


    if (cr.getCount() > 0)
    {
        cr.moveToFirst();
        do{

     int Age=cr.getInt(cr.getColumnIndex(MyBD.TableUserAge));
            //usInfo.setage(Age);
        String Email=cr.getString(cr.getColumnIndex(MyBD.TableUserEmail));
            //usInfo.setEmail(Email);
            emailtxt.setText("Email: "+ Email);
            agetext.setText("Age: "+Age);
            editor.putString("Email",Email);
            editor.putInt("Age",Age);
          editor.commit();
        }while (cr.moveToNext());

        cr.close();
        db2.close();
    }

}


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




}
