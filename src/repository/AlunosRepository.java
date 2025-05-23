package repository;

import model.Aluno;
import java.util.ArrayList;

public class AlunosRepository {
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public Aluno getAlunoById(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public ArrayList<Aluno> getAllAlunos() {
        return alunos;
    }

    public void removeAluno(Aluno aluno){
        alunos.remove(aluno);
    }
}