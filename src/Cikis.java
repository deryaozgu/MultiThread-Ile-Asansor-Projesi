
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cikis extends Thread {
    Random random = new Random();
    private int kisi,hedef;
    
    private Lock lock = new ReentrantLock(); // senkranizasyon görevi görüyor.
    
    @Override
    public void run() {
        for(;;){
            lock.lock();
            kisi = random.nextInt(5) + 1;
            hedef = random.nextInt(4) + 1;
            
            try {
                if(Main.katlar[hedef] >= kisi){
                    Yon yon = new Yon(kisi, hedef, 1);
                    Main.asagiDizi.add(yon);
                    Main.asagi_kisi.put(kisi);
                    Main.asagi_hedef.put(hedef);
                }
                else {
                    continue;
                }
                Thread.sleep(1000);
                System.out.println("Çıkış Thread Çalışıyor.");
                System.out.println("çıkış hedef:" + Main.asagi_hedef);
                System.out.println("çıkış kisi : " + Main.asagi_kisi);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cikis.class.getName()).log(Level.SEVERE, null, ex);
            }
            lock.unlock();
        }
    }
    
}
