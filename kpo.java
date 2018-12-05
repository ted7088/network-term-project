package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.border.*;

public class kpo extends JFrame {

      BufferedReader in;
      PrintWriter out;
      String fromCL = null;
      String fromCLL = "";
      String fromLO = null;
      String fromSV = null;
      int loginsus = 0;
      Font f2 = new Font("야놀자 야체 Regular", Font.BOLD, 20);
      Color green = new Color(168, 209, 141);
      Font f3 = new Font("야놀자 야체 Regular", Font.BOLD, 17);
      Font f4 = new Font("야놀자 야체 Regular", Font.BOLD, 20);
      JTextField userText = new JTextField(20);
      JPasswordField passText = new JPasswordField(20);
      JLabel userLabel = new JLabel("아이디:", JLabel.RIGHT);
      JLabel passLabel = new JLabel("비밀번호:", JLabel.RIGHT);
      JButton join = new JButton("회원가입");
      JButton btnLogin = new JButton("로그인");
      private final JLabel lblNewLabel = new JLabel("EDU - C");

   public kpo() {
   	getContentPane().setBackground(Color.WHITE);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   Container con = getContentPane();
	   con.setLayout(null);
	   userText.setSize(160,25);
	   userText.setLocation(443,396);
	   userText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
	   con.add(userText);
	   passText.setSize(160,25);
	   passText.setLocation(443,436);
	   passText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
	   con.add(passText);
	   userLabel.setSize(100,50);
	   userLabel.setLocation(342,382);
	   userLabel.setFont(f2); 
	   con.add(userLabel);
	   passLabel.setSize(100,50);
	   passLabel.setLocation(342,422);
	   passLabel.setFont(f2);
	   con.add(passLabel);
	   join.setSize(90,25);
	   join.setLocation(514,470);
	   join.setFont(f3);
	   join.setBorderPainted(false);
	   join.setBackground(green);
	   join.setForeground(Color.white);
	   con.add(join);
	   btnLogin.setSize(80,65);
	   btnLogin.setLocation(611,395);
	   btnLogin.setFont(f4);
	   btnLogin.setBorderPainted(false);
	   btnLogin.setBackground(green);
	   btnLogin.setForeground(Color.white);
	   con.add(btnLogin);
	   lblNewLabel.setFont(new Font("야놀자 야체 R", Font.PLAIN, 90));
	   lblNewLabel.setBounds(390, 195, 273, 87);
	   
	   getContentPane().add(lblNewLabel);
	   setTitle("login");
	   setSize(1100, 700);
	   setResizable(false);
	   setLocation(0, 0);
	   setVisible(true);
      btnLogin.addActionListener(new ActionListener() {    	  
    		 public void actionPerformed(ActionEvent e) {
		            String str = "login/";
		            String pw = "";
		            Object obj = e.getSource();
		            char[] s_pw = passText.getPassword();
		            for (char cha : s_pw) {
		               Character.toString(cha);
		               pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
		            }
		            fromCLL = str + userText.getText() + "/" + pw + "/name/";
		            if(loginsus==1) {
		            	if ((JButton) obj == btnLogin) {
			                Chapter chap = new Chapter();
			                dispose();
			                setVisible(false);
			             }
		            }
    	  }
      });
	   
	   join.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             Object obj = e.getSource();
	             if ((JButton) obj == join) {
	                Join jo = new Join();
	             }
	       }
	   });  
   }
   private void run() throws IOException {

       //String serverAddress = getServerAddress(); // at first we set IP address and save them into serverAddress variable
       Socket socket = new Socket("192.168.43.22", 9001); // we set Socketnumber as 9001 with IP address of we sent before
       in =  new BufferedReader(new InputStreamReader(socket.getInputStream())); // this function allows clients to listen what server sent.
       out = new PrintWriter(socket.getOutputStream(), true); // this function allows clients to send commands to server
       while (true) {
    	   fromCL = Join.getMessage();
    	   Join.initMessage();
    	   System.out.println(fromCL);
           if(fromCL.startsWith("join")) {
        	   System.out.println("done");
        	   out.println(fromCL);
        	   fromCL ="";
        	   fromSV = in.readLine();
        	   String success;
        	   JOptionPane.showMessageDialog(null, fromSV);
           }
           if(fromCLL.startsWith("login")) {
        	   //out.println(fromCL);
        	   out.println(fromCLL);
        	   fromCLL = "";
        	   fromSV = in.readLine();
        	   //System.out.println("hi");
        	   if(fromSV.startsWith("success")){
        		   loginsus = 1;
        	   }
        	   else{
        		   System.out.println("error");
        	   }
           }
           if(loginsus==1) {
        	   break;
           }
       }   	
   }
	public static void main(String[] args) throws Exception {
		kpo client = new kpo();
		client.run();
	}
}