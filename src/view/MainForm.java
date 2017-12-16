package view;

import model.AbstractRobot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JPanel pnlAxisX;
    private JPanel pnlYAxis;
    private JButton btnSetStart;
    private JButton btnClear;
    private JButton btnSetEnd;
    private JButton btnStart;
    private JComboBox cmbDirection;
    private JPanel pnlCanvas;
    public static CanvasPanel canvasPanel;

    public enum EditingMode {
        START_POINT,
        END_POINT
    }

    private EditingMode mode;

    public MainFormDelegate delegate;


    public MainForm() {
        super("AbstractRobot");
        //draw on a canvas
        canvasPanel = new CanvasPanel();
        canvasPanel.setPreferredSize(new Dimension(640, 480));
        pnlCanvas.add(canvasPanel, BorderLayout.CENTER);

        cmbDirection.setModel(new DefaultComboBoxModel(AbstractRobot.Direction.values()));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mode = EditingMode.START_POINT;

        btnSetStart.addActionListener(e -> {
            mode = EditingMode.START_POINT;
        });

        btnSetEnd.addActionListener(e -> {
            mode = EditingMode.END_POINT;
        });

        btnStart.addActionListener(e -> {
            if (delegate != null) {
                delegate.didStart();
            }
        });

        btnClear.addActionListener(e -> {
            //todo clear
            if (delegate != null) {
                delegate.didClickOnCanvas(-1, -1, EditingMode.START_POINT);
                delegate.didClickOnCanvas(-1, -1, EditingMode.END_POINT);
                mode = EditingMode.START_POINT;
            }
        });

        cmbDirection.addActionListener(e -> {
            if (delegate != null) {
                delegate.didPickDirection((AbstractRobot.Direction) cmbDirection.getSelectedItem());
            }
        });

        pnlCanvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - canvasPanel.getX();
                int y = e.getY() - canvasPanel.getY();
                if (delegate != null) {
                    delegate.didClickOnCanvas(x, y, mode);
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
