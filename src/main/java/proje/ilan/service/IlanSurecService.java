package proje.ilan.service;

import proje.ilan.model.Ilan;
import proje.ilan.model.IlanSurec;
import proje.ilan.model.Surec;
import proje.ilan.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class IlanSurecService extends Service<IlanSurec> implements BaseService<IlanSurec>{

    // onay_beklıyor -> aktif -> deaktif
    public boolean surecDegistir(Ilan ilan)
    {

        try{

            String sqlIlanSurec = "select * from ilan_surec where ilan_id="+ilan.getId();

            PreparedStatement statement = connection().prepareStatement(sqlIlanSurec);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {

                Long id = resultSet.getLong("id");
                // Long ilanId = resultSet.getLong("ilan_id");
                String surec = resultSet.getString("surec");

                IlanSurec ilanSurec = new IlanSurec(id,ilan,Surec.valueOf(surec));
                String sql ="";
                if(ilanSurec.getSurec().equals(Surec.ONAY_BEKLIYOR)) {
                    ilanSurec.setSurec(Surec.AKTIF);

                    sql = " update ilan_surec set surec= '"+Surec.AKTIF.name()+"' where id = "+ilanSurec.getId();

                    logYaz("İlanın süreci AKTIF hale getirildi! ONAY_BEKLIYOR -> AKTIF");

                }else if(ilanSurec.getSurec().equals(Surec.AKTIF)) {
                    ilanSurec.setSurec(Surec.DEAKTIF);
                    sql = " update ilan_surec set surec= '"+Surec.DEAKTIF.name()+"' where id = "+ilanSurec.getId();
                    logYaz("İlanın süreci DEAKTIF hale getirildi! AKTIF -> DEAKTIF");
                }

                PreparedStatement statement1 = connection().prepareStatement(sql);

                int row = statement1.executeUpdate();
                return true;

            }

        }

        catch (Exception ex)
        {
            logYaz("Hata IlanSurecService SurecDegistir => ",ex.getMessage());
        }

        logYaz("İlan süreci değiştirilmedi!");
        return false;
    }



    @Override
    public List<IlanSurec> getList() {
        return DBUtil.ILAN_SUREC_LISTESI;
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
