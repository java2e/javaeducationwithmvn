package proje.ilan;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class TestDB {


    /*

    Veriyi cekeceğiz
        Select
    Veri kaydedeceğiz
        Insert
    Veriyi sileceğiz
        Delete
    Veriyi güncelleme yapacağız
        Update

        CRUD => create read update delete

     */

    public static void main(String[] args) {

        try {

            String url ="jdbc:postgresql://localhost:5432/sahibinden";
            String user="postgres";
            String password = "root";

            Connection connection = DriverManager.getConnection(url,user,password);

            if(connection != null)
            {
                System.out.println("Veritabanına bağlandı!");
            }
            else{
                System.out.println("Veritabanına bağlanırken hata alındı!");
            }

        }
        catch (Exception ex){

            System.out.println("Veritabanında problem oldu!"+ex.getMessage());


        }

    }
}
