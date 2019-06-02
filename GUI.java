import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.*;
import java.awt.event.*;
import javax.swing.table.*;


public class GUI extends JFrame implements ActionListener {

    JMenuBar menuBar;
    String actualDay;
    JLabel actualMonth= new JLabel();
    JLabel eventLook = new JLabel(actualDay);
    JFrame frame = new JFrame();
    JMenuItem AddEvent, AboutProgram;
    JMenu Calendar, Events, Info;
    DefaultTableModel model;
    JLabel label = new JLabel();
    Calendar cal = new GregorianCalendar();
    String month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);

    GUI() {

        frame.setLayout(null);//using no layout managers
        frame.setSize(1200, 600);
        frame.setVisible(true);//making the frame visible

        JButton nextMonth = new JButton(">>>");
        nextMonth.setBounds(700, 50, 100, 40);

        JButton BackMonth = new JButton("<<<");
        BackMonth.setBounds(100, 50, 100, 40);


        actualMonth.setFont(new Font("Serif", Font.PLAIN, 28));
        actualMonth.setBounds(380, -30, 200, 200);


        AddEvent = new JMenuItem("Add Event");
        AddEvent.addActionListener(this);

        AboutProgram = new JMenuItem("About Program");
        AboutProgram.addActionListener(this);

        menuBar = new JMenuBar();

        Calendar = new JMenu("Calendar");
        Events = new JMenu("Events");
        Info = new JMenu("Info");

        Events.add(AddEvent);
        Info.add(AboutProgram);
        menuBar.add(Calendar);
        menuBar.add(Events);
        menuBar.add(Info);


        String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(100,100,700,400);

        table.setCellSelectionEnabled(true);

        ListSelectionModel select= table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        eventLook.setBounds(900,0,200,100);
        eventLook.setFont(new Font("Serif", Font.PLAIN, 28));


        // buttons action listener
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cal.add(java.util.Calendar.MONTH, +1);
                updateMonth();

            }
        });

        BackMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cal.add(java.util.Calendar.MONTH, -1);
                updateMonth();

            }
        });

        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               int Data =0;
                int[] row =table.getSelectedRows();
                int[] columns = table.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (int)table.getValueAt(row[i], columns[j]);
                    } }
                actualDay = String.valueOf(Data)+" "+cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US)+" "+ (int)cal.get(java.util.Calendar.YEAR); ;
                eventLook.setText(actualDay);
            }
        });

        frame.add(pane);
        frame.add(eventLook);
        frame.add(actualMonth);
        frame.add(nextMonth);
        frame.add(BackMonth);
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.revalidate();
        frame.repaint();
        this.updateMonth();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddEvent)
            System.out.println("AddEvent");

        if (e.getSource() == AboutProgram) {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "It is program which has written " +
                    "by Adam Krzanowski and Jakub Guzek.");
        }


    }

    public void updateMonth() {
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
        int year = cal.get(java.util.Calendar.YEAR);
        actualMonth.setText(month+" "+year);

        int startDay = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(java.util.Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }
    }
}

