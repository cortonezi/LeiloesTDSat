import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    // Método responsável pela conexão com o banco
    public static Connection conector() {
        Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/uc11?useSSL=false";  // Desativando o uso de SSL
        String user = "root";
        String password = "123456";
        
        // Estabelecendo a conexão com o banco        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // A linha abaixo serve para mostrar o erro de conexão
            System.out.println("Erro de conexão: " + e);
            return null;
        }
    }
}
