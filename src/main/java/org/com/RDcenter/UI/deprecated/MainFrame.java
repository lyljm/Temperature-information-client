package org.com.RDcenter.UI.deprecated;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public static JTextArea jTextArea = new JTextArea();
    public static MainFrame mainFrame = new MainFrame("测温枪采集程序MQTT版");

    JMenu exitMenu;

    Font font = new Font("宋体", Font.BOLD, 24);

    public MainFrame(String title) throws HeadlessException {
        super(title);
//        setBounds();
        this.setSize(700, 700);
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu serviceMenu = new JMenu("服务管理");
        JMenu editMenu = new JMenu("配置管理");
        JMenu deviceMenu = new JMenu("设备管理");
        exitMenu = new JMenu("退出");
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("exit");
                int status = JOptionPane.showConfirmDialog(mainFrame, "是否退出程序?", "exit", JOptionPane.YES_NO_OPTION);
               if(status==0){
                   System.exit(0);
               }
            }
        });

        JMenuItem sqlItem = new JMenuItem("数据库连接");
        JMenuItem mqttItem = new JMenuItem("MQTT设置");
        mqttItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MQTTDialog instance = new MQTTDialog(mainFrame,true);
                instance.init();
                instance.setVisible(true);
            }
        });


        editMenu.add(sqlItem);
        editMenu.add(mqttItem);

        menuBar.add(serviceMenu);
        menuBar.add(editMenu);
        menuBar.add(deviceMenu);
        menuBar.add(exitMenu);

        return menuBar;
    }

    public static void init() {
        System.out.println("当前线程是否是 事件调度线程: " + SwingUtilities.isEventDispatchThread());

        Container contentPane = mainFrame.getContentPane();
        mainFrame.setJMenuBar(mainFrame.createMenu());

        BorderLayout borderLayout = new BorderLayout();
        mainFrame.setLayout(borderLayout);
        contentPane.add(mainFrame.createContentPanel(), BorderLayout.CENTER);
        contentPane.add(mainFrame.createBottomBar(), BorderLayout.SOUTH);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
//    public JTextArea createTextArea() {
//        JTextArea jTextArea = new JTextArea();
//        jTextArea.setSize(500, 500);
//        jTextArea.setLineWrap(true);
//
//        jTextArea.setFont(font);
//        return jTextArea;
//    }

    public JComponent createContentPanel() {

        jTextArea.setSize(500, 500);
        jTextArea.setLineWrap(true);
        jTextArea.setFont(font);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        return jScrollPane;
    }

    public JPanel createBottomBar() {
        JPanel jPanel = new JPanel();
        GridLayout borderLayout = new GridLayout();
        jPanel.setLayout(borderLayout);

        Label mqttlabel = new Label("MQTT");
        Dimension dimension = new Dimension(100, 200);
        mqttlabel.setMaximumSize(dimension);
        mqttlabel.setBackground(Color.blue);

        Label sqlLable = new Label("数据库");
        sqlLable.setMaximumSize(dimension);
        sqlLable.setBackground(Color.red);

        Label clinetidLable = new Label("Client");
        clinetidLable.setBackground(Color.green);
        clinetidLable.setMaximumSize(dimension);
        clinetidLable.setAlignment(Label.CENTER);


//        jPanel.add(mqttlabel,BorderLayout.LINE_START);
//        jPanel.add(sqlLable,BorderLayout.LINE_START);
//        jPanel.add(clinetidLable,BorderLayout.CENTER);
        jPanel.add(mqttlabel, 0);
        jPanel.add(sqlLable, 1);
        jPanel.add(clinetidLable, 2);
//        jPanel.add(mqttlabel,0);


        jPanel.setBackground(Color.pink);
        return jPanel;
    }
}
