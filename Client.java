package EDU_C;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.border.*;

public class Client extends JFrame {
	static String name = "";
	static String chapter = "";
	BufferedReader in;
	PrintWriter out;
	String fromCL = null;
	String fromCLL = "";
	String fromLO = null;
	String fromSV = null;
	int loginsus = 0;
	static String curID = "";
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

	public Client() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container con = getContentPane();
		con.setLayout(null);
		userText.setSize(160, 25); // make a ID textfield
		userText.setLocation(443, 396);
		userText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		con.add(userText);
		passText.setSize(160, 25); // make a password textfield
		passText.setLocation(443, 436);
		passText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		con.add(passText);
		userLabel.setSize(100, 50);
		userLabel.setLocation(342, 382);
		userLabel.setFont(f2);
		con.add(userLabel);
		passLabel.setSize(100, 50);
		passLabel.setLocation(342, 422);
		passLabel.setFont(f2);
		con.add(passLabel);
		join.setSize(90, 25); // make a sign-up button
		join.setLocation(514, 470);
		join.setFont(f3);
		join.setBorderPainted(false);
		join.setBackground(green);
		join.setForeground(Color.white);
		con.add(join);
		btnLogin.setSize(80, 65); // make a login button
		btnLogin.setLocation(611, 395);
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
		btnLogin.addActionListener(new ActionListener()  { // if login button clicked
			public void actionPerformed(ActionEvent e) {
				String str = "login/";
				String pw = "";
				Object obj = e.getSource();
				char[] s_pw = passText.getPassword();
				for (char cha : s_pw) {
					Character.toString(cha);
					pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
				}
				fromCLL = str + userText.getText().toString() + "/" + pw + "/name"; // make a string to send to server
				curID = userText.getText();
				if (loginsus == 1) { // if login is success
					if ((JButton) obj == btnLogin) {
						Chapter chap = new Chapter(); // shows chapter
						dispose();
						setVisible(false);
					}
				}
			}
		});

		join.addActionListener(new ActionListener() { // if sign-up button clicked
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if ((JButton) obj == join) {
					Join jo = new Join(); // shows sign-up page
				}
			}
		});
	}

	private void run() throws IOException {
		Socket socket = new Socket("127.0.0.1", 9001); // we set Socketnumber as 9001 with IP address of we sent
														// before
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // this function allows clients to
																					// listen what server sent.
		out = new PrintWriter(socket.getOutputStream(), true); // this function allows clients to send commands to
																// server
		while (true) {
			fromCL = Join.getMessage(); // the message from join class that to send to server 
			Join.initMessage();
			if (fromCL.startsWith("join")) {
				out.println(fromCL); // send to server
				fromCL = "";
				fromSV = in.readLine();
				JOptionPane.showMessageDialog(null, fromSV);
			}
			if (fromCLL.startsWith("login")) {
				out.println(fromCLL); // send to server about ID & password
				fromCLL = "";
				fromSV = in.readLine(); // receive from server that ID & password matches
				System.out.println(fromSV);
				if (fromSV.startsWith("success")) { // if it succeed
					loginsus = 1; // login status becomes 1
					String[] datas;
					datas = fromSV.split("/");
					name = datas[1];
					chapter = datas[2];
				} else {
				}
			}
			if (loginsus == 1) {
				break; // if login succeed breaks
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.run();
	}
}