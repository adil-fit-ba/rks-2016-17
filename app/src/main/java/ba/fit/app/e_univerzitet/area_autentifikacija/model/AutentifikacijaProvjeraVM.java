package ba.fit.app.e_univerzitet.area_autentifikacija.model;

import java.util.List;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class AutentifikacijaProvjeraVM
{
    public Integer KorisnikId ;
    public String Ime ;
    public String Prezime ;
    public String KorisnickoIme ;
    public List<ZaposlenjaInfoVM> Zaposlenjas;
    public List<StudiranjeInfoVM> Studiranjes;

    public static class ZaposlenjaInfoVM
    {
        public Integer OrganizacionaJedinicaId ;
        public String OrganizacionaJedinicaNaziv ;
        public Integer KorisnickaUloga ;
        public String ZaposlenjeMjestoNaziv ;


    }

    public static class StudiranjeInfoVM
    {


        public Integer NppId ;
        public String NppNaziv ;
        public String FakultetNaziv ;
        public String OdsjekNaziv ;
        public Integer StudiranjeId;
    }
}
