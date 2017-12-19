package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketApi;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketDetaljiVM;
import ba.fit.app.e_univerzitet.helper.F;
import ba.fit.app.e_univerzitet.helper.MyDate;
import ba.fit.app.e_univerzitet.helper.MyRunnable;
import ba.fit.app.e_univerzitet.helper.Sesija;

public class TicketDetaljiFragment extends DialogFragment
{
    private static final String ARG_TICKET_ID = "ticket_id";

    private View rootView;
    private int ticketId;

    public static TicketDetaljiFragment newInstance(int ticketId)
    {
        TicketDetaljiFragment fragment = new TicketDetaljiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TICKET_ID, ticketId);
        fragment.setArguments(args);
        return fragment;
    }

    public TicketDetaljiFragment()
    {
        // Required empty public constructor
    }
    boolean IsWriting =false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ticket_detalji, container, false);

        ticketId = getArguments().getInt(ARG_TICKET_ID);

        Response.Listener<TicketDetaljiVM> successListener = new Response.Listener<TicketDetaljiVM>() {
            @Override
            public void onResponse(TicketDetaljiVM response) {
                pripremi_listview(response);
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        TicketApi.Detalji(ticketId, successListener, errorListener);

        getDialog().setCancelable(false);

        F.findView(rootView, R.id.btnOdgovori, Button.class).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                do_btnOdgovoriClick();
            }
        });

        F.findView(rootView, R.id.btnZatvori, Button.class).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });

        return rootView;
    }

    private void do_btnOdgovoriClick()
    {
        if (!IsWriting)
        {
            F.findView(rootView, R.id.editText, EditText.class).setVisibility(View.VISIBLE);
            F.findView(rootView, R.id.btnOdgovori, Button.class).setText("Pošalji");
            IsWriting = true;
        } else
        {
            String porukaStr = F.findView(rootView, R.id.editText, EditText.class).getText().toString();

            Response.Listener<TicketDetaljiVM> successListener = new Response.Listener<TicketDetaljiVM>() {
                @Override
                public void onResponse(TicketDetaljiVM response) {
                    F.findView(rootView, R.id.editText, EditText.class).setVisibility(View.GONE);
                    F.findView(rootView, R.id.btnOdgovori, Button.class).setText("Odgovori");
                    IsWriting = false;
                    pripremi_listview(response);
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
            TicketApi.Odgovori(ticketId, porukaStr,successListener, errorListener);
        }
    }

    private void pripremi_listview(final TicketDetaljiVM model)
    {
        getDialog().setTitle(model.Naslov);

        F.findView(rootView, R.id.listView, ListView.class).setAdapter(new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return model.TicketDetaljiInfos.size();
            }

            @Override
            public Object getItem(int position)
            {
                return model.TicketDetaljiInfos.get(position);
            }

            @Override
            public long getItemId(int position)
            {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                if (view == null)
                {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_ticket_detalji, parent, false);
                }

                final TicketDetaljiVM.TicketDetaljiInfo info = model.TicketDetaljiInfos.get(position);

                final String vrijeme = MyDate.to_dd_mm_yyyy_hh_mm(info.PorukaVrijeme);
                if (info.PorukaKorisnikId == Sesija.getLogiraniKorisnik().KorisnikId)
                    F.findView(view, R.id.lblPorukaSadrzaj, TextView.class).setTextColor(Color.MAGENTA);
                else
                    F.findView(view, R.id.lblPorukaSadrzaj, TextView.class).setTextColor(Color.BLACK);
                F.findView(view, R.id.lblPorukaSadrzaj, TextView.class).setText(info.PorukaSadrzaj);
                F.findView(view, R.id.lblPorukaKorisnik, TextView.class).setText(info.PorukaKorisnikIme);
                F.findView(view, R.id.lblPorukaVrijeme, TextView.class).setText(vrijeme);

                return view;
            }
        });
    }




}
