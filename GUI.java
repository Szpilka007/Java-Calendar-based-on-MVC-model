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

/**
 * param
 *
 */
public class GUI extends JFrame implements ActionListener {


    JMenuBar menuBar;
    String actualDay;
    JLabel actualMonth= new JLabel();
    JTextArea infoAboutEvent;
    JLabel eventLook = new JLabel(actualDay);
    JFrame frame = new JFrame();
    JMenuItem AddEvent, AboutProgram,RemoveEvent,Theme;
    JMenu Calendar, Events, Info,Settings;
    DefaultTableModel model;
    JLabel label = new JLabel();
    Calendar cal = new GregorianCalendar();
    String month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
    String des = "";
    EventManager eManager = new EventManager();

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


        //USTAWIENIA PASKA NA GORE

        AddEvent = new JMenuItem("Add Event");
        AddEvent.addActionListener(this);

        RemoveEvent = new JMenuItem("Remove Event before date");
        RemoveEvent.addActionListener(this);

        AboutProgram = new JMenuItem("About Program");
        AboutProgram.addActionListener(this);

        Theme = new JMenuItem("Theme");
        Theme.addActionListener(this);

        menuBar = new JMenuBar();

        Calendar = new JMenu("Calendar");
        Events = new JMenu("Events");
        Info = new JMenu("Info");
        Settings = new JMenu("Settings");


        Events.add(AddEvent);
        Events.add(RemoveEvent);
        Info.add(AboutProgram);
        Settings.add(Theme);
        menuBar.add(Calendar);
        menuBar.add(Events);
        menuBar.add(Info);
        menuBar.add(Settings);


