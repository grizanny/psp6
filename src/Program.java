import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Program extends JFrame {
    // JTextField
    static JTextField jTextField;

    // JFrame
    static JFrame jFrame;

    // JButton
    static JButton OKButton, GOButton;

    // label to display Program
    static JLabel jLabel, enteredLabel, wrongData;

    static DefaultListModel lastNames = new DefaultListModel(), firstNames = new DefaultListModel(),
            middlenames = new DefaultListModel();

    // JList
    static JList lastNameList, firstNameList, middleNameList;

    // main class
    public static void main(String[] args) {

        // create a new frame to store Program field and button
        jFrame = new JFrame("Списки студентов");

        // create labelы to display Program
        jLabel = new JLabel("Введите ФИО: ");
        enteredLabel = new JLabel();
        wrongData = new JLabel();

        // create new buttons
        OKButton = new JButton("OK");
        GOButton = new JButton("Готово");

        // addActionListener to buttons
        OKButton.addActionListener(new TextActionListener());
        GOButton.addActionListener(new TextActionListener());

        // create an object of JTextField with 25 columns
        jTextField = new JTextField(25);

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();

        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        lastNameList = new JList(lastNames);
        lastNameList.setCellRenderer(new CheckboxListCellRenderer());
        firstNameList = new JList(firstNames);
        middleNameList = new JList(middlenames);

        // add buttons and textfields to panel
        p.add(jLabel);
        p.add(jTextField);
        p.add(OKButton, gbc);

        p.add(enteredLabel, gbc);

        p.add(wrongData, gbc);

        p.add(new JScrollPane(lastNameList));
        p.add(new JScrollPane(firstNameList));
        p.add(new JScrollPane(middleNameList), gbc);

        p.add(OKButton, gbc);

        // add panel to frame
        jFrame.add(p);

        // set the size of frame
        jFrame.setSize(700, 700);

        jFrame.setVisible(true);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static class TextActionListener implements ActionListener {
        static int count = 0;
        // if the OKButton is pressed
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("OK")) {
                String str = jTextField.getText();

                // set the Program of the label to the Program of the field
                enteredLabel.setText("Вы ввели ФИО: " + str);

                if(count > 0)
                    str = str.substring(1);

                String[] values = str.split(" ");

                boolean isInteger;
                int countNotStrings = 0;
                for (int i = 0; i < values.length; i++) {
                    isInteger = isInteger(values[i]);
                    if (isInteger)
                        countNotStrings++;
                }

                if (countNotStrings > 0)
                    wrongData.setText("Некорректный ввод! Введите 3 !!!СТРОКИ!!!");
                else if (values.length != 3)
                    wrongData.setText("Некорректный ввод! Введите !!!3!!! строки (фамилия, имя, отчество студента)");
                else {
                    lastNames.add(lastNames.size(), values[0]);
                    firstNames.add(firstNames.size(), values[1]);
                    middlenames.add(middlenames.size(), values[2]);

                    // set the Program of field to blank
                    count++;
                }

                jTextField.setText(" ");
            }
        }
    }

    public static class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            setComponentOrientation(list.getComponentOrientation());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setSelected(isSelected);
            setEnabled(list.isEnabled());

            setText(value == null ? "" : value.toString());

            return this;
        }
    }
}

