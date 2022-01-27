package proje.ilan.util;

import proje.ilan.model.Ilan;
import proje.ilan.model.IlanSurec;
import proje.ilan.model.Kullanici;
import proje.ilan.service.IlanSurecService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DBUtil {

    public static List<Kullanici> KULLANICI_LISTESI;

    public static List<Ilan> ILAN_LISTESI;

    public static List<IlanSurec> ILAN_SUREC_LISTESI;

    public final String url ="jdbc:postgresql://localhost:5432/sahibinden";
    public final String user="postgres";
    public final String password = "root";

    private static Connection connection;

    public Connection connection()
    {

        try{
            if(connection == null)
                connection = DriverManager.getConnection(url,user,password);
        }
        catch (Exception ex)
        {
            System.out.println("Veritabanına baglanırken hata aldı!"+ ex.getMessage());
        }

        return connection;

    }

}
