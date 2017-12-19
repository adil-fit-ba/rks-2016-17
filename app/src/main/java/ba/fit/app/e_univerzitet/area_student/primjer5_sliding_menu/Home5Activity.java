package ba.fit.app.e_univerzitet.area_student.primjer5_sliding_menu;

import android.os.Bundle;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_autentifikacija.LoginActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjeApi;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata.PrijavljeniIspitiFragment;
import ba.fit.app.e_univerzitet.helper.Sesija;

public class Home5Activity extends ActionBarActivity
{


    private DrawerLayout drawer_Layout;
    private ListView left_drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence drawerNaslov;
    private CharSequence activityNaslov;
    private StudiranjePregledVM model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        init();
        Response.Listener<StudiranjePregledVM> successListener=new Response.Listener<StudiranjePregledVM>() {
            @Override
            public void onResponse(StudiranjePregledVM response) {
                model = response;
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StudiranjeApi.Pregled(successListener, errorListener);

    }

    private void init() {
        activityNaslov = drawerNaslov = getTitle();
        drawer_Layout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        left_drawer = (ListView) findViewById(R.id.left_drawer);

        drawer_Layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        left_drawer.setAdapter(new ArrayAdapter<>(this, R.layout.stavka_drawer_list, getNaslovi()));
        left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selectItem(position);
            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer_Layout,         /* DrawerLayout object */
                R.string.drawer_otvoreno,  /* "open drawer" description for accessibility */
                R.string.drawer_zatvoreno  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(activityNaslov);
                // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerNaslov);
                //    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer_Layout.setDrawerListener(mDrawerToggle);
    }

    protected String[] getNaslovi()
    {
        return new String[]{
                "Prijavljeni ispiti",
                "Sluša predmete",
                "Uplata studija",
                "Logout"};
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }


    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        if (position == 0)
            fragment = PrijavljeniIspitiFragment.NapraviInstancu(model);

        if (position == 1)
            fragment = PrijavljeniIspitiFragment.NapraviInstancu(model);

        if (position == 2)
            fragment = PrijavljeniIspitiFragment.NapraviInstancu(model);

        if (position == 3)
        {
            Sesija.setLogiraniKorisnik(null);
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mjestoFragment, fragment).commit();

        // update selected item and title, then close the drawer
        left_drawer.setItemChecked(position, true);
        setTitle(getNaslovi()[position]);
        drawer_Layout.closeDrawer(left_drawer);
    }



    @Override
    public void setTitle(CharSequence title) {
        activityNaslov = title;
        getSupportActionBar().setTitle(activityNaslov);
    }
}

