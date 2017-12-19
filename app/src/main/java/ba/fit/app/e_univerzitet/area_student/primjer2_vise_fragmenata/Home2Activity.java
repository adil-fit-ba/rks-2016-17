package ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_autentifikacija.LoginActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjeApi;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.helper.Sesija;


public class Home2Activity extends AppCompatActivity
{
    private StudiranjePregledVM studijInfo=null;
    private void otvoriFragmentKaoReplace(Fragment fragment)
    {
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        // Replace the default fragment animations with animator resources representing
        // rotations when switching to the back of the card, as well as animator
        // resources representing rotations when flipping back to the front (e.g. when
        // the system Back button is pressed).


        transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);

        transaction.replace(R.id.kontenjerId, fragment);
    /* addToBackStack - dodaje transakciju u stack tako da dugme "Back“ vraća na
       prethodni framentu (umjesto na prethodni aktivity) */
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void otvoriFragmentKaoDialog(DialogFragment fragment)
    {
        final FragmentManager fm = getSupportFragmentManager();
        fragment.show(fm, "neki_tag");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studiranje_home);

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLogout_click();
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

    private void do_btnPrijaveIspita_click()
    {
        otvoriFragmentKaoReplace(PrijavljeniIspitiFragment.NapraviInstancu(studijInfo));
    }

    private void do_btnPredmeti_click() {
        otvoriFragmentKaoReplace(SlusaPredmeteFragment.NapraviInstancu(studijInfo));
    }

    private void do_btnUplate_click() {
        otvoriFragmentKaoReplace(UplataStudijaFragment.NapraviInstancu(studijInfo));
    }

    private void do_btnLogout_click() {

        Sesija.setLogiraniKorisnik(null);
        startActivity(new Intent(this, LoginActivity.class));
    }


}
