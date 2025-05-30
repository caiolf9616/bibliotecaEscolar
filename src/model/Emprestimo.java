package model;
import java.sql.Date;



public class Emprestimo {
    private int idEmprestimo;
    private int idAluno;
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(){}

    public Emprestimo(int idAluno, int idLivro, Date dataEmprestimo, Date dataDevolucao){
        this.idAluno = idAluno;
        this.idLivro = idLivro;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(int idEmprestimo ,int idAluno, int idLivro, Date dataEmprestimo, Date dataDevolucao){
        this.idEmprestimo = idEmprestimo;
        this.idAluno = idAluno;
        this.idLivro= idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }




    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public String toString(){
        return "Empréstimo [ID: " + idEmprestimo +
                ", Aluno: " + idAluno +
                ", Livro: " + idLivro +
                ", Empréstimo: " + dataEmprestimo +
                ", Devolução: " + dataDevolucao + "]";
    }

}
