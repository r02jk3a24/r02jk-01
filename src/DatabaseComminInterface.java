import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseComminInterface {
	String connectionUrl = "jdbc:sqlserver://r02jk-01.database.windows.net:1433;database=R02JK-01;user=t.yoshizawa@r02jk-01;password=StandUp4YourRight;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(connectionUrl);
		return con;
	}
}
