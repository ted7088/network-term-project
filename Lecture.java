package pack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;

public class Lecture extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lecture frame = new Lecture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Lecture() {
		setTitle("login");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// panel
		JPanel panel = new JPanel() {};
		panel.setBackground(Color.WHITE);
		placeLoginPanel(panel);
		getContentPane().add(panel);
		
		JButton pre = new JButton("");
		pre.setForeground(Color.WHITE);
		ImageIcon icon = new ImageIcon(Lecture.class.getResource("/pre.png"));
		Image originImg = icon.getImage();
		Image changedImg = originImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(changedImg);
		pre.setIcon(icon2);
		pre.setBounds(474, 620, 40, 40);
		pre.setBorderPainted(false);
		panel.add(pre);
		JLabel page = new JLabel("0/0");
		page.setForeground(Color.BLACK);
		page.setBackground(Color.WHITE);
		page.setBounds(535, 631, 40, 15);
		panel.add(page);
		
		JButton next = new JButton("");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page.setText("fuck");
				
			}
		});
		icon = new ImageIcon(Lecture.class.getResource("/next.png"));
		originImg = icon.getImage();
		changedImg = originImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(changedImg);
		next.setIcon(icon3);
		next.setBounds(587, 620, 40, 40);
		next.setBorderPainted(false);
		panel.add(next);
		
		
		
		JButton homeBtn = new JButton("");
		ImageIcon homeicon = new ImageIcon("home.png");
		Image temp = homeicon.getImage();
		Image temp1 = temp.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon homeIcon = new ImageIcon(temp1);
		homeBtn.setIcon(homeIcon);
		homeBtn.setBorderPainted(false);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if ((JButton) obj == homeBtn) {
					Chapter chapter = new Chapter();
					dispose();
				}
			}
		});
		homeBtn.setBounds(1032, 621, 40, 40);
		panel.add(homeBtn);
		
		JLabel lblLecture = new JLabel("LECTURE");
		lblLecture.setFont(new Font("具愁磊 具眉 B", Font.BOLD, 50));
		lblLecture.setBounds(12, 603, 267, 70);
		panel.add(lblLecture);
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {
		Font f2 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
		Color green = new Color(168, 209, 141);
		panel.setLayout(null);
		JPanel lectureImage = new JPanel();
		lectureImage.setBackground(Color.WHITE);
		lectureImage.setBounds(140, 10, 800, 600);
		lectureImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		lectureImage.setLayout(null);
		panel.add(lectureImage);

		Font f3 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);

		Font f4 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
	}
}
