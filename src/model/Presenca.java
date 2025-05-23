package model;

public class Presenca {
    private Aluno aluno;
    private boolean present;

    public Presenca(Aluno aluno, boolean present) {
        this.aluno = aluno;
        this.present = present;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public boolean isPresent() {
        return present;
    }

    @Override
    public String toString() {
        return "Presenca{aluno=" + aluno.getName() + ", present=" + present + "}";
    }
}