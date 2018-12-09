package EDU_C;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
import java.net.Socket;
import java.net.UnknownHostException;

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
	static int lecnum = 0; // about which chapter chosen
	static String lecture6 = ""; // about which lecture page is read
	static String lecture5 = "";
	static String lecture4 = "";
	static String lecture3 = "";
	static String lecture2 = "";
	static String lecture1 = "";
	static int quiznum = 0; // about which quiz chosen
	static String quiz6 = ""; // about which quiz page is solved
	static String quiz5 = "";
	static String quiz4 = "";
	static String quiz3 = "";
	static String quiz2 = "";
	static String quiz1 = "";

	static String getLecture(int i) { // this function is used at Lecture that which pages to show
		switch (i) {
		case 1:
			return lecture1;
		case 2:
			return lecture2;
		case 3:
			return lecture3;
		case 4:
			return lecture4;
		case 5:
			return lecture5;
		case 6:
			return lecture6;
		default:
			return "";
		}
	}

	static int getQuiznum() { // this function is used at Lecture which lecture is read
		return quiznum;
	}

	static String getQuiz(int i) { // this function is used at Quiz which quiz images to show
		switch (i) {
		case 1:
			return quiz1;
		case 2:
			return quiz2;
		case 3:
			return quiz3;
		case 4:
			return quiz4;
		case 5:
			return quiz5;
		case 6:
			return quiz6;
		default:
			return "MhongJae";
		}
	}

	static int getLecnum() { // this function is used at Quiz which quiz is solved
		return lecnum;
	}

	public Chapter() {

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
		getContentPane().setBackground(Color.WHITE);

		setTitle("Chapter");
		setSize(1100, 700);
		setResizable(false);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		

		JButton Chap1 = new JButton("Lecture"); // make a first lecture button 
		Chap1.addActionListener(new ActionListener() { // if first lecture button is clicked
			public void actionPerformed(ActionEvent e) {
				out.println("Lecture1/L1-0.png"); // tell to the server that client is using 1st lecture
				try {
					lecture1 = in.readLine(); // receive from server that which image client have to show
					lecnum = Character.getNumericValue(lecture1.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec1 = new Lecture(); // start lecture 1
				dispose();
			}
		});
		JLabel lblNewLabel = new JLabel(Client.name);
		lblNewLabel.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
		lblNewLabel.setBounds(105, 12, 62, 34);
		getContentPane().add(lblNewLabel);
		Chap1.setForeground(Color.WHITE);
		Chap1.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap1.setBorderPainted(false);
		Chap1.setBackground(new Color(168, 209, 141));
		Chap1.setBounds(471, 275, 153, 34);
		getContentPane().add(Chap1);

		JButton Chap2 = new JButton("Lecture"); // same as lecture1
		Chap2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Lecture2/L2-0.png");
				try {
					lecture2 = in.readLine();
					lecnum = Character.getNumericValue(lecture2.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec2 = new Lecture();
				dispose();
			}
		});

		JButton Quiz1 = new JButton("QUIZ"); // make a first quiz button
		Quiz1.setForeground(Color.WHITE);
		Quiz1.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz1.setBorderPainted(false);
		Quiz1.setBackground(new Color(168, 209, 141));
		Quiz1.setBounds(631, 275, 78, 34);
		getContentPane().add(Quiz1);
		Quiz1.addActionListener(new ActionListener() { // if first quiz button is clicked
			public void actionPerformed(ActionEvent e) {
				out.println("Quiz1/Q1-0.png"); // send to server that first quiz is chosen
				try {
					quiz1 = in.readLine(); // receive from server that which quiz have to show
					quiznum = Character.getNumericValue(quiz1.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz1 = new Quiz(); // starts quiz
				dispose();
			}
		});

		Chap2.setForeground(Color.WHITE);
		Chap2.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap2.setBorderPainted(false);
		Chap2.setBackground(new Color(168, 209, 141));
		Chap2.setBounds(471, 319, 153, 34);
		getContentPane().add(Chap2);

		JButton Chap3 = new JButton("Lecture"); // same as first lecture
		Chap3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Lecture3/L3-0.png");

				try {
					lecture3 = in.readLine();
					lecnum = Character.getNumericValue(lecture3.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec3 = new Lecture();
				dispose();
			}
		});

		if (Client.chapter.charAt(0) == '0') { // if user's chapter1 value is 0, it means that user didn't solved 1st quiz
			JLabel progress_1 = new JLabel("Not Done"); 
			progress_1.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_1.setBounds(722, 275, 97, 33);
			getContentPane().add(progress_1);
		} else {
			JLabel progress_1 = new JLabel("Done");
			progress_1.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_1.setBounds(722, 275, 97, 33);
			getContentPane().add(progress_1);
		}
		if (Client.chapter.charAt(1) == '0') { // same as chpater 1
			JLabel progress_2 = new JLabel("Not Done");
			progress_2.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_2.setBounds(722, 319, 97, 33);
			getContentPane().add(progress_2);
		} else {
			JLabel progress_2 = new JLabel("Done");
			progress_2.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_2.setBounds(722, 319, 97, 33);
			getContentPane().add(progress_2);
		}
		if (Client.chapter.charAt(2) == '0') { // same as chapter 1
			JLabel progress_3 = new JLabel("Not Done");
			progress_3.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_3.setBounds(722, 363, 97, 33);
			getContentPane().add(progress_3);
		} else {
			JLabel progress_3 = new JLabel("Done");
			progress_3.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_3.setBounds(722, 363, 97, 33);
			getContentPane().add(progress_3);
		}
		if (Client.chapter.charAt(3) == '0') { // same as chapter 1
			JLabel progress_4 = new JLabel("Not Done");
			progress_4.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_4.setBounds(722, 407, 97, 33);
			getContentPane().add(progress_4);
		} else {
			JLabel progress_4 = new JLabel("Done");
			progress_4.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_4.setBounds(722, 407, 97, 33);
			getContentPane().add(progress_4);
		}
		if (Client.chapter.charAt(4) == '0') { // same as chapter 1
			JLabel progress_5 = new JLabel("Not Done");
			progress_5.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_5.setBounds(722, 451, 97, 33);
			getContentPane().add(progress_5);
		} else {
			JLabel progress_5 = new JLabel("Done");
			progress_5.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_5.setBounds(722, 451, 97, 33);
			getContentPane().add(progress_5);
		}
		if (Client.chapter.charAt(5) == '0') { // same as chapter 1
			JLabel progress_6 = new JLabel("Not Done");
			progress_6.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_6.setBounds(722, 491, 97, 33);
			getContentPane().add(progress_6);
		} else {
			JLabel progress_6 = new JLabel("Done");
			progress_6.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
			progress_6.setBounds(722, 491, 97, 33);
			getContentPane().add(progress_6);
		}
		JButton Quiz2 = new JButton("QUIZ"); // same as quiz 1
		Quiz2.setForeground(Color.WHITE);
		Quiz2.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz2.setBorderPainted(false);
		Quiz2.setBackground(new Color(168, 209, 141));
		Quiz2.setBounds(631, 319, 78, 34);
		getContentPane().add(Quiz2);
		Quiz2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Quiz2/Q2-0.png");

				try {
					quiz2 = in.readLine();
					quiznum = Character.getNumericValue(quiz2.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz32000 = new Quiz();
				dispose();
			}
		});

		Chap3.setForeground(Color.WHITE);
		Chap3.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap3.setBorderPainted(false);
		Chap3.setBackground(new Color(168, 209, 141));
		Chap3.setBounds(471, 363, 153, 34);
		getContentPane().add(Chap3);

		JButton Quiz3 = new JButton("QUIZ"); 
		Quiz3.setForeground(Color.WHITE);
		Quiz3.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz3.setBorderPainted(false);
		Quiz3.setBackground(new Color(168, 209, 141));
		Quiz3.setBounds(631, 363, 78, 34);
		getContentPane().add(Quiz3);
		Quiz3.addActionListener(new ActionListener() { // same as quiz 1
			public void actionPerformed(ActionEvent e) {

				out.println("Quiz3/Q3-0.png");

				try {
					quiz3 = in.readLine();
					quiznum = Character.getNumericValue(quiz3.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz65000 = new Quiz();
				dispose();
			}
		});
		
		JButton Chap5 = new JButton("Lecture");
		Chap5.addActionListener(new ActionListener() { // same as lecture1
			public void actionPerformed(ActionEvent e) {

				out.println("Lecture5/L5-0.png");
				try {
					lecture5 = in.readLine();
					lecnum = Character.getNumericValue(lecture5.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec5 = new Lecture();
				dispose();
			}
		});
		
		JButton Chap4 = new JButton("Lecture"); // same as lecture 1
		Chap4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Lecture4/L4-0.png");
				try {
					lecture4 = in.readLine();
					lecnum = Character.getNumericValue(lecture4.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec4 = new Lecture();
				dispose();
			}
		});
		Chap4.setForeground(Color.WHITE);
		Chap4.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap4.setBorderPainted(false);
		Chap4.setBackground(new Color(168, 209, 141));
		Chap4.setBounds(471, 407, 153, 34);
		getContentPane().add(Chap4);

		JButton Quiz4 = new JButton("QUIZ");

		Quiz4.setForeground(Color.WHITE);
		Quiz4.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz4.setBorderPainted(false);
		Quiz4.setBackground(new Color(168, 209, 141));
		Quiz4.setBounds(631, 407, 78, 34);
		getContentPane().add(Quiz4);
		Quiz4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // same as quiz 1

				out.println("Quiz4/Q4-0.png");

				try {
					quiz4 = in.readLine();
					quiznum = Character.getNumericValue(quiz4.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz4 = new Quiz();
				dispose();
			}
		});

		Chap5.setForeground(Color.WHITE);
		Chap5.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap5.setBorderPainted(false);
		Chap5.setBackground(new Color(168, 209, 141));
		Chap5.setBounds(471, 451, 153, 34);
		getContentPane().add(Chap5);

		JButton Quiz5 = new JButton("QUIZ");
		Quiz5.setForeground(Color.WHITE);
		Quiz5.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz5.setBorderPainted(false);
		Quiz5.setBackground(new Color(168, 209, 141));
		Quiz5.setBounds(631, 451, 78, 34);
		getContentPane().add(Quiz5);
		Quiz5.addActionListener(new ActionListener() { // same as quiz 1
			public void actionPerformed(ActionEvent e) {

				out.println("Quiz5/Q5-0.png");

				try {
					quiz5 = in.readLine();
					quiznum = Character.getNumericValue(quiz5.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz31212 = new Quiz();
				dispose();
			}
		});

		JButton Chap6 = new JButton("Lecture"); // same as lecture 1
		Chap6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Lecture6/L6-0.png");
				try {
					lecture6 = in.readLine();
					lecnum = Character.getNumericValue(lecture6.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Lecture lec6 = new Lecture();
				dispose();
			}
		});
		Chap6.setForeground(Color.WHITE);
		Chap6.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Chap6.setBorderPainted(false);
		Chap6.setBackground(new Color(168, 209, 141));
		Chap6.setBounds(471, 495, 153, 34);
		getContentPane().add(Chap6);

		JButton Quiz6 = new JButton("QUIZ");
		Quiz6.setForeground(Color.WHITE);
		Quiz6.setFont(new Font("具愁磊 具眉 R", Font.BOLD, 20));
		Quiz6.setBorderPainted(false);
		Quiz6.setBackground(new Color(168, 209, 141));
		Quiz6.setBounds(631, 495, 78, 34);
		getContentPane().add(Quiz6); // same as quiz 1
		Quiz6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Quiz6/Q6-0.png");

				try {
					quiz6 = in.readLine();
					quiznum = Character.getNumericValue(quiz6.charAt(1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Quiz quiz3121323333333 = new Quiz();
				dispose();
			}
		});

		JLabel lblChapter = new JLabel("Chapter 1 :");
		lblChapter.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter.setBounds(321, 275, 128, 34);
		getContentPane().add(lblChapter);

		JLabel lblChapter_1 = new JLabel("Chapter 2 :");
		lblChapter_1.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter_1.setBounds(321, 321, 138, 24);
		getContentPane().add(lblChapter_1);

		JLabel lblChapter_2 = new JLabel("Chapter 3 :");
		lblChapter_2.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter_2.setBounds(321, 365, 138, 24);
		getContentPane().add(lblChapter_2);

		JLabel lblChapter_3 = new JLabel("Chapter 4 :");
		lblChapter_3.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter_3.setBounds(321, 409, 138, 25);
		getContentPane().add(lblChapter_3);

		JLabel lblChapter_4 = new JLabel("Chapter 5 :");
		lblChapter_4.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter_4.setBounds(321, 453, 151, 25);
		getContentPane().add(lblChapter_4);

		JLabel lblChapter_5 = new JLabel("Chapter 6 :");
		lblChapter_5.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 30));
		lblChapter_5.setBounds(321, 497, 138, 25);
		getContentPane().add(lblChapter_5);

		JLabel label = new JLabel("\uC5B4\uC11C\uC624\uC138\uC694                 \uB2D8");
		label.setFont(new Font("具愁磊 具眉 R", Font.PLAIN, 25));
		label.setBounds(14, 12, 184, 34);
		getContentPane().add(label);

		Color green = new Color(168, 209, 141);
		Font f2 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
		Font f3 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {
		Font f4 = new Font("具愁磊 具眉 Regular", Font.BOLD, 20);
	}
}