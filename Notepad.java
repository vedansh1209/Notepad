import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.time.LocalDate;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;

public class NotepadTest{
    private static JTextArea textArea;
    public static int fontSize = 20;
    private static UndoManager undoManager;

    public static void main(String[] args) {
        JFrame j = new JFrame("Notepad");
        j.setSize(700, 700);
        j.setLocationRelativeTo(null);
        j.getContentPane().setBackground(Color.white);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(new BorderLayout());

        JMenuBar jmb = new JMenuBar();
        j.setJMenuBar(jmb);
        jmb.setBackground(new Color(0xDBEFFF));

        JMenu m1 = new JMenu("File");
        jmb.add(m1);
        m1.setPreferredSize(new Dimension(70, 30));
        JMenuItem m11 = new JMenuItem("New");
        m11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        m1.add(m11);
        JMenuItem m12 = new JMenuItem("Open");
        m12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        m1.add(m12);
        JMenuItem m13 = new JMenuItem("Save");
        m13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        m1.add(m13);
        JMenuItem m14 = new JMenuItem("Save As");
        m14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        m1.add(m14);
        m1.addSeparator();
        JMenuItem m15 = new JMenuItem("Page Setup...");
        m1.add(m15);
        JMenuItem m16 = new JMenuItem("Print...");
        m16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        m1.add(m16);
        m1.addSeparator();
        JMenuItem m17 = new JMenuItem("Exit");
        m17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        m1.add(m17);

        JMenu m2 = new JMenu("Edit");
        jmb.add(m2);
        m2.setPreferredSize(new Dimension(70, 30));
        JMenuItem m21 = new JMenuItem("Undo");
        m21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        m2.add(m21);
        m2.addSeparator();
        JMenuItem m22 = new JMenuItem("Cut");
        m22.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        m2.add(m22);
        JMenuItem m23 = new JMenuItem("Copy");
        m23.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        m2.add(m23);
        JMenuItem m24 = new JMenuItem("Paste");
        m24.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        m2.add(m24);
        JMenuItem m25 = new JMenuItem("Delete");
        m25.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        m2.add(m25);
        m2.addSeparator();
        JMenuItem m26 = new JMenuItem("Find...");
        m26.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        m2.add(m26);
        JMenuItem m27 = new JMenuItem("Find Next");
        m27.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_DOWN_MASK));
        m2.add(m27);
        JMenuItem m28 = new JMenuItem("Replace...");
        m27.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        m2.add(m28);
        JMenuItem m29 = new JMenuItem("Go To...");
        m29.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        m2.add(m29);
        m2.addSeparator();
        JMenuItem m210 = new JMenuItem("Select All");
        m210.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        m2.add(m210);
        JMenuItem m211 = new JMenuItem("Time/Date");
        m2.add(m211);

        JMenu m3 = new JMenu("View");
        jmb.add(m3);
        m3.setPreferredSize(new Dimension(70, 30));
        JMenuItem m31 = new JMenuItem("Zoom In");
        m31.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK));
        m3.add(m31);
        JMenuItem m32 = new JMenuItem("Zoom Out");
        m32.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK));
        m3.add(m32);
        JMenuItem m33 = new JMenuItem("Reset Zoom");
        m33.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK));
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
        LocalDate currentDate = LocalDate.now();
        JLabel statusBar = new JLabel("READY "+" Font: "+fontSize+"  Date :"+currentDate);
        j.add(statusBar, BorderLayout.SOUTH);

        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(j);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        textArea.setText(content.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        m13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
                int option = fileChooser.showSaveDialog(j);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    // Append .txt extension if it's not already present
                    if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                    }
                    try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                        writer.println(textArea.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        m14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
                int option = fileChooser.showSaveDialog(j);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    // Append .txt extension if it's not already present
                    if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                    }
                    try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                        writer.println(textArea.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        m15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement "Page Setup..." functionality
                // You can use java.awt.print.PageFormat to set up the page format.
            }
        });

        m16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();
                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        m17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);


        m21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });

        m22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedText = textArea.getSelectedText();
                if (selectedText != null) {
                    StringSelection selection = new StringSelection(selectedText);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    textArea.replaceSelection("");
                }
            }
        });

        m23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedText = textArea.getSelectedText();
                if (selectedText != null) {
                    StringSelection selection = new StringSelection(selectedText);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                }
            }
        });

        m24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable clipData = clipboard.getContents(clipboard);
                try {
                    if (clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String s = (String) clipData.getTransferData(DataFlavor.stringFlavor);
                        textArea.insert(s, textArea.getCaretPosition());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        m25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start != end) {
                    textArea.replaceRange("", start, end);
                }
            }
        });

        m26.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter text to find:");
                if (input != null && !input.isEmpty()) {
                    String text = textArea.getText();
                    int index = text.indexOf(input);
                    if (index != -1) {
                        textArea.setSelectionStart(index);
                        textArea.setSelectionEnd(index + input.length());
                    } else {
                        JOptionPane.showMessageDialog(null, "Text not found.");
                    }
                }
            }
        });
        m27.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter text to find:");
                if (input != null && !input.isEmpty()) {
                    String text = textArea.getText();
                    int startIndex = textArea.getSelectionEnd();
                    int index = text.indexOf(input, startIndex);
                    if (index != -1) {
                        textArea.setSelectionStart(index);
                        textArea.setSelectionEnd(index + input.length());
                    } else {
                        JOptionPane.showMessageDialog(null, "Text not found.");
                    }
                }
            }
        });

        m28.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String findText = JOptionPane.showInputDialog(null, "Enter text to Replace:");
                if (findText != null && !findText.isEmpty()) {
                    String replaceText = JOptionPane.showInputDialog(null, "Enter text to replace with:");
                    if (replaceText != null) {
                        String text = textArea.getText();
                        text = text.replace(findText, replaceText);
                        textArea.setText(text);
                    }
                }
            }
        });

        m29.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter line number to go to:");
                if (input != null && !input.isEmpty()) {
                    try {
                        int lineNumber = Integer.parseInt(input);
                        int start = textArea.getLineStartOffset(lineNumber - 1);
                        int end = textArea.getLineEndOffset(lineNumber - 1);
                        textArea.setCaretPosition(start);
                        textArea.setSelectionStart(start);
                        textArea.setSelectionEnd(end);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid line number.");
                    }
                }
            }
        });
        m210.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
            }
        });

        m211.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(LocalDate.now().toString());
            }
        });
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
