package model;

import java.util.ArrayList;

public class Turma {
    private int id;
    private String name;
    private ArrayList<Aluno> alunos;
    private ArrayList<Aula> aulas;

    public Turma(int id, String name) {
        this.id = id;
        this.name = name;
        this.alunos = new ArrayList<>();
        this.aulas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removeAluno(Aluno removedAluno){
        alunos.remove(removedAluno);
    }

    public void addAula(Aula aula) {
        aulas.add(aula);
    }

    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Turma{id=" + id + ", name='" + name + "'}";
    }
}