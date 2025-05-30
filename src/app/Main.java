package app;

import dao.*;
import model.*;
import utill.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Connection conn = Conexao.checkDB();){
            Scanner scanner = new Scanner(System.in);
            AlunoDAO alunoDAO = new AlunoDAO(conn);
            LivroDAO livroDAO = new LivroDAO(conn);
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO(conn);

            int opcao;

            do {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1 - Menu Aluno");
                System.out.println("2 - Menu Livros");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // limpar buffer

                switch (opcao) {
                    case 1:
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
                        break;

                    case 2:
                        do {
                            System.out.println("\n=== MENU LIVROS ===");
                            System.out.println("1 - Cadastrar livro");
                            System.out.println("2 - Listar livros");
                            System.out.println("3 - Emprestimo de livro por ID");
                            System.out.println("4 - Atualizar aluno");
                            System.out.println("5 - Deletar aluno");
                            System.out.println("0 - Sair");
                            System.out.print("Escolha: ");
                            opcao = scanner.nextInt();
                            scanner.nextLine(); // limpar buffer

                            try {
                                switch (opcao) {
                                    case 1:
                                        System.out.print("Titulo: ");
                                        String titulo = scanner.nextLine();
                                        System.out.print("Autor: ");
                                        String autor = scanner.nextLine();
                                        System.out.print("Ano de publicação: ");
                                        String anoPublicacao = scanner.nextLine();
                                        System.out.print("Quantidade de livros: ");
                                        String quantidadeLivros = scanner.nextLine();

                                        quantidadeLivros = quantidadeLivros.replaceAll("[^0-9]", "");
                                        anoPublicacao = anoPublicacao.replaceAll("[^0-9]", "");

                                        Livro novoLivro = new Livro(titulo, autor, anoPublicacao, quantidadeLivros);
                                        livroDAO.inserir(novoLivro);

                                        System.out.println("Aluno cadastrado com sucesso.");
                                        break;

                                    case 2:
                                        List<Livro> livros = livroDAO.listarTodos();
                                        System.out.println("Lista de livros:");
                                        for (Livro l : livros) {
                                            System.out.println(l);
                                        }
                                        break;

                                    case 3:
                                        System.out.print("ID do livro: ");
                                        int idBusca = scanner.nextInt();
                                        Livro encontrado = livroDAO.buscarPorId(idBusca);
                                        if (encontrado != null) {
                                            System.out.println("Livro encontrado:\n" + encontrado);
                                        } else {
                                            System.out.println("Livro não encontrado.");
                                        }
                                        break;

                                    case 4:
                                        System.out.print("ID do livro a atualizar: ");
                                        int idAtualiza = scanner.nextInt();
                                        scanner.nextLine(); // limpar buffer
                                        System.out.print("Novo titulo: ");
                                        String novotitulo = scanner.nextLine();
                                        System.out.print("Novo autor: ");
                                        String novoAutor = scanner.nextLine();
                                        System.out.print("Novo Ano de publicação: ");
                                        String novoAno = scanner.nextLine();
                                        System.out.print("Nova Quantidade de livros: ");
                                        String novaQuantidade = scanner.nextLine();

                                        novoAno = novoAno.replaceAll("[^0-9]", "");
                                        novaQuantidade = novaQuantidade.replaceAll("[^0-9]", "");

                                        Livro atualizarLivro = new Livro(idAtualiza, novotitulo, novoAutor, novoAno, novaQuantidade);
                                        livroDAO.atualizar(atualizarLivro);
                                        System.out.println("Livro atualizado com sucesso.");
                                        break;

                                    case 5:
                                        System.out.print("ID do livro a excluir: ");
                                        int idDelete = scanner.nextInt();
                                        livroDAO.deletar(idDelete);
                                        System.out.println("Livro excluído.");
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
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } while (opcao != 0);

            scanner.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
