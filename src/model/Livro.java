package model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private int quantidade;

    public Livro() {}

    public Livro(int id, String titulo, String autor, String ano, String quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = Integer.parseInt(ano);
        this.quantidade = Integer.parseInt(quantidade);
    }

    public Livro(String titulo, String autor, String ano, String quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = Integer.parseInt(ano);
        this.quantidade = Integer.parseInt(quantidade);
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + ", " +
                "\"titulo\": \"" + titulo + "\", " +
                "\"autor\": \"" + autor + "\", " +
                "\"ano\": " + ano + ", " +
                "\"quantidade\": " + quantidade +
                "}";
    }
}
