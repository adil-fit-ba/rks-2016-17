package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Adil on 8.5.2015.
 */
public class TicketDetaljiVM
{
    public class TicketDetaljiInfo
    {
        public int TicketDetaljId;
        public String PorukaKorisnikIme;
        public int PorukaKorisnikId;
        public Date PorukaVrijeme;
        public String PorukaSadrzaj;

    }

    public String Naslov;
    public String Predmet;
    public String Kategorija;
    public boolean IsClosed;

    public int TicketId;
    public List<TicketDetaljiInfo> TicketDetaljiInfos;
}
