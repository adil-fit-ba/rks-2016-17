package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model;

import com.android.volley.Request;
import com.android.volley.Response;

import ba.fit.app.e_univerzitet.MyApp;
import ba.fit.app.e_univerzitet.area_autentifikacija.model.AutentifikacijaProvjeraVM;
import ba.fit.app.e_univerzitet.helper.Config;
import ba.fit.app.e_univerzitet.helper.MyGson;
import ba.fit.app.e_univerzitet.helper.Sesija;
import ba.fit.app.e_univerzitet.helper.volley.GsonRequest;
import ba.fit.app.e_univerzitet.helper.volley.MySingleton;

/**
 * Created by Adil on 29.4.2015.
 */
public class TicketApi
{
    public static void AddNew(final TicketAddNewVM model,
                              Response.Listener<TicketPregledVM> successListener, Response.ErrorListener errorListener
                             )
    {
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Ticket")
                .appendPath("AddNew")
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, TicketPregledVM.class, null, MyGson.build().toJson(model), successListener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static void Pregled(Response.Listener<TicketPregledVM> successListener, Response.ErrorListener errorListener)
    {

        Integer studiranjeId = Sesija.getOdabraniStudij().StudiranjeId;
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Ticket")
                .appendPath("Pregled")
                .appendQueryParameter("StudiranjeId", studiranjeId.toString())
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, TicketPregledVM.class, null, null, successListener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static void Detalji(final Integer ticketId, Response.Listener<TicketDetaljiVM> successListener, Response.ErrorListener errorListener)
    {
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Ticket")
                .appendPath("Detalji")
                .appendQueryParameter("ticketId", ticketId.toString())
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, TicketDetaljiVM.class, null, null, successListener, errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static void Odgovori(final Integer ticketId, final String odgovorStr, Response.Listener<TicketDetaljiVM> successListener, Response.ErrorListener errorListener)
    {
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Ticket")
                .appendPath("Odgovori")
                .appendQueryParameter("korisnikId", Sesija.getLogiraniKorisnik().KorisnikId.toString())
                .appendQueryParameter("ticketId", ticketId.toString())
                .appendQueryParameter("odgovorStr", odgovorStr)
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, TicketDetaljiVM.class, null, null, successListener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static void KategorijaGetAll(Response.Listener<TicketKategorijaGetAllVM> successListener, Response.ErrorListener errorListener)
    {
        final String url = Config.baseUrl.buildUpon()
                .appendPath("Ticket")
                .appendPath("KategorijaGetAll")
                .toString();

        GsonRequest<?> gsonRequest = new GsonRequest<>(url, TicketKategorijaGetAllVM.class, null, null, successListener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
