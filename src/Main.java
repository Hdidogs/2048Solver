import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    boolean fin = false;
    static Cube[][] grille = new Cube[4][4];

    public static JLabel scoreLabel = new JLabel("Score: " + Score.getScore());
    public static JPanel mainPanel = new JPanel(new GridLayout(4, 4));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    public Main() {
        super("2048");

        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= 3; y++) {
                grille[i][y] = new Cube(0, false);
            }
        }

        Cube.ramdomCube(grille, Cube.nbrspawn);
        Cube.ramdomCube(grille, Cube.nbrspawn);

        // Fenetre
        setSize(400, 400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Gestion Touche
        addKeyListener(this);

        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mainPanel.add(new CubeVisuel(grille[i][j].getValeur()));
            }
        }

        add(mainPanel);
        add(scoreLabel, BorderLayout.SOUTH);

        setVisible(true);

        int cubeA0 = 0;

        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= 3; y++) {
                if (grille[i][y].getValeur() >= 2048) {
                    System.out.println("2048 Vous avez gagné !");
                    fin = true;
                } else if (grille[i][y].getValeur() == 0) {
                    cubeA0 = cubeA0 + 1;
                }
                Score.setScore(Cube.countVal(grille));
            }
        }

        if(cubeA0==0 && !(Cube.checkMouv(grille))) {
            System.out.println("perdu");
            fin = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Cube.mooveCubeUp(grille);
        } else  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Cube.mooveCubeDown(grille);
        } else  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Cube.mooveCubeRight(grille);
        } else  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Cube.mooveCubeLeft(grille);
        }
        majScreen(this);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void majScreen(JFrame frame) {
        frame.remove(mainPanel);
        frame.remove(scoreLabel);
        mainPanel.removeAll();
        frame.revalidate();
        frame.repaint();

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                mainPanel.add(new CubeVisuel(grille[i][j].getValeur()));
            }
        }

        frame.add(mainPanel);

        Score.setScore(Cube.countVal(grille));

        scoreLabel = new JLabel("Score: " + Score.getScore());
        frame.add(scoreLabel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }
}