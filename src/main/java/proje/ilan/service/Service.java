package proje.ilan.service;

import proje.ilan.model.Ilan;
import proje.ilan.model.IlanSurec;
import proje.ilan.model.Kullanici;
import proje.ilan.model.Surec;
import proje.ilan.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/*
Java Generic yapıları
 */
public abstract class Service<T> extends DBUtil{

    private IlanSurecService ilanSurecService;

    public void ekle(T t)
    {
        try {
            if (t instanceof Ilan) {
                Ilan ilan = (Ilan) t;
                String sql = "insert into ilan(baslik,detay,kategori,kullanici_id) " +
                        " values('"+ilan.getBaslik()+"','"+ilan.getDetay()+"','"+ilan.getKategori().name()+"'" +
                        ",'"+ilan.getKullanici().getId()+"')";

                int row = executeSql(sql);

                // ?? ilan.setId(row);

                if (ilanSurecService == null)
                    ilanSurecService = new IlanSurecService();

                IlanSurec ilanSurec = new IlanSurec();
                ilanSurec.setId(1l);
                ilanSurec.setIlan(ilan);
                ilanSurec.setSurec(Surec.ONAY_BEKLIYOR);

                ilanSurecService.ekle(ilanSurec);


                logYaz("ilan eklendi!", ilan.toString());
            } else if (t instanceof Kullanici) {

                Kullanici kullanici = (Kullanici) t;

                String sql = "INSERT INTO kullanici(" +
                        "adi, soyadi, adres)" +
                        "VALUES ('"+ kullanici.getAdi() +"', ' "+ kullanici.getSoyadi() + "', '" + kullanici.getAdres() + "');";

                int row = executeSql(sql);

                logYaz("Kullanıcı eklendi! ID :"+row, kullanici.toString());

            } else if (t instanceof IlanSurec) {

                System.out.println("<----- Veritabanın yazılıyor....... ");

                IlanSurec ilanSurec = (IlanSurec) t;

                String sql = "insert into ilan_surec(ilan_id,surec) " +
                        " values("+ilanSurec.getIlan().getId()+", '"+ilanSurec.getSurec().name()+"')";

                int row = executeSql(sql);

                System.out.println("<------- Veritabanına sürec yazıldı! ------>");

                logYaz("İlan Süreç Bilgi Eklendi!", ilanSurec.toString());

            }
        }
        catch (Exception ex)
        {
            System.out.println("Hata -> "+ex.getMessage());
        }
    }

    private int executeSql(String sql) throws SQLException {
        PreparedStatement statement = connection().prepareStatement(sql);
        int row = statement.executeUpdate();
        return row;
    }

    public void sil(T t){
        try {
            if (t == null)
                throw new Exception("Model nesnesi null!");

            if (t instanceof Ilan) {


                Ilan ilan = (Ilan) t;

                if (DBUtil.ILAN_LISTESI != null) {
                    DBUtil.ILAN_LISTESI.remove(ilan);
                    logYaz("İlan eklendi!", ilan.toString());
                } else {
                    logYaz("İlan listesi boş!");
                }


            } else if (t instanceof Kullanici) {

                Kullanici kullanici = (Kullanici) t;
                for (int i = 0; i < DBUtil.KULLANICI_LISTESI.size(); i++) {
                    if (DBUtil.KULLANICI_LISTESI.get(i).getId().equals(kullanici.getId())) {
                        DBUtil.KULLANICI_LISTESI.remove(i);
                        break;
                    }
                }

                logYaz("Kullanıcı silindi!", kullanici.toString());


            } else if (t instanceof IlanSurec) {

                IlanSurec ilanSurec = (IlanSurec) t;

                if (DBUtil.ILAN_SUREC_LISTESI != null) {
                    for (int i = 0; i < DBUtil.ILAN_SUREC_LISTESI.size(); i++) {
                        if (DBUtil.ILAN_SUREC_LISTESI.get(i).equals(ilanSurec.getId())) {
                            DBUtil.ILAN_SUREC_LISTESI.remove(i);
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            logYaz("Hata ->",ex.getMessage());
        }
    }

    public void methodYaz(String methodAdi)
    {
        System.out.println(methodAdi+" çağrıldı!");
    }

    public abstract void logYaz(String mesaj,String ...parametreler);

}
