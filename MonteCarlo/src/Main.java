public class Main {

    public static void main(String[] args) {
        int n = 10;
        double a = 250;
        MonteCarlo m1 = new MonteCarlo(0,0, a/2, a/2, n);
        MonteCarlo m2 = new MonteCarlo(a/2,0, 1, a/2, n);
        MonteCarlo m3 = new MonteCarlo(0, a/2, a/2, a, n);
        MonteCarlo m4 = new MonteCarlo(a/2,a/2, a, a, n);

        m1.start();
        m2.start();
        m3.start();
        m4.start();

        try {
            m1.join();
            m2.join();
            m3.join();
            m4.join();
        }catch (Exception e){ }

        double pole = m1.getPoints() + m2.getPoints() + m3.getPoints() + m4.getPoints();
        pole = pole / ((double)n * 4) * (8 * 8);
        System.out.println("Pole wynosi " + pole);
    }


}
