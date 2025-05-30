package model;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeEstoque;

    // Construtor vazio
    public Livro() {}

    // Construtor completo
    public Livro(int idLivro, String titulo, String autor, String anoPublicacao, String quantidadeEstoque) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = Integer.parseInt(anoPublicacao);
        this.quantidadeEstoque = Integer.parseInt(quantidadeEstoque);
    }

    public Livro(String titulo, String autor, String anoPublicacao, String quantidadeLivros) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = Integer.parseInt(anoPublicacao);
        this.quantidadeEstoque = Integer.parseInt(quantidadeLivros);
    }

    // Getters e Setters
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

}
