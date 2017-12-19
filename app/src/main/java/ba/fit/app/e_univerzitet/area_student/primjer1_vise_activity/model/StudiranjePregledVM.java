package ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Adil on 3.6.2015.
 */
public class StudiranjePregledVM implements Serializable
{
    public static class SlusaPredmeteInfoVM  implements Serializable
    {
        public SlusaPredmeteInfoVM(String slusaPredmetId, String nazivPredmet, Integer ocjena, Date datum, boolean priznato, float ects, int godinaStudija) {
            SlusaPredmetId = slusaPredmetId;
            NazivPredmet = nazivPredmet;
            Ocjena = ocjena;
            Datum = datum;
            Priznato = priznato;
            Ects = ects;
            GodinaStudija = godinaStudija;
        }
        public String SlusaPredmetId ;
        public String NazivPredmet ;
        public Integer Ocjena ;
        public Date Datum ;
        public boolean Priznato ;
        public float Ects ;
        public int GodinaStudija ;
    }
    public static class UplataStudijaVM  implements Serializable
    {
        public UplataStudijaVM(int uplataId, Date datumUplate, String svrha, float iznos, String evidentiranoKorisnik, Date evidentiranoDatum) {
            UplataId = uplataId;
            DatumUplate = datumUplate;
            Svrha = svrha;
            Iznos = iznos;
            EvidentiranoKorisnik = evidentiranoKorisnik;
            EvidentiranoDatum = evidentiranoDatum;
        }

        public int UplataId ;
        public Date DatumUplate ;
        public String Svrha ;
        public float Iznos ;
        public String EvidentiranoKorisnik ;
        public Date EvidentiranoDatum ;
    }

    public static class UpisGodineStudijaVM  implements Serializable
    {
        public UpisGodineStudijaVM(int upisGodineId, int godinaStudija, String akademskaGodina, List<UplataStudijaVM> uplataStudijaInfo, float cijena, float uplaceno) {
            UpisGodineId = upisGodineId;
            GodinaStudija = godinaStudija;
            AkademskaGodina = akademskaGodina;
            UplataStudijaInfo = uplataStudijaInfo;
            Cijena = cijena;
            Uplaceno = uplaceno;
        }

        public int UpisGodineId ;
        public int GodinaStudija ;
        public String AkademskaGodina ;
        public List<UplataStudijaVM> UplataStudijaInfo ;
        public float Cijena ;
        public float Uplaceno ;
    }

    public static class PrijavljeniIspitiVM implements Serializable
    {
        public PrijavljeniIspitiVM(int prijavaIspitaId, int godinaStudija, String predmet, String nastavnik, Date datumIspita, Date datumPrijava, Date datumOdjava) {
            PrijavaIspitaId = prijavaIspitaId;
            GodinaStudija = godinaStudija;
            Predmet = predmet;
            Nastavnik = nastavnik;
            DatumIspita = datumIspita;
            DatumPrijava = datumPrijava;
            DatumOdjava = datumOdjava;
        }

        public int PrijavaIspitaId ;
        public int GodinaStudija ;
        public String Predmet ;
        public String Nastavnik ;
        public Date DatumIspita ;
        public Date DatumPrijava ;
        public Date DatumOdjava ;
    }

    public String BrojIndeksa ;
    public String Fakultet ;
    public String Odsjek ;
    public String Npp ;
    public Integer TrenutnaGodinaStudija ;

    public List<UpisGodineStudijaVM> UpisGodineStudijaInfo;
    public List<SlusaPredmeteInfoVM> SlusaPredmeteInfo;
    public List<PrijavljeniIspitiVM> PrijavljeniIspitiInfo ;
}
