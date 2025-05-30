package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Livro;
import utill.Conexao;

public class LivroDAO {

    private Connection conn;

    public LivroDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getQuantidade());
            stmt.executeUpdate();

            System.out.println("Livro cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE id_aluno = ?";
        Livro livro = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("ano_publicacao"),
                            rs.getString("quantidade_estoque")
                    );
                }
            }
        }

        return livro;
    }

    public List<Livro> listarLivros() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano_publicacao"));
                livro.setQuantidade(rs.getInt("quantidade_estoque"));
                lista.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE livros SET titulo=?, autor=?, editora=?, ano=?, quantidade=? WHERE id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getQuantidade());
            stmt.setInt(6, livro.getId());
            stmt.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Livro excluído com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
