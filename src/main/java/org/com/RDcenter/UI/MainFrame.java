/*
 * Created by JFormDesigner on Tue Jul 18 12:57:28 HKT 2023
 */

package org.com.RDcenter.UI;

import org.com.RDcenter.MQTTv3.Manager;
import org.com.RDcenter.MQTTv3.MqttCallBackImp;
import org.com.RDcenter.MQTTv3.MqttV3Client;
import org.com.RDcenter.Service.ShowMessage;
import org.com.RDcenter.config.DBconfig;
import org.com.RDcenter.model.MqttV3ConnectionParm;
import org.com.RDcenter.model.MqttV3SubscribeParm;
import org.com.RDcenter.model.Qos;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * @author 21772
 */
public class MainFrame extends JFrame implements ShowMessage {
    public MainFrame() {
        initComponents();
        this.textArea1.setAutoscrolls(true);
        this.stopItem.setEnabled(false);
    }

    static long sequence = 0;
    SqlDialog dialog = new SqlDialog(this);
    ConnectionDialog connectionDialog = new ConnectionDialog(this);

    @Override
    public void showMsg(String msg) {
        System.out.println(textArea1.getLineCount()+" "+textArea1.getColumns());
        if (this.textArea1.getLineCount() > 50) {
            this.textArea1.replaceRange(null, 0, 4 * msg.length());
        }
//        System.out.println(this.textArea1.getLineCount());
        this.textArea1.append("序号：" + sequence + " " + msg + "\n-----------------------------\n");
        sequence++;
        if (sequence > 100000) {
            sequence = 0;
        }
    }

    public void setConLable(boolean iscon) {
        if (iscon) {
            this.mqttCon.setText("MQTT已经连接");
            this.mqttCon.setForeground(Color.green);
        } else {
            this.mqttCon.setText("MQTT断开连接");
            this.mqttCon.setForeground(Color.red);
        }
    }

    public void setsqlLable(boolean iscon) {
        if (iscon) {
            this.sqlCon.setText("数据库已经连接");
            this.sqlCon.setForeground(Color.green);
        } else {
            this.sqlCon.setText("数据库断开连接");
            this.sqlCon.setForeground(Color.red);
        }
    }

    public void setClientIDLable(String msg) {
        this.clientIDLable.setText(msg);
    }

    private void exitMenuMouseClicked(MouseEvent e) {
        System.out.println("exit");
        int status = JOptionPane.showConfirmDialog(this, "是否退出程序?", "exit", JOptionPane.YES_NO_OPTION);
        if (status == 0) {
            System.exit(0);
        }
    }

    private void connectItemClickDialog(ActionEvent e) {
        connectionDialog.setModal(true);
        connectionDialog.setVisible(true);
    }

