//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private int x0, y0, x1, y1;
    private JTextField jtf_x0, jtf_y0, jtf_x1, jtf_y1;
    private JPanel drawingPanel;

    public Main() {
        setTitle("Algoritmo de Bresenham");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(222, 255, 245));

        JLabel txt_x0 = new JLabel("x0");
        txt_x0.setBounds(20, 20, 50, 25);
        add(txt_x0);
        jtf_x0 = new JTextField("10");
        jtf_x0.setBounds(70, 20, 50, 25);
        add(jtf_x0);

        JLabel txt_y0 = new JLabel("y0");
        txt_y0.setBounds(20, 60, 50, 25);
        add(txt_y0);
        jtf_y0 = new JTextField("10");
        jtf_y0.setBounds(70, 60, 50, 25);
        add(jtf_y0);

        JLabel txt_x1 = new JLabel("x1");
        txt_x1.setBounds(150, 20, 50, 25);
        add(txt_x1);
        jtf_x1 = new JTextField("200");
        jtf_x1.setBounds(200, 20, 50, 25);
        add(jtf_x1);

        JLabel txt_y1 = new JLabel("y1");
        txt_y1.setBounds(150, 60, 50, 25);
        add(txt_y1);
        jtf_y1 = new JTextField("150");
        jtf_y1.setBounds(200, 60, 50, 25);
        add(jtf_y1);

        JButton drawButton = new JButton("Dibujar Línea");
        drawButton.setBounds(350, 20, 150, 25);
        add(drawButton);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLineBresenham(g, x0, y0, x1, y1);
            }

            private void drawLineBresenham(Graphics g, int x0, int y0, int x1, int y1) {
                int dx = Math.abs(x1 - x0);
                int dy = Math.abs(y1 - y0);
                int sx = x0 < x1 ? 1 : -1;
                int sy = y0 < y1 ? 1 : -1;
                int err = dx - dy;

                while (true) {
                    g.drawRect(x0, y0, 1, 1);  // Dibuja el píxel

                    if (x0 == x1 && y0 == y1) break;  // Si llega al punto final, se detiene
                    int e2 = 2 * err;
                    if (e2 > -dy) {
                        err -= dy;
                        x0 += sx;
                    }
                    if (e2 < dx) {
                        err += dx;
                        y0 += sy;
                    }
                }
            }
        };
        drawingPanel.setBounds(20, 100, 740, 400);
        add(drawingPanel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    x0 = Integer.parseInt(jtf_x0.getText());
                    y0 = Integer.parseInt(jtf_y0.getText());
                    x1 = Integer.parseInt(jtf_x1.getText());
                    y1 = Integer.parseInt(jtf_y1.getText());
                    drawingPanel.repaint(); // redibuja
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores válidos.");
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }
}
