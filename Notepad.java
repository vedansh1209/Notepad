import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Notepad {
    private static JTextArea textArea;
    private static int fontSize = 20;

    public static void main(String[] args) {
        JFrame j = new JFrame("Notepad");
        j.setSize(700, 700);
        j.setLocationRelativeTo(null);
        j.getContentPane().setBackground(Color.white);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(new BorderLayout());

        JMenuBar jmb = new JMenuBar();
        j.setJMenuBar(jmb);
        jmb.setBackground(new Color(0xAEDEFF));

        JMenu m1 = new JMenu("File");
        jmb.add(m1);
        m1.setPreferredSize(new Dimension(70, 30));
        JMenuItem m11 = new JMenuItem("New");
        m1.add(m11);
        JMenuItem m12 = new JMenuItem("Open");
        m1.add(m12);
        JMenuItem m13 = new JMenuItem("Save");
        m1.add(m13);
        JMenuItem m14 = new JMenuItem("Save As");
        m1.add(m14);
        m1.addSeparator();
        JMenuItem m15 = new JMenuItem("Page Setup...");
        m1.add(m15);
        JMenuItem m16 = new JMenuItem("Print...");
        m1.add(m16);
        m1.addSeparator();
        JMenuItem m17 = new JMenuItem("Exit");
        m1.add(m17);

        JMenu m2 = new JMenu("Edit");
        jmb.add(m2);
        m2.setPreferredSize(new Dimension(70, 30));
        JMenuItem m21 = new JMenuItem("Undo");
        m2.add(m21);
        m2.addSeparator();
        JMenuItem m22 = new JMenuItem("Cut");
        m2.add(m22);
        JMenuItem m23 = new JMenuItem("Copy");
        m2.add(m23);
        JMenuItem m24 = new JMenuItem("Paste");
        m2.add(m24);
        JMenuItem m25 = new JMenuItem("Delete");
        m2.add(m25);
        m2.addSeparator();
        JMenuItem m26 = new JMenuItem("Find...");
        m2.add(m26);
        JMenuItem m27 = new JMenuItem("Find Next");
        m2.add(m27);
        JMenuItem m28 = new JMenuItem("Replace...");
        m2.add(m28);
        JMenuItem m29 = new JMenuItem("Go To...");
        m2.add(m29);
        m2.addSeparator();
        JMenuItem m210 = new JMenuItem("Select All");
        m2.add(m210);
        JMenuItem m211 = new JMenuItem("Time/Date");
        m2.add(m211);

        JMenu m3 = new JMenu("View");
        jmb.add(m3);
        m3.setPreferredSize(new Dimension(70, 30));
        JMenuItem m31 = new JMenuItem("Zoom In");
        m3.add(m31);
        JMenuItem m32 = new JMenuItem("Zoom Out");
        m3.add(m32);
        JMenuItem m33 = new JMenuItem("Reset Zoom");
        m3.add(m33);
        JMenuItem m34 = new JMenuItem("Status Bar");
        m3.add(m34);

        textArea = new JTextArea(20, 40);
        updateFontSize();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        j.add(scrollPane, BorderLayout.CENTER);
        JLabel statusBar = new JLabel("Ready");
        j.add(statusBar, BorderLayout.SOUTH);

        m31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontSize += 2;
                updateFontSize();
            }
        });

        m32.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fontSize > 2) {
                    fontSize -= 2;
                    updateFontSize();
                }
            }
        });

        m33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontSize = 20;
                updateFontSize();
            }
        });

        j.setVisible(true);
    }

    private static void updateFontSize() {
        textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }
}
