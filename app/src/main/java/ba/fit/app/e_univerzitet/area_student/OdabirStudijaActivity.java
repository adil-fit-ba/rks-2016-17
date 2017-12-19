package ba.fit.app.e_univerzitet.area_student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ba.fit.app.e_univerzitet.R;

import ba.fit.app.e_univerzitet.area_autentifikacija.ShowHideActivity;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.Home1Activity;
import ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata.Home2Activity;
import ba.fit.app.e_univerzitet.area_student.primjer3_alert_dialoga.Home3Activity;
import ba.fit.app.e_univerzitet.area_student.primjer4_tabHost.Home4Activity;
import ba.fit.app.e_univerzitet.area_student.primjer5_sliding_menu.Home5Activity;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.Home6Activity;
import ba.fit.app.e_univerzitet.helper.Sesija;
import ba.fit.app.e_univerzitet.area_autentifikacija.model.AutentifikacijaProvjeraVM;


public class OdabirStudijaActivity extends ActionBarActivity
{
    final OdabirStudijaActivity context = OdabirStudijaActivity.this;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudent_odabir_studija);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Home1: Više activtiy-a");
        list.add("Home2: Više fragmenata");
        list.add("Home3: Alert Dialog");
        list.add("Home4: Tabhost (activity kao page)");
        list.add("Home5: Sliding menue (Navigation Drawer)");
        list.add("Home6: Custom Dialog i povratna vrijednost (tickets)");
        list.add("Home7: Show Hide Animation");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(dataAdapter);


        final ListView listView = (ListView) findViewById(R.id.listView);

        final List<AutentifikacijaProvjeraVM.StudiranjeInfoVM> studiranjeInfo = Sesija.getLogiraniKorisnik().Studiranjes;


        listView.setAdapter(new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return studiranjeInfo.size();
            }

            @Override
            public Object getItem(int position)
            {
                return studiranjeInfo.get(position);
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                final AutentifikacijaProvjeraVM.StudiranjeInfoVM x = studiranjeInfo.get(position);

                if( view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_studij, parent, false);
                }

                TextView tvFakultet = (TextView) view.findViewById(R.id.tvFakultet);
                TextView tvOdsjek = (TextView) view.findViewById(R.id.tvOdsjek);
                TextView tvNpp = (TextView) view.findViewById(R.id.tvNpp);

                tvFakultet.setText(x.FakultetNaziv);
                tvOdsjek.setText(x.OdsjekNaziv);
                tvNpp.setText(x.NppNaziv);

                return view;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final AutentifikacijaProvjeraVM.StudiranjeInfoVM x = studiranjeInfo.get(position);
                Sesija.setOdabraniStudij(x);

                int pos = spinner.getSelectedItemPosition();

                if(pos == 0)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home1Activity.class));
                if(pos == 1)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home2Activity.class));
                if(pos ==2)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home3Activity.class));
                if(pos == 3)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home4Activity.class));
                if(pos == 4)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home5Activity.class));
                if(pos == 5)
                    startActivity(new Intent(OdabirStudijaActivity.this, Home6Activity.class));
                if(pos == 6)
                    startActivity(new Intent(OdabirStudijaActivity.this, ShowHideActivity.class));

            }
        });
    }


}
