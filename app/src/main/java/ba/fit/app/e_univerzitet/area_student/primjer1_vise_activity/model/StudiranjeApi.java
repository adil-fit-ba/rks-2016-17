package ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model;

import com.android.volley.Request;
import com.android.volley.Response;


import ba.fit.app.e_univerzitet.MyApp;
import ba.fit.app.e_univerzitet.helper.Config;
import ba.fit.app.e_univerzitet.helper.Sesija;
import ba.fit.app.e_univerzitet.helper.volley.GsonRequest;
import ba.fit.app.e_univerzitet.helper.volley.MySingleton;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class StudiranjeApi
{
    public static void Pregled(Response.Listener<StudiranjePregledVM> successListener, Response.ErrorListener errorListener)
    {
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Studiranje")
                .appendPath("Pregled")
                .appendQueryParameter("studiranjeId", Sesija.getOdabraniStudij().StudiranjeId.toString())
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, StudiranjePregledVM.class, null, null, successListener, errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
    
}
