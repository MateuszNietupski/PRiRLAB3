import java.util.concurrent.Semaphore ;
public class f1 extends Thread {
    static  int MAX;
    static Semaphore[] widelec;
    int mojNum;

    public f1(int MAX, int nr) {
        mojNum = nr;
        f1.MAX = MAX;
        widelec = new Semaphore[MAX];
        for (int i = 0; i < f1.MAX; i++) {
            widelec[i] = new Semaphore( 1 );
        }
    }

    public void run() {
        while (true) {
// myslenie
            System.out.println("Mysle ¦ " + mojNum);
            try {
                Thread.sleep((long) (7000 * Math.random()));
            } catch (InterruptedException ignored) {
            }
            widelec[mojNum].acquireUninterruptibly(); //przechwycenie L widelca
            widelec[(mojNum + 1) % MAX].acquireUninterruptibly(); //przechwycenie P widelca
// jedzenie
            System.out.println("Zaczyna jesc " + mojNum);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException ignored) {
            }
            System.out.println("Konczy jesc " + mojNum);
            widelec[mojNum].release(); //zwolnienie L widelca
            widelec[(mojNum + 1) % MAX].release(); //zwolnienie P widelca
        }
    }
}
