package dao;
import model.Emprestimo;
import utill.Conexao;

import  java.sql.*;
import  java.util.ArrayList;
import  java.util.List;

public class EmprestimoDAO {

    public boolean registraEmprestimo(Emprestimo emp) throws SQLException {
        String sqlVerificaEstoque = "SELECT quantidade FROM livros WHERE id= ?";
        String sqlAtualizaEstoque = "UPDATE livros SET quantidade = quantidade - 1 WHERE id= ?";
        String sqlInserirEmprestimo = "INSERT INTO emprestimos (id_aluno, id_livro, data_devolucao) VALUES (?,?,?) ";

        try (Connection conn = Conexao.getConexao()){
            try (PreparedStatement verificarStmt = conn.prepareStatement(sqlVerificaEstoque)){
                verificarStmt.setInt(1, emp.getIdLivro());
                ResultSet rs = verificarStmt.executeQuery();

                if (rs.next()){
                    int estoque = rs.getInt("quantidade");
                    if (estoque <= 0) {
                        System.out.println("Livro sem estoque Disponivel!");
                        return false;
                    }

                } else {
                    System.out.println("Livro não encontrado!");
                    return false;
                }

            }

            try (PreparedStatement atualizarStmt = conn.prepareStatement(sqlAtualizaEstoque)) {
                atualizarStmt.setInt(1, emp.getIdLivro());
                atualizarStmt.executeUpdate();
            }


            try (PreparedStatement inserirStmt = conn.prepareStatement(sqlInserirEmprestimo)) {
                inserirStmt.setInt(1, emp.getIdAluno());
                inserirStmt.setInt(2, emp.getIdLivro());
                inserirStmt.setDate(3, emp.getDataDevolucao());
                inserirStmt.executeUpdate();
            }



            System.out.println("Emprestimo registrado com Sucesso!");
            return true;


        }
    }
    public List<Emprestimo> ListaEmprestimos(){
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try(Connection conn = Conexao.getConexao();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Emprestimo emp = new Emprestimo();

                emp.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emp.setIdAluno(rs.getInt("id_aluno"));
                emp.setIdLivro(rs.getInt("id_livro"));
                emp.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emp.setDataDevolucao(rs.getDate("data_devolucao"));

                lista.add(emp);

            }

        } catch (SQLException e){
            e.printStackTrace();
        } return lista;
    }
}