        //TABELA
        String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model) {
        	 public boolean isCellEditable(int rowIndex, int mColIndex) {
        	        return false;
        	      }
        };

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(100,100,700,373);
        table.setRowHeight(58);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<7; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        table.setCellSelectionEnabled(true);

        ListSelectionModel select= table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        eventLook.setBounds(900,0,300,100);
        eventLook.setFont(new Font("Serif", Font.PLAIN, 28));

        //Informacje o evencie tzw szczegoly;

        infoAboutEvent = new JTextArea();
        infoAboutEvent.setBounds(900,100,250,190);
        infoAboutEvent.setText(des);



        // button nastepny miesiac
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cal.add(java.util.Calendar.MONTH, +1);
                updateMonth();

            }
        });

        //button porpzedni miesiac
        BackMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cal.add(java.util.Calendar.MONTH, -1);
                updateMonth();

            }
        });


        //wyswietlanie zdarzen pod data obok kalendarza
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               int Data = 1 ;
                int[] row =table.getSelectedRows();
                int[] columns = table.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (int)table.getValueAt(row[i], columns[j]);
                    } }
                String month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
                int intMonth = cal.get(java.util.Calendar.MONTH)+1;
                int year = cal.get(java.util.Calendar.YEAR);
                actualDay = String.valueOf(Data)+" "+month+" "+ year ;
                eventLook.setText(actualDay);
                Vector<Event> ev = new Vector<Event>();
                ev = eManager.getEventsOnDate(Data,intMonth,year);
                System.out.println(ev.size());
                des = "";
                String newLine = System.getProperty("line.separator");
                if(ev.size() != 0) {
                    for (int i = 0; i < ev.size(); i++)
                        des += i+1+". "+ev.get(i).getDescription();
                }
                else
                    des = "No events";

                infoAboutEvent.setText(des);
            }
        });

        frame.add(pane);
        frame.add(infoAboutEvent);
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
       //TWORZENIE WYDARZENIA
        if (e.getSource() == AddEvent) {
            JFrame add = new JFrame();
            add.setBounds(100,200,500,300);

            JLabel mainTitle = new JLabel("Add new event");
            mainTitle.setFont(new Font("Serif",Font.BOLD,21));
            mainTitle.setBounds(10,10,300,20);
            JLabel WriteDay = new JLabel("Day: ");
            JLabel WriteMonth = new JLabel("Month: ");
            JLabel WriteYear = new JLabel("Year: ");
            JLabel WriteDescription = new JLabel("Description: ");

            WriteDay.setBounds(20,30,50,50);
            WriteMonth.setBounds(20,60,50,50);
            WriteYear.setBounds(20,90,50,50);
            WriteDescription.setBounds(20,120,80,50);

            JTextArea day = new JTextArea();
            JTextArea month = new JTextArea();
            JTextArea year = new JTextArea();
            JTextArea description = new JTextArea();

            day.setBounds(110,48,300,20);
            month.setBounds(110,78,300,20);
            year.setBounds(110,108,300,20);
            description.setBounds(110,138,300,60);


            JButton addEvent = new JButton("Add event");
            addEvent.setBounds(300,220,100,30);
            addEvent.addActionListener(this);

            add.setTitle("Add new event");
            add.setVisible(true);
            add.setLayout(null);
            add.add(mainTitle);
            add.add(WriteDay);
            add.add(WriteMonth);
            add.add(WriteYear);
            add.add(WriteDescription);
            add.add(day);
            add.add(month);
            add.add(year);
            add.add(description);
            add.add(addEvent);


            addEvent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    String d = day.getText();
                    String m = month.getText();
                    String y = year.getText();
                    String de = description.getText();
                    eManager.addEvent(Integer.parseInt(d),Integer.parseInt(m),Integer.parseInt(y),de);

                }
            });

        };


        //informacje o projekcie
        if (e.getSource() == AboutProgram) {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "It is program which has written " +
                    "by Adam Krzanowski and Jakub Guzek.");
        }


        //usuwanie wszystkich wydarzen przed data
        if (e.getSource() == RemoveEvent) {

            JFrame add = new JFrame();
            add.setBounds(100,200,500,300);

            JLabel mainTitle = new JLabel("Remove events before date:");
            mainTitle.setFont(new Font("Serif",Font.BOLD,21));
            mainTitle.setBounds(10,10,300,20);
            JLabel WriteDay = new JLabel("Day: ");
            JLabel WriteMonth = new JLabel("Month: ");
            JLabel WriteYear = new JLabel("Year: ");

            WriteDay.setBounds(20,30,50,50);
            WriteMonth.setBounds(20,60,50,50);
            WriteYear.setBounds(20,90,50,50);

            JTextArea day = new JTextArea();
            JTextArea month = new JTextArea();
            JTextArea year = new JTextArea();

            day.setBounds(110,48,300,20);
            month.setBounds(110,78,300,20);
            year.setBounds(110,108,300,20);


            JButton addEvent = new JButton("Remove");
            addEvent.setBounds(300,220,100,30);
            addEvent.addActionListener(this);

            add.setTitle("Add new event");
            add.setVisible(true);
            add.setLayout(null);
            add.add(mainTitle);
            add.add(WriteDay);
            add.add(WriteMonth);
            add.add(WriteYear);
            add.add(day);
            add.add(month);
            add.add(year);
            add.add(addEvent);


            addEvent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    String d = day.getText();
                    String m = month.getText();
                    String y = year.getText();
                    eManager.removeTooOldEvents(Integer.parseInt(d),Integer.parseInt(m),Integer.parseInt(y));
                }
            });

        };


        //wyswietlanie ustawien

        //zmiana motywu
        if(e.getSource()==Theme){
            JRadioButton rb1,rb2,rb3;
            JButton b;
            JFrame add = new JFrame();
            rb1=new JRadioButton("Default");
            rb1.setBounds(100,50,100,30);
            rb2=new JRadioButton("Old");
            rb2.setBounds(100,100,100,30);
            rb3=new JRadioButton("Additional");
            rb3.setBounds(100,150,100,30);
            ButtonGroup bg=new ButtonGroup();
            bg.add(rb1);
            bg.add(rb2);
            bg.add(rb3);
            b=new JButton("Select");
            b.setBounds(100,200,80,30);
            b.addActionListener(this);
            add(rb1);
            add(rb2);
            add(rb3);
            add(b);
            setSize(300,300);
            setLayout(null);
            setVisible(true);

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if(rb1.isSelected()){
                        try {
                            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (InstantiationException ex) {
                            ex.printStackTrace();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(rb2.isSelected()){
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (InstantiationException ex) {
                            ex.printStackTrace();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(rb3.isSelected()){
                        try {
                            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (InstantiationException ex) {
                            ex.printStackTrace();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

    }


    // metoda pozwalajaca poprawnie wyswieltac kalendarz
    public void updateMonth() {
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
        int year = cal.get(java.util.Calendar.YEAR);
        actualMonth.setText(month+" "+year);

        int startDay = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(java.util.Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(6);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }
    }
}

