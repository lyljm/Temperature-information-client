package org.com.RDcenter.UI.deprecated;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MQTTDialog extends JDialog {
    private volatile static MQTTDialog mqttDialog;

    Label urlLabel = new Label("url");
    Label clientIdLable = new Label("clientId");
    Label userLable = new Label("username");
    Label pwdLable = new Label("password");

    JButton submitBtn=new JButton("确定");

    public TextField urlText = new TextField();
    public TextField idText = new TextField();
    public TextField userText = new TextField();
    public TextField pwdText = new TextField();

    public MQTTDialog(JFrame mainFrame, boolean model) {
        super(mainFrame,model);
    }

    public void init() {
        JPanel panel = new JPanel();
        setSize(500, 500);
//        panel.setLayout(new GridLayout(3,2,5,5));

        int column=30;
        urlText.setColumns(column);
        idText.setColumns(column);
        userText.setColumns(column);
        pwdText.setColumns(column);

        panel.add(urlLabel);
        panel.add(urlText);
        panel.add(clientIdLable);
        panel.add(idText);
        panel.add(userLable);
        panel.add(userText);
        panel.add(pwdLable);
        panel.add(pwdText);

        panel.setSize(100, 100);
        panel.setBackground(Color.pink);

        panel.setBounds(0, 0, 50, 50);
        Container contentPane = this.getContentPane();

        submitBtn.setBounds(50,50,50,50);
        panel.add(submitBtn);
        submitBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("确定");
            }
        });
        contentPane.add(panel);
        contentPane.add(panel);
    }

//    public static void main(String[] args) {
//        mqttDialog.getInstance().setVisible(true);
//    }

//    public static MQTTDialog getInstance() {
//        if (mqttDialog == null) {
//            synchronized (MQTTDialog.class) {
//                if (mqttDialog == null) {
//                    mqttDialog = new MQTTDialog();
//                    return mqttDialog;
//                }
//            }
//        }
//        return mqttDialog;
//    }
}
