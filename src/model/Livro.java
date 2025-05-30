package model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeEstoque;


    public Livro() {}


    public Livro(int id, String titulo, String autor, String anoPublicacao, String quantidadeEstoque) {
        this.id = id;
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

    public int getIdLivro() {
        return id;
    }

    public void setIdLivro(int idLivro) {
        this.id = idLivro;
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
