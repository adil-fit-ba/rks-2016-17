package ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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


public class PrijavljeniIspitiFragment extends DialogFragment
{
    public static  String ARG_MODEL="model";
    public static PrijavljeniIspitiFragment NapraviInstancu(StudiranjePregledVM model)
    {
        PrijavljeniIspitiFragment fragment=new PrijavljeniIspitiFragment();
        Bundle parametri=new Bundle();
        parametri.putSerializable(ARG_MODEL,model);
        fragment.setArguments(parametri);
        return  fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_student_prijavljeni_ispiti, container, false);

        ListView lista = (ListView) view.findViewById(R.id.listView);

        pripremi(lista);
        return view;
    }

    private void pripremi(ListView lista) {
        final StudiranjePregledVM model = (StudiranjePregledVM) getArguments().getSerializable(ARG_MODEL);

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
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
