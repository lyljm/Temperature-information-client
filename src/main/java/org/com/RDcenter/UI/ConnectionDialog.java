/*
 * Created by JFormDesigner on Tue Jul 18 13:50:38 HKT 2023
 */

package org.com.RDcenter.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import org.com.RDcenter.MQTTv3.Manager;

import org.com.RDcenter.config.MQTTConfig;
import org.com.RDcenter.model.MqttConf;


/**
 * @author 21772
 */
public class ConnectionDialog extends JDialog {
    public ConnectionDialog(Window owner) {
        super(owner);
        initComponents();
        initValue();
    }

    private void initValue() {
        this.clientIdText.setText(Manager.mqttConf.clientID);
        this.pwdText.setText(Manager.mqttConf.password);
        this.urlText.setText(Manager.mqttConf.url);
        this.userText.setText(Manager.mqttConf.username);
        this.topicText.setText(Manager.mqttConf.topic);
        this.qosSpinner.setValue(Integer.parseInt(Manager.mqttConf.qos));
    }

    private boolean isNull() {
        if (this.clientIdText.getText().isEmpty() ||
                this.urlText.getText().isEmpty() ||
                this.topicText.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void submitBtnClicked(ActionEvent e) {
        if(isNull()){
            JOptionPane.showMessageDialog(null, "请填写url,clentId,topic,qos");
            return;
        }
        Manager.mqttConf.clientID = this.clientIdText.getText();
        Manager.mqttConf.password = this.pwdText.getText();
        Manager.mqttConf.url = this.urlText.getText();
        Manager.mqttConf.username = this.userText.getText();
        Manager.mqttConf.topic = this.topicText.getText();
        Manager.mqttConf.qos = String.valueOf(this.qosSpinner.getValue());
        new MQTTConfig<MqttConf>().writeConfg(Manager.mqttConf);
        this.dispose();
    }

    private void randomBtnClicked(ActionEvent e) {
      this.clientIdText.setText(randomClientID());
    }
    public static String randomClientID(){
        long uid = new Random().nextLong();
        return "mqtt-client-test-" + uid;
    }

    private void resetClicked(ActionEvent e) {
        this.clientIdText.setText("");
        this.pwdText.setText("");
        this.urlText.setText("");
        this.userText.setText("");
        this.qosSpinner.setValue("0");
        this.topicText.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - ly
        ResourceBundle bundle = ResourceBundle.getBundle("org.com.RDcenter.UI.mainFrame");
        panel1 = new JPanel();
        urlLable = new JLabel();
        urlText = new JTextField();
        clientIdLable = new JLabel();
        clientIdText = new JTextField();
        randomBtn = new JButton();
        userLable = new JLabel();
        userText = new JTextField();
        pwdLable = new JLabel();
        pwdText = new JTextField();
        topicLable = new JLabel();
        topicText = new JTextField();
        pwdLable2 = new JLabel();
        qosSpinner = new JSpinner();
        reset = new JButton();
        submitBtn = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            panel1.setLayout(new FormLayout(
                "default, $lcgap, default:grow, $lcgap, default",
                "8*(default, $lgap), default"));

            //---- urlLable ----
            urlLable.setText(bundle.getString("sqlDialog.urlLable.text"));
            urlLable.setFont(urlLable.getFont().deriveFont(urlLable.getFont().getSize() + 6f));
            panel1.add(urlLable, CC.xy(1, 1));

            //---- urlText ----
            urlText.setColumns(20);
            urlText.setFont(urlText.getFont().deriveFont(urlText.getFont().getSize() + 6f));
            panel1.add(urlText, CC.xy(3, 1));

            //---- clientIdLable ----
            clientIdLable.setText(bundle.getString("sqlDialog.clientIdLable.text"));
            clientIdLable.setFont(clientIdLable.getFont().deriveFont(clientIdLable.getFont().getSize() + 6f));
            panel1.add(clientIdLable, CC.xy(1, 3));

            //---- clientIdText ----
            clientIdText.setFont(clientIdText.getFont().deriveFont(clientIdText.getFont().getSize() + 6f));
            panel1.add(clientIdText, CC.xy(3, 3));

            //---- randomBtn ----
            randomBtn.setText(bundle.getString("sqlDialog.randomBtn.text"));
            randomBtn.setFont(randomBtn.getFont().deriveFont(randomBtn.getFont().getSize() + 6f));
            randomBtn.addActionListener(e -> randomBtnClicked(e));
            panel1.add(randomBtn, CC.xy(5, 3));

            //---- userLable ----
            userLable.setText(bundle.getString("sqlDialog.userLable.text"));
            userLable.setFont(userLable.getFont().deriveFont(userLable.getFont().getSize() + 6f));
            panel1.add(userLable, CC.xy(1, 5));

            //---- userText ----
            userText.setFont(userText.getFont().deriveFont(userText.getFont().getSize() + 6f));
            panel1.add(userText, CC.xy(3, 5));

            //---- pwdLable ----
            pwdLable.setText(bundle.getString("sqlDialog.pwdLable.text"));
            pwdLable.setFont(pwdLable.getFont().deriveFont(pwdLable.getFont().getSize() + 6f));
            panel1.add(pwdLable, CC.xy(1, 7));

            //---- pwdText ----
            pwdText.setFont(pwdText.getFont().deriveFont(pwdText.getFont().getSize() + 6f));
            panel1.add(pwdText, CC.xy(3, 7));

            //---- topicLable ----
            topicLable.setText(bundle.getString("sqlDialog.topicLable.text"));
            topicLable.setFont(topicLable.getFont().deriveFont(topicLable.getFont().getSize() + 6f));
            panel1.add(topicLable, CC.xy(1, 9));

            //---- topicText ----
            topicText.setFont(topicText.getFont().deriveFont(topicText.getFont().getSize() + 6f));
            panel1.add(topicText, CC.xy(3, 9));

            //---- pwdLable2 ----
            pwdLable2.setText(bundle.getString("sqlDialog.pwdLable2.text"));
            pwdLable2.setFont(pwdLable2.getFont().deriveFont(pwdLable2.getFont().getSize() + 6f));
            panel1.add(pwdLable2, CC.xy(1, 11));

            //---- qosSpinner ----
            qosSpinner.setFont(qosSpinner.getFont().deriveFont(qosSpinner.getFont().getSize() + 6f));
            qosSpinner.setModel(new SpinnerNumberModel(0, 0, 2, 1));
            panel1.add(qosSpinner, CC.xy(3, 11, CC.LEFT, CC.DEFAULT));

            //---- reset ----
            reset.setText(bundle.getString("sqlDialog.reset.text"));
            reset.setFont(reset.getFont().deriveFont(reset.getFont().getSize() + 6f));
            reset.addActionListener(e -> resetClicked(e));
            panel1.add(reset, CC.xy(1, 13));

            //---- submitBtn ----
            submitBtn.setText(bundle.getString("sqlDialog.submitBtn.text"));
            submitBtn.setFont(submitBtn.getFont().deriveFont(submitBtn.getFont().getSize() + 6f));
            submitBtn.addActionListener(e -> submitBtnClicked(e));
            panel1.add(submitBtn, CC.xy(3, 13));
        }
        contentPane.add(panel1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - ly
    private JPanel panel1;
    private JLabel urlLable;
    private JTextField urlText;
    private JLabel clientIdLable;
    private JTextField clientIdText;
    private JButton randomBtn;
    private JLabel userLable;
    private JTextField userText;
    private JLabel pwdLable;
    private JTextField pwdText;
    private JLabel topicLable;
    private JTextField topicText;
    private JLabel pwdLable2;
    private JSpinner qosSpinner;
    private JButton reset;
    private JButton submitBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
