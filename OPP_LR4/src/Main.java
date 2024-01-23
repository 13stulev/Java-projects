import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main {

	
	public static void main(String[] args) throws SQLException {
		Connection conn;
		Statement statement;
		PreparedStatement st;
		try{
			String url = "jdbc:mysql://localhost/customers";
            String username = "root";
            String password = "12345";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to Store DB succesfull!");
            statement = conn.createStatement();
		Scanner in = new Scanner(System.in);
		int customerid;
		boolean quitting = false;
		ResultSet result;
		String id;
		while(!quitting) {
        	System.out.println("Введите число где: 1 - вывод строк таблиц;\n 2 - добавить запись во вторую таблицу;\n 3 - добавить запись в первую таблицу;\n 4 - удалить запись из второй таблицы;\n 5 - удалить запись из первой таблицы;\n 6 - выход.");
        	switch(in.nextInt()) {
        		case 1:
        			statement = conn.createStatement();
        			String task1 = "select* from customer join email_adress on (customerid = idcustomer)";
        			result = statement.executeQuery(task1);
        			System.out.format("|%-8s|%-10s|%-10s|%-10s|%-8s|%-10s\n","idcustomer","username","name","surname","idemail_adress","adress");
        			while (result.next()) {
        				System.out.format("|%-8s|%-10s|%-10s|%-10s|%-8s|%-10s\n", result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6));
        			}
        			statement.close();
        			break;
        		case 2:
        			statement = conn.createStatement();
        			System.out.println("Введите логин");
        			String value = "(\"" + in.next() + "\",\"";
        			System.out.println("Введите имя");
        			value += in.next() + "\",\"";
        			System.out.println("Введите фамилию");
        			value += in.next() + "\");";
        			String task2 = "insert into customer(username, name, surname) values " + value;
        			statement.executeUpdate(task2);
        			statement.close();
        			break;
        		case 3:
        			statement = conn.createStatement();
        			System.out.println("Введите почтовый адрес");
        			String adress = in.next();
        			System.out.println("Введите id покупателя");
        			id = in.next();
        			String task3 = "insert into email_adress(adress, customerid) values " + "(\"" + adress + "\"," + id + ")";
        			statement.executeUpdate(task3);
        			statement.close();
        			break;
        		case 4:
        			String task4 = "delete from email_adress where customerid = ?\n";
        			st = conn.prepareStatement(task4);
        			System.out.println("Введите id покупателя");
        			customerid = in.nextInt();
        			st.setInt(1, customerid);
        			st.execute();
        			task4 = "delete from customer where idcustomer = ?";
        			st = conn.prepareStatement(task4);
        			st.setInt(1, customerid);
        			st.execute();
        			st.close();
        			break;
        		case 5:
        			String task5 = "delete from email_adress where idemail_adress = ?";
        			st = conn.prepareStatement(task5);
        			System.out.println("Введите id покупателя");
        			customerid = in.nextInt();
        			st.setInt(1, customerid);
        			st.execute();
        			st.close();
        			break;
        		case 6:
        			quitting = true;
        			conn.close();
        			statement.close();
        			break;
        	};
        	
        	
        }
		
		}
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
	}

}
