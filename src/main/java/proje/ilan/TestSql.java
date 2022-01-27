package proje.ilan;

import proje.ilan.model.Ilan;
import proje.ilan.model.Kategori;
import proje.ilan.model.Kullanici;
import proje.ilan.service.IlanService;
import proje.ilan.service.KullaniciService;

import java.util.List;

public class TestSql {

    public static void main(String[] args) {

        KullaniciService kullaniciService = new KullaniciService();

        Kullanici kullanici = new Kullanici(10l,"test","developer","Ankara");

        kullaniciService.ekle(kullanici);

        List<Kullanici> kullaniciList = kullaniciService.getList();

        IlanService ilanService = new IlanService();

        Ilan ilan = new Ilan(12l,"Test","Araba", Kategori.ARAC,kullaniciList.get(0));

        ilanService.ekle(ilan);

        List<Ilan> ilanList = ilanService.getList();

        for (Ilan ilan1 : ilanList)
            System.out.println(ilan1);








    }
}
