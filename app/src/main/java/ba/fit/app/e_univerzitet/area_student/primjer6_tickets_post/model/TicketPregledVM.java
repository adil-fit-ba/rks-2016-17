package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Adil on 8.5.2015.
 */
public class TicketPregledVM
{
    public class TicketInfo
    {
        public int TicketId;
        public String Naslov;
        public String Predmet;
        public String Kategorija;
        public boolean IsClosed;

        public String ZadnjaPorukaKorisnik;

        public Date ZadnjaPorukaVrijeme;
        public String ZadnjaPorukaSadrzaj;
    }

    public List<TicketInfo> TicketInfos;
}
