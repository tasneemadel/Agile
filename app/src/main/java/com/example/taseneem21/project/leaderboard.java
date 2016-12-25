package com.example.taseneem21.project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taseneem 21 on 12/10/2016.
 */
public class leaderboard extends Activity {

    ListView listView;
    TextView txt;
    ArrayList<userInfo> arrayList = new ArrayList<userInfo>();
    ArrayAdapter arrayAdapter;
    EditText eTDialog,etDialogSalary;
    userInfo t;
    CustomListAdapter customListAdapter;
    private static final int RESULT_LOAD_IMG=1;
    ImageView profileimg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list1);
        profileimg=(ImageView)findViewById(R.id.imagelogo);


        MyBD db2 = new MyBD(leaderboard.this);
        Cursor cr = db2.getAllUsers(db2);




        int rank=0; int oldtotal=100000000;
        if (cr.getCount() > 0)
        {
            cr.moveToFirst();
            do{


                t=new userInfo();

                String UserName = cr.getString(cr.getColumnIndex(MyBD.TableUserName));

                t.setusername(UserName);
                int mathscore = cr.getInt(cr.getColumnIndex(MyBD.tablemathscore));
                t.setmathscore(mathscore);
                int engscore = cr.getInt(cr.getColumnIndex(MyBD.tableenglishscore));
                t.setengscore(engscore);
                                                         int totalscore = cr.getInt(cr.getColumnIndex(MyBD.tabletotalscore));
                  String image=cr.getString(cr.getColumnIndex(MyBD.TableUserImage));
                t.settotalcore(totalscore);
               // t.setmathscore(mathscore);
                    if(oldtotal>totalscore) {
                        rank++;

                    }

                t.setrank(rank);

                t.setuserimage(image);


                arrayList.add(t);


                oldtotal=totalscore;

            }while (cr.moveToNext());

            cr.close();
            db2.close();
            customListAdapter = new CustomListAdapter(arrayList,this);

            listView.setAdapter(customListAdapter);
        }



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
}
