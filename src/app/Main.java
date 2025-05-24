package app;

import dao.AlunoDAO;
import model.Aluno;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();
        int opcao;

        do {
            System.out.println("\n=== MENU ALUNOS ===");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Buscar aluno por ID");
            System.out.println("4 - Atualizar aluno");
            System.out.println("5 - Deletar aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Matrícula: ");
                        String matricula = scanner.nextLine();
                        System.out.print("Data de nascimento (YYYY-MM-DD): ");
                        String dataNascimento = scanner.nextLine();

                        Aluno novoAluno = new Aluno(nome, matricula, dataNascimento);
                        alunoDAO.inserir(novoAluno);
                        System.out.println("Aluno cadastrado com sucesso.");
                        break;

                    case 2:
                        List<Aluno> alunos = alunoDAO.listarTodos();
                        System.out.println("Lista de alunos:");
                        for (Aluno a : alunos) {
                            System.out.println(a);
                        }
                        break;

                    case 3:
                        System.out.print("ID do aluno: ");
                        int idBusca = scanner.nextInt();
                        Aluno encontrado = alunoDAO.buscarPorId(idBusca);
                        if (encontrado != null) {
                            System.out.println("Aluno encontrado:\n" + encontrado);
                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("ID do aluno a atualizar: ");
                        int idAtualiza = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Nova matrícula: ");
                        String novaMatricula = scanner.nextLine();
                        System.out.print("Nova data de nascimento (YYYY-MM-DD): ");
                        String novaData = scanner.nextLine();

                        Aluno atualizaAluno = new Aluno(idAtualiza, novoNome, novaMatricula, novaData);
                        alunoDAO.atualizar(atualizaAluno);
                        System.out.println("Aluno atualizado com sucesso.");
                        break;

                    case 5:
                        System.out.print("ID do aluno a excluir: ");
                        int idDelete = scanner.nextInt();
                        alunoDAO.deletar(idDelete);
                        System.out.println("Aluno excluído.");
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}
