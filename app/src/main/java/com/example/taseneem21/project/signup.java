package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
       btn=(Button) findViewById(R.id.sign_upbtn);
        bd=new MyBD(signup.this);
        signupUser();
       // if(bd.validateUSername(bd,txt1.getText().toString(),txt3.getText().toString())){
            /*Toast.makeText(signup.this,
                    "USer Not Found ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(signup.this, Login.class);
            //intent.putExtras(b);
            startActivity(intent);
            finish();*/
  /*      //}
else{
            Toast.makeText(signup.this,
                    "the username  was already taken ", Toast.LENGTH_LONG).show();


        }
*/
    }

    public void signupUser(){


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              txt1=(EditText)findViewById(R.id.l_name);
                txt2=(EditText)findViewById(R.id.editText8);
               txt3=(EditText)findViewById(R.id.editText7);
                txt4=(EditText)findViewById(R.id.editText);

                if(txt1.getText().toString().equals("")){

                    txt1.setError("NAme is required ");
                }
                if(txt2.getText().toString().equals("")){

                    txt2.setError("Age is required ");
                }

                if(txt3.getText().toString().equals("")){

                    txt3.setError("Password is required ");
                }

                if(txt4.getText().toString().equals("")){

                    txt4.setError("Email is required ");
                }
        else{

                    if(bd.validateUSername(bd, txt1.getText().toString())){

                        txt1.setError("User name is already taken ,try another one");
                    }

                    else if(!bd.validemail(txt4.getText().toString())){

                        txt4.setError("Email is not valid");
                    }

                    else if(!bd.validage(Integer.parseInt(txt2.getText().toString()))){
                    txt2.setError("Sorry you can't create account,your age is out of the range");

                }






             else {
                    bd.insertUser(bd, txt1.getText().toString(), Integer.parseInt(txt2.getText().toString()), txt3.getText().toString(), txt4.getText().toString());

                    if(bd.validateUSer(bd,txt1.getText().toString(),txt3.getText().toString())){

                        Toast.makeText(signup.this,
                                txt1.getText().toString()+"is inserted", Toast.LENGTH_LONG).show();

                      /*  Intent intent = new Intent(signup.this, Login.class);

                    startActivity(intent);
                    finish();
*/
                        Bundle b = new Bundle();
                        b.putString("User", txt1.getText().toString());

                        Intent intent = new Intent(signup.this, Account.class);
                        intent.putExtras(b);
                        startActivity(intent);

                    }
                }

            }
            }
        });
        //Bundle b = new Bundle();
        //b.putString("User", txt1.getText().toString());



    }

}
