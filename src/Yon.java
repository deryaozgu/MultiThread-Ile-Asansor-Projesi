
public class Yon {
    private int kisi;
    private int hedef = 0;
    private int yon = 0; // 0 : yukari 1 : asagi

    public Yon(int kisi, int hedef, int yon) {
        this.kisi = kisi;
        this.hedef = hedef;
        this.yon = yon;
    }

    public int getKisi() {
        return kisi;
    }

    public void setKisi(int kisi) {
        this.kisi = kisi;
    }

    public int getHedef() {
        return hedef;
    }

    public void setHedef(int hedef) {
        this.hedef = hedef;
    }

    public int getYon() {
        return yon;
    }

    public void setYon(int yon) {
        this.yon = yon;
    }
    
    
}
