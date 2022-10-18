
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asansor extends Thread {

    public String isim;
    public int kapasite = 10;
    public int bulundugu_kat = 0;
    public String yon = "yukari";
    public boolean durum = false; // aktif / pasif durumunu tutar
    public int icindeki_kisi = -1;
    public int hedef = -1;
    public int ensongeldigikat; // aşağı inerken kata getirdiğini geri götürmeye çalışabilir diye ekledik.
    
    private Lock lock = new ReentrantLock(); // senkranizasyon görevi görüyor.

    public Asansor(String isim, boolean durum) {
        this.isim = isim;
        this.durum = durum;
    }

    @Override
    public void run() {
        for (;;) {
            lock.lock();
            try {
                if (durum == true) {
                    if ("yukari".equals(yon)) {
                        if (icindeki_kisi == -1 && hedef == -1) {
                            icindeki_kisi = Main.yukariDizi.get(0).getKisi();
                            hedef = Main.yukariDizi.get(0).getHedef();
                            Main.katlar[0] -= icindeki_kisi;
                            Main.yukariDizi.remove(0);
                            Main.yukari_kisi.take();
                            Main.yukari_hedef.take();
                        }

                        if (bulundugu_kat < hedef) {
                            bulundugu_kat++;
                        } else if (bulundugu_kat == hedef) {
                            ensongeldigikat = hedef;
                            Main.katlar[hedef] += icindeki_kisi; // girişten gelenleri bu kata ekliyoruz.
                             // girişten gelenleri siliyoruz.
                            Main.kontrolDizi.get(0).setKisisayisi(Main.katlar[hedef]);
                            yon = "asagi";
                            hedef = -1;
                            icindeki_kisi = -1;
                        }
                        if (bulundugu_kat == 4) {
                            yon = "asagi";
                            hedef = -1;
                            icindeki_kisi = -1;
                        }
                        //System.out.println("Yukarı gidiyor. Hedef : " + hedef + "Bulunudugu Kat : " + bulundugu_kat);
                    } else if ("asagi".equals(yon)) {
                        if (Main.asagiDizi.size() > 0) {
                            //System.out.println("sss - " + Main.asagiDizi.get(0).getKisi());
                            if (ensongeldigikat != bulundugu_kat) {
                                if (Main.asagiDizi.get(0).getKisi() <= Main.katlar[bulundugu_kat]) {
                                    if (icindeki_kisi == -1 && hedef == -1) {
                                        icindeki_kisi = Main.asagiDizi.get(0).getKisi();//Main.yukari_kisi.take();
                                        hedef = Main.asagiDizi.get(0).getHedef();//Main.yukari_hedef.take();
                                        Main.katlar[bulundugu_kat] -= icindeki_kisi;
                                        Main.kontrolDizi.get(bulundugu_kat).setKisisayisi(Main.katlar[bulundugu_kat]);
                                        Main.asagiDizi.remove(0);
                                        Main.asagi_kisi.take();
                                        Main.asagi_hedef.take();
                                    }
                                }
                            }

                        }
                        if (bulundugu_kat > 0) {
                            bulundugu_kat--;
                        }
                        if (bulundugu_kat == 0) {
                            yon = "yukari";
                            hedef = -1;
                            icindeki_kisi = -1;
                        }

                        //System.out.println("Aşağı gidiyor. Hedef : " + hedef + "Bulunudugu Kat : " + bulundugu_kat);
                    }
                }
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Asansor.class.getName()).log(Level.SEVERE, null, ex);
            }
            lock.unlock();
        }
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getHedef() {
        return hedef;
    }

    public void setHedef(int hedef) {
        this.hedef = hedef;
    }

    public int getIcindeki_kisi() {
        return icindeki_kisi;
    }

    public void setIcindeki_kisi(int icindeki_kisi) {
        this.icindeki_kisi = icindeki_kisi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public int getBulundugu_kat() {
        return bulundugu_kat;
    }

    public void setBulundugu_kat(int bulundugu_kat) {
        this.bulundugu_kat = bulundugu_kat;
    }

    public String getYon() {
        return yon;
    }

    public void setYon(String yon) {
        this.yon = yon;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

}
