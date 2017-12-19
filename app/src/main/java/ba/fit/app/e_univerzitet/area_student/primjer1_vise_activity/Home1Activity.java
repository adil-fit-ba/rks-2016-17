package ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_autentifikacija.LoginActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjeApi;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.helper.Sesija;


public class Home1Activity extends AppCompatActivity
{
    private StudiranjePregledVM studijInfo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studiranje_home);



        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sesija.setLogiraniKorisnik(null);
                Intent i = new Intent(Home1Activity.this, LoginActivity.class);

                startActivity(i);
                Log.w("do_btnLogout_click", "otvara se LoginActivity");
            }
        });

        Button btnPredmeti = (Button) findViewById(R.id.btnPredmeti);
        btnPredmeti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnPredmeti_click();
            }
        });

        Button btnPrijavljeniIspiti = (Button) findViewById(R.id.btnPrijavljeniIspiti);
        btnPrijavljeniIspiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  do_btnPrijaveIspita_click();            }
        });

        Button btnUplate = (Button) findViewById(R.id.btnUplate);
        btnUplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnUplate_click();
            }
        });


        Response.Listener<StudiranjePregledVM> successListener=new Response.Listener<StudiranjePregledVM>() {
            @Override
            public void onResponse(StudiranjePregledVM response) {
                studijInfo = response;
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StudiranjeApi.Pregled(successListener, errorListener);

    }

    private void do_btnPrijaveIspita_click() {
        Intent i = new Intent(this, PrijavljeniIspitiActivity.class);
        i.putExtra("modelKljuc", studijInfo);
        startActivity(i);
        Log.w("do_btnPrijaveIspita", "otvara se PrijavljeniIspitiFragment");
    }

    private void do_btnPredmeti_click() {
        Intent i = new Intent(this, SlusaPredmeteActivity.class);
        i.putExtra("modelKljuc", studijInfo);
        startActivity(i);
        Log.w("do_btnPredmeti_click", "otvara se SlusaPredmeteFragment");
    }

    private void do_btnUplate_click() {
        Intent i = new Intent(this, UplateActivity.class);
        i.putExtra("modelKljuc", studijInfo);
        startActivity(i);
        Log.w("do_btnUplate_click", "otvara se UplataStudijaFragment");
    }

    private void do_btnLogout_click() {

        Sesija.setLogiraniKorisnik(null);
        startActivity(new Intent(this, LoginActivity.class));
    }


}
