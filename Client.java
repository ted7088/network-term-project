package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.border.*;
/*
 * Main class for client
 * to process Login operations or call Join class
 * give informations to server and get answer from server
 * */
public class Client extends JFrame {

	//to hear/talk to server
    BufferedReader in;
    PrintWriter out;
   
    //string to store Join Class's totalMessage which contains new user's information and header
    String joinInfo = null;
 
    //string to store user's login information
    String loginInfo = "";
    
    //string to store server's reply about login/join operation's success/failure
    String reply = null;
    
    //boolean variable to notice success of login
    boolean LoginSuccess = false;
    
    //store fonts and color into each variable
    Font font1 = new Font("야놀자 야체 Regular", Font.BOLD, 20);
    Font font2 = new Font("야놀자 야체 Regular", Font.BOLD, 17);
    Color green = new Color(168, 209, 141);
   
    //labels to clarify each text fields
    JLabel id = new JLabel("아이디:", JLabel.RIGHT);
    JLabel pw = new JLabel("비밀번호:", JLabel.RIGHT);
    
    //text fields to get user's information
    JTextField idText = new JTextField(20);
    JPasswordField pwText = new JPasswordField(20);
    
    //button to process join/login
    JButton join = new JButton("회원가입");
    JButton login = new JButton("로그인");
    
    //name of our project
    private final JLabel title = new JLabel("EDU - C");
    
    public Client() {
    	getContentPane().setBackground(Color.WHITE);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container con = getContentPane();
	    con.setLayout(null);

	    //to set size of each labels
	    id.setSize(100,50);
	    id.setLocation(342,382);
	    id.setFont(font1); 
	    con.add(id);
	   
	    pw.setSize(100,50);
	    pw.setLocation(342,422);
	    pw.setFont(font1);
	    con.add(pw);

	    //to set size of each text fields
	    idText.setSize(160,25);
  	    idText.setLocation(443,396);
	    idText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
	    con.add(idText);
	  
	    pwText.setSize(160,25);
	    pwText.setLocation(443,436);
	    pwText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
	    con.add(pwText);
	   
	    //to set size and color of join button
	    join.setSize(90,25);
	    join.setLocation(514,470);
	    join.setFont(font2);
	    join.setBorderPainted(false);
	    join.setBackground(green);
	    join.setForeground(Color.white);
	    con.add(join);
	    //to call Join Class when user clicks button
	    join.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Object obj = e.getSource();
	    		if ((JButton) obj == join) {
	    			Join newMember = new Join();
	    		}
	    	}
	    });  
	    
	    //to set size and color of login button
	    login.setSize(80,65);
	    login.setLocation(611,395);
	    login.setFont(font1);
	    login.setBorderPainted(false);
	    login.setBackground(green);
	    login.setForeground(Color.white);
	    con.add(login);
	    //to merge all information into a string with header 'login/' and store it into loginInfo when user clicks button
	    //message is formed with header/id/pw/name/chapter
	    login.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String header = "login/";
	    		String pw = "";
	    		Object obj = e.getSource();
	    		char[] s_pw = pwText.getPassword();
	    		for (char cha : s_pw) {
	    			Character.toString(cha);
	    			pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
	    		}
	    		loginInfo = header + idText.getText() + "/" + pw + "/name/";
	    		//close current window when login succeed
	    		if(LoginSuccess == true) {
	    			if ((JButton) obj == login) {
	    				Chapter chap = new Chapter();
	    				dispose();
	    				setVisible(false);
	    			}
	    		}
	    	}
	    });
	    
	    //to set size of title
	    title.setFont(new Font("야놀자 야체 R", Font.PLAIN, 90));
	    title.setBounds(390, 195, 273, 87);
	    getContentPane().add(title);
	    
	    //to set size of frame and make whole contents visible
	    setTitle("EDU - C");
	    setSize(1100, 700);
	    setResizable(false);
	    setLocation(0, 0);
	    setVisible(true);
    }
    
    /*
     * runs operations(join&login)
     * */
    private void run() throws IOException {

    	Socket socket = new Socket("192.168.43.22", 9001); 
    	//talk to/hear server
    	in =  new BufferedReader(new InputStreamReader(socket.getInputStream())); 
    	out = new PrintWriter(socket.getOutputStream(), true);

    	//keep this panel till login success
    	while (true) {
    		//store Join's totalMessage(string that contains all information that user put) into joinInfo and initialize totalMessage
    		joinInfo = Join.getMessage();
    		Join.initMessage();

    		//send joinInfo to server and initialize joinInfo
            if(joinInfo.startsWith("join")) {
            	out.println(joinInfo);
        	    joinInfo ="";
        	    //get reply from server and print it
        	    reply = in.readLine();
        	    JOptionPane.showMessageDialog(null, reply);
            }
            
            //send loginInfo to server and initialize loginInfo
            if(loginInfo.startsWith("login")) {
            	out.println(loginInfo);
        	    loginInfo = "";
        	    //get reply from server and print it
        	    reply = in.readLine();

        	    //make LoginSuccess true if login succeed
        	    if(reply.startsWith("success")){
        	    	LoginSuccess = true;
        	    	break;
        	    }

        	    //or print error message from server
        	    else {
        	    	JOptionPane.showMessageDialog(null, reply);
        	    }
            }
    	}   	
    }
    
	public static void main(String[] args) throws Exception {
		Client client1 = new Client();
		client1.run();
	}
}