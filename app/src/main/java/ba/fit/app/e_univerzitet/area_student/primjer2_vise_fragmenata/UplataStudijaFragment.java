package ba.fit.app.e_univerzitet.area_student.primjer2_vise_fragmenata;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model.StudiranjePregledVM;
import ba.fit.app.e_univerzitet.helper.F;
import ba.fit.app.e_univerzitet.helper.MyDate;

public class UplataStudijaFragment extends DialogFragment
{
    private static final String ARG_MODEL = "model";

    public static UplataStudijaFragment NapraviInstancu(StudiranjePregledVM model)
    {
        UplataStudijaFragment fragment = new UplataStudijaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MODEL, model);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_student_uplate, container, false);

        ExpandableListView lista = (ExpandableListView) view.findViewById(R.id.listView);

        pripremi(lista);
        return view;
    }

    private void pripremi(ExpandableListView listView) {

        final StudiranjePregledVM model = (StudiranjePregledVM) getArguments().getSerializable(ARG_MODEL);


        listView.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount()
            {
                return model.UpisGodineStudijaInfo.size();
            }

            @Override
            public int getChildrenCount(int groupPosition)
            {
                return model.UpisGodineStudijaInfo.get(groupPosition).UplataStudijaInfo.size();
            }

            @Override
            public Object getGroup(int groupPosition)
            {
                return model.UpisGodineStudijaInfo.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition)
            {
                return model.UpisGodineStudijaInfo.get(groupPosition).UplataStudijaInfo.get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition)
            {
                return model.UpisGodineStudijaInfo.get(groupPosition).UpisGodineId;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition)
            {
                return model.UpisGodineStudijaInfo.get(groupPosition).UplataStudijaInfo.get(childPosition).UplataId;
            }

            @Override
            public boolean hasStableIds()
            {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent)
            {
                if (view == null)
                {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_godina_studija, parent, false);
                }

                final StudiranjePregledVM.UpisGodineStudijaVM x = model.UpisGodineStudijaInfo.get(groupPosition);

                F.findView(view, R.id.tvGodinaStudijaValue, TextView.class).setText(x.GodinaStudija + "");
                F.findView(view, R.id.tvAkademskaGodinaValue, TextView.class).setText(x.AkademskaGodina + "");
                F.findView(view, R.id.tvCijenaValue, TextView.class).setText(F.decimal_0_00(x.Cijena));
                F.findView(view, R.id.tvUplacenoValue, TextView.class).setText(F.decimal_0_00(x.Uplaceno));

                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent)
            {
                if (view == null)
                {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_uplata, parent, false);
                }
                final StudiranjePregledVM.UplataStudijaVM x = model.UpisGodineStudijaInfo.get(groupPosition).UplataStudijaInfo.get(childPosition);

                F.findView(view, R.id.lvSvrha, TextView.class).setText(x.Svrha);
                F.findView(view, R.id.lvIznos, TextView.class).setText(F.decimal_0_00(x.Iznos));
                F.findView(view, R.id.lvDatum, TextView.class).setText(MyDate.to_dd_mm_yyyy(x.DatumUplate));
                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition)
            {
                return false;
            }
        });

    }



}
