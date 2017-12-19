package ba.fit.app.e_univerzitet.area_student.primjer4_tabHost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjeApi;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.PrijavljeniIspitiActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.SlusaPredmeteActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.UplateActivity;
import ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata.PrijavljeniIspitiFragment;
import ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata.SlusaPredmeteFragment;
import ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata.UplataStudijaFragment;

public class Home4Activity extends FragmentActivity {


    private StudiranjePregledVM studijInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);

        Response.Listener<StudiranjePregledVM> successListener=new Response.Listener<StudiranjePregledVM>() {
            @Override
            public void onResponse(StudiranjePregledVM response) {
                studijInfo = response;
                Priprema();
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StudiranjeApi.Pregled(successListener, errorListener);

    }

    private void Priprema() {
        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(R.id.fragment_tabs);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        Bundle arg = new Bundle();
        arg.putSerializable("model", studijInfo);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tab 1", null),
                PrijavljeniIspitiFragment.class, arg);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2", null),
                SlusaPredmeteFragment.class, arg);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3", null),
                UplataStudijaFragment.class, arg);



        mTabHost.setCurrentTab(0);
    }
}

