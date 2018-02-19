package com.bafomdad.duelingbot.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by bafomdad on 2/4/2018.
 */
public class TestImage {

    private static int rotateOffsetX = -4;
    private static int rotateOffsetY = 4;

    public static void main(String[] args) {

        BufferedImage card = null;
        BufferedImage field = null;

        try {
            card = ImageIO.read(new File("images/cardBack.png"));
            field = ImageIO.read(new File("images/field.png"));
            final BufferedImage output = new BufferedImage(field.getWidth(), field.getHeight(), BufferedImage.TYPE_INT_RGB);

            int startX = 64;
            int startY = 25;

            Graphics g = output.createGraphics();
            g.drawImage(field, 0, 0, null);
//            for (int i = 0; i < 5; i++) {
//                g.drawImage(rotate(card), (startX - 12) * i + (card.getHeight() + 9), startY + rotateOffsetY, null);
//            }
            Random rand = new Random();
            for (int i = 1; i <= 5; i++) {
                if (rand.nextBoolean())
                    g.drawImage(card, 12 + (startX) * i / 2, startY, null);
                else
                    g.drawImage(rotate(card), 8 + (startX) * i / 2, startY + 3, null);
            }
//            g.drawImage(rotate(card), startX + rotateOffsetX, startY + rotateOffsetY, null);
//            g.drawImage(rotate(card), startX + rotateOffsetX + card.getHeight() + 1, startY + rotateOffsetY, null);
//            g.drawImage(card, 4, 33, null);
//            g.drawImage(card, startX, startY + card.getHeight() + 1, null);
//            g.drawImage(card, startX + card.getWidth() + 9, startY + card.getHeight() + 1, null);
            g.dispose();

            ImageIO.write(output, "png", new File("output.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage rotate(BufferedImage img) {

        AffineTransform tx = new AffineTransform();
        tx.rotate(1.575, img.getHeight() / 2, (img.getHeight() / 2) + 1);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(img, null);
    }
}
