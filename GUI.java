import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * param
 *
 */
public class GUI extends JFrame implements ActionListener {


    JMenuBar menuBar;
    String actualDay;
    JLabel actualMonth= new JLabel();
    JLabel infoAboutEvent;
    JLabel eventLook = new JLabel(actualDay);
    JFrame frame = new JFrame();
    JMenuItem AddEvent, AboutProgram,RemoveEvent,Theme,SaveToXMLfile,LoadFromXmlFile,LoadToBase,ExportToCsv,RemoveEV,ModifyEv,FilterEvent;
    JMenu Calendar, Events, Info,Settings;
    DefaultTableModel model;
    JLabel label = new JLabel();
    Calendar cal = new GregorianCalendar();
    String month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
    String des = "";
    EventManager eManager = new EventManager();
    int Data;

    GUI() {
    	
    	//eManager.loadEventsFromSQL();
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

        RemoveEV = new JMenuItem("Remove Event");
        RemoveEV.addActionListener(this);

        ModifyEv = new JMenuItem("Modify Event");
        ModifyEv.addActionListener(this);

        FilterEvent = new JMenuItem("Filter Event by part of name");
        FilterEvent.addActionListener(this);

        AboutProgram = new JMenuItem("About Program");
        AboutProgram.addActionListener(this);

        Theme = new JMenuItem("Theme");
        Theme.addActionListener(this);

        SaveToXMLfile = new JMenuItem("Save to XML file");
        SaveToXMLfile.addActionListener(this);

        LoadFromXmlFile = new JMenuItem("Load from XML file");
        LoadFromXmlFile.addActionListener(this);

        LoadToBase = new JMenuItem("Load From SQL Base");
        LoadToBase.addActionListener(this);

        ExportToCsv = new JMenuItem("Export To CSV");
        ExportToCsv.addActionListener(this);

        menuBar = new JMenuBar();

        Calendar = new JMenu("Calendar");
        Events = new JMenu("Events");
        Info = new JMenu("Info");
        Settings = new JMenu("Settings");


        Events.add(AddEvent);
        Events.add(RemoveEvent);
        Events.add(RemoveEV);
        Events.add(ModifyEv);
        Events.add(FilterEvent);
        Info.add(AboutProgram);
        Settings.add(Theme);
        menuBar.add(Calendar);
        menuBar.add(Events);
        menuBar.add(Info);
        menuBar.add(Settings);
        Calendar.add(SaveToXMLfile);
        Calendar.add(LoadFromXmlFile);
        Calendar.add(LoadToBase);
        Calendar.add(ExportToCsv);


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

        infoAboutEvent = new JLabel();
        infoAboutEvent.setBounds(900,30,250,190);
        infoAboutEvent.setFont(new Font("Serif", Font.PLAIN, 28));
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
                        final JTable jTable = (JTable) e.getSource();
                        final int row = jTable.getSelectedRow();
                        final int column = jTable.getSelectedColumn();
                        final int valueInCell = (int) jTable.getValueAt(row, column);
                        String month = cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG, Locale.US);
                        int intMonth = cal.get(java.util.Calendar.MONTH) + 1;
                        int year = cal.get(java.util.Calendar.YEAR);
                        actualDay = String.valueOf(valueInCell) + " " + month + " " + year;
                        eventLook.setText(actualDay);
                        Vector<Event> ev = new Vector<Event>();
                        ev = eManager.getEventsOnDate(valueInCell, intMonth, year);
                        System.out.println(ev.size());
                        des = "";
                        if (ev.size() != 0) {
                            for (int i = 0; i < ev.size(); i++)
                                des += i + 1 + " " + ev.get(i).getName() + " " + " -- " + ev.get(i).getDescription();
                        } else
                            des = "No events";
                        Data = 0;
                        infoAboutEvent.setText(des);
                    }
                    catch(NullPointerException bla)
                    {System.out.println("Error");}
                }
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

            JLabel name = new JLabel("Name: ");
            JLabel WriteDay = new JLabel("Day: ");
            JLabel WriteMonth = new JLabel("Month: ");
            JLabel WriteYear = new JLabel("Year: ");
            JLabel WriteDescription = new JLabel("Description: ");

            name.setBounds(20,5,50,50);
            WriteDay.setBounds(20,30,50,50);
            WriteMonth.setBounds(20,60,50,50);
            WriteYear.setBounds(20,90,50,50);
            WriteDescription.setBounds(20,120,80,50);

            JTextArea Name = new JTextArea();
            JTextArea day = new JTextArea();
            JTextArea month = new JTextArea();
            JTextArea year = new JTextArea();
            JTextArea description = new JTextArea();

            Name.setBounds(110,18,300,20);
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
            add.add(Name);
            add.add(name);
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
                    try {
                        String n = Name.getText();
                        String d = day.getText();
                        String m = month.getText();
                        String y = year.getText();
                        String de = description.getText();
                        eManager.addEvent(Integer.parseInt(d), Integer.parseInt(m), Integer.parseInt(y), de, n);

                    }catch(NumberFormatException e)
                    {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Data must be in format:\nName:      text\n" +
                                "Day:         number\n" +
                                "Month:       number\n" +
                                "Year:        number\n" +
                                "Description: text");
                    }
                }
            });

        };


        //Usuwanie wydarzenia
        if (e.getSource() == RemoveEV) {
            JFrame add = new JFrame();
            add.setBounds(100,200,500,300);

            JLabel name = new JLabel("Name: ");
            JLabel WriteDay = new JLabel("Day: ");
            JLabel WriteMonth = new JLabel("Month: ");
            JLabel WriteYear = new JLabel("Year: ");

            name.setBounds(20,5,50,50);
            WriteDay.setBounds(20,30,50,50);
            WriteMonth.setBounds(20,60,50,50);
            WriteYear.setBounds(20,90,50,50);

            JTextArea Name = new JTextArea();
            JTextArea day = new JTextArea();
            JTextArea month = new JTextArea();
            JTextArea year = new JTextArea();

            Name.setBounds(110,18,300,20);
            day.setBounds(110,48,300,20);
            month.setBounds(110,78,300,20);
            year.setBounds(110,108,300,20);

            JButton addEvent = new JButton("Remove event");
            addEvent.setBounds(260,220,130,30);
            addEvent.addActionListener(this);

            add.setTitle("Remove event");
            add.setVisible(true);
            add.setLayout(null);
            add.add(Name);
            add.add(name);
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
                    try {
                        String n = Name.getText();
                        String d = day.getText();
                        String m = month.getText();
                        String y = year.getText();
                        eManager.deleteEve(n,Integer.parseInt(d),Integer.parseInt(m),Integer.parseInt(y));

                    }catch(NumberFormatException e)
                    {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Data must be in format:\nName:      text\n" +
                                "Day:         number\n" +
                                "Month:       number\n" +
                                "Year:        number\n" +
                                "Description: text");
                    }
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
                    try {
                        String d = day.getText();
                        String m = month.getText();
                        String y = year.getText();
                        eManager.removeTooOldEvents(Integer.parseInt(d), Integer.parseInt(m), Integer.parseInt(y));
                        System.out.println("BO");
                    }
                    catch(NumberFormatException e)
                    {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Data must be in format:\n" +
                                "Day:         number\n" +
                                "Month:       number\n" +
                                "Year:        number\n");
                    }
                }
            });

        };

        //modyfikowanie wydarzenia

        if (e.getSource() == ModifyEv ) {

            JFrame add = new JFrame();
            add.setBounds(100,200,500,230);

            JLabel name = new JLabel("Name: ");
            JLabel WriteDay = new JLabel("Day: ");
            JLabel WriteMonth = new JLabel("Month: ");
            JLabel WriteYear = new JLabel("Year: ");

            name.setBounds(20,5,50,50);
            WriteDay.setBounds(20,30,50,50);
            WriteMonth.setBounds(20,60,50,50);
            WriteYear.setBounds(20,90,50,50);

            JTextArea Name = new JTextArea();
            JTextArea day = new JTextArea();
            JTextArea month = new JTextArea();
            JTextArea year = new JTextArea();

            Name.setBounds(110,18,300,20);
            day.setBounds(110,48,300,20);
            month.setBounds(110,78,300,20);
            year.setBounds(110,108,300,20);

            JButton addEvent = new JButton("Get event");
            addEvent.setBounds(260,150,130,30);
            addEvent.addActionListener(this);

            add.setTitle("Modify event");
            add.setVisible(true);
            add.setLayout(null);
            add.add(Name);
            add.add(name);
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
                    try {
                        String n = Name.getText();
                        String d = day.getText();
                        String m = month.getText();
                        String y = year.getText();
                        //Modyfikacja wydarzenia
                        System.out.println(n + " " + d + " " + m + " " + y);
                        Event e = eManager.getEvent(n,Integer.parseInt(d),Integer.parseInt(m),Integer.parseInt(y));
                        eManager.showEvents();

                        JFrame add = new JFrame();
                        add.setBounds(100,200,500,300);

                        JLabel name = new JLabel("Name: ");
                        JLabel WriteDay = new JLabel("Day: ");
                        JLabel WriteMonth = new JLabel("Month: ");
                        JLabel WriteYear = new JLabel("Year: ");
                        JLabel WriteDescription = new JLabel("Description: ");

                        name.setBounds(20,5,50,50);
                        WriteDay.setBounds(20,30,50,50);
                        WriteMonth.setBounds(20,60,50,50);
                        WriteYear.setBounds(20,90,50,50);
                        WriteDescription.setBounds(20,120,80,50);

                        JTextArea Name = new JTextArea();
                        JTextArea day = new JTextArea();
                        JTextArea month = new JTextArea();
                        JTextArea year = new JTextArea();
                        JTextArea description = new JTextArea();

                        Name.setText(e.getName());
                        day.setText(String.valueOf(e.getDayNumber()));
                        month.setText(String.valueOf(e.getMonthNumber()));
                        year.setText(String.valueOf(e.getYearNumber()));
                        description.setText(d);

                        Name.setBounds(110,18,300,20);
                        day.setBounds(110,48,300,20);
                        month.setBounds(110,78,300,20);
                        year.setBounds(110,108,300,20);
                        description.setBounds(110,138,300,60);

                        eManager.deleteEve(e.getName(),e.getDayNumber(),e.getMonthNumber(),e.getYearNumber());

                        JButton addEvent = new JButton("Modify event");
                        addEvent.setBounds(300,220,100,30);
                        addEvent.addActionListener(this);

                        add.setTitle("Modify Event");
                        add.setVisible(true);
                        add.setLayout(null);
                        add.add(Name);
                        add.add(name);
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
                                try {
                                    String n = Name.getText();
                                    String d = day.getText();
                                    String m = month.getText();
                                    String y = year.getText();
                                    String de = description.getText();
                                    eManager.addEvent(Integer.parseInt(d), Integer.parseInt(m), Integer.parseInt(y), de, n);

                                }catch(NumberFormatException e)
                                {
                                    JFrame f = new JFrame();
                                    JOptionPane.showMessageDialog(f, "Data must be in format:\nName:      text\n" +
                                            "Day:         number\n" +
                                            "Month:       number\n" +
                                            "Year:        number\n" +
                                            "Description: text");
                                }
                            }
                        });

                    }catch(NumberFormatException e)
                    {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Data must be in format:\nName:      text\n" +
                                "Day:         number\n" +
                                "Month:       number\n" +
                                "Year:        number\n" +
                                "Description: text");
                    }
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

        //Zapis do pliku XML
        if (e.getSource() == SaveToXMLfile) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(null);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileChooser.getSelectedFile()+".xml";
                    eManager.writeToXMLFile(path);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

        if (e.getSource() == FilterEvent) {

            JFrame add = new JFrame();
            add.setBounds(100,200,500,150);

            JLabel mainTitle = new JLabel("Filtred events");
            mainTitle.setFont(new Font("Serif",Font.BOLD,21));
            mainTitle.setBounds(10,10,300,20);
            JLabel Part = new JLabel("Part of name: ");

            Part.setBounds(20,30,80,50);

            JTextArea part = new JTextArea();;

            part.setBounds(110,48,300,20);


            JButton addEvent = new JButton("Filtr");
            addEvent.setBounds(300,80,100,30);
            addEvent.addActionListener(this);

            add.setTitle("Filtr Event");
            add.setVisible(true);
            add.setLayout(null);
            add.add(mainTitle);
            add.add(Part);
            add.add(part);
            add.add(addEvent);


            addEvent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        String d = part.getText();
                        Vector<Event> e = new Vector<Event>();
                        e = eManager.getFilterEventsWithString(d);
                        String des = "";
                        for(int i =0; i<e.size(); i++)
                        {
                            des += e.get(i).getDayNumber()+"--"+e.get(i).getMonthNumber()+"--"+e.get(i).getYearNumber()+"  "+e.get(i).getDescription()+"\n";
                        }
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f,des);

                    }
                    catch(NumberFormatException e)
                    {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Data must be in format:\n" +
                                "Day:         number\n" +
                                "Month:       number\n" +
                                "Year:        number\n");
                    }
                }
            });


        }

        // Wczytywanie z pliku XML
        if (e.getSource() == LoadFromXmlFile) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(null);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileChooser.getSelectedFile()+"";
                    System.out.println(path);
                    eManager.loadFromXMLFile(path);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }


        //WCZYTYWANIE I ZAPISYWANIE DO BAZY DANYCH

        if (e.getSource() == LoadToBase) {
            eManager.loadEventsFromSQL();
        }

        if (e.getSource() == ExportToCsv) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(null);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileChooser.getSelectedFile()+".csv";
                    eManager.exportToCSV(path);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
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

