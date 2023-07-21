/*
 * Created by JFormDesigner on Tue Jul 18 14:35:35 HKT 2023
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
import org.com.RDcenter.model.MqttV3SubscribeParm;
import org.com.RDcenter.model.Qos;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import com.jgoodies.forms.factories.CC;

/**
 * @author 21772
 */
public class TopDialog extends JDialog {
    public TopDialog(Window owner) {
        super(owner);
        initComponents();
        initValue();
    }

    private void initValue() {
//        this.topicText.setText(MQTTConfig.topic);
//        this.qosSpinner.setValue(MQTTConfig.qos);
    }

    private void resetBtnClicked(ActionEvent e) {
        this.topicText.setText("");
        this.qosSpinner.setValue(0);
    }

    private boolean isNull() {
        int qos = (int) this.qosSpinner.getValue();
        if (qos < 0 || qos > 2) {
            JOptionPane.showMessageDialog(this, "qos应为0，1，2");
            return true;
        }
        if (this.topicText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "参数不能为空");
            return true;
        }
        return false;
    }

    private void submitBtnClicked(ActionEvent e) {
        // TODO add your code here
        if (isNull()) return;
        String text = this.topicText.getText();
        int qos = (int) this.qosSpinner.getValue();

        MqttV3SubscribeParm mqttV3SubscribeParm = new MqttV3SubscribeParm(this.topicText.getText(), Qos.getQos(qos));
        if (Manager.client == null) {
            JOptionPane.showMessageDialog(null, "请先建立连接");
            return;
        }
        if(Manager.client!=null&&!Manager.client.isConnection()){
            JOptionPane.showMessageDialog(null, "等待重新连接");
        }

        TopDialog dialog = this;
        Manager.client.subscribe(mqttV3SubscribeParm, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                String topics="";
                for (String t:  iMqttToken.getTopics()) {
                    topics=topics+t+"; ";
                }

                JOptionPane.showMessageDialog(dialog, "订阅成功: " + topics);
                dialog.dispose();
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                JOptionPane.showMessageDialog(dialog, "订阅失败 " + iMqttToken.getException().toString());
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - ly
        ResourceBundle bundle = ResourceBundle.getBundle("org.com.RDcenter.UI.mainFrame");
        topicLable = new JLabel();
        topicText = new JTextField();
        qosLable = new JLabel();
        qosSpinner = new JSpinner();
        resetBtn = new JButton();
        submitBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("TopDialog.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, $lcgap, default",
            "2*(default, $lgap), default"));

        //---- topicLable ----
        topicLable.setText(bundle.getString("TopDialog.topicLable.text"));
        topicLable.setFont(topicLable.getFont().deriveFont(topicLable.getFont().getSize() + 6f));
        contentPane.add(topicLable, CC.xy(1, 1));

        //---- topicText ----
        topicText.setColumns(20);
        topicText.setFont(topicText.getFont().deriveFont(topicText.getFont().getSize() + 6f));
        contentPane.add(topicText, CC.xy(3, 1));

        //---- qosLable ----
        qosLable.setText(bundle.getString("TopDialog.qosLable.text"));
        qosLable.setFont(qosLable.getFont().deriveFont(qosLable.getFont().getSize() + 6f));
        contentPane.add(qosLable, CC.xy(1, 3));

        //---- qosSpinner ----
        qosSpinner.setFont(qosSpinner.getFont().deriveFont(qosSpinner.getFont().getSize() + 6f));
        qosSpinner.setModel(new SpinnerNumberModel(0, 0, 2, 1));
        contentPane.add(qosSpinner, CC.xy(3, 3, CC.LEFT, CC.DEFAULT));

        //---- resetBtn ----
        resetBtn.setText(bundle.getString("TopDialog.resetBtn.text"));
        resetBtn.setFont(resetBtn.getFont().deriveFont(resetBtn.getFont().getSize() + 6f));
        resetBtn.addActionListener(e -> resetBtnClicked(e));
        contentPane.add(resetBtn, CC.xy(1, 5));

        //---- submitBtn ----
        submitBtn.setText(bundle.getString("TopDialog.submitBtn.text"));
        submitBtn.setFont(submitBtn.getFont().deriveFont(submitBtn.getFont().getSize() + 6f));
        submitBtn.addActionListener(e -> submitBtnClicked(e));
        contentPane.add(submitBtn, CC.xy(3, 5));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - ly
    private JLabel topicLable;
    private JTextField topicText;
    private JLabel qosLable;
    private JSpinner qosSpinner;
    private JButton resetBtn;
    private JButton submitBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
