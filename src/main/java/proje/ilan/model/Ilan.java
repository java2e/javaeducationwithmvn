package proje.ilan.model;

/*
Kalıtım İnheritance
extends
Single Kalıtım
Coklu kalıtım c++
 */

public class Ilan extends BaseModel {


    public Ilan()
    {

    }

    public Ilan(Long id,String baslik,String detay,Kategori kategori,Kullanici kullanici){

        setId(id);
        this.baslik = baslik;
        this.detay = detay;
        this.kategori = kategori;
        this.kullanici = kullanici;

    }

    private String  baslik;

    private String detay;

    private Kategori kategori; // ENUM

    private Kullanici kullanici;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }


    @Override
    public String toString() {
        return "Ilan{" +
                "baslik='" + baslik + '\'' +
                ", detay='" + detay + '\'' +
                ", kategori=" + kategori +
                ", kullanici=" + kullanici +
                '}';
    }
}
