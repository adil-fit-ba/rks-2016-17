package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketApi;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketDetaljiVM;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketKategorijaGetAllVM;
import ba.fit.app.e_univerzitet.helper.MyRunnable;


public class TicketKategorijaOdabirFragment extends DialogFragment
{

    private static final String ARG_RunOnPositiveDismiss = "ARG_RunOnPositiveDismiss";;
    private ListView listView;

    public TicketKategorijaOdabirFragment()
    {
        // Required empty public constructor
    }

    public static void otvoriKaoDialog(FragmentActivity activity, MyRunnable<TicketKategorijaGetAllVM.TicketKategorijaVM> runOnPositiveDismiss)
    {
        final TicketKategorijaOdabirFragment fragment = new TicketKategorijaOdabirFragment();

        final Bundle args = new Bundle();
        args.putSerializable(ARG_RunOnPositiveDismiss, runOnPositiveDismiss);
        fragment.setArguments(args);

        FragmentManager fm = activity.getSupportFragmentManager();
        fragment.show(fm, "nesto");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_odabir, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        getDialog().setTitle("Novi ticket :: Odabir kategorije");

        Response.Listener<TicketKategorijaGetAllVM> successListener = new Response.Listener<TicketKategorijaGetAllVM>() {
            @Override
            public void onResponse(TicketKategorijaGetAllVM response) {
                pripremiListView(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        TicketApi.KategorijaGetAll(successListener, errorListener);

        final Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });

        return view;
    }

    private void pripremiListView(final TicketKategorijaGetAllVM model)
    {
        listView.setAdapter(new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return model.kategorijaInfos.size();
            }

            @Override
            public Object getItem(int position)
            {
                return  model.kategorijaInfos.get(position);
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
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_odabir_generic, parent,false);
                }
                final TicketKategorijaGetAllVM.TicketKategorijaVM item = model.kategorijaInfos.get(position);

                final TextView tvGlavni = (TextView) view.findViewById(R.id.tvGlavni);
                tvGlavni.setText(item.Opis);

                return view;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final TicketKategorijaGetAllVM.TicketKategorijaVM selectedKategorija = model.kategorijaInfos.get(position);

                final MyRunnable<TicketKategorijaGetAllVM.TicketKategorijaVM> onPositiveDismiss = (MyRunnable<TicketKategorijaGetAllVM.TicketKategorijaVM>) getArguments().getSerializable(TicketKategorijaOdabirFragment.ARG_RunOnPositiveDismiss);

                onPositiveDismiss.run(selectedKategorija);
                getDialog().dismiss();
            }
        });
    }

}
