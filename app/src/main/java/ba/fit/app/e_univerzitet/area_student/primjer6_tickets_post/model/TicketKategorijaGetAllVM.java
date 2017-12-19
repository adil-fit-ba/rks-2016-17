package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post.model;

import java.util.List;

/**
 * Created by Adil on 13.5.2015.
 */
public class TicketKategorijaGetAllVM
{
    public static class TicketKategorijaVM
    {
        public int KategorijaID;
        public String Opis;
    }

    public List<TicketKategorijaVM> kategorijaInfos;
}