    private void sqlItemClicked(ActionEvent e) {
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void mqttConnect() {
        MqttConnectOptions conOpts = new MqttConnectOptions();
        String[] urls = new String[]{Manager.mqttConf.url};
        conOpts.setServerURIs(urls);
        if (!Manager.mqttConf.username.isEmpty()) {
            conOpts.setUserName(Manager.mqttConf.username);
        }
        if (!Manager.mqttConf.password.isEmpty()) {
            conOpts.setPassword(Manager.mqttConf.password.toCharArray());
        }
        conOpts.setKeepAliveInterval(10);
//        conOpts.setAutomaticReconnect(true);
        String clientId = Manager.mqttConf.clientID;
        MqttV3ConnectionParm connectionParm = new MqttV3ConnectionParm(clientId, conOpts);
        MqttCallback mqttCallback = new MqttCallBackImp();
        MqttV3Client v3Executor = new MqttV3Client(mqttCallback, 1000);
        v3Executor.connect(connectionParm, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
//                JOptionPane.showMessageDialog(null, "成功");
                Manager.client = v3Executor;
                Manager.mqttConnection = true;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Manager.mainJFrame.setClientIDLable("clientID: " + clientId);
                    }
                });
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                JOptionPane.showMessageDialog(null, "连接失败\n" + throwable.toString());
                Manager.mqttConnection = false;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Manager.mainJFrame.setConLable(false);
                    }
                });
            }
        });
    }

    public void subscribeTopic() {
        int qos = Integer.parseInt(Manager.mqttConf.qos);
        MqttV3SubscribeParm mqttV3SubscribeParm = new MqttV3SubscribeParm(Manager.mqttConf.topic, Qos.getQos(qos));
        Manager.client.subscribe(mqttV3SubscribeParm, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Manager.mainJFrame.setConLable(true);
                    }
                });
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                JOptionPane.showMessageDialog(null, "订阅失败" + throwable.toString());
            }
        });
    }

    private void sqlConnect() {
        //数据库连接
        try {
            DBconfig.isConnection();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Manager.mainJFrame.setsqlLable(true);
            }
        });
        Manager.sqlConnection = true;
    }

    private void startItemClicked(ActionEvent e) {
        mqttConnect();
        subscribeTopic();
        sqlConnect();
        this.configMenu.setEnabled(false);
        this.startItem.setEnabled(false);
        this.stopItem.setEnabled(true);
    }

    private void stopItemClicked(ActionEvent e) {
        this.startItem.setEnabled(true);
        this.stopItem.setEnabled(false);
        this.configMenu.setEnabled(true);
        Manager.client.disconnectClient();
        Manager.client.closeClient();
        this.setsqlLable(false);
        this.setConLable(false);
        Manager.sqlConnection = false;
        Manager.mqttConnection=false;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - ly
        ResourceBundle bundle = ResourceBundle.getBundle("org.com.RDcenter.UI.mainFrame");
        menuBar1 = new JMenuBar();
        serviceMenu = new JMenu();
        startItem = new JMenuItem();
        stopItem = new JMenuItem();
        configMenu = new JMenu();
        sqlItem = new JMenuItem();
        connectItem = new JMenuItem();
        DeviceMenu = new JMenu();
        exitMenu = new JMenu();
        panel1 = new JPanel();
        mqttCon = new JLabel();
        sqlCon = new JLabel();
        clientIDLable = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        setTitle("\u6d4b\u6e29\u67aa\u91c7\u96c6\u7a0b\u5e8f");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== serviceMenu ========
            {
                serviceMenu.setText(bundle.getString("MainFrame.serviceMenu.text"));
                serviceMenu.setFont(serviceMenu.getFont().deriveFont(serviceMenu.getFont().getSize() + 6f));

                //---- startItem ----
                startItem.setText(bundle.getString("MainFrame.startItem.text"));
                startItem.setFont(startItem.getFont().deriveFont(startItem.getFont().getSize() + 6f));
                startItem.addActionListener(e -> startItemClicked(e));
                serviceMenu.add(startItem);

                //---- stopItem ----
                stopItem.setText(bundle.getString("MainFrame.stopItem.text"));
                stopItem.setFont(stopItem.getFont().deriveFont(stopItem.getFont().getSize() + 6f));
                stopItem.addActionListener(e -> stopItemClicked(e));
                serviceMenu.add(stopItem);
            }
            menuBar1.add(serviceMenu);

            //======== configMenu ========
            {
                configMenu.setText(bundle.getString("MainFrame.configMenu.text"));
                configMenu.setFont(configMenu.getFont().deriveFont(configMenu.getFont().getSize() + 6f));

                //---- sqlItem ----
                sqlItem.setText(bundle.getString("MainFrame.sqlItem.text"));
                sqlItem.setFont(sqlItem.getFont().deriveFont(sqlItem.getFont().getSize() + 6f));
                sqlItem.addActionListener(e -> sqlItemClicked(e));
                configMenu.add(sqlItem);

                //---- connectItem ----
                connectItem.setText(bundle.getString("MainFrame.connectItem.text"));
                connectItem.setFont(connectItem.getFont().deriveFont(connectItem.getFont().getSize() + 6f));
                connectItem.addActionListener(e -> connectItemClickDialog(e));
                configMenu.add(connectItem);
            }
            menuBar1.add(configMenu);

            //======== DeviceMenu ========
            {
                DeviceMenu.setText(bundle.getString("MainFrame.DeviceMenu.text"));
                DeviceMenu.setFont(DeviceMenu.getFont().deriveFont(DeviceMenu.getFont().getSize() + 6f));
            }
            menuBar1.add(DeviceMenu);

            //======== exitMenu ========
            {
                exitMenu.setText(bundle.getString("MainFrame.exitMenu.text"));
                exitMenu.setFont(exitMenu.getFont().deriveFont(exitMenu.getFont().getSize() + 6f));
                exitMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        exitMenuMouseClicked(e);
                    }
                });
            }
            menuBar1.add(exitMenu);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
                    0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder
                    .BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.
                    red), panel1.getBorder()));
            panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.
                                                   beans.PropertyChangeEvent e) {
                    if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
            panel1.setLayout(new BorderLayout(5, 0));

            //---- mqttCon ----
            mqttCon.setText(bundle.getString("MainFrame.mqttCon.text"));
            mqttCon.setForeground(new Color(0xff3333));
            panel1.add(mqttCon, BorderLayout.WEST);

            //---- sqlCon ----
            sqlCon.setText(bundle.getString("MainFrame.sqlCon.text"));
            sqlCon.setForeground(Color.red);
            panel1.add(sqlCon, BorderLayout.CENTER);

            //---- clientIDLable ----
            clientIDLable.setText(bundle.getString("MainFrame.clientIDLable.text"));
            panel1.add(clientIDLable, BorderLayout.EAST);
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setLineWrap(true);
            textArea1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
            textArea1.setForeground(Color.black);
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        setSize(760, 690);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - ly
    private JMenuBar menuBar1;
    private JMenu serviceMenu;
    private JMenuItem startItem;
    private JMenuItem stopItem;
    private JMenu configMenu;
    private JMenuItem sqlItem;
    private JMenuItem connectItem;
    private JMenu DeviceMenu;
    private JMenu exitMenu;
    private JPanel panel1;
    private JLabel mqttCon;
    private JLabel sqlCon;
    private JLabel clientIDLable;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
