package ba.fit.app.e_univerzitet.area_autentifikacija.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;

import ba.fit.app.e_univerzitet.MyApp;
import ba.fit.app.e_univerzitet.helper.Config;
import ba.fit.app.e_univerzitet.helper.MyGson;
import ba.fit.app.e_univerzitet.helper.MyRunnable;
import ba.fit.app.e_univerzitet.helper.volley.GsonRequest;
import ba.fit.app.e_univerzitet.helper.volley.MySingleton;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class AutentifikacijaApi
{
    public static void Provjera(final String username, final String password, Response.Listener<AutentifikacijaProvjeraVM> successListener, Response.ErrorListener errorListener)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath("Autentifikacija")
                .appendPath("Provjera")
                .appendQueryParameter("username", username)
                .appendQueryParameter("password", password)
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, AutentifikacijaProvjeraVM.class, null, null, successListener, errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
