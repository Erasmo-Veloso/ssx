package repository;


import model.Director;

import java.util.ArrayList;

public class DirectorRepository {
        private ArrayList<Director> directors = new ArrayList<>();

        public void addDirector(Director director) {
            directors.add(director);
        }

        public Director getDirectorById(int id) {
            for (Director d : directors) {
                if (d.getID() == id) return d;
            }
            return null;
        }
}