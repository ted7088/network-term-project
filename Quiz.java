package pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Quiz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz frame = new Quiz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Quiz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Color green = new Color(158,209,121);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(140, 10, 800, 600);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		contentPane.add(panel);
		ImageIcon homeicon = new ImageIcon("home.png");
		Image temp = homeicon.getImage();
		Image temp1 = temp.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon homeIcon = new ImageIcon(temp1);
		
		JButton homeBtn = new JButton("");
		contentPane.add(homeBtn);
		homeBtn.setIcon(homeIcon);
		homeBtn.setBorderPainted(false);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		homeBtn.setBounds(1016, 611, 40, 40);
		
		JLabel lblLecture = new JLabel("QUIZ");
		lblLecture.setFont(new Font("具愁磊 具眉 B", Font.BOLD, 50));
		lblLecture.setBounds(12, 603, 267, 70);
		contentPane.add(lblLecture);
		setVisible(true);
		
		JButton pre = new JButton("");
		pre.setForeground(Color.WHITE);
		ImageIcon icon = new ImageIcon(Lecture.class.getResource("/pre.png"));
		Image originImg = icon.getImage();
		Image changedImg = originImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(changedImg);
		pre.setIcon(icon2);
		pre.setBounds(398, 615, 40, 40);
		pre.setBorderPainted(false);
		contentPane.add(pre);
		
		JButton next = new JButton("");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		icon = new ImageIcon(Lecture.class.getResource("/next.png"));
		originImg = icon.getImage();
		changedImg = originImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(changedImg);
		next.setIcon(icon3);
		next.setBounds(653, 614, 40, 40);
		next.setBorderPainted(false);
		contentPane.add(next);
		
		JButton num1 = new JButton("1");
		num1.setForeground(Color.WHITE);
		num1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		num1.setBackground(green);
		num1.setFont(new Font("具愁磊 具眉 B", Font.BOLD, 19));
		num1.setBorderPainted(false);
		num1.setBounds(459, 611, 40, 40);
		contentPane.add(num1);
		
		JButton num2 = new JButton("2");
		num2.setForeground(Color.WHITE);
		num2.setFont(new Font("具愁磊 具眉 B", Font.BOLD, 15));
		num2.setBounds(522, 611, 40, 40);
		num2.setBackground(green);
		num2.setBorderPainted(false);
		contentPane.add(num2);
		
		JButton num3 = new JButton("3");
		num3.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 12));
		num3.setForeground(Color.WHITE);
		num3.setBounds(585, 611, 40, 40);
		num3.setBackground(green);
		num3.setBorderPainted(false);
		contentPane.add(num3);
		
	}
	
	
}
