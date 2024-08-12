
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // Método para cadastrar um novo produto
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conexao = conectaDAO.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getValor());
            pst.setString(3, produto.getStatus());

            pst.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método para listar todos os produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try {
            conexao = conectaDAO.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    // Método para vender um produto (alterar status para "Vendido")
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            conexao = conectaDAO.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Produto com ID " + id + " foi vendido.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
