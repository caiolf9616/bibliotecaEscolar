package utill;

import java.sql.*;

public class Conexao {
    private static final String HOST = "localhost";
    private static final String PORTA = "3306";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String BANCO = "bibliotecaescolar";

    public static Connection checkDB() throws SQLException {
        try {
            createDBNoExists();
            try (Connection conexao = conectWithDB()) {
                createTableIfNoExists(conexao);
                System.out.println("Banco e tabelas prontos para uso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conectWithDB();
    }

    private static Connection conectWithoutDB() throws SQLException {
        String url = "jdbc:mysql://" + HOST + ":" + PORTA;
        return DriverManager.getConnection(url, USUARIO, SENHA);
    }

    private static Connection conectWithDB() throws SQLException {
        String url = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + BANCO;
        return DriverManager.getConnection(url, USUARIO, SENHA);
    }

    private static void createDBNoExists() throws SQLException {
        try (Connection conexao = conectWithoutDB(); Statement stmt = conexao.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + BANCO;
            stmt.executeUpdate(sql);
            System.out.println("Banco verificado/criado.");
        }
    }

    private static void createTableIfNoExists(Connection conexao) throws SQLException {
        DatabaseMetaData meta = conexao.getMetaData();
        try (ResultSet resultado = meta.getTables(null, null, "Alunos", new String[]{"TABLE"})) {
            if (!resultado.next()) {
                try (Statement stmt = conexao.createStatement()) {
                    String sql = """
                            CREATE TABLE Alunos (
                                id_aluno INT AUTO_INCREMENT PRIMARY KEY ,
                                nome_aluno VARCHAR (100) NOT NULL , 
                                matricula VARCHAR (20) UNIQUE , 
                                data_nascimento DATE
                            );                            
                            """;
                    stmt.execute(sql);
                    System.out.println("Tabela Alunos criada.");
                }
            } else {
                System.out.println("Tabela Alunos já existe.");
            }
        }
        try (ResultSet resultado = meta.getTables(null, null, "Livros", new String[]{"TABLE"})) {
            if (!resultado.next()) {
                try (Statement stmt = conexao.createStatement()) {
                    String sql = """
                            CREATE TABLE Livros (
                                id_livro INT AUTO_INCREMENT PRIMARY KEY , 
                                titulo VARCHAR (150) NOT NULL ,
                                autor VARCHAR (100) ,
                                ano_publicacao INT , 
                                quantidade_estoque INT DEFAULT 0
                            );                                                      
                            """;
                    stmt.execute(sql);
                    System.out.println("Tabela Livros criada.");
                }
            } else {
                System.out.println("Tabela Livros já existe.");
            }
        }
        try (ResultSet resultado = meta.getTables(null, null, "Emprestimos", new String[]{"TABLE"})) {
            if (!resultado.next()) {
                try (Statement stmt = conexao.createStatement()) {
                    String sql = """
                            CREATE TABLE Emprestimos (
                                id_emprestimo INT AUTO_INCREMENT PRIMARY KEY , id_aluno INT ,
                                id_livro INT ,
                                data_emprestimo DATE DEFAULT ( CURRENT_DATE ), 
                                data_devolucao DATE ,
                                FOREIGN KEY ( id_aluno ) REFERENCES Alunos( id_aluno ), 
                                FOREIGN KEY ( id_livro ) REFERENCES Livros( id_livro )
                            );                                                                                 
                            """;
                    stmt.execute(sql);
                    System.out.println("Tabela Emprestimos criada.");
                }
            } else {
                System.out.println("Tabela Emprestimos já existe.");
            }
        }
    }
}
