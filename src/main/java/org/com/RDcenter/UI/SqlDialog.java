/*
 * Created by JFormDesigner on Tue Jul 18 15:10:48 HKT 2023
 */

package org.com.RDcenter.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import org.com.RDcenter.config.DBconfig;
import org.com.RDcenter.config.MybatisConfigLoader;

/**
 * @author 21772
 */
public class SqlDialog extends JDialog {
    public SqlDialog(Window owner) {
        super(owner);
        initComponents();
        initValue();
    }

    private void initValue() {
        this.urlText.setText(DBconfig.url);
        this.pwdText.setText(DBconfig.password);
        this.userText.setText(DBconfig.username);
    }

    private void resetBtnClicked(ActionEvent e) {
        this.urlText.setText("");
        this.userText.setText("");
        this.pwdText.setText("");
    }

    private void submitBtnClicked(ActionEvent e) {
        // TODO add your code here
        String url = this.urlText.getText();
        String user = this.userText.getText();
        String pwd = this.pwdText.getText();
        DBconfig.setProperties(null,url,user,pwd);
        this.dispose();
        MybatisConfigLoader.reloadConfig();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - ly
        ResourceBundle bundle = ResourceBundle.getBundle("org.com.RDcenter.UI.mainFrame");
        panel1 = new JPanel();
        urlLable = new JLabel();
        urlText = new JTextField();
        userLable = new JLabel();
        userText = new JTextField();
        pwdLable = new JLabel();
        pwdText = new JTextField();
        resetBtn = new JButton();
        submitBtn = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.LINE_AXIS));

        //======== panel1 ========
        {
            panel1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new FormLayout(
                "default, $lcgap, default:grow",
                "3*(default, $lgap), default"));

            //---- urlLable ----
            urlLable.setText(bundle.getString("SqlDialog.urlLable.text"));
            urlLable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(urlLable, CC.xy(1, 1));

            //---- urlText ----
            urlText.setColumns(40);
            urlText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(urlText, CC.xy(3, 1));

            //---- userLable ----
            userLable.setText(bundle.getString("SqlDialog.userLable.text"));
            userLable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(userLable, CC.xy(1, 3));

            //---- userText ----
            userText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(userText, CC.xy(3, 3));

            //---- pwdLable ----
            pwdLable.setText(bundle.getString("SqlDialog.pwdLable.text"));
            pwdLable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(pwdLable, CC.xy(1, 5));

            //---- pwdText ----
            pwdText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            panel1.add(pwdText, CC.xy(3, 5));

            //---- resetBtn ----
            resetBtn.setText(bundle.getString("SqlDialog.resetBtn.text"));
            resetBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            resetBtn.addActionListener(e -> resetBtnClicked(e));
            panel1.add(resetBtn, CC.xy(1, 7));

            //---- submitBtn ----
            submitBtn.setText(bundle.getString("SqlDialog.submitBtn.text"));
            submitBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            submitBtn.addActionListener(e -> submitBtnClicked(e));
            panel1.add(submitBtn, CC.xy(3, 7));
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
    private JLabel userLable;
    private JTextField userText;
    private JLabel pwdLable;
    private JTextField pwdText;
    private JButton resetBtn;
    private JButton submitBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
