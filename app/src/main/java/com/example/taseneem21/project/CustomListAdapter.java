package com.example.taseneem21.project;

import it.sephiroth.android.library.picasso.Picasso;


        import android.content.Context;
        import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.io.File;
        import java.util.ArrayList;

        import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by taseneem 21 on 12/11/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList<userInfo> arrayList;
    private Context ctx;

    public CustomListAdapter(java.util.ArrayList<userInfo> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    public void addItem(userInfo t)
    {
        arrayList.add(t);
        notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public userInfo getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.child_list,parent,false);

        TextView nameText = (TextView) convertView.findViewById(R.id.name);
        TextView mathText = (TextView) convertView.findViewById(R.id.mathscore);
        TextView engText = (TextView) convertView.findViewById(R.id.engscore);
        TextView totalText = (TextView) convertView.findViewById(R.id.totalscore);
        TextView rankText = (TextView) convertView.findViewById(R.id.rank);
        ImageView userimage=(ImageView) convertView.findViewById(R.id.imagelogo);
        final userInfo t = getItem(position);

        nameText.setText(t.getUsername());
        mathText.setText("Math Game Score:  "+t.getmathscore());
        engText.setText("English Game Score:  "+t.getengscore());
        totalText.setText("Total Score:  "+t.gettotalscore());
        rankText.setText("Rank:  "+t.getrank());

        Uri uri;
        uri = Uri.parse(t.getuserimage());


        /////// Condition for null retrieved user images
        if (uri==null) {
            //Picasso.with(this).load(f).noPlaceholder().centerCrop().fit().into(profile_img);


        }
        else{
            File f = new File(uri.getPath());

            Picasso.with(convertView.getContext())
                    .load(uri)
                    .fit()
                    .skipMemoryCache()
                    .into(userimage);
        }
        return convertView;
    }
}
