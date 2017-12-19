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

import java.text.SimpleDateFormat;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.helper.F;


public class PrijavljeniIspitiActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_prijavljeni_ispiti);


        ListView lista = (ListView) findViewById(R.id.listView);

        final StudiranjePregledVM model = (StudiranjePregledVM) getIntent().getSerializableExtra("modelKljuc");

        lista.setAdapter(new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return model.PrijavljeniIspitiInfo.size();
            }

            @Override
            public Object getItem(int position)
            {
                return model.PrijavljeniIspitiInfo.get(position);
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                if(view==null)
                {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_prijavljen_ispit, parent,false);
                }
                StudiranjePregledVM.PrijavljeniIspitiVM x = model.PrijavljeniIspitiInfo.get(position);
                final SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

                F.findView(view, R.id.lvNazivPredmeta, TextView.class).setText(x.Predmet);
                F.findView(view,R.id.tvDatumIspita, TextView.class).setText(x.DatumIspita != null ? sdf1.format(x.DatumIspita) : "");
                F.findView(view,R.id.tvVrijemeIspita, TextView.class).setText(x.DatumIspita != null ? sdf2.format(x.DatumIspita) : "");
                F.findView(view, R.id.godinaStudija, TextView.class).setText(x.GodinaStudija + "");

                return view;
            }
        });

    }



}
