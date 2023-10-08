package com.host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;

class App {
    /*
     *   Назначение JFrame и JPanel
     */
    static JFrame jframe = Okno();
    static JPanel jpanel = new JPanel();
    
    public static void main(String[] args) {
        JComponent jComponent = new mouse();
        AbstractAction abstractAction = new Proba();
        AbstractAction abstractAction2 = new colorBlue();
        /* 
        * ПОЧЕМУТА НЕ РАБОТАЕТ ВМЕСТЕ!!
        * ещё нужно будет фиксить
        */
        jframe.add(jpanel);
        // jframe.add(jComponent);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu("Base");
        jMenu1.add(new JMenuItem("Eshe ne pridumal"));
        jMenu1.addSeparator();
        JMenuItem exit = new JMenuItem("Exit", 'E');
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        jMenu1.add(exit);
        menuBar.add(jMenu1);

        JMenu jMenu2 = new JMenu("Color");
        JMenuItem red = new JMenuItem("Red");
        red.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                jpanel.setBackground(Color.RED);
            }
            
        });
        JMenuItem yellow = new JMenuItem("Yellow");
        yellow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jpanel.setBackground(Color.YELLOW);
            }

        });
        JMenuItem randomColor = new JMenuItem("Random color", 'R');
        randomColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jpanel.setBackground(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
            }
            
        });
        randomColor.setAccelerator(KeyStroke.getKeyStroke("ctrl alt R"));
        jMenu2.add(red);
        jMenu2.add(yellow);
        jMenu2.addSeparator();
        jMenu2.add(randomColor);
        menuBar.add(jMenu2);
        jframe.setJMenuBar(menuBar);

        // Добавление кнопки которая меняет название окна на "Klik Klak"
        JButton jbutton = new JButton("Klik Klak");
        jbutton.addActionListener(EventHandler.create(ActionListener.class, jframe, "title", "source.text"));
        jpanel.add(jbutton);
        
        // Добавление кнопки которая меняет цвет на GRAY
        JButton jbutton1 = new JButton(abstractAction);
        jbutton1.setText("color");
        jpanel.add(jbutton1);
        
        // Добавление кнопки которая меняет цвет на WHITE
        JButton jbutton2 = new JButton("colorR");
        jbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jpanel.setBackground(Color.WHITE);
            }
        });
        jpanel.add(jbutton2);

        // Создание флажка 
        jpanel.add(new JCheckBox("Квадратик с галочкой)"));
        jpanel.add(new JCheckBox("Ещё один)"));

        // Создания и присвоение радио кнопок 
        JRadioButton jRadioButton = new JRadioButton("тут?", true);
        JRadioButton jRadioButton2 = new JRadioButton("тут?");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton);
        buttonGroup.add(jRadioButton2);
        jpanel.add(jRadioButton);
        jpanel.add(new JLabel("или")); // Вывод текста "или"
        jpanel.add(jRadioButton2);

        // Создание кнопки с выбором
        JComboBox<String> jCombokBox = new JComboBox<String>();
        jCombokBox.addItem("1");
        jCombokBox.addItem("2");
        jCombokBox.addItem("3");
        jpanel.add(jCombokBox);


        // Добавление панели ввода текста логин и пароль 
        jpanel.add(new JLabel("Логин")); 
        jpanel.add(new JTextField(10));
        jpanel.add(new JLabel("Пароль"));
        jpanel.add(new JPasswordField(10));

        // Создание листа с множеством строк 
        jpanel.add(new JLabel("Множество строк"));
        JTextArea jTextArea = new JTextArea(5, 10);
        jTextArea.setLineWrap(true); // Добавляет скрол бар 
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jpanel.add(jScrollPane);

        // Назначение координат для курсора 
        jframe.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                mouse.xCord = e.getX();
                mouse.yCord = e.getY();
                jComponent.repaint();
            } 
        });

        // Сочитание клавиш Ctrl+A меняет цвет на BLUE
        KeyStroke keyStroke = KeyStroke.getKeyStroke("ctrl A");
        InputMap inputMap = jpanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(keyStroke, "soso");
        ActionMap actionMap = jpanel.getActionMap();
        actionMap.put("soso", abstractAction2);
        
        jpanel.revalidate();
    }
    
    private static void jMenu1add(JMenuItem exit) {
    }

    // Класс для определения координат mouse
    public static class mouse extends JComponent {
        private static int xCord;
        private static int yCord;
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D)g).drawString("X: " + xCord + "Y: " + yCord, 450, 580);
        }
    }

    // Класс для смены цвета на BLUE
    public static class colorBlue extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            jpanel.setBackground(Color.BLUE);
        }
    }

    // Класс для смены ццвета на GRAY
    public static class Proba extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            jpanel.setBackground(Color.GRAY);
        }
    }

    // Метод создание окна
    public static JFrame Okno() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        frame.setBounds(dim.width / 2 - 350, dim.height / 2 -300, 700, 600);
        frame.setVisible(true);
        return frame;
    }

}
