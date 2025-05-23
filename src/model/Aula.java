package model;

import java.util.ArrayList;
import java.util.Date;

public class Aula {
    private int id;
    private Date date;
    private ArrayList<Presenca> presencas;

    public Aula(int id, Date date) {
        this.id = id;
        this.date = date;
        this.presencas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void addPresenca(Aluno aluno, boolean present) {
        presencas.add(new Presenca(aluno, present));
    }

    public ArrayList<Presenca> getPresencas() {
        return presencas;
    }

    @Override
    public String toString() {
        return "Aula{id=" + id + ", date=" + date + "}";
    }
}