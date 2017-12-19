package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketAddNewVM;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketApi;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketKategorijaGetAllVM;
import ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model.TicketPregledVM;
import ba.fit.app.e_univerzitet.helper.F;
import ba.fit.app.e_univerzitet.helper.MyRunnable;
import ba.fit.app.e_univerzitet.helper.Sesija;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketNewFragment extends Fragment
{
    private TicketKategorijaGetAllVM.TicketKategorijaVM odabranaKategorija;
    private View rootView;

    public TicketNewFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ticket_new, container, false);

        F.findView(rootView, R.id.btnKategorija, Button.class).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            do_btnOdaberiKategoriju();
        }
    });

        F.findView(rootView, R.id.btnPosalji, Button.class).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                do_btnPosalji();
            }
        });
        
        return rootView;
    }

    private void do_btnPosalji()
    {
        TicketAddNewVM model = new TicketAddNewVM();
        model.KategorijaId = odabranaKategorija.KategorijaID;
        model.Naslov = F.findView(rootView, R.id.txtNaslov, EditText.class).getText().toString();
        model.Poruka = F.findView(rootView, R.id.txtPoruka, EditText.class).getText().toString();
        model.StudiranjeId = Sesija.getOdabraniStudij().StudiranjeId;

        Response.Listener<TicketPregledVM> successListener=new Response.Listener<TicketPregledVM>() {
            @Override
            public void onResponse(TicketPregledVM response) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(getId(), TicketPregledFragment.newInstance());
                fragmentTransaction.commit();
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        TicketApi.AddNew(model,successListener, errorListener);

    }

    private void do_btnOdaberiKategoriju()
    {
        final MyRunnable<TicketKategorijaGetAllVM.TicketKategorijaVM> onPositiveDismiss = new MyRunnable<TicketKategorijaGetAllVM.TicketKategorijaVM>()
        {
            @Override
            public void run(TicketKategorijaGetAllVM.TicketKategorijaVM response)
            {
                Toast.makeText(getActivity(), "Odabrano " + response.Opis, Toast.LENGTH_LONG).show();
                odabranaKategorija = response;
                F.findView(rootView, R.id.btnKategorija, Button.class).setText(response.Opis);
            }
        };
        TicketKategorijaOdabirFragment.otvoriKaoDialog(getActivity(), onPositiveDismiss);
    }

    public static TicketNewFragment newInstance()
    {
        final TicketNewFragment fragment = new TicketNewFragment();

        return fragment;
    }


}
