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

public class Login {

    private static final int PORT = 9001; // set port-number
    static Connection con = null;
    static String[] datas = new String[6];
    
   
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT); // make a socket to communicate with client
        try {
            con = DB.getConnection();
         }catch(Exception e) {
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
        //declaration of variables
    	private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // we set threads to use
        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                socket.getInputStream())); // this function allows to listen what server has sent
                out = new PrintWriter(socket.getOutputStream(), true); // this function allows to send commands to clients
                while (true) {          	
                    String input = in.readLine().toString(); // we read sentences from text box
                    datas = input.split("/");
                    if(datas[0].equalsIgnoreCase("login"))//�α���
                    {
                       try {
                    	   System.out.println(datas[0]+datas[1]+datas[2]+datas[3]);
                          String select = "select chapter from member where id = \"" + datas[1] + "\"" ;
                          Statement stmt = (Statement)con.createStatement();
                          ResultSet rs = stmt.executeQuery(select);
                          boolean exists = false;
                          String chapter = "null";
                          while(rs.next()) {
                             chapter = rs.getString(1);
                             if(rs.wasNull()) chapter = "null";
                 
                             if(chapter != "null")
                             {
                                exists = true;
                                break;
                             }
                          }
                        if(exists)
                            out.println("success/" + chapter);
                        else
                        	out.println("Invalid id or pw");
                          DB.close(stmt);
                          DB.close(rs);
                          
                       } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture1")) {
                    	System.out.println("hi");
                    	if(Character.getNumericValue(datas[1].charAt(3))<9)
                    		out.println("L1-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");
                    	else
                    		out.println("L1-"+Character.getNumericValue(datas[1].charAt(3))+".png");
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture2")) {
                    	if(Character.getNumericValue(datas[1].charAt(3))<9)
                    		out.println("L2-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");
                    	else
                    		out.println("L2-"+Character.getNumericValue(datas[1].charAt(3))+".png");
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture3")) {
                    	if(Character.getNumericValue(datas[1].charAt(3))==9)
                    		out.println("L3-10.png");
                    	else if(Character.getNumericValue(datas[1].charAt(4))==0)
                    		out.println("L3-11.png");
                    	else if(Character.getNumericValue(datas[1].charAt(3))<9)
                    		out.println("L3-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");	
                    	else
                    		out.println("L3-11.png");
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture4")) {
                    	if(Character.getNumericValue(datas[1].charAt(3))<9)
                    		out.println("L4-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");
                    	else
                    		out.println("L4-"+Character.getNumericValue(datas[1].charAt(3))+".png");
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture5")) {
                    	if(Character.getNumericValue(datas[1].charAt(3))==9)
                    		out.println("L5-10.png");
                    	else if(Character.getNumericValue(datas[1].charAt(4))==0)
                    		out.println("L5-10.png");
                    	else if(Character.getNumericValue(datas[1].charAt(3))!=9)
                    		out.println("L5-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");	
                    	else
                    		out.println("L5-"+Character.getNumericValue(datas[1].charAt(3))+".png");
                    }
                    else if(datas[0].equalsIgnoreCase("Lecture6")) {
                    	if(Character.getNumericValue(datas[1].charAt(3))<9)
                    		out.println("L6-"+(Character.getNumericValue(datas[1].charAt(3))+1)+".png");
                    	else
                    		out.println("L6-"+Character.getNumericValue(datas[1].charAt(3))+".png");
                    }
                    else if(datas[0].equalsIgnoreCase("join"))//ȸ������
                    {
                        //��� datas[1][2][3]�� ����ְ� chapter���� 0�� ����.
                        try {
                         String select = "select * from member where id = " + "\""+datas[1]+"\"";
                         Statement check = null;
                         check = (Statement)con.createStatement();
                         ResultSet rs = (ResultSet)check.executeQuery(select);
                         boolean exists = false;
                         System.out.println(datas[0]);
                         while(rs.next()) {
                            String name = rs.getString(1);
                            if(rs.wasNull()) name = "null";
                            if(name != "null")
                            {   
                               exists = true;
                               break;
                            }
                         }
                         if(exists) {
                        	 out.println("another");
                         }
                         else
                             {
                               String insert = "insert into member values(\"" + datas[1] + "\",\"" + datas[2] + "\",\"" + datas[3] + "\",\"000000\")";
                               Statement stmt = con.createStatement();
                               stmt.executeUpdate(insert);
                               DB.close(stmt);
                               out.println("Welcome");
                             }
                            DB.close(check);
                          DB.close(rs);
                      } catch (SQLException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                      }
                     }
                    else if(datas[0].equalsIgnoreCase("done"))//é�� �Ϸ� �޼���
                    {
                       //datas[1]�� ��񿡼� ��Ī�Ǵ� ���� chapter�κп� datas[4]�� �߰�
                       try {
                          //datas[4]�� �״�� �Է��ϱ� ������ Ŭ���̾�Ʈ������ ó���� ���� chapter ��Ʈ���� �״�� �Ϸ��� é�͸� �̾�ٿ��� �Ѱ������
                          String select = "select chapter from member where id = \"" + datas[1] + "\"";
                          Statement stmt = con.createStatement();
                          ResultSet rs = stmt.executeQuery(select);
                          String chapter = "";
                          while(rs.next()) {
                             chapter = rs.getString(1);
                          }
                          
                          char[] changed = new char[6];
                          for(int i = 0; i < 6; i++) {
                             if(i == Integer.parseInt(datas[4]) - 1)
                                changed[i] = '1';
                             else
                                changed[i] = chapter.charAt(i);
                          }
                          
                          String change = "";
                          for(int i = 0; i < 6; i++) {
                             change += Character.toString(changed[i]);
                          }
                          
                          String update = "update member set chapter = \"" + change + "\" where id = \"" + datas[1] + "\"";
                          Statement stmt2 = con.createStatement();
                        stmt2.executeUpdate(update);
                        DB.close(stmt2);
                        DB.close(rs);
                        DB.close(stmt);
                        System.out.println("Update success");
                     }catch (SQLException e) {
                        e.printStackTrace();
                     }
                }
               } 
            }catch (IOException e) {
                System.out.println(e);
            } finally { // when we close the chatting bo
                try { // we close the socket of closed user's
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
