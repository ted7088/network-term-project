package EDU_C;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Join extends JFrame  {
   int check = 0;
   JLabel lblNewLabel = new JLabel("이름", JLabel.CENTER);
   JLabel lblNewLabel_2 = new JLabel("아이디", JLabel.CENTER);
   JLabel lblNewLabel_3 = new JLabel("비밀번호", JLabel.CENTER);
   JButton joinbtn = new JButton("\uD655\uC778");
   JPasswordField passwordText = new JPasswordField();
   JTextField idText = new JTextField();
   JTextField nameText = new JTextField();
   Color green = new Color(168, 209, 141);
   static JLabel errorText = new JLabel("");
   static String totalMessage ="";
   public static String getMessage() {
	   return totalMessage;
   }
   public static void initMessage() {
	   totalMessage = "";
   }
   public Join() {
      getContentPane().setBackground(Color.WHITE);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Container con = getContentPane();
         getContentPane().setLayout(null);
         errorText.setFont(new Font("야놀자 야체 R", Font.PLAIN, 18));
         errorText.setBounds(78, 200, 125, 18);
         con.add(errorText);
         lblNewLabel.setBounds(33, 44, 68, 15);
         lblNewLabel.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
         con.add(lblNewLabel);
         lblNewLabel_2.setBounds(27, 90, 68, 15);
         lblNewLabel_2.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
         con.add(lblNewLabel_2);
         lblNewLabel_3.setBounds(22, 138, 68, 15);
         lblNewLabel_3.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
         con.add(lblNewLabel_3);
         nameText.setBounds(87, 42, 116, 21); // make a name textfield
         nameText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
         nameText.setColumns(10);
         con.add(nameText);
         idText.setBounds(87, 88, 116, 21); // make a ID textfield
         idText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
         idText.setColumns(10);
         con.add(idText);
         passwordText.setBounds(87, 134, 116, 21); // make a password textfield
         passwordText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
         con.add(passwordText);
         joinbtn.setBounds(86, 165, 97, 23);
     

         joinbtn.setForeground(Color.WHITE); // make a submit button
         joinbtn.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
         joinbtn.setBackground(green);
         joinbtn.setBorderPainted(false);
         con.add(joinbtn);
         setTitle("회원가입");
         setSize(269, 267);
         setResizable(false);
         setLocation(800, 350);
         setVisible(true);
         
         joinbtn.addActionListener(new ActionListener() { // if submit button is clicked
            public void actionPerformed(ActionEvent e) {
               String str = "join/";
               String pw = "";
	           char[] s_pw = passwordText.getPassword();
	           for (char cha : s_pw) {
	              Character.toString(cha);
	              pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
	           }
	           totalMessage = str + idText.getText() + "/" + pw + "/" + nameText.getText().toString()+"/000000"; // make a static string to send to server
	           dispose();
	           con.setVisible(false);
            }
         });
   }
}