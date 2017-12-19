package ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;


public class SlusaPredmeteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_slusa_predmete);
        ListView lista = (ListView) findViewById(R.id.listView);

        final StudiranjePregledVM model = (StudiranjePregledVM) getIntent().getSerializableExtra("modelKljuc");


        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return model.SlusaPredmeteInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if( view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_slusa_predmete, parent, false);
                }

                StudiranjePregledVM.SlusaPredmeteInfoVM podaci = model.SlusaPredmeteInfo.get(position);

                TextView lvNazivPredmeta = (TextView) view.findViewById(R.id.lvNazivPredmeta);
                lvNazivPredmeta.setText(podaci.NazivPredmet);


                return view;
            }
        });
    }



}
