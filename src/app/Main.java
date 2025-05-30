package app;

import dao.AlunoDAO;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.EmprestimoDAO;
import model.Aluno;
import model.Livro;
import model.Emprestimo;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Menu de Alunos");
            System.out.println("2 - Menu de Livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAlunos(scanner);
                    break;
                case 2:
                    menuLivros(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void menuAlunos(Scanner scanner) {
        AlunoDAO alunoDAO = new AlunoDAO();
        int opcao;

        do {
            System.out.println("\n=== MENU ALUNOS ===");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Buscar aluno por ID");
            System.out.println("4 - Atualizar aluno");
            System.out.println("5 - Deletar aluno");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                        scanner.nextLine();
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
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    public static void menuLivros(Scanner scanner) {
        LivroDAO livroDAO = new LivroDAO();
        int opcao;

        do {
            System.out.println("\n=== MENU LIVROS ===");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Atualizar livro");
            System.out.println("4 - Excluir livro");
            System.out.println("5 - Menu de Empréstimos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Livro novo = new Livro();
                    System.out.print("Título: ");
                    novo.setTitulo(scanner.nextLine());
                    System.out.print("Autor: ");
                    novo.setAutor(scanner.nextLine());
                    System.out.print("Editora: ");
                    novo.setEditora(scanner.nextLine());
                    System.out.print("Ano: ");
                    novo.setAno(scanner.nextInt());
                    System.out.print("Quantidade: ");
                    novo.setQuantidade(scanner.nextInt());
                    livroDAO.cadastrarLivro(novo);
                    break;

                case 2:
                    List<Livro> livros = livroDAO.listarLivros();
                    for (Livro l : livros) {
                        System.out.println("ID: " + l.getId() + " | Título: " + l.getTitulo() + " | Autor: " + l.getAutor());
                    }
                    break;

                case 3:
                    Livro atualizar = new Livro();
                    System.out.print("ID do livro: ");
                    atualizar.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Novo Título: ");
                    atualizar.setTitulo(scanner.nextLine());
                    System.out.print("Novo Autor: ");
                    atualizar.setAutor(scanner.nextLine());
                    System.out.print("Nova Editora: ");
                    atualizar.setEditora(scanner.nextLine());
                    System.out.print("Novo Ano: ");
                    atualizar.setAno(scanner.nextInt());
                    System.out.print("Nova Quantidade: ");
                    atualizar.setQuantidade(scanner.nextInt());
                    livroDAO.atualizarLivro(atualizar);
                    break;

                case 4:
                    System.out.print("ID do livro a excluir: ");
                    int id = scanner.nextInt();
                    livroDAO.excluirLivro(id);
                    break;

                case 5:
                    menuEmprestimos(scanner);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuEmprestimos(Scanner scanner) {
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        int opcao;

        do {
            System.out.println("\n=== MENU EMPRÉSTIMOS ===");
            System.out.println("1 - Registrar empréstimo");
            System.out.println("2 - Listar empréstimos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("ID do aluno: ");
                        int idAluno = scanner.nextInt();
                        System.out.print("ID do livro: ");
                        int idLivro = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer
                        System.out.print("Data de devolução (YYYY-MM-DD): ");
                        String dataDev = scanner.nextLine();

                        Emprestimo novoEmp = new Emprestimo();
                        novoEmp.setIdAluno(idAluno);
                        novoEmp.setIdLivro(idLivro);
                        novoEmp.setDataDevolucao(Date.valueOf(dataDev));

                        boolean sucesso = emprestimoDAO.registraEmprestimo(novoEmp);
                        if (sucesso) {
                            System.out.println("Empréstimo registrado com sucesso.");
                        } else {
                            System.out.println("Não foi possível registrar o empréstimo.");
                        }
                        break;

                    case 2:
                        List<Emprestimo> lista = emprestimoDAO.ListaEmprestimos();
                        for (Emprestimo e : lista) {
                            System.out.println("ID: " + e.getIdEmprestimo() + " | Aluno ID: " + e.getIdAluno() +
                                    " | Livro ID: " + e.getIdLivro() + " | Empréstimo: " + e.getDataEmprestimo() +
                                    " | Devolução: " + e.getDataDevolucao());
                        }
                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }

        } while (opcao != 0);
    }


}
