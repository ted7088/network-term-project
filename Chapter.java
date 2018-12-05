package pack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
import java.net.Socket;
public class Chapter extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chapter frame = new Chapter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	BufferedReader in;
	PrintWriter out;
	public Chapter() {
		getContentPane().setBackground(Color.WHITE);
		
		
		setTitle("Chapter");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Chapter 5");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button.setBorderPainted(false);
		button.setBackground(new Color(168, 209, 141));
		button.setBounds(452, 380, 153, 34);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Chapter 1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lecture lec1 = new Lecture();
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_1.setBorderPainted(false);
		button_1.setBackground(new Color(168, 209, 141));
		button_1.setBounds(452, 204, 153, 34);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("QUIZ");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_2.setBorderPainted(false);
		button_2.setBackground(new Color(168, 209, 141));
		button_2.setBounds(612, 204, 78, 34);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("QUIZ");
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_3.setBorderPainted(false);
		button_3.setBackground(new Color(168, 209, 141));
		button_3.setBounds(612, 248, 78, 34);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Chapter 2");
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_4.setBorderPainted(false);
		button_4.setBackground(new Color(168, 209, 141));
		button_4.setBounds(452, 248, 153, 34);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("Chapter 3");
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_5.setBorderPainted(false);
		button_5.setBackground(new Color(168, 209, 141));
		button_5.setBounds(452, 292, 153, 34);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("QUIZ");
		button_6.setForeground(Color.WHITE);
		button_6.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_6.setBorderPainted(false);
		button_6.setBackground(new Color(168, 209, 141));
		button_6.setBounds(612, 292, 78, 34);
		getContentPane().add(button_6);
		
		JButton button_7 = new JButton("QUIZ");
		button_7.setForeground(Color.WHITE);
		button_7.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_7.setBorderPainted(false);
		button_7.setBackground(new Color(168, 209, 141));
		button_7.setBounds(612, 336, 78, 34);
		getContentPane().add(button_7);
		
		JButton button_8 = new JButton("Chapter 4");
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_8.setBorderPainted(false);
		button_8.setBackground(new Color(168, 209, 141));
		button_8.setBounds(452, 336, 153, 34);
		getContentPane().add(button_8);
		
		JButton button_9 = new JButton("QUIZ");
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_9.setBorderPainted(false);
		button_9.setBackground(new Color(168, 209, 141));
		button_9.setBounds(612, 380, 78, 34);
		getContentPane().add(button_9);
		
		JButton button_10 = new JButton("QUIZ");
		button_10.setForeground(Color.WHITE);
		button_10.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_10.setBorderPainted(false);
		button_10.setBackground(new Color(168, 209, 141));
		button_10.setBounds(612, 424, 78, 34);
		getContentPane().add(button_10);
		
		JButton button_11 = new JButton("Chapter 6");
		button_11.setForeground(Color.WHITE);
		button_11.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_11.setBorderPainted(false);
		button_11.setBackground(new Color(168, 209, 141));
		button_11.setBounds(452, 424, 153, 34);
		getContentPane().add(button_11);
		
		JButton button_12 = new JButton("Chapter 7");
		button_12.setForeground(Color.WHITE);
		button_12.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_12.setBorderPainted(false);
		button_12.setBackground(new Color(168, 209, 141));
		button_12.setBounds(452, 468, 153, 34);
		getContentPane().add(button_12);
		
		JButton button_13 = new JButton("QUIZ");
		button_13.setForeground(Color.WHITE);
		button_13.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		button_13.setBorderPainted(false);
		button_13.setBackground(new Color(168, 209, 141));
		button_13.setBounds(612, 468, 78, 34);
		getContentPane().add(button_13);
		Color green = new Color(168, 209, 141);
		Font f2 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);

		Font f3 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {

		Font f4 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
	}
}
