package ba.fit.app.e_univerzitet.area_autentifikacija;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.MyApp;
import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_autentifikacija.model.AutentifikacijaApi;
import ba.fit.app.e_univerzitet.area_student.OdabirStudijaActivity;
import ba.fit.app.e_univerzitet.helper.F;
import ba.fit.app.e_univerzitet.helper.MyRunnable;
import ba.fit.app.e_univerzitet.helper.Sesija;
import ba.fit.app.e_univerzitet.area_autentifikacija.model.AutentifikacijaProvjeraVM;


public class LoginActivity extends AppCompatActivity
{

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        txtUsername.setText("admin");

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                do_btnLogin_click();
            }
        });

        ((Button)  findViewById(R.id.btnLogin)).setAllCaps(true);

        F.findView(this, R.id.btnLogin, Button.class).setAllCaps(true);
    }

    private void do_btnLogin_click()
    {
        final ProgressDialog dialog = ProgressDialog.show(this, "Pristup podacima", "U toku");
        dialog.show();

        Response.Listener<AutentifikacijaProvjeraVM> successListener = new Response.Listener<AutentifikacijaProvjeraVM>() {
            @Override
            public void onResponse(AutentifikacijaProvjeraVM result) {
                Sesija.setLogiraniKorisnik(result);
                if (result == null)
                    Toast.makeText(LoginActivity.this, "Pogrešan username ili password", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(LoginActivity.this, result.KorisnickoIme, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, OdabirStudijaActivity.class));
                }
                dialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        };

        AutentifikacijaApi.Provjera(txtUsername.getText().toString(), txtPassword.getText().toString(), successListener, errorListener);

        System.out.print("Završena aktivacija poziva AutentifikacijaApi.Provjera");
    }
}
