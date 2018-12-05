package test;

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

/*
 * Main class for server
 * to process user's login/join/progress update by connecting database
 * give success message and current progress to client when login succeed
 * give welcome message to client when join succeed
 * give error message to client when login/join failed
 * */
public class Server {

	private static final int PORT = 9001;

	// to connect with database
	static Connection con = null;

	// to split user's message
	static String[] datas = new String[6];

	public static void main(String[] args) throws Exception {

		// make a socket to connect with client
		ServerSocket listener = new ServerSocket(PORT);

		// make a connection with database by using DB class's method
		try {
			con = DB.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// wait for client and process client's order
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close(); // ends the threads
		}
	}

	private static class Handler extends Thread {

		// socket for connection with client
		private Socket socket;

		// to hear/to talk to client
		private BufferedReader in;
		private PrintWriter out;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		/*
		 * runs operations(processing user's join&login)
		 */
		public void run() {
			try {
				// talk to/hear client
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// keep this work
				while (true) {

					// store message from client into input
					// message is formed with header/id/pw/name/chapter
					String input = in.readLine().toString();

					// split input message with '/' token and store it into datas array
					// datas[0] = header
					// datas[1] = id
					// datas[2] = pw
					// datas[3] = name
					// datas[4] = chapter
					datas = input.split("/");

					// if header is login
					if (datas[0].equalsIgnoreCase("login")) {
						/*
						 * check is there same id and pw in database store current progress chapter data
						 * into chapter variable and give it to client if there is member in database
						 * give error message to client if there is no member in database chapter data
						 * is formed with 6 numbers. 0 for not studied one, 1 for studied one
						 */
						try {
							// execute query that checks is member in database and store result tuple into
							// rs
							String select = "select chapter from member where id = \"" + datas[1] + "\" and pw = \""
									+ datas[2] + "\"";
							Statement stmt = (Statement) con.createStatement();
							ResultSet rs = stmt.executeQuery(select);

							// to store chapter data that is in database
							String chapter = "------";
							while (rs.next()) {
								chapter = rs.getString(1);
								if (rs.wasNull())
									chapter = "------";
								// tell client that login succeed if there is member information in database
								// that matches with input
								if (chapter != "------") {
									out.println("success/" + chapter);
									break;
								}
							}
							// tell client that input is invalid if there's no member in database
							if (chapter == "------")
								out.println("Invalid id or pw");

							DB.close(stmt);
							DB.close(rs);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// if header is join
					else if (datas[0].equalsIgnoreCase("join")) {
						/*
						 * check is there same id in database store user's input data into database if
						 * there is no member in database give error message to client if there is
						 * member who has same id in database already
						 */
						try {
							// execute query that checks is member who has same id in database and store result tuple into rs
							String select = "select * from member where id = " + "\"" + datas[1] + "\"";
							Statement check = (Statement) con.createStatement();
							ResultSet rs = (ResultSet) check.executeQuery(select);

							String id = rs.getString(1);
							while (rs.next()) {
								if (rs.wasNull())
									id = "----";
								// tell client that there is same id if there is same id
								if (id != "----") {
									out.println("need another ID");
									break;
								}
							}
							// if there is no same id in database
							if (id == "----") {
								// execute query that inserts new member in database and tell client welcome
								String insert = "insert into member values(\"" + datas[1] + "\",\"" + datas[2] + "\",\""
										+ datas[3] + "\",\"000000\")";
								Statement stmt = con.createStatement();
								stmt.executeUpdate(insert);
								DB.close(stmt);
								out.println("Welcome");
							}

							DB.close(check);
							DB.close(rs);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					/* if header is done(user finished to study lecture)
					 * chapter information that client passed formed like 1 when user finished
					 * chapter 1, 2 when user finished chapter 2
					 * */
					else if (datas[0].equalsIgnoreCase("done"))
					{

						/*
						 * search for user in database and update that tuple
						 * */
						try {
							// execute query that gets user's current chapter progress and store it into chapter variable
							String select = "select chapter from member where id = \"" + datas[1] + "\"";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(select);
							String chapter = "";
							while (rs.next()) {
								chapter = rs.getString(1);
							}

							// change bit to 1 which means the chapter that user finished
							char[] changed = new char[6];
							for (int i = 0; i < 6; i++) {
								if (i == Integer.parseInt(datas[4]) - 1)
									changed[i] = '1';
								else
									changed[i] = chapter.charAt(i);
							}

							// merge changed character array into a string
							String change = "";
							for (int i = 0; i < 6; i++) {
								change += Character.toString(changed[i]);
							}

							// execute query that updates user's current progress 
							String update = "update member set chapter = \"" + change + "\" where id = \"" + datas[1]
									+ "\"";
							stmt = con.createStatement();
							stmt.executeUpdate(update);
							
							DB.close(stmt);
							DB.close(rs);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			} finally {
				try {
					//close user's socket when user closed 
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
