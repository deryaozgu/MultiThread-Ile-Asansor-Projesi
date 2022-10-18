
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Giris extends Thread {
    Random random = new Random();
    private int kisi,hedef;
    
    private Lock lock = new ReentrantLock(); // senkranizasyon görevi görüyor.
    
    @Override
    public void run() {
        for(;;){
            lock.lock();
            kisi = random.nextInt(10) + 1;
            hedef = random.nextInt(4) + 1;
            
            try {
                Yon yon = new Yon(kisi, hedef, 0);
                Main.yukariDizi.add(yon);
                Main.yukari_hedef.put(hedef);
                Main.yukari_kisi.put(kisi);
                Main.katlar[0] += kisi;
                Thread.sleep(500);
                
                System.out.println("Giris Thread Çalışıyor.");
                System.out.println("hedef:" + Main.yukari_hedef);
                System.out.println("kisi : " + Main.yukari_kisi);
            } catch (InterruptedException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            }
            lock.unlock();
            
        }
    }
    
}
