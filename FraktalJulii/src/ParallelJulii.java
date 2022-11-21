import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class ParallelJulii extends Thread {
    final static int N = 4096;

    //stala okreslająca czy szereg manderglora w aktualnym punkcje będzie nieskończony
    final static int CUTOFF = 100;
    final static double X = -0.10;
    final static double Y = 0.65;
    static int[][] set = new int[N][N];
    public static void main(String[] args) throws Exception {
//ustawienie stopera liczącego czas obliczeń
        long startTime = System.currentTimeMillis();
//ustawienie 4 wątków generujących fraktal w 4 ćwiartkach
        ParallelJulii thread0 = new ParallelJulii(0);
        ParallelJulii thread1 = new ParallelJulii(1);
        ParallelJulii thread2 = new ParallelJulii(2);
        ParallelJulii thread3 = new ParallelJulii(3);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
//czekanie głównego programu na zakończenie 4 wątków roboczych
        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();
//zakończenie działania stopera i wyświetlenie czasu generowania fraktala
        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");
//ustawienie pliku graficznego, w którym zostanie wygenerowany fraktal
                BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
//wstawianie pixeli do pliku graficznego
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = set[i][j];
                float level;
                if (k < CUTOFF) {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = (float) k / CUTOFF;
                } else {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = 0;
                }
//zapisywanie pixela (na zielono lub czarno)
                Color c = new Color(0, level, 0); // zielony
                img.setRGB(i, j, c.getRGB());
            }
        }
//zapis rysunku do pliku Mandelbrot.png
        ImageIO.write(img, "PNG", new File("Julii.png"));
    }
    int me;
public ParallelJulii(int me) {
      this.me = me;
}

    public void run() {
        int begin = 0, end = 0;
        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        }
        else if (me == 1) {
            begin = (N / 4) * 1;
            end = (N / 4) * 2;
        }
        else if (me == 2) {
            begin = (N / 4) * 2;
            end = (N / 4) * 3;
        }
        else if (me == 3) {
            begin = (N / 4) * 3;
            end = N;
        }
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
//przeskalowanie punktów cr i ci z dziedziny obrazka do
//układu wspolrzednych kartezjanskich
                double cr = (4.0 * i - 2 * N) / N;
                double ci = (4.0 * j - 2 * N) / N;
                double zr = cr, zi = ci;
                int k = 0;
//sprawdzanie czy szereg jest nieskonczony
                while (k < CUTOFF && zr * zr + zi * zi < 4.0) {
// z = c + z * z
                    double newr = X + (zr*zr - zi*zi);
                    double newi = Y + (zr*zi + zr*zi);
                    zr = newr;
                    zi = newi;
                    k++;
                }
                set[i][j] = k;
            }
        }
    }
}

