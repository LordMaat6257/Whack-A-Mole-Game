import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class WhackAMoleGame extends JFrame {
    private JButton[] buttons;
    private int score;
    private JLabel scoreLabel;
    private Timer timer;
    private Random random;

    public WhackAMoleGame() {
        setTitle("Whack-a-Mole Game");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        random = new Random();
        score = 0;

        scoreLabel = new JLabel("Score: " + score);
        add(scoreLabel);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.GREEN);
            buttons[i].setEnabled(false);
            buttons[i].addMouseListener(new MoleClickListener(i));
            add(buttons[i]);
        }

        timer = new Timer(1000, new MoleTimer());
        timer.start();
    }

    private class MoleClickListener extends MouseAdapter {
        private int index;

        public MoleClickListener(int index) {
            this.index = index;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (buttons[index].isEnabled()) {
                score++;
                scoreLabel.setText("Score: " + score);
                buttons[index].setEnabled(false);
            }
        }
    }

    private class MoleTimer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int moleIndex = random.nextInt(9);
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setEnabled(false);
            }
            buttons[moleIndex].setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WhackAMoleGame game = new WhackAMoleGame();
            game.setVisible(true);
        });
    }
}
