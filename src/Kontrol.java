
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Kontrol extends Thread{
    private int kat;
    private int toplam = 0;
    private int kisisayisi;
    private int m = 0;
    
    private Lock lock = new ReentrantLock(); // senkranizasyon görevi görüyor.

    public Kontrol(int kat, int kisisayisi) {
        this.kat = kat;
        this.kisisayisi = kisisayisi;
    }

    @Override
    public void run() {
        for(;;){
            lock.lock();
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kontrol.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < 5; i++) {
                toplam = toplam + Main.kontrolDizi.get(i).getKisisayisi();
            }
            
            m = 0;
            
            if(toplam < 20){
                Main.asansorDizi.forEach((e) -> {
                    if(m == 0){
                        e.durum = true;
                    } else {
                        e.durum = false;
                    }
                    m++;
                });
            }
            else if(toplam >= 20 && toplam < 40){
                Main.asansorDizi.forEach((e) -> {
                    if(m < 2){
                        e.durum = true;
                    } else {
                        e.durum = false;
                    }
                    m++;
                });
            }
            else if(toplam >= 40 && toplam < 60){
                Main.asansorDizi.forEach((e) -> {
                    if(m < 3){
                        e.durum = true;
                    } else {
                        e.durum = false;
                    }
                    m++;
                });
            }
            else if(toplam >= 60 && toplam < 80){
                Main.asansorDizi.forEach((e) -> {
                    if(m < 4){
                        e.durum = true;
                    } else {
                        e.durum = false;
                    }
                    m++;
                });
            }
            else {
                Main.asansorDizi.forEach((e) -> {
                    e.durum = true;
                });
            }
            lock.unlock();
        }
    }

    public int getKisisayisi() {
        return kisisayisi;
    }

    public void setKisisayisi(int kisisayisi) {
        this.kisisayisi = kisisayisi;
    }

    public int getKat() {
        return kat;
    }

    public void setKat(int kat) {
        this.kat = kat;
    }

    public int getToplam() {
        return toplam;
    }

    public void setToplam(int toplam) {
        this.toplam = toplam;
    }
    
    
}
