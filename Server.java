package EDU_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {

	private static final int PORT = 9001; // set port-number
	static Connection con = null;
	static String[] datas = new String[6]; // the string value to store client's query
	static String name;
	static String answers = "3/5/%c/5/2/++num/3/3/input%3/5/5/4/2/1/float/3/2/hello world/"; // quiz's answer values
	static String[] answer = answers.split("/");

	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(PORT); // make a socket to communicate with client
		try {
			con = DB.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while (true) {
				new Handler(listener.accept()).start(); // starts the threads
			}
		} finally {
			listener.close(); // ends the threads
		}
	}

	private static class Handler extends Thread {
		// declaration of variables
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		// we set threads to use
		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {

				in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // this function allows to
																							// listen what server has
																							// sent
				out = new PrintWriter(socket.getOutputStream(), true); // this function allows to send commands to
																		// clients
				while (true) {
					String input = in.readLine().toString(); // we read sentences from text box
					datas = input.split("/");
					if (datas[0].equalsIgnoreCase("login"))// case of login
					{
						try {
							String select = "select name, chapter from member where id = \"" + datas[1]
									+ "\" and pw = \"" + datas[2] + "\""; // make a SQL query that finds name and chapter information which ID and passwords matches
							
							Statement stmt = (Statement) con.createStatement(); // execute SQL query
							ResultSet rs = stmt.executeQuery(select); // get result of SQL query
							boolean exists = false;
							
							String chapter = "000000"; // default chapter value
							while (rs.next()) { // if resultset exists 
								name = rs.getString(1);
								chapter = rs.getString(2);
								if (rs.wasNull()) // 
									chapter = "000000";

								if (chapter != "000000") { // if the chapter value is not 000000 which tells it's not first time to visit EDU_C
									exists = true;
									break;
								}
							}

							if (exists) { //if there's right information about login information
								out.println("success/" + name + "/" + chapter); // tell to the client about login user's name and chpater value

							} else { // if the ID or password does not matches
								out.println("Invalid id or pw"); // tell to the client that there's no such ID or matching password
							}
							DB.close(stmt); 
							DB.close(rs);// close the SQL

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (datas[0].equalsIgnoreCase("Lecture1")) { // case of first lecture
						if (Character.getNumericValue(datas[1].charAt(3)) < 9)
							out.println("L1-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png"); // tell to the client about next page's information until it's not the last page
						else
							out.println("L1-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // if it is last page just send current page's information
					} else if (datas[0].equalsIgnoreCase("Lecture2")) { // case of second lecture
						if (Character.getNumericValue(datas[1].charAt(3)) < 9)
							out.println("L2-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("L2-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as lecture1
					} else if (datas[0].equalsIgnoreCase("Lecture3")) {// case of third lecture
						if (Character.getNumericValue(datas[1].charAt(3)) == 9)
							out.println("L3-10.png");
						else if (Character.getNumericValue(datas[1].charAt(4)) == 0)
							out.println("L3-11.png");
						else if (Character.getNumericValue(datas[1].charAt(3)) < 9)
							out.println("L3-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("L3-11.png"); // same as 1 & 2 but it's little bit different with the page's count
					} else if (datas[0].equalsIgnoreCase("Lecture4")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 9)
							out.println("L4-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("L4-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2 & 3
					} else if (datas[0].equalsIgnoreCase("Lecture5")) {
						if (Character.getNumericValue(datas[1].charAt(3)) == 9)
							out.println("L5-10.png");
						else if (Character.getNumericValue(datas[1].charAt(4)) == 0)
							out.println("L5-10.png");
						else if (Character.getNumericValue(datas[1].charAt(3)) != 9)
							out.println("L5-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("L5-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2 & 3 & 4
					} else if (datas[0].equalsIgnoreCase("Lecture6")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 9)
							out.println("L6-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("L6-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2& 3 & 4
					} else if (datas[0].equalsIgnoreCase("Quiz1")) { // case of first quiz

						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q1-" + Character.getNumericValue(datas[1].charAt(3) + 1) + ".png"); // send next quiz's page
						else
							out.println("Q1-" + Character.getNumericValue(datas[1].charAt(3)) + ".png");
					} else if (datas[0].equalsIgnoreCase("Quiz2")) {//case of second quiz
						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q2-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("Q2-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as quiz1
					} else if (datas[0].equalsIgnoreCase("Quiz3")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q3-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("Q3-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2
					} else if (datas[0].equalsIgnoreCase("Quiz4")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q4-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("Q4-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2 & 3
					} else if (datas[0].equalsIgnoreCase("Quiz5")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q5-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("Q5-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2 & 3 & 4
					} else if (datas[0].equalsIgnoreCase("Quiz6")) {
						if (Character.getNumericValue(datas[1].charAt(3)) < 3)
							out.println("Q6-" + (Character.getNumericValue(datas[1].charAt(3)) + 1) + ".png");
						else
							out.println("Q6-" + Character.getNumericValue(datas[1].charAt(3)) + ".png"); // same as 1 & 2 & 3 & 4 & 5
					}
					else if (datas[0].equalsIgnoreCase("Answer")) { // case of checks quiz's answer
						if (datas[3].equalsIgnoreCase(
								answer[(Integer.parseInt(datas[1]) - 1) * 3 + Integer.parseInt(datas[2])])) // if it matches with server's answer information
							out.println("answer"); // send clients that answer is right
						else
							out.println("wrong"); // else send clients that answer is wrong
					} else if (datas[0].equalsIgnoreCase("join"))// case of sign up
					{
						try {
							String select = "select * from member where id = " + "\"" + datas[1] + "\""; // make a SQL query that is there same ID
							Statement check = null;
							check = (Statement) con.createStatement(); // executes SQL query
							ResultSet rs = (ResultSet) check.executeQuery(select); // get the result
							boolean exists = false;
							while (rs.next()) { // if there's result about SQL query
								String name = rs.getString(1);
								if (rs.wasNull())
									name = "null";
								if (name != "null") { // if the name value is not null check that there's already exists same ID
									exists = true; 
									break;
								}
							}
							if (exists) { // if ID is same
								out.println("another"); // send to client that need another ID
							} else { // else
								String insert = "insert into member values(\"" + datas[1] + "\",\"" + datas[2] + "\",\""
										+ datas[3] + "\",\"000000\")";
								Statement stmt = con.createStatement();
								stmt.executeUpdate(insert); // update at Database
								DB.close(stmt);
								out.println("Welcome"); // send to client that welcome
							}
							DB.close(check);
							DB.close(rs);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (datas[0].equalsIgnoreCase("done"))//case of complete of chapter
					{
						try {
							String select = "select chapter from member where id = \"" + datas[1] + "\"";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(select); // get chapter information from database
							String chapter = "";
							while (rs.next()) {
								chapter = rs.getString(1);
							}

							char[] changed = new char[6];
							for (int i = 0; i < 6; i++) { // change the Chapter information
								if (i == Integer.parseInt(datas[4]) - 1)
									changed[i] = '1';
								else
									changed[i] = chapter.charAt(i);
							}

							String change = "";
							for (int i = 0; i < 6; i++) {
								change += Character.toString(changed[i]);
							}

							String update = "update member set chapter = \"" + change + "\" where id = \"" + datas[1]
									+ "\"";
							Statement stmt2 = con.createStatement();
							stmt2.executeUpdate(update); // update database with changed chapter information
							DB.close(stmt2);
							DB.close(rs);
							DB.close(stmt);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
			} finally { 
				try { socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
