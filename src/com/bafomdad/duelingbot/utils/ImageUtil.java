package com.bafomdad.duelingbot.utils;

import com.bafomdad.duelingbot.api.IPosition;
import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.internal.FieldObject;
import com.bafomdad.duelingbot.internal.PlayingField;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by bafomdad on 2/5/2018.
 */
public class ImageUtil {

    private static final String DIR = "images/";
    private static final File FIELD = new File(DIR + "field.png");
    private static final File CARDBACK = new File(DIR + "cardBack.png");
    private static final File SPELLCARD = new File(DIR + "cardSpell.png");
    private static final File TRAPCARD = new File(DIR + "cardTrap.png");
    private static final File NORMALMONSTER = new File(DIR + "cardMonster.png");
    private static final File EFFECTMONSTER = new File(DIR + "cardEffectMonster.png");
    private static final File FUSIONMONSTER = new File(DIR + "cardFusionMonster.png");
    private static final File RITUALMONSTER = new File(DIR + "cardRitualMonster.png");

    private static BufferedImage cardDefault = null;

    public static ByteArrayInputStream getField(PlayingField pf) {

        BufferedImage field;
        try {
            field = create(FIELD);
            cardDefault =  create(CARDBACK);
            final BufferedImage output = new BufferedImage(field.getWidth(), field.getHeight(), BufferedImage.TYPE_INT_RGB);

            Graphics g = output.getGraphics();
            g.drawImage(field, 0, 0, null);
            drawMonsterZone(g, pf);
            drawSpellZone(g, pf);
            drawSpellField(g, pf);
            drawDeck(g, pf);
            g.dispose();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(output, "png", bos);
            return new ByteArrayInputStream(bos.toByteArray());
//            return "data:image/png;base64," + DatatypeConverter.printBase64Binary(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void drawCard(Graphics g, IPosition position) {

    }

    private static void drawMonsterZone(Graphics g, PlayingField pf) throws IOException {

        FieldObject[] objs = pf.getMonsterZone();
        for (int i = 1; i <= objs.length; i++) {
            FieldObject obj = objs[i - 1];
            if (obj.getCard() != null) {
                if (obj.isFaceDown()) {
                    if (obj.isDefensePosition())
                        g.drawImage(rotate(cardDefault), 8 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY() + 3, null);
                    else
                        g.drawImage(cardDefault, 12 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY(), null);
                } else {
                    BufferedImage monster = create(NORMALMONSTER);
                    if (obj.isDefensePosition())
                        g.drawImage(rotate(monster), 8 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY() + 3, null);
                    else
                        g.drawImage(monster, 12 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY(), null);
                }
            }
        }
    }

    private static void drawSpellZone(Graphics g, PlayingField pf) throws IOException {

        FieldObject[] objs = pf.getSpellZone();
        for (int i = 1; i <= objs.length; i++) {
            FieldObject obj = objs[i - 1];
            if (obj.getCard() != null) {
                if (obj.isFaceDown()) {
                    g.drawImage(cardDefault, 12 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY(), null);
                } else {
                    switch (obj.getCard().getCardType()) {
                        case TRAP: g.drawImage(create(TRAPCARD), 12 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY(), null);
                        case SPELL: g.drawImage(create(SPELLCARD), 12 + ((int)obj.getPosition().getX()) * i / 2, (int)obj.getPosition().getY(), null);
                    }
                }
            }
        }
    }

    private static void drawSpellField(Graphics g, PlayingField pf) throws IOException {

        FieldObject obj = pf.getFieldZone()[0];
        if (obj.getCard() != null) {
            if (obj.isFaceDown()) {
                g.drawImage(cardDefault, (int)obj.getPosition().getX(), (int)obj.getPosition().getY(), null);
            } else {
                g.drawImage(create(SPELLCARD), (int)obj.getPosition().getX(), (int)obj.getPosition().getY(), null);
            }
        }
    }

    private static void drawDeck(Graphics g, PlayingField pf) throws IOException {

        Deck deck = pf.getPlayerDeck();
        if (!deck.getDeck().isEmpty() && !deck.isConvulsed())
            g.drawImage(cardDefault, (int)deck.getPosition().getX(), (int)deck.getPosition().getY(), null);
    }

    private static BufferedImage create(File f) throws IOException {

        BufferedImage img = ImageIO.read(f);
        return img;
    }

    private static BufferedImage rotate(BufferedImage img) {

        AffineTransform tx = new AffineTransform();
        tx.rotate(1.6, img.getHeight() / 2, img.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(img, null);
    }
}
