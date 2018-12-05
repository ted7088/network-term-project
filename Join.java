package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Client.Client;
import Client.DTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Join extends JFrame {
	int check = 0;

	public Join() {
		String name,id, password;
		setTitle("회원가입");
		setSize(269, 267);
		setResizable(false);
		setLocation(800, 350);
		Color green = new Color(168, 209, 141);
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setVisible(true);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		JLabel lblNewLabel = new JLabel("이름", JLabel.CENTER);
		lblNewLabel.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
		lblNewLabel.setBounds(33, 44, 68, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("아이디", JLabel.CENTER);
		lblNewLabel_2.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
		lblNewLabel_2.setBounds(27, 90, 68, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("비밀번호", JLabel.CENTER);
		lblNewLabel_3.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
		lblNewLabel_3.setBounds(22, 138, 68, 15);
		panel.add(lblNewLabel_3);

		JTextField nameText = new JTextField();
		nameText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		nameText.setBounds(87, 42, 116, 21);
		panel.add(nameText);
		nameText.setColumns(10);
		
		JTextField idText = new JTextField();
		idText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		idText.setBounds(87, 88, 116, 21);
		panel.add(idText);
		idText.setColumns(10);

		JPasswordField passwordText = new JPasswordField();
		passwordText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		passwordText.setBounds(87, 134, 116, 21);
		panel.add(passwordText);

		JButton joinbtn = new JButton("\uD655\uC778");
		joinbtn.setForeground(Color.WHITE);
		joinbtn.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
		joinbtn.setBackground(green);
		joinbtn.setBorderPainted(false);
		joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "join/";
				str = str + idText.getText()+"/" + passwordText.getText()+"/" + nameText.getText()+"/"; 
				
			}
		});
		joinbtn.setBounds(84, 184, 97, 23);
		panel.add(joinbtn);

	}
}
