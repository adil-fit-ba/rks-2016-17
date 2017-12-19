package ba.fit.app.e_univerzitet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import ba.fit.app.e_univerzitet.area_autentifikacija.LoginActivity;
import ba.fit.app.e_univerzitet.area_student.OdabirStudijaActivity;
import ba.fit.app.e_univerzitet.helper.Sesija;


public class MainActivity extends ActionBarActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       if(Sesija.getLogiraniKorisnik() == null)
       {
           final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
           startActivity(intent);
       }
        else
       {

           final Intent intent = new Intent(MainActivity.this, OdabirStudijaActivity.class);
           startActivity(intent);
       }

    }




}
