package model;

public class Director {
    private int ID;
    private String nome;

    public Director(int ID, String nome){
        this.ID = ID;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Director{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                '}';
    }
}
