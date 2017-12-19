package ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class UplateActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_uplate);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);


        final StudiranjePregledVM model = (StudiranjePregledVM) getIntent().getSerializableExtra("modelKljuc");

        listView.setAdapter(new BaseExpandableListAdapter()
        {
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
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition)
            {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition)
            {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition)
            {
                return 0;
            }

            @Override
            public boolean hasStableIds()
            {
                return false;
            }

            @Override
            public View getGroupView(final int groupPosition, boolean isExpanded, View view, ViewGroup parent)
            {
                if( view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                if( view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
