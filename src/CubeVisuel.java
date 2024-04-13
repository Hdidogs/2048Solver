import javax.swing.*;
import java.awt.*;

public class CubeVisuel extends JPanel {
    private int cubeValue;

    public CubeVisuel(int value) {
        this.cubeValue = value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner la tuile avec la couleur appropriée
        Color tileColor = getColorForValue(cubeValue);
        g2d.setColor(tileColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Dessiner le texte (valeur de la tuile) au centre
        g2d.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        String text = (cubeValue == 0) ? "" : String.valueOf(cubeValue);
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2d.drawString(text, x, y);
    }



    private Color getColorForValue(int value) {
        switch (value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            default:
                return Color.LIGHT_GRAY;
        }

    }

}
