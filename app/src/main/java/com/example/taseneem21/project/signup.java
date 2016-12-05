package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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



    }

    public void signupUser(){


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              txt1=(EditText)findViewById(R.id.l_name);
                txt2=(EditText)findViewById(R.id.editText8);
               txt3=(EditText)findViewById(R.id.editText7);
                txt4=(EditText)findViewById(R.id.editText);
              bd.insertUser(bd,txt1.getText().toString(),Integer.parseInt(txt2.getText().toString()),txt3.getText().toString(),txt4.getText().toString());

            }
        });

       Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

}
