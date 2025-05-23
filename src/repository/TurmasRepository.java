package repository;

import model.Turma;
import java.util.ArrayList;

public class TurmasRepository {
    private ArrayList<Turma> turmas = new ArrayList<>();

    public void addTurma(Turma turma) {
        turmas.add(turma);
    }

    public Turma getTurmaById(int id) {
        for (Turma t : turmas) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public void removeTurma(Turma turma){
        turmas.remove(turma);
    }

    public ArrayList<Turma> getAllTurmas() {
        return turmas;
    }
}