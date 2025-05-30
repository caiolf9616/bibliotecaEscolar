package dao;

import model.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmprestimoDAO {
    private Connection conn;

    public EmprestimoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO Emprestimos (id_aluno, id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getIdAluno());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setDate(3, (Date) emprestimo.getDataEmprestimo());
            stmt.setDate(4, (Date) emprestimo.getDataDevolucao());
            stmt.executeUpdate();
        }
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Emprestimos WHERE id_emprestimo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearEmprestimo(rs);
            }
        }
        return null;
    }

    public List<Emprestimo> listarTodos() throws SQLException {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimos";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(mapearEmprestimo(rs));
            }
        }
        return lista;
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE Emprestimos SET id_aluno = ?, id_livro = ?, data_emprestimo = ?, data_devolucao = ? WHERE id_emprestimo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getIdAluno());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setDate(3, (Date) emprestimo.getDataEmprestimo());
            stmt.setDate(4, (Date) emprestimo.getDataDevolucao());
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Emprestimos WHERE id_emprestimo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Emprestimo mapearEmprestimo(ResultSet rs) throws SQLException {
        Emprestimo e = new Emprestimo();
        e.setId(rs.getInt("id_emprestimo"));
        e.setIdAluno(rs.getInt("id_aluno"));
        e.setIdLivro(rs.getInt("id_livro"));
        e.setDataEmprestimo(rs.getDate("data_emprestimo"));
        e.setDataDevolucao(rs.getDate("data_devolucao"));
        return e;
    }


}
