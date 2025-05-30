package dao;

import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection conn;

    public AlunoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO Alunos (nome_aluno, matricula, data_nascimento) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDataNascimento());

            stmt.executeUpdate();
        }
    }

    public List<Aluno> listarTodos() throws SQLException {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM Alunos";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getString("nome_aluno"),
                        rs.getString("matricula"),
                        rs.getString("data_nascimento")
                );
                lista.add(aluno);
            }
        }

        return lista;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Alunos WHERE id_aluno = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE Alunos SET nome_aluno = ?, matricula = ?, data_nascimento = ? WHERE id_aluno = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setInt(4, aluno.getId());

            stmt.executeUpdate();
        }
    }

    public Aluno buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Alunos WHERE id_aluno = ?";
        Aluno aluno = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno(
                            rs.getInt("id_aluno"),
                            rs.getString("nome_aluno"),
                            rs.getString("matricula"),
                            rs.getString("data_nascimento")
                    );
                }
            }
        }

        return aluno;
    }
}
