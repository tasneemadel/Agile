package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity {
    Button btn;
    EditText txt1;
    EditText txt2;
    MyBD bd;
    TextView txt;

    boolean validateUser=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_log_in);

        txt=(TextView) findViewById(R.id.txtLink);
        btn=(Button) findViewById(R.id.btnSignIn);
        bd=new MyBD(Login.this);

        Login_User();

    }

    public void Login_User(){

        btn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                txt1=(EditText)  findViewById(R.id.txtUserName);
                txt2=(EditText)  findViewById(R.id.txtPassword);
                validateUser=bd.validateUSer(bd,txt1.getText().toString(),txt2.getText().toString());
                if(validateUser){
                    Toast.makeText(Login.this,
                            "Found user", Toast.LENGTH_LONG).show();


                    Bundle b = new Bundle();
                    b.putString("User", txt1.getText().toString());
                    SharedPreferences preferences = getSharedPreferences("userInfo",getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",txt1.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(Login.this, Account.class);

                    intent.putExtras(b);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(Login.this,
                            "USer Not Found ", Toast.LENGTH_LONG).show();
                }
            }
        });


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
                 Bundle b = new Bundle();
               // b.putString("User", txt1.getText().toString());

                //intent.putExtras(b);
                startActivity(intent);

            }
        });

    }


}