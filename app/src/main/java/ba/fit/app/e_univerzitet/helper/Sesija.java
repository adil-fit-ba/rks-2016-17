package ba.fit.app.e_univerzitet.helper;

import android.content.Context;
import android.content.SharedPreferences;

import ba.fit.app.e_univerzitet.MyApp;
import ba.fit.app.e_univerzitet.area_autentifikacija.model.AutentifikacijaProvjeraVM;

/**
 * Created by Adil on 11.5.2015.
 */
public class Sesija
{
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces6";
    public static final String KEY_BROJ_PORUKE = "brojPoruke";
    public static final String KEY_ODABRANI_STUDIJ = "odabraniStudijJson";
    public static final String KEY_LOGIRANI_KORISNIK = "logiraniKorisnikJson";

    public static AutentifikacijaProvjeraVM getLogiraniKorisnik()
    {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        String str = settings.getString(KEY_LOGIRANI_KORISNIK, "");
        if (str.length() == 0)
            return null;
        return MyGson.build().fromJson(str, AutentifikacijaProvjeraVM.class);
    }

    public static AutentifikacijaProvjeraVM.StudiranjeInfoVM getOdabraniStudij()
    {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        String  str = settings.getString(KEY_ODABRANI_STUDIJ, "");
        if(str.length() == 0)
            return null;

        return MyGson.build().fromJson(str, AutentifikacijaProvjeraVM.StudiranjeInfoVM.class);
    }

    public static void setLogiraniKorisnik(AutentifikacijaProvjeraVM logiraniKorisnik)
    {

        final String str = logiraniKorisnik!=null?MyGson.build().toJson(logiraniKorisnik):"";
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_LOGIRANI_KORISNIK, str);

        // Commit the edits!
        editor.commit();
    }

    public static void setOdabraniStudij(AutentifikacijaProvjeraVM.StudiranjeInfoVM odabraniStudij)
    {
        final String str = MyGson.build().toJson(odabraniStudij);
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings =MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ODABRANI_STUDIJ, str);

        // Commit the edits!
        editor.commit();
    }


}
