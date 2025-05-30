package dao;

import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livros (titulo, autor, ano_publicacao, quantidade_estoque) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQuantidadeEstoque());
            stmt.executeUpdate();
        }
    }

    public Livro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE id_livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearLivro(rs);
            }
        }
        return null;
    }

    public List<Livro> listarTodos() throws SQLException {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Livros";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(mapearLivro(rs));
            }
        }
        return lista;
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, ano_publicacao = ?, quantidade_estoque = ? WHERE id_livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQuantidadeEstoque());
            stmt.setInt(5, livro.getIdLivro());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Livros WHERE id_livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Boolean checarDisponibilidade(Livro livro) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE id_livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, livro.getIdLivro());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id_livro") == livro.getIdLivro()) {
                    int estoque = rs.getInt("quantidade_estoque");
                    if(estoque > 0){
                        return true;
                    }else{
                        return false;
                    }
                }

            }
            return false;
        }
    }

    private Livro mapearLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setIdLivro(rs.getInt("id_livro"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
        livro.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        return livro;
    }
}
