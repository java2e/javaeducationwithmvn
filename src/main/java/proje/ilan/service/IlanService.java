package proje.ilan.service;

import proje.ilan.model.Ilan;
import proje.ilan.model.Kategori;
import proje.ilan.model.Kullanici;
import proje.ilan.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IlanService extends Service<Ilan> implements BaseService<Ilan>{

    private KullaniciService kullaniciService;

    public IlanService(){
        kullaniciService = new KullaniciService();
    }


    @Override
    public List<Ilan> getList() {

        List<Ilan> liste = new ArrayList<>();

        try{
            String sql = "Select * from ilan";
            PreparedStatement statement = connection().prepareStatement(sql);

            ResultSet sonucListesi = statement.executeQuery();

            while (sonucListesi.next()) {

                Long id = sonucListesi.getLong("id");
                String baslik = sonucListesi.getString("baslik");
                String detay = sonucListesi.getString("detay");
                String kategori = sonucListesi.getString("kategori");
                Long kullanici_id = sonucListesi.getLong("kullanici_id");

                /*
                ilan tablosu içerisinde kullanicinin id si tutuyorum
                kullanıcının bilgieleri kullanici tablosu içerisidne tutuluyor

                id => kullanici

                select * from kullanici where id = kullanici_id

                 */

                String sqlForKullanici = "Select * from kullanici where id ="+kullanici_id;


                PreparedStatement statement2 = connection().prepareStatement(sqlForKullanici);

                ResultSet kullaniciBilgisi = statement2.executeQuery();

                List<Kullanici> kullaniciList = kullaniciService.convertToKullanici(kullaniciBilgisi);

                if(kullaniciList.size()>0){
                    Kullanici kullanici = kullaniciList.get(0);

                    Ilan ilan = new Ilan(id,baslik,detay,Kategori.valueOf(kategori),kullanici);
                    liste.add(ilan);

                }

            }

        }
        catch (Exception ex){

            logYaz("Hata => ",ex.getMessage());

        }

        return liste;
    }

    @Override
    public void logYaz(String mesaj, String... parametreler) {

        System.out.println(mesaj);
        if(parametreler.length>0)
        {
            System.out.println(parametreler[0]);
        }

    }
}
