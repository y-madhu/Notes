import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.*;

public class Notes {
    private JPanel Notes;
    private JTextArea textArea;
    private JMenuBar Menubar;
    private JMenu filemenu;
    private JMenu editmenu;
    private JMenu helpmenu;
    private JMenu viewmenu;
    private JMenuItem New;
    private JMenuItem Open;
    private JMenuItem Save;
    private JMenuItem Saveas;
    private JMenuItem PageSetup;
    private JMenuItem Print;
    private JMenuItem Exit;
    private JMenuItem Cut;
    private JMenuItem Copy;
    private JMenuItem Paste;
    private JMenuItem SelectAll;
    private JMenuItem Zoom;
    private JMenuItem About;
    String zoom;

    public Notes() {
        textArea.setLineWrap(true);


        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));

        SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));


        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to save the file", "save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    saving();
                } else {
                    textArea.setText(null);
                }
                textArea.setText(" ");


            }
        });
        Open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filechooser = new JFileChooser();
                FileNameExtensionFilter textFilter = new FileNameExtensionFilter("only text files (.txt)", "txt");
                filechooser.setAcceptAllFileFilterUsed(false);
                filechooser.addChoosableFileFilter(textFilter);
                int action = filechooser.showSaveDialog(null);
                if (action != JFileChooser.APPROVE_OPTION) {
                    return;
                } else {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                        textArea.read(reader, null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            }
        });
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saving();
            }
        });
        Saveas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saving();
            }
        });
        PageSetup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                PageFormat pf = pj.pageDialog(pj.defaultPage());
            }
        });
        Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        Cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();

            }
        });
        Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();

            }
        });
        Paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });


        SelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();

            }
        });

        Zoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                zoom = Zoom.getText();
                if (zoom.contains("Zoom in")) {
                    Font font = textArea.getFont();
                    float fontSize = font.getSize() + 10.0f;
                    textArea.setFont(font.deriveFont(fontSize));
                    Zoom.setText("Zoom out");
                }
                if (zoom.contains("Zoom out")) {
                    Font font = textArea.getFont();
                    float fontSize = font.getSize() - 10.0f;
                    textArea.setFont(font.deriveFont(fontSize));
                    Zoom.setText("Zoom in");
                }
            }
        });

        About.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "<html><head><h1><b><i>NOTES</i></b></h1></head><body><b>This Notes is like Notepad</b> <br/><b>This is only for creating new document<br/>editing the existed document <br/>saving the created document  </b></body></html>");

            }
        });
    }

    public void saving() {
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("only text files (.txt)", "txt");
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(textFilter);
        int action = filechooser.showSaveDialog(null);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        } else {
            String fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
            if (!fileName.contains(".txt")) {
                fileName = fileName + ".txt";
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(writer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Notes");
        frame.setContentPane(new Notes().Notes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
