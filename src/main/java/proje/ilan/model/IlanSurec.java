package proje.ilan.model;

public class IlanSurec extends BaseModel {



    public IlanSurec(){}

    public IlanSurec(Long id,Ilan ilan,Surec surec)
    {
        setId(id);
        this.ilan = ilan;
        this.surec = surec;
    }

    private Ilan ilan;

    private Surec surec;

    public Ilan getIlan() {
        return ilan;
    }

    public void setIlan(Ilan ilan) {
        this.ilan = ilan;
    }

    public Surec getSurec() {
        return surec;
    }

    public void setSurec(Surec surec) {
        this.surec = surec;
    }

    @Override
    public String toString() {
        return "IlanSurec{" +
                "ilan=" + ilan +
                ", surec=" + surec +
                '}';
    }
}
