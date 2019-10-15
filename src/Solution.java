import javax.swing.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution extends JFrame implements ListSelectionListener {
    static JFrame jFrame;

    //field
    static JTextField jTextField;

    //lists
    static JList lastName, firstName, middleName;

    //label
    static JLabel jLabel;

    //main class
    public static void main(String[] args) throws IOException {
        //create a new frame
        jFrame = new JFrame("frame");

        //create a object
        Solution s = new Solution();

        //create a panel
        JPanel p = new JPanel();

        //create a new label
        JLabel l = new JLabel("Введите ФИО");
        jLabel = new JLabel();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String string = bufferedReader.readLine();

        String[] parts = string.split(" ");
        String surname = parts[0];
        String name = parts[1];
        String patronymic = parts[2];

        //String array to store weekdays
        String month[]= { "January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};

        //create a array for months and year
        String date[]=new String[31],year[]=new String[31];

        //add month number and year to list
        for(int i=0;i<31;i++)
        {
            date[i]=""+(int)(i+1);
            year[i]=""+(int)(2018-i);
        }

        //create lists
        lastName = new JList(date);
        firstName = new JList(month);
        middleName = new JList(year);

        //set a selected index
        lastName.setSelectedIndex(2);
        firstName.setSelectedIndex(1);
        middleName.setSelectedIndex(2);

        jLabel.setText(lastName.getSelectedValue()+ " "+ firstName.getSelectedValue()
                +" "+ middleName.getSelectedValue());

        //add item listener
        lastName.addListSelectionListener(s);
        firstName.addListSelectionListener(s);
        middleName.addListSelectionListener(s);

        //add list to panel
        p.add(l);
        p.add(lastName);
        p.add(firstName);
        p.add(middleName);
        p.add(jLabel);

        jFrame.add(p);

        //set the size of frame
        jFrame.setSize(500,600);

        jFrame.setVisible(true);
    }
    public void valueChanged(ListSelectionEvent e)
    {
        //set the Program of the label to the selected value of lists
        jLabel.setText(lastName.getSelectedValue()+" "+ firstName.getSelectedValue()
                +" "+ middleName.getSelectedValue());

    }
}
