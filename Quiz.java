package EDU_C;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.border.*;

public class Quiz extends JFrame {
	Color green = new Color(168, 209, 141); // refactor about color green
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lecture frame = new Lecture(); //start this 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JLabel qNewLabel = new JLabel("");
	BufferedReader in;
	PrintWriter out;
	int q; 
	int num = 1;
	int right = 0;
	String quizOrder;
	JLabel page = new JLabel();
	private JTextField textField;

	public Quiz() {
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
		q = Chapter.getQuiznum(); // get which lecture's quiz
		quizOrder = Chapter.getQuiz(q); // get which quiz we start
		setTitle("QUIZ");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		page.setText("1/3");
		JPanel panel = new JPanel() {
		};
		panel.setBackground(Color.WHITE);
		placeLoginPanel(panel);
		getContentPane().add(panel); 

		qNewLabel.setIcon(new ImageIcon(Quiz.class.getResource("/" + quizOrder))); // changes the quiz image as user solves the quiz
		JButton next = new JButton(""); // create next button

		page.setForeground(Color.BLACK);
		page.setBackground(Color.WHITE);
		page.setBounds(203, 638, 40, 15);
		panel.add(page);

		JButton homeBtn = new JButton(""); // create home button
		homeBtn.setIcon(new ImageIcon(Quiz.class.getResource("/home.png")));
		ImageIcon homeicon = new ImageIcon("/next.png");
		Image temp = homeicon.getImage();
		Image temp1 = temp.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon homeIcon = new ImageIcon(temp1);
		homeBtn.setBorderPainted(false);
		homeBtn.addActionListener(new ActionListener() { // when home button is clicked
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if ((JButton) obj == homeBtn) {
					Chapter chapter = new Chapter(); // return to chapter
					dispose();
				}
			}
		});
		homeBtn.setBounds(1032, 621, 40, 40);
		panel.add(homeBtn);

		JLabel qLecture = new JLabel("QUIZ");
		qLecture.setFont(new Font("야놀자 야체 B", Font.BOLD, 50)); // set font
		qLecture.setBounds(12, 603, 267, 70);
		panel.add(qLecture);

		textField = new JTextField(); // answer textfield
		textField.setBounds(333, 633, 396, 24);
		panel.add(textField);
		textField.setColumns(10);

		JButton submit = new JButton("제출"); // submit button
		submit.setBounds(731, 632, 105, 27);
		submit.setFont(new Font("야놀자 야체 B", Font.BOLD, 18));
		submit.setBackground(green);
		submit.setBorderPainted(false);
		panel.add(submit);
		setVisible(true);
		submit.addActionListener(new ActionListener() { // if submit button is clicked
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if ((JButton) obj == submit) {
					out.println("Answer/" + q + "/" + (Character.getNumericValue(quizOrder.charAt(3)) - 1) + "/"
							+ textField.getText().toString()); // send it to the server
					String result = null;
					try {
						result = in.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (result.equals("answer")) { // if it is answer
						JOptionPane.showMessageDialog(null, "정답입니다.");// make a pop-up box that it's correct
						out.println("Quiz" + q + "/" + quizOrder); // send to server to get next page's information
						try {
							quizOrder = in.readLine(); // next quiz's image
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						qNewLabel.setIcon(new ImageIcon(Lecture.class.getResource("/" + quizOrder)));
						num++;
						page.setText(Character.getNumericValue(quizOrder.charAt(3)) - 1 + "/3"); // which quiz we solving
						if (num == 4) { // if quiz finishes
							char[] c = new char[6];
							for (int i = 0; i < 6; i++) {
								c[i] = Client.chapter.charAt(i);
								if (i == q - 1)
									c[i] = '1';
							}
							Client.chapter = "";
							for (int i = 0; i < 6; i++)
								Client.chapter += c[i]; 
							out.println("done/" + Client.curID + "/password/name/" + q); // send to server that user finished which chapter
							Chapter newChap = new Chapter();
							dispose();
						}
					} else { // if the answer is wrong
						JOptionPane.showMessageDialog(null, "오답입니다."); // make a pop-up box that it's wrong
					}
					textField.setText("");
				}
			}
		});
	}

	public void placeLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JPanel quizImage = new JPanel();
		quizImage.setBackground(Color.WHITE);
		quizImage.setBounds(139, 0, 830, 626);
		quizImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, green, green));
		quizImage.setLayout(null);
		panel.add(quizImage);
		qNewLabel.setBounds(14, 12, 800, 600);
		quizImage.add(qNewLabel);
	}
}