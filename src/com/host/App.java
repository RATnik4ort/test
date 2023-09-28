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
        jframe.add(jpanel);
        jframe.add(jComponent);
        
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


        jframe.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                mouse.xCord = e.getX();
                mouse.yCord = e.getY();
                jComponent.repaint();
            } 
        });

        // Сочитание клавиш Ctrl+A меняет цвет на GRAY
        KeyStroke keyStroke = KeyStroke.getKeyStroke("ctrl A");
        InputMap inputMap = jpanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(keyStroke, "soso");
        ActionMap actionMap = jpanel.getActionMap();
        actionMap.put("soso", abstractAction);
        
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
        frame.setBounds(dim.width / 2 - 300, dim.height / 2 -300, 600, 600);
        frame.setVisible(true);
        return frame;
    }

}
