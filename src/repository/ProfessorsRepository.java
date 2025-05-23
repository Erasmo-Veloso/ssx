package repository;

import model.Professor;
import java.util.ArrayList;

public class ProfessorsRepository {
    private ArrayList<Professor> professors = new ArrayList<>();

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public Professor getProfessorById(int id) {
        for (Professor p : professors) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void deleteProfessor(Professor selectedProfessor){
        professors.remove(selectedProfessor);
    }
    public ArrayList<Professor> getAllProfessors(){
        return this.professors;
    }
}