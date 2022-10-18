
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static BlockingQueue<Integer> yukari_kisi = new LinkedBlockingQueue<Integer>();
    static BlockingQueue<Integer> yukari_hedef = new LinkedBlockingQueue<Integer>();
    static BlockingQueue<Integer> asagi_kisi = new LinkedBlockingQueue<Integer>();
    static BlockingQueue<Integer> asagi_hedef = new LinkedBlockingQueue<Integer>();

    static ArrayList<Kontrol> kontrolDizi = new ArrayList<>();
    static ArrayList<Asansor> asansorDizi = new ArrayList<>();
    static ArrayList<Yon> asagiDizi = new ArrayList<>();
    static ArrayList<Yon> yukariDizi = new ArrayList<>();

    static int[] katlar = new int[5];

    //static int toplamgiris = 0;

    public static void main(String[] args) {

        Giris giris = new Giris();
        giris.start();

        Cikis cikis = new Cikis();
        cikis.start();
        
        Asansor asansor1 = new Asansor("1.Asansor", true);
        asansorDizi.add(asansor1);
        Asansor asansor2 = new Asansor("2.Asansor", false);
        asansorDizi.add(asansor2);
        Asansor asansor3 = new Asansor("3.Asansor", false);
        asansorDizi.add(asansor3);
        Asansor asansor4 = new Asansor("4.Asansor", false);
        asansorDizi.add(asansor4);
        Asansor asansor5 = new Asansor("5.Asansor", false);
        asansorDizi.add(asansor5);
        
        asansor1.start();
        asansor2.start();
        asansor3.start();
        asansor4.start();
        asansor5.start();
        
        Kontrol kontrol = new Kontrol(0, 0);
        kontrolDizi.add(kontrol);
        Kontrol kontrol1 = new Kontrol(1, 0);
        kontrolDizi.add(kontrol1);
        Kontrol kontrol2 = new Kontrol(2, 0);
        kontrolDizi.add(kontrol2);
        Kontrol kontrol3 = new Kontrol(3, 0);
        kontrolDizi.add(kontrol3);
        Kontrol kontrol4 = new Kontrol(4, 0);
        kontrolDizi.add(kontrol4);
        
        kontrol.start();
        kontrol1.start();
        kontrol2.start();
        kontrol3.start();
        kontrol4.start();
        
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        while (true) {
            for (int i = 0; i < katlar.length; i++) {
                System.out.println(i + ". Kattaki Kişi Sayısı : " + katlar[i]);
            }
            System.out.println("**********************\r\n");
            
            asansorDizi.forEach((e) -> {
                System.out.println(e.getIsim());
                
                if(e.isDurum()) System.out.println("Durum \t\t: Aktif / Çalışıyor");
                else System.out.println("Durum \t\t: Pasif / Beklemede");
                
                System.out.println("Bulunduğu Kat \t: " + e.getBulundugu_kat());
                
                if(e.getHedef() == -1) System.out.println("Hedef Kat \t: 0");
                else System.out.println("Hedef Kat \t: " + e.getHedef());
                
                System.out.println("Yön \t\t: " + e.getYon());
                System.out.println("Kapasite \t: " + e.getKapasite());
                
                if(e.getIcindeki_kisi() == -1) System.out.println("İçindeki Kişi \t: 0");
                else System.out.println("İçindeki Kişi \t: " + e.getIcindeki_kisi());
                System.out.println("---------------------------------------------\r\n\r\n");
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

    }
}
