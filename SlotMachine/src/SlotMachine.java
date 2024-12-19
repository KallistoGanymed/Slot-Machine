import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class SlotMachine extends JPanel{
    private Color color1;
    private Color color2;
    private Color color3;
    private JLabel label2;
    private JLabel label3;
    private int money = 10;

    public static void main(String[] args) {
        new SlotMachine();
    }

    public SlotMachine() {

        JFrame frame = new JFrame("SlotMachine");
        frame.setSize(815, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.red);

        JLabel label1 = new JLabel("Cost: 3 €");
        label1.setBounds(360, 300, 80, 40);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        frame.add(label1);

        label2 = new JLabel("Spin the Slotmachine to start.");
        label2.setBounds(100, 300, 600, 225);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalTextPosition(SwingConstants.CENTER);
        label2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 40));
        frame.add(label2);

        label3 = new JLabel("Your Money: " + money + " €");
        label3.setBounds(0, 350, 815, 50);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalTextPosition(SwingConstants.CENTER);
        label3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        frame.add(label3);

        JButton button = new JButton("Spin!");
        button.setBounds(360, 330, 80, 30);
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        button.addActionListener(e -> {
            color1 = ColorGenerator();
            color2 = ColorGenerator();
            color3 = ColorGenerator();
            updateLabel();
            frame.repaint();
        });
        frame.add(button);
        frame.add(this);
        frame.setVisible(true);

    }

    public static Color ColorGenerator() {
        int color = ThreadLocalRandom.current().nextInt(1, 10);
        return switch (color) {
            case 1 -> Color.BLUE;
            case 2 -> Color.CYAN;
            case 3 -> Color.GREEN;
            case 4 -> Color.MAGENTA;
            case 5 -> Color.RED;
            case 6 -> Color.ORANGE;
            case 7 -> Color.YELLOW;
            case 8 -> Color.WHITE;
            case 9 -> Color.BLACK;
            default -> null;
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(30, 30, 40));
        g.fillRoundRect(15, 15, 770, 270, 75, 75);
        g.setColor(new Color(60, 60, 70));
        g.fillRoundRect(35, 35, 230, 230, 120, 120);
        g.fillRoundRect(285, 35, 230, 230, 120, 120);
        g.fillRoundRect(535, 35, 230, 230, 120, 120);

        g.setColor(color1);
        g.fillOval(50, 50, 200, 200);
        g.setColor(color2);
        g.fillOval(300, 50, 200, 200);
        g.setColor(color3);
        g.fillOval(550, 50, 200, 200);
    }

    public void updateLabel() {
        if (CheckWin() == 0) {
            money = money - 5;
            label2.setText("You lost!");
            label3.setText("Your money: " + money + " €");
        } else if (CheckWin() == 1) {
            money = money + 5;
            label2.setText("Small Prize: You won 10 €");
            label3.setText("Your money: " + money + " €");
        } else {
            money = money + 95;
            label2.setText("Jackpot: You won 100 €");
            label3.setText("Your money: " + money + " €");
        }
    }

    public int CheckWin() {
        if (color1.equals(color2) && color2.equals(color3)) {
            return 2;
        } else {
            if (color1.equals(color2) || color2.equals(color3) || color1.equals(color3)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
