package proje.ilan.service;

import proje.ilan.model.Kullanici;
import proje.ilan.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KullaniciService extends Service<Kullanici> implements BaseService<Kullanici> {

    @Override
    public List<Kullanici> getList() {

        List<Kullanici> kullaniciList = new ArrayList<>();

        try {

            String sql = "Select * from kullanici";

            PreparedStatement statement = connection().prepareStatement(sql);

            ResultSet sonucListesi = statement.executeQuery();

            while (sonucListesi.next()){

                long id = sonucListesi.getLong("id");
                String adi = sonucListesi.getString("adi");
                String soyadi = sonucListesi.getString("soyadi");
                String adres= sonucListesi.getString("adres");


                Kullanici kullanici = new Kullanici(id,adi,soyadi,adres);
                kullaniciList.add(kullanici);

            }

        }
        catch (Exception ex){

            logYaz("GetList üzerinde hata alındı! ",ex.getMessage());
        }

        return kullaniciList;
    }

    @Override
    public void logYaz(String mesaj, String... parametreler) {

        System.out.println(mesaj);
        if(parametreler.length>0)
            System.out.println(parametreler[0]);


    }
}
