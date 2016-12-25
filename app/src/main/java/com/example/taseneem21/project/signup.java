package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class signup extends Activity {
    Button btn;
    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    MyBD bd;
    TextView txt;
    ImageView profilePicture;
    String imageUrl;
    EditText txt5;
    public static Bundle b1 = new Bundle();
    private static final int RESULT_LOAD_IMG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btn=(Button) findViewById(R.id.sign_upbtn);
        profilePicture=(ImageView)findViewById(R.id.profilePic);
        bd=new MyBD(signup.this);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });


        signupUser();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profilePicture.setImageURI(selectedImage);
            imageUrl=data.getData().toString();

        }
    }


    public void signupUser(){


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=(EditText)findViewById(R.id.l_name);
                txt2=(EditText)findViewById(R.id.editText8);
                txt3=(EditText)findViewById(R.id.editText7);
                txt4=(EditText)findViewById(R.id.editText);
                txt5=(EditText)findViewById(R.id.c_password);

                if(txt1.getText().toString().equals("")){

                    txt1.setError("Name is required ");
                }
                if(txt2.getText().toString().equals("")){

                    txt2.setError("Age is required ");
                }

                if(txt3.getText().toString().equals("")){

                    txt3.setError("Password is required ");
                }
                if(txt5.getText().toString().equals("")){

                    txt5.setError("You must confirm your password");
                }
                if(txt4.getText().toString().equals("")){

                    txt4.setError("Email is required ");
                }
                else{

                    if(bd.validateUSername(bd, txt1.getText().toString())){

                        txt1.setError("User name is already taken ,try another one");
                    }

                  //  else if(!bd.validemail(txt4.getText().toString())){

                     //   txt4.setError("Email is not valid");
                   // }

                    else if(!bd.validage(Integer.parseInt(txt2.getText().toString()))){
                        txt2.setError("Sorry you can't create account,your age is out of the range");

                    }
                    else if(!txt5.getText().toString().equals(txt3.getText().toString())){
                        txt5.setError("your password doesn't conform");

                    }





                    else {
                        bd.insertUser(bd, txt1.getText().toString(), Integer.parseInt(txt2.getText().toString()), txt3.getText().toString(), txt4.getText().toString(),imageUrl);

                        if(bd.validateUSer(bd,txt1.getText().toString(),txt3.getText().toString())){

                            Toast.makeText(signup.this,
                                    txt1.getText().toString()+"is inserted", Toast.LENGTH_LONG).show();



                            //b1.putString("User", txt1.getText().toString());
                            //b1.putString("Email",txt4.getText().toString());
                            //b1.putInt("Age",Integer.parseInt(txt2.getText().toString()));
                            SharedPreferences preferences = getSharedPreferences("userInfo",getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username",txt1.getText().toString());
                            editor.commit();
                            Intent intent = new Intent(signup.this, Account.class);
                            intent.putExtras(b1);
                            startActivity(intent);

                        }
                    }

                }
            }
        });




    }

}