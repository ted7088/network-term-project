package pack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
import java.net.Socket;
public class Login extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	BufferedReader in;
	PrintWriter out;
	public Login() {
		
		
		setTitle("login");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("Loginbackground.jpg");
		Image originImg = icon.getImage();
		Image changedImg = originImg.getScaledInstance(1100, 700, Image.SCALE_SMOOTH);
		// panel
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
			//	g.drawImage(Icon.getImage(), 0, 0, null);
			//	setOpaque(false);// 배경 띄워주기
				super.paintComponent(g);
			}
		};
		panel.setBackground(Color.WHITE);

		getContentPane().add(panel);

		Font f2 = new Font("야놀자 야체 Regular", Font.BOLD, 20);
		panel.setLayout(null);
		Color green = new Color(168, 209, 141);

		Font f3 = new Font("야놀자 야체 Regular", Font.BOLD, 17);

		Font f4 = new Font("야놀자 야체 Regular", Font.BOLD, 20);
		JTextField idText = new JTextField(20);
		idText.setBounds(444, 396, 160, 25);
		panel.add(idText);
		idText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(444, 436, 160, 25);
		panel.add(passwordText);
		passwordText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));

		JLabel userLabel = new JLabel("아이디:", JLabel.RIGHT);
		userLabel.setBounds(332, 384, 100, 50);
		panel.add(userLabel);
		userLabel.setFont(f2);

		JLabel passLabel = new JLabel("비밀번호:", JLabel.RIGHT);
		passLabel.setBounds(332, 422, 100, 50);
		panel.add(passLabel);
		passLabel.setFont(f2);
		JButton btnJoin = new JButton("회원가입");
		btnJoin.setBounds(514, 470, 90, 25);
		panel.add(btnJoin);
		btnJoin.setFont(f3);
		btnJoin.setBorderPainted(false);
		btnJoin.setBackground(green);
		btnJoin.setForeground(Color.white);
		JButton btnLogin = new JButton("로그인");
		btnLogin.setBounds(611, 395, 80, 65);
		panel.add(btnLogin);
		btnLogin.setFont(f4);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(green);
		btnLogin.setForeground(Color.white);
		
		JLabel lblEduc = new JLabel("EDU-C");
		lblEduc.setFont(new Font("야놀자 야체 B", Font.BOLD, 90));
		lblEduc.setBounds(435, 116, 237, 117);
		panel.add(lblEduc);
		
				btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if ((JButton) obj == btnJoin) {
					Join jo = new Join();
					
				}

			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "login/";
				String pw = "";
				char[] s_pw = passwordText.getPassword();
				for (char cha : s_pw) {
					Character.toString(cha);
					pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
				}
				str = str + idText.getText() + "/" + pw + "/name/";
				
			}
		});
		setVisible(true);
	}
	
	private void run() throws IOException{

        String serverAddress = "127.0.0.1";
        String chapter;
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if(line.startsWith("success"))
            {
            	chapter = line.substring(9);
            }
               
        }
	}

}
