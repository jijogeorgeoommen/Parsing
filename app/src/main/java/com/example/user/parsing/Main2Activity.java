package com.example.user.parsing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;
    ListView lv;
    LayoutInflater inflater;

    ArrayList<String>hspital_name;
    ArrayList<String>place;
    ArrayList<String>phone;
    ArrayList<String>email;
    ArrayList<String>fax;
    ArrayList<String>regg;
    ArrayList<String>type;

    String url="http://srishti-systems.info/projects/accident/viewallhospital.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        client=new AsyncHttpClient();
        params=new RequestParams();
        lv=findViewById(R.id.listview);

        hspital_name=new ArrayList<String>();
        place=new ArrayList<String>();
        phone=new ArrayList<String>();
        email=new ArrayList<String>();
        fax=new ArrayList<String>();
        regg=new ArrayList<String>();
        type=new ArrayList<String>();

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try {
                    Log.e("innn", "inside");
                    jobject = new JSONObject(content);
                    Log.e("innns", content);
                    String s = jobject.getString("Result");
                    if (s.equals("success")) {
                        jarray = jobject.getJSONArray("HospitalDetails");
                        for (int i = 0; i < jarray.length(); i++) {
                            JSONObject obj = jarray.getJSONObject(i);
                            String hsptlname = obj.getString("Hospitalname");
                            hspital_name.add("Hospitalname:" + hsptlname);
                            String plc = obj.getString("place");
                            place.add("place :" + plc);
                            String phn = obj.getString("phone");
                            phone.add("phone :" + phn);
                            String emlid = obj.getString("email_id");
                            email.add("Emailid :" + emlid);
                            String faxx = obj.getString("fax");
                            fax.add("fax :" + faxx);
                            String regt = obj.getString("reg.no");
                            regg.add("Register" + regt);
                            String typt = obj.getString("type");
                            type.add("Type :" + typt);
                        }
                    }
                    adapter adp = new adapter();
                    lv.setAdapter(adp);
                }
                catch (Exception e) {
                }

                }

                });

                }
                class adapter extends BaseAdapter {


                    @Override
                    public int getCount() {
                        return hspital_name.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return position;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        convertView=inflater.inflate(R.layout.hospital,null);
                        TextView hospitalC=convertView.findViewById(R.id.hsptl_nme);
                        TextView placeC=convertView.findViewById(R.id.plce);
                        TextView phoneC=convertView.findViewById(R.id.phn);
                        TextView emaillC=convertView.findViewById(R.id.eml);
                        TextView faxC=convertView.findViewById(R.id.faxx);
                        TextView regC=convertView.findViewById(R.id.reg);
                        TextView typeC=convertView.findViewById(R.id.typ);
                        hospitalC.setText(hspital_name.get(position));
                        placeC.setText(place.get(position));
                        phoneC.setText(phone.get(position));
                        emaillC.setText(email.get(position));
                        faxC.setText(fax.get(position));
                        regC.setText(regg.get(position));
                        typeC.setText(type.get(position));
                        return convertView;
                    }
                }
}
