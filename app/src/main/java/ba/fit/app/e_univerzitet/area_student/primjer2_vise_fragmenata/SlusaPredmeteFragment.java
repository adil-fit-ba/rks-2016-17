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

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;


public class SlusaPredmeteFragment extends DialogFragment
{
    public static  String ARG_MODEL="model";
    public static SlusaPredmeteFragment NapraviInstancu(StudiranjePregledVM model)
    {
        SlusaPredmeteFragment fragment=new SlusaPredmeteFragment();
        Bundle parametri=new Bundle();
        parametri.putSerializable(ARG_MODEL,model);
        fragment.setArguments(parametri);
        return  fragment;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_student_slusa_predmete, container, false);

        ListView lista = (ListView) view.findViewById(R.id.listView);

        pripremi(lista);
        return view;
    }

    private void pripremi(ListView lista) {
        final StudiranjePregledVM model = (StudiranjePregledVM)getArguments().getSerializable(ARG_MODEL);

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
                    final LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
