import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by xj on 2017-05-09.
 */
@SuppressWarnings("WeakerAccess")
public class View extends JFrame {

    private int mSize;
    private int nSize;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private JPanel mainPanel;
    private JLabel statusLabel;
    private JButton goButton;

    private boolean startSelected;
    private boolean endSelected;

    private Cell[][] cells;

    public View() {
        setTitle("Ant colony thing.");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        statusLabel = new JLabel("Hello.");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        statusLabel.setFont(new Font(statusLabel.getFont().getName(), Font.BOLD, 20));
        add(statusLabel, constraints);

        mainPanel = new JPanel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        add(mainPanel, constraints);

        goButton = new JButton("GO");
        constraints.gridy = 2;
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
        goButton.setEnabled(false);
        add(goButton, constraints);

        pack();

        startSelected = false;
        endSelected = false;
    }

    private void startSimulation() {
        synchronized (this){
            notify();
        }
        goButton.setVisible(false);
    }

    public int obtainN() {
        String input = JOptionPane.showInputDialog(mainPanel, "Please provide the value for N:");
        nSize = Integer.parseInt(input);
        return nSize;
    }

    public int obtainM() {
        String input = JOptionPane.showInputDialog(mainPanel, "Please provide the value for M:");
        mSize = Integer.parseInt(input);
        return mSize;
    }


    public void buildGrid() {

//        we should by now know N and M
        cells = new Cell[mSize][nSize];

        mainPanel.setLayout(new GridLayout(mSize, nSize));
        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < nSize; j++) {
                Cell cell = new Cell(j, i, this);
                cells[i][j] = cell;
                mainPanel.add(cell);
            }
        }
    }

    public void display() {
        setVisible(true);
        pack();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    private void setStartingSquare(int x, int y) {
        startSelected = true;
        synchronized (this) {
            this.startX = x;
            this.startY = y;
            this.notify();
        }
    }

    private void setEndingSquare(int x, int y) {
        endSelected = true;
        synchronized (this) {
            this.endX = x;
            this.endY = y;
            this.notify();
        }
        goButton.setEnabled(true);
    }

    public int getStartY() {
        return startY;
    }


    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public Cell[][] getCells() {
        return cells;
    }

    protected class Cell extends JPanel {
        private int x, y;

        private JLabel label;

        private View view;

        public Cell(int x, int y, View view) {
            this.x = x;
            this.y = y;
            this.view = view;

            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setPreferredSize(new Dimension(40, 35));
            setBackground(Color.WHITE);
            setVisible(true);

            setUpListeners();

            label = new JLabel();
            this.add(label);
        }

        public void setText(String text) {
            if (text.equals("0"))
                label.setText("");
            else
                label.setText(text);
        }

        public void setColorIntensity(float intensity) {
//            set background color respectively to the value given as argument (should be between 0 and 1)
            if (intensity > 1) intensity = 1;
            if (intensity < 0) intensity = 0;
            Color c = new Color(1,1-intensity,1-intensity);
            setBackground(c);
        }

        private void setUpListeners() {
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!view.startSelected) {
                        view.setStartingSquare(x, y);
                        setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
                    }else if (!view.endSelected) {
                        view.setEndingSquare(x, y);
                        setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
    }
}
