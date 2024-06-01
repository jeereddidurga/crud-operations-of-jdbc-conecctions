package applications;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class crudoperations {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "110845@Durga";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ch;
		do {
			operations();
			System.out.println("Enter Operation number");
			ch = Integer.parseInt(sc.nextLine());

			switch (ch) {
			case 1: {

				createdatabse();
				break;
			}
			case 2:
				createtable();
				break;
			case 3:
				insertion();
				break;
			case 4:
				update();
				break;
			case 5:
				deletion();
				break;
			case 6:
				getall();
				break;
			case 7:
				getbyid();
				break;
			case 8:
				droptable();
				break;
			case 9:
				dropdatabase();
				break;
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
			}

		} while (ch > 0);
	}

	private static void dropdatabase() {
		String url = "jdbc:mysql://localhost:3306/";
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter Database Name");
			String sql = "drop database " + sc.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("database dropped");
			} else {
				System.out.println("Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void droptable() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/" + sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "drop table " + sc.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("Table dropped");
			} else {
				System.out.println("Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getbyid() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/" + sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "select * from " + sc.next() + " where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter employee id");
			pmst.setInt(1, sc.nextInt());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println(
						"id" + rs.getInt("id") + "name" + rs.getString("name") + "email" + rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getall() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/" + sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "select * from " + sc.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("ID:" + rs.getInt("id"));
				System.out.println("NAME:" + rs.getString("name"));
				System.out.println("EMAIL:" + rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void deletion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/" + sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "delete from " + sc.next() + " where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, sc.nextInt());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Deleted");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void update() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name:");
			String sql = "update " + sc.next() + " set name = ?, email = ? where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter name:");
			pmst.setString(1, sc.next());
			System.out.println("Enter email:");
			pmst.setString(2, sc.next());
			System.out.println("Enter Id:");
			pmst.setInt(3, sc.nextInt());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Successfully Updated");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/" + sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "insert into " + sc.next() + "(id,name,email) values (?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, sc.nextInt());
			System.out.println("Enter name");
			pmst.setString(2, sc.next());
			System.out.println("Enter email");
			pmst.setString(3, sc.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Inserted");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void createtable() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "create table "+ sc.next() + "(id int,name varchar(20),email varchar(30))";
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("Table created");
			}
			else {
				System.out.println("Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		
	}
	}

	private static void createdatabse() {
		String url = "jdbc:mysql://localhost:3306/";
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
			Connection conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter Database name:");
			String sql = "create database " +sc.next();
			PreparedStatement pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Successfully Created");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void operations() {
		System.out.println("Operations List");
		System.out.println("\t1 .Create database");
		System.out.println("\t2 .Create table");
		System.out.println("\t3 .Insert data into Table");
		System.out.println("\t4 .Update data");
		System.out.println("\t5 .Delete data from table");
		System.out.println("\t6 .Fetch all records from table");
		System.out.println("\t7 .Get record by id from table");
		System.out.println("\t8 .Delete table");
		System.out.println("\t9.Delete database");
		System.out.println("\t10 .Terminate process");

	}

}