public class MonteCarlo extends Thread{
    double StartX,StopX,StartY,StopY;
    int n;
    double r;

    int points = 0;

    public MonteCarlo(double startX, double stopX, double startY, double stopY, int n) {
        StartX = startX;
        StopX = stopX;
        StartY = startY;
        StopY = stopY;
        this.n = n;
        r = Math.sqrt(Math.pow(StartX-StopX,2) + Math.pow(StartY-StopY,2))/2;
    }
    public double funkcja(double x)
    {
        return Math.sqrt(Math.pow(r,2) -Math.pow(x,2));
    }

    double randomPoint(double a, double b){
        return a + Math.random() * (b-a);
    }
    double funcIN(double x, double y){
        if ((y > 0) && (y <= funkcja(x)))
            return 1;
        return 0;
    }
    @Override
    public void run() {
        for (int i=0;i<n;i++)
        {
            double x = Math.random();
            double y = Math.random();
            points += funcIN(randomPoint(StartX, StopX), randomPoint(StartY, StopY));
            //   if ((x * x + y * y) <= 1) points++;

        }
    }

    public double getPoints() {
        return points;
    }
}