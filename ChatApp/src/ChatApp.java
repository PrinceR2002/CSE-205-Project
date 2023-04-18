import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class ChatApp{
	
	public static void main(String[] args) {
	Connection c = null;
	Statement stmt = null;
	
	try {
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/usersdb",
				"postgres", "password");
		System.out.println("Connected to the database.");
					
	}catch (Exception e) {
		e.printStackTrace();
		System.err.print(e.getClass().getName()+": "+ e.getMessage());
		System.exit(0);
		
	}
	///*
	try {
		stmt = c.createStatement();
		String sql = "CREATE TABLE LOGIN"+"(USERNAME CHAR(10),"+"PASSWORD CHAR(50))";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		System.out.println("Table has been created.");
	}catch (Exception e) {
		e.printStackTrace();
		System.err.print(e.getClass().getName()+": "+ e.getMessage());
		System.exit(0);
	}
	//*/
	/*
	try {
		c.setAutoCommit(false);
		stmt = c.createStatement();
		String sql = "INSERT INTO login("
				+"USERNAME, PASSWORD)"+"VALUES( 'Prince.R', 'Prince$2002');";
		stmt.executeLargeUpdate(sql);
		
		sql = "INSERT INTO login("
				+"USERNAME, PASSWORD)"+"VALUES('Yannick_', 'Yannick0612');";
		stmt.executeLargeUpdate(sql);
		
		stmt.close();
		c.commit();
		c.close();
		System.out.println("Just added 2 elements to the table");
		
		
	}catch(Exception e) {
		e.printStackTrace();
		System.err.println(e.getClass().getName()+": "+e.getMessage());
	}
	*/
	}
}