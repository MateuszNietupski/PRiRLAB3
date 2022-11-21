package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class Main{

    static public void main(String args[]) throws Exception {
        BufferedImage image;
        int width;
        int height;
        File input = new File("Ratusz_Białystok_rok_2013.jpg");
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        Negatyw quart1 = new Negatyw(image, 0, 0, width/2, height / 2);
        Negatyw quart2 = new Negatyw(image, width / 2, 0, width, height / 2);
        Negatyw quart3 = new Negatyw(image, 0, height / 2, width / 2, height);
        Negatyw quart4 = new Negatyw(image, width / 2, height / 2, width, height);

        quart1.start();
        quart2.start();
        quart3.start();
        quart4.start();
        try {
            quart1.join();
            quart2.join();
            quart3.join();
            quart4.join();
        } catch (Exception e) {
            System.out.println();
        }


        File negatywJPG = new File("Negatyw.jpg");
        ImageIO.write(image,"jpg",negatywJPG);

    }
}

 class Negatyw extends Thread{
    BufferedImage image;
    int StartX,StopX,StartY,StopY;

     public Negatyw(BufferedImage image, int startX, int startY, int stopX, int stopY) {
         this.image = image;
         StartX = startX;
         StopX = stopX;
         StartY = startY;
         StopY = stopY;
     }

     @Override
     public void run() {
         for (int i = StartX; i < StopX; i++) {
             for (int j = StartY; j < StopY; j++) {

                 //odczyt składowych koloru RGB
                 Color c = new Color(image.getRGB(i, j));
                 int red = (int) (c.getRed());
                 int green = (int) (c.getGreen());
                 int blue = (int) (c.getBlue());

                 int final_red, final_green, final_blue;

                 //negatyw
                 final_red = 255 - red;
                 final_green = 255 - green;
                 final_blue = 255 - blue;
                 Color newColor = new Color(final_red, final_green, final_blue);
                 image.setRGB(i, j, newColor.getRGB());
             }
         }
         System.out.println("Cwiartka: " + Thread.currentThread().getId() + "Wymiary startx: " + StartX + " starty " + StartY + " stopx" + StopX + " stopy  " + StopY);
     }

}