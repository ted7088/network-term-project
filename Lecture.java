package EDU_C;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.border.*;

public class Lecture extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lecture frame = new Lecture(); // start this
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JLabel lblNewLabel = new JLabel("");
	BufferedReader in;
	PrintWriter out;
	int lec;
	int num = 1;
	String lecOrder;
	JLabel page = new JLabel();

	public Lecture() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9001); // start socket communication
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lec = Chapter.getLecnum();
		lecOrder = Chapter.getLecture(lec);
		setTitle("EDU-C");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (lec == 5) // if lecture 5
			page.setText("1/10"); // there's 10 image of lecture 5
		else if (lec == 3) // if lecture 3
			page.setText("1/11"); // there's 11 image of lecture 3
		else // else there's 9 image of lecture
			page.setText("1/9");
		JPanel panel = new JPanel() {
		};
		panel.setBackground(Color.WHITE);
		placeLoginPanel(panel);
		getContentPane().add(panel);
		lblNewLabel.setIcon(new ImageIcon(Lecture.class.getResource("/lecture/" + lecOrder))); // changes the image of lecture 
		JButton next = new JButton(""); // make next button
		next.addActionListener(new ActionListener() { // if the next button clicked
			public void actionPerformed(ActionEvent e) {
				out.println("Lecture" + lec + "/" + lecOrder); // send to server which lecture images user read
				try {
					lecOrder = in.readLine(); // get from server that which lecture images to show
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				lblNewLabel.setIcon(new ImageIcon(Lecture.class.getResource("/lecture/" + lecOrder)));
				num++;
				if (lec == 5) {// if lecture 5
					if (num == 11) {  // finishes to read lecture
						Chapter newChap = new Chapter(); // go back to home
						dispose();
					} else if ((Character.getNumericValue(lecOrder.charAt(4)) == 0))
						page.setText("10/10");
					else
						page.setText(Character.getNumericValue(lecOrder.charAt(3)) + "/10");
				} else if (lec == 3) { // if lecture 3
					if (num == 12) { // finishes to read lecture
						Chapter newChap = new Chapter(); // go back to home
						dispose();
					} else if ((Character.getNumericValue(lecOrder.charAt(3)) == 1)
							&& (Character.getNumericValue(lecOrder.charAt(4)) == 0))
						page.setText("10/11");
					else if ((Character.getNumericValue(lecOrder.charAt(3)) == 1)
							&& (Character.getNumericValue(lecOrder.charAt(4)) == 1))
						page.setText("11/11");
					else
						page.setText(Character.getNumericValue(lecOrder.charAt(3)) + "/11");
				} else { // if other lecutres
					page.setText(Character.getNumericValue(lecOrder.charAt(3)) + "/9");
					if (num == 10) { // finishes to read lecture
						Chapter newChap = new Chapter(); // go back to home
						dispose();
					}
				}
			}
		});
		next.setIcon(new ImageIcon(Lecture.class.getResource("/next.png")));
		next.setBounds(900, 622, 40, 40);
		next.setBorderPainted(false);
		panel.add(next);

		page.setForeground(Color.BLACK);
		page.setBackground(Color.WHITE);
		page.setBounds(203, 638, 40, 15);
		panel.add(page);

		JButton homeBtn = new JButton(""); // make a home button
		ImageIcon homeicon = new ImageIcon("/next.png");
		Image temp = homeicon.getImage();
		Image temp1 = temp.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon homeIcon = new ImageIcon(temp1);
		homeBtn.setIcon(new ImageIcon(Lecture.class.getResource("/home.png")));
		homeBtn.setBorderPainted(false);
		homeBtn.addActionListener(new ActionListener() { // if home button clicked
			public void actionPerformed(ActionEvent e) { 
				Object obj = e.getSource();
				if ((JButton) obj == homeBtn) {
					Chapter chapter = new Chapter(); // go back to chapter
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
		JPanel lectureImage = new JPanel(); // about lecture image's panel
		lectureImage.setBackground(Color.WHITE);
		lectureImage.setBounds(139, 9, 830, 605);
		lectureImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		lectureImage.setLayout(null);
		panel.add(lectureImage);
		lblNewLabel.setBounds(14, 12, 800, 581);
		lectureImage.add(lblNewLabel);

		Font f3 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);

		Font f4 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
	}
}