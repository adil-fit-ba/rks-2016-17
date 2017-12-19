package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketApi;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketPregledVM;
import ba.fit.app.e_univerzitet.helper.F;
import ba.fit.app.e_univerzitet.helper.MyDate;
import ba.fit.app.e_univerzitet.helper.MyRunnable;

public class TicketPregledFragment extends Fragment
{
    private View rootView;

    public static TicketPregledFragment newInstance()
    {
        TicketPregledFragment fragment = new TicketPregledFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    public TicketPregledFragment()
    {
        // Required empty public constructor
        //getActivity().getSupportFragmentManager()
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ticket_pregled, container, false);

        Response.Listener<TicketPregledVM> successListener=new Response.Listener<TicketPregledVM>() {
            @Override
            public void onResponse(TicketPregledVM response) {
                pripremi(response);
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        TicketApi.Pregled(successListener, errorListener);


        return rootView;
    }

    private void pripremi(final TicketPregledVM model)
    {
        final ListView listView = F.findView(rootView, R.id.listView, ListView.class);
        listView.setAdapter(new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return model.TicketInfos.size();
            }

            @Override
            public Object getItem(int position)
            {
                return model.TicketInfos.get(position);
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                if (view == null)
                {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_tickets, parent, false);
                }

                final TicketPregledVM.TicketInfo info = model.TicketInfos.get(position);
                final String vrijeme = MyDate.to_dd_mm_yyyy_hh_mm(info.ZadnjaPorukaVrijeme);

                F.findView(view, R.id.lblKategorija, TextView.class).setText(info.Kategorija);
                F.findView(view, R.id.lblNaslovTicketa, TextView.class).setText(info.Naslov);
                F.findView(view, R.id.lblPredmet, TextView.class).setText(info.Predmet);
                F.findView(view, R.id.lblZadnjaPorukaSadrzaj, TextView.class).setText(info.ZadnjaPorukaSadrzaj);
                F.findView(view, R.id.lblZadnjaPorukaKorisnik, TextView.class).setText(info.ZadnjaPorukaKorisnik);
                F.findView(view, R.id.lblZadnjaPorukaVrijeme, TextView.class).setText(vrijeme);

                return view;
            }
        });

        //listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.e("TicketPregledFragment", "setOnItemClickListener");
                final TicketPregledVM.TicketInfo info = model.TicketInfos.get(position);
                final TicketDetaljiFragment fragment = TicketDetaljiFragment.newInstance(info.TicketId);
                otvoriFragmentKaoDialog(fragment);
            }
        });
    }

    private void otvoriFragmentKaoDialog(DialogFragment fragment)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fragment.show(fm, "nesto");
    }



}
