package test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;

/* Subclass for Client to process new user's join
 * makes a new frame to get user's information
 * merge all information into a string, and store it into 'totalMessage'
 * */
public class Join extends JFrame  {
   int check = 0;
   
   //labels to clarify each text fields
   JLabel name = new JLabel("이름", JLabel.CENTER);
   JLabel id = new JLabel("아이디", JLabel.CENTER);
   JLabel pw = new JLabel("비밀번호", JLabel.CENTER);
   
   //text fields to get user's information
   JPasswordField pwText = new JPasswordField();
   JTextField idText = new JTextField();
   JTextField nameText = new JTextField();

   //to submit user's information
   JButton submit = new JButton("\uD655\uC778");

   //color to set each button's background color 
   Color green = new Color(168, 209, 141);

   //to make user's informations into one string variable
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
      
      //to set size of each label
      name.setBounds(33, 44, 68, 15);
      name.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
      con.add(name);
         
      id.setBounds(27, 90, 68, 15);
      id.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
      con.add(id);
         
      pw.setBounds(22, 138, 68, 15);
      pw.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
      con.add(pw);

      //to set size of each text fields
      nameText.setBounds(87, 42, 116, 21);
      nameText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
      nameText.setColumns(10);
      con.add(nameText);
         
      idText.setBounds(87, 88, 116, 21);
      idText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
      idText.setColumns(10);
      con.add(idText);

      pwText.setBounds(87, 134, 116, 21); 
      pwText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
      con.add(pwText);
      
      //to set size, color and font of submit button 
      submit.setBounds(86, 165, 97, 23);
      submit.setForeground(Color.WHITE);
      submit.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
      submit.setBackground(green);
      submit.setBorderPainted(false);
      con.add(submit);
      //to merge all information into a string with header 'join/' and store it into totalMessage when user clicks button
      submit.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  String header = "join/";
              String pw = "";
	          char[] s_pw = pwText.getPassword();
	          for (char cha : s_pw) {
	        	  Character.toString(cha);
	              pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
	          }
	          totalMessage = header + idText.getText() + "/" + pw + "/" + nameText.getText().toString()+"/000000";
	          dispose();
	          con.setVisible(false);
    	  }
    	  });

      //to set size of frame and make whole contents visible
      setTitle("회원가입");
      setSize(269, 267);
      setResizable(false);
      setLocation(800, 350);
      setVisible(true);
   }
}