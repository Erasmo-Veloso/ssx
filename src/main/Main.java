package main;

import model.*;
import repository.*;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ProfessorsRepository professorsRepo = new ProfessorsRepository();
    private static TurmasRepository turmasRepo = new TurmasRepository();
    private static AlunosRepository alunosRepo = new AlunosRepository();
    private static DirectorRepository directorRepository = new DirectorRepository();
    public static final String RESET = "\u001B[0m";
    private static final String[] COLORS = {
            "\u001B[31m", // Red
            "\u001B[33m", // Yellow
            "\u001B[32m", // Green
            "\u001B[36m", // Cyan
            "\u001B[34m", // Blue
            "\u001B[35m"  // Magenta
    };

    public static void main(String[] args) throws InterruptedException {
        // Criação de Director
        directorRepository.addDirector(new Director(1, "Erasmo"));

        // Criação de professores
        professorsRepo.addProfessor(new Professor(1, "Carlos"));

        // Criação de turmas
        Turma turma1 = new Turma(1, "Turma A");
        Turma turma2 = new Turma(2, "Turma B");
        turmasRepo.addTurma(turma1);
        turmasRepo.addTurma(turma2);

        // Criação de alunos
        Aluno aluno1 = new Aluno(1, "João");
        Aluno aluno2 = new Aluno(2, "Maria");
        alunosRepo.addAluno(aluno1);
        alunosRepo.addAluno(aluno2);

        // Adição de alunos às turmas (consistente)
        turma1.addAluno(aluno2);
        turma2.addAluno(aluno2);

        // Início
        Scanner scanner = new Scanner(System.in);
        Director loggedInDirector = null;
        Professor loggedInProfessor = null;

        printBanner();
        while (true) {
            System.out.println("\n==================================");
            System.out.println("<<< Bem-vindo ao Sistema Escolar >>>");
            System.out.println("==================================");
            System.out.println("  1. Director");
            System.out.println("  2. Professor");
            System.out.println("  3. Sair");
            System.out.println("==================================");
            System.out.print("Por favor, diga quem é você? ");
            int choice = getValidOption(scanner, 1, 3);

            // Director entrando
            if (choice == 1) {
                System.out.println("\n==================================");
                System.out.println("<<< Bem-vindo ao Sistema Escolar(Director) >>>");
                System.out.println("==================================");
                System.out.println("  1. Entrar no Sistema");
                System.out.println("  2. Cadastrar novo Director");
                System.out.println("  3. Voltar");
                System.out.println("==================================");
                System.out.print("Por favor, insira a opção desejada: ");
                int directorchoice = getValidOption(scanner, 1, 3);
                if (directorchoice == 1) {
                    System.out.print("Por favor, insira o id do Director: ");
                    int directorId = scanner.nextInt();
                    loggedInDirector = directorRepository.getDirectorById(directorId);
                    if (loggedInDirector == null) {
                        System.out.println("Director não encontrado! Tente novamente.");
                    } else {
                        System.out.println("==================================");
                        System.out.println("<<<    Bem-vindo, " + loggedInDirector.getNome() + "!    >>>");
                        System.out.println("==================================");
                        roleDirector(loggedInDirector, scanner);
                    }
                } else if (directorchoice == 2) {
                    System.out.print("Por favor, digite o nome do director: ");
                    scanner.nextLine(); // Limpar buffer
                    String dirNome = scanner.nextLine();
                    System.out.print("Por favor, digite o id do director: ");
                    int dirId = scanner.nextInt();
                    if (directorRepository.getDirectorById(dirId) != null) {
                        System.out.println("Aviso: ID " + dirId + " já existe! Prosseguindo, mas cuidado com duplicatas.");
                    }
                    directorRepository.addDirector(new Director(dirId, dirNome));
                    System.out.println("Director criado com sucesso!");
                }
            }

            // Professor entrando
            else if (choice == 2) {
                if (loggedInProfessor == null) {
                    System.out.println("\n==================================");
                    System.out.println("<<< Bem-vindo ao Sistema Escolar(Professor) >>>");
                    System.out.println("==================================");
                    System.out.println("  1. Entrar no Sistema");
                    System.out.println("  2. Cadastrar novo Professor");
                    System.out.println("  3. Sair");
                    System.out.println("==================================");
                    System.out.print("Por favor, insira a opção desejada: ");
                    int choic = getValidOption(scanner, 1, 3);

                    if (choic == 1) {
                        System.out.print("Por favor, insira o ID do Professor: ");
                        int profId = scanner.nextInt();
                        loggedInProfessor = professorsRepo.getProfessorById(profId);
                        if (loggedInProfessor == null) {
                            System.out.println("Professor não encontrado! Tente novamente.");
                        } else {
                            System.out.println("==================================");
                            System.out.println("<<<    Bem-vindo, " + loggedInProfessor.getName() + "!    >>>");
                            System.out.println("==================================");
                            roleProfessor(loggedInProfessor, scanner);
                        }
                    } else if (choic == 2) {
                        System.out.print("Por favor, insira o ID do novo Professor: ");
                        int profId = scanner.nextInt();
                        if (professorsRepo.getProfessorById(profId) != null) {
                            System.out.println("Aviso: ID " + profId + " já existe! Prosseguindo, mas cuidado com duplicatas.");
                        }
                        scanner.nextLine(); // Limpar buffer
                        System.out.print("Por favor, insira o Nome do Professor: ");
                        String profName = scanner.nextLine();
                        professorsRepo.addProfessor(new Professor(profId, profName));
                        System.out.println("==================================");
                        System.out.println("<<< Professor " + profName + " cadastrado com sucesso! >>>");
                        System.out.println("==================================");
                    } else if (choic == 3) {
                        System.out.println("==================================");
                        System.out.println("<< Obrigado por usar o sistema! >>");
                        System.out.println("==================================");
                        break;
                    }
                }
            }

            // Saindo
            else if (choice == 3) {
                System.out.println("==================================");
                System.out.println("<< Obrigado por usar o sistema! >>");
                System.out.println("==================================");
                break;
            }
        }
    }

    // Roles
    private static void roleDirector(Director loggedInDirector, Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("<<<      Menu do Director     >>> ");
            System.out.println("==================================");
            System.out.println("  1. Professores");
            System.out.println("  2. Alunos");
            System.out.println("  3. Turmas");
            System.out.println("  4. Sair (Logout)");
            System.out.println("==================================");
            System.out.print("Por favor, insira a opção desejada: ");
            int choice = getValidOption(scanner, 1, 4);

            // Professores
            if (choice == 1) {
                while (true) {
                    System.out.println("\n==================================");
                    System.out.println("<<<      Menu do Director     >>> ");
                    System.out.println("==================================");
                    System.out.println("  1. Listar");
                    System.out.println("  2. Criar");
                    System.out.println("  3. Eliminar");
                    System.out.println("  4. Actualizar");
                    System.out.println("  5. Voltar");
                    System.out.println("==================================");
                    System.out.print("Por favor, insira a opção desejada: ");
                    int directChoice = getValidOption(scanner, 1, 5);

                    if (directChoice == 1) {
                        System.out.println("\n==================================");
                        System.out.println("<<<       Professores          >>>");
                        System.out.println("==================================");
                        if (professorsRepo.getAllProfessors().isEmpty()) {
                            System.out.println("Nenhum professor encontrado!");
                        } else {
                            for (Professor p : professorsRepo.getAllProfessors()) {
                                System.out.println("  - " + p.toString());
                            }
                        }
                        System.out.println("==================================");
                    } else if (directChoice == 2) {
                        System.out.println("\n==================================");
                        System.out.println("<<<      CRIANDO PROFESSOR     >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, insira o nome do professor: ");
                        scanner.nextLine(); // Limpar buffer
                        String teacherName = scanner.nextLine();
                        System.out.print("Por favor, insira o id do professor: ");
                        int teacherId = scanner.nextInt();
                        if (professorsRepo.getProfessorById(teacherId) != null) {
                            System.out.println("Aviso: ID " + teacherId + " já existe! Prosseguindo, mas cuidado com duplicatas.");
                        }
                        professorsRepo.addProfessor(new Professor(teacherId, teacherName));
                        System.out.println("Professor criado com sucesso!");
                    } else if (directChoice == 3) {
                        System.out.println("\n==================================");
                        System.out.println("<<<      ELIMINANDO PROFESSOR     >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, insira o id do professor: ");
                        int professorId = scanner.nextInt();
                        Professor selectedProfessor = professorsRepo.getProfessorById(professorId);
                        if (selectedProfessor == null) {
                            System.out.println("Professor não encontrado");
                        } else {
                            professorsRepo.deleteProfessor(selectedProfessor);
                            System.out.println("Professor (" + selectedProfessor.getName() + ") eliminado com sucesso!");
                        }
                    } else if (directChoice == 4) {
                        System.out.println("\n==================================");
                        System.out.println("<<<      ACTUALIZANDO PROFESSOR     >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o id do professor: ");
                        int professorId = scanner.nextInt();
                        Professor selectedProfessor = professorsRepo.getProfessorById(professorId);
                        if (selectedProfessor == null) {
                            System.out.println("Professor não encontrado!");
                        } else {
                            System.out.println("\n==================================");
                            System.out.println("<<<      ACTUALIZANDO PROFESSOR (" + selectedProfessor.getName() + ")     >>> ");
                            System.out.println("==================================");
                            System.out.print("Por favor, digite o novo nome: ");
                            scanner.nextLine(); // Limpar buffer
                            String professorName = scanner.nextLine();
                            selectedProfessor.setName(professorName);
                            System.out.println("PROFESSOR ACTUALIZADO!");
                        }
                    } else if (directChoice == 5) {
                        System.out.println("==================================");
                        System.out.println("===          Saindo...        ===");
                        System.out.println("==================================");
                        break;
                    }
                }
            }

            // Alunos
            else if (choice == 2) {
                while (true) {
                    System.out.println("\n==================================");
                    System.out.println("<<<          Alunos            >>>");
                    System.out.println("==================================");
                    System.out.println("  1. Listar");
                    System.out.println("  2. Criar");
                    System.out.println("  3. Eliminar");
                    System.out.println("  4. Actualizar");
                    System.out.println("  5. Voltar");
                    System.out.println("==================================");
                    System.out.print("Por favor, insira a opção desejada: ");
                    int directorChoice = getValidOption(scanner, 1, 5);

                    if (directorChoice == 1) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         LISTANDO ALUNOS         >>>");
                        System.out.println("==================================");
                        if (alunosRepo.getAllAlunos().isEmpty()) {
                            System.out.println("  - Nenhum aluno encontrado!");
                        } else {
                            for (Aluno aluno : alunosRepo.getAllAlunos()) {
                                System.out.println("  - " + aluno.toString());
                            }
                        }
                        System.out.println("==================================");
                    } else if (directorChoice == 2) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         CRIANDO ALUNO      >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o nome do aluno: ");
                        scanner.nextLine(); // Limpar buffer
                        String studentName = scanner.nextLine();
                        System.out.print("Por favor, digite o id do aluno: ");
                        int studentId = scanner.nextInt();
                        if (alunosRepo.getAlunoById(studentId) != null) {
                            System.out.println("Aviso: ID " + studentId + " já existe! Prosseguindo, mas cuidado com duplicatas.");
                        }
                        alunosRepo.addAluno(new Aluno(studentId, studentName));
                        System.out.println("Aluno criado com sucesso.");
                    } else if (directorChoice == 3) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         ELIMINANDO ALUNO    >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o id do aluno: ");
                        int deleteAluno = scanner.nextInt();
                        Aluno selectedAluno = alunosRepo.getAlunoById(deleteAluno);
                        if (selectedAluno == null) {
                            System.out.println("Aluno não encontrado, tente novamente.");
                        } else {
                            // Remover o aluno de todas as turmas antes de deletar
                            for (Turma turma : turmasRepo.getAllTurmas()) {
                                turma.removeAluno(selectedAluno);
                            }
                            alunosRepo.removeAluno(selectedAluno);
                            System.out.println("Aluno eliminado com sucesso.");
                        }
                    } else if (directorChoice == 4) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         ACTUALIZANDO ALUNO    >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o id do aluno: ");
                        int updateAluno = scanner.nextInt();
                        Aluno selectedAluno = alunosRepo.getAlunoById(updateAluno);
                        if (selectedAluno == null) {
                            System.out.println("Aluno não encontrado, tente novamente.");
                        } else {
                            System.out.print("Por favor, digite o novo nome do aluno: ");
                            scanner.nextLine(); // Limpar buffer
                            String name = scanner.nextLine();
                            System.out.println("Nome antigo: " + selectedAluno.getName());
                            selectedAluno.setName(name);
                            System.out.println("Nome novo: " + selectedAluno.getName());
                            System.out.println("Aluno actualizado com sucesso!");
                        }
                    } else if (directorChoice == 5) {
                        break;
                    }
                }
            }

            // Turmas
            else if (choice == 3) {
                while (true) {
                    System.out.println("\n==================================");
                    System.out.println("<<<          TURMAS            >>>");
                    System.out.println("==================================");
                    System.out.println("  1. Listar");
                    System.out.println("  2. Criar");
                    System.out.println("  3. Eliminar");
                    System.out.println("  4. Actualizar");
                    System.out.println("  5. Voltar");
                    System.out.println("==================================");
                    System.out.print("Por favor, insira a opção desejada: ");
                    int directorChoice = getValidOption(scanner, 1, 5);

                    // Listar turmas
                    if (directorChoice == 1) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         LISTANDO TURMAS         >>>");
                        System.out.println("==================================");
                        if (turmasRepo.getAllTurmas().isEmpty()) {
                            System.out.println("  - Nenhuma turma encontrada!");
                        } else {
                            for (Turma turma : turmasRepo.getAllTurmas()) {
                                System.out.println("  - " + turma.toString());
                            }
                        }
                        System.out.println("==================================");
                    }

                    // Criar turmas
                    else if (directorChoice == 2) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         CRIANDO TURMA      >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o nome da turma: ");
                        scanner.nextLine(); // Limpar buffer
                        String turmaName = scanner.nextLine();
                        System.out.print("Por favor, digite o id da turma: ");
                        int turmaId = scanner.nextInt();
                        if (turmasRepo.getTurmaById(turmaId) != null) {
                            System.out.println("Aviso: ID " + turmaId + " já existe! Prosseguindo, mas cuidado com duplicatas.");
                        }
                        Turma newTurma = new Turma(turmaId, turmaName);
                        turmasRepo.addTurma(newTurma);
                        System.out.println("Turma criada com sucesso.");
                    }

                    // Eliminando turmas
                    else if (directorChoice == 3) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         ELIMINANDO TURMA    >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o id da turma: ");
                        int deleteTurma = scanner.nextInt();
                        Turma selectedTurma = turmasRepo.getTurmaById(deleteTurma);
                        if (selectedTurma == null) {
                            System.out.println("Turma não encontrada, tente novamente.");
                        } else {
                            turmasRepo.removeTurma(selectedTurma);
                            System.out.println("Turma eliminada com sucesso.");
                        }
                    }

                    // Actualizando turmas
                    else if (directorChoice == 4) {
                        System.out.println("\n==================================");
                        System.out.println("<<<         ACTUALIZANDO TURMA    >>> ");
                        System.out.println("==================================");
                        System.out.print("Por favor, digite o id da turma: ");
                        int updateTurma = scanner.nextInt();
                        Turma selectedTurma = turmasRepo.getTurmaById(updateTurma);
                        if (selectedTurma == null) {
                            System.out.println("Turma não encontrada, tente novamente.");
                        } else {
                            // Mexendo na turma
                            while (true) {
                                System.out.println("\n==================================");
                                System.out.println("<<<         MEXENDO NA TURMA " + selectedTurma.getName() + "    >>> ");
                                System.out.println("==================================");
                                System.out.println("  1. Ver alunos");
                                System.out.println("  2. Adicionar aluno");
                                System.out.println("  3. Remover aluno");
                                System.out.println("  4. Voltar");
                                System.out.println("==================================");
                                System.out.print("Por favor, insira a opção desejada: ");
                                int turmaChoice = getValidOption(scanner, 1, 4);

                                // Listando aluno
                                if (turmaChoice == 1) {
                                    System.out.println("\n==================================");
                                    System.out.println("<<<        LISTANDO ALUNOS       >>> ");
                                    System.out.println("==================================");
                                    if (selectedTurma.getAlunos().isEmpty()) {
                                        System.out.println("  - Esta turma não tem alunos.");
                                    } else {
                                        for (Aluno aluno : selectedTurma.getAlunos()) {
                                            System.out.println("  - " + aluno.getName());
                                        }
                                    }
                                    System.out.println("==================================");
                                }

                                // Adicionando aluno
                                else if (turmaChoice == 2) {
                                    System.out.println("\n==================================");
                                    System.out.println("<<<         ADICIONANDO ALUNO    >>> ");
                                    System.out.println("==================================");
                                    System.out.print("Por favor, digite o id do aluno: ");
                                    int alunoId = scanner.nextInt();
                                    Aluno selectedAluno = alunosRepo.getAlunoById(alunoId);
                                    if (selectedAluno == null) {
                                        System.out.println("Aluno não encontrado.");
                                    } else {
                                        selectedTurma.addAluno(selectedAluno);
                                        System.out.println("Aluno adicionado com sucesso.");
                                    }
                                }

                                // Removendo aluno
                                else if (turmaChoice == 3) {
                                    System.out.println("\n==================================");
                                    System.out.println("<<<         REMOVENDO ALUNO    >>> ");
                                    System.out.println("==================================");
                                    System.out.print("Por favor, digite o id do aluno: ");
                                    int alunoId = scanner.nextInt();
                                    Aluno selectedAluno = alunosRepo.getAlunoById(alunoId);
                                    if (selectedAluno == null) {
                                        System.out.println("Aluno não encontrado.");
                                    } else {
                                        selectedTurma.removeAluno(selectedAluno);
                                        System.out.println("Aluno removido com sucesso.");
                                    }
                                }

                                // Voltar
                                else if (turmaChoice == 4) {
                                    break;
                                }
                            }
                        }
                    }

                    // Voltando
                    else if (directorChoice == 5) {
                        break;
                    }
                }
            }

            // LogOut
            else if (choice == 4) {
                System.out.println("==================================");
                System.out.println("=== Logout realizado com sucesso! ===");
                System.out.println("==================================");
                break;
            }
        }
    }

    private static void roleProfessor(Professor loggedInProfessor, Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("<<<      Menu do Professor     >>> ");
            System.out.println("==================================");
            System.out.println("  1. Listar Turmas");
            System.out.println("  2. Sair (Logout)");
            System.out.println("==================================");
            System.out.print("Por favor, insira a opção desejada: ");
            int choice = getValidOption(scanner, 1, 2);

            if (choice == 1) {
                System.out.println("\n==================================");
                System.out.println("<<<    Turmas Disponíveis      >>>");
                System.out.println("==================================");
                for (Turma t : turmasRepo.getAllTurmas()) {
                    System.out.println("  - " + t);
                }
                System.out.println("==================================");
                System.out.print("Por favor, insira o ID da turma para entrar: ");
                int turmaId = scanner.nextInt();
                Turma selectedTurma = turmasRepo.getTurmaById(turmaId);
                if (selectedTurma == null) {
                    System.out.println("==================================");
                    System.out.println("<<<     Turma não encontrada!  >>>");
                    System.out.println("==================================");
                    continue;
                }

                while (true) {
                    System.out.println("\n==================================");
                    System.out.println("=== Menu da Turma: " + selectedTurma.getName() + " ===");
                    System.out.println("==================================");
                    System.out.println("  1. Ver Alunos");
                    System.out.println("  2. Criar Nova Aula");
                    System.out.println("  3. Listar Aulas");
                    System.out.println("  4. Voltar");
                    System.out.println("==================================");
                    System.out.print("Por favor, insira a opção desejada: ");
                    int turmaChoice = getValidOption(scanner, 1, 4);

                    if (turmaChoice == 1) {
                        System.out.println("\n==================================");
                        System.out.println("=== Alunos da Turma ===");
                        System.out.println("==================================");
                        if (selectedTurma.getAlunos().isEmpty()) {
                            System.out.println("  - Esta turma não tem alunos.");
                        } else {
                            for (Aluno a : selectedTurma.getAlunos()) {
                                System.out.println("  - " + a);
                            }
                        }
                        System.out.println("==================================");
                    } else if (turmaChoice == 2) {
                        System.out.print("Por favor, insira o ID da nova aula: ");
                        int aulaId = scanner.nextInt();
                        Aula aula = new Aula(aulaId, new Date());
                        selectedTurma.addAula(aula);
                        System.out.println("==================================");
                        System.out.println("=== Aula criada com sucesso: " + aula + " ===");
                        System.out.println("==================================");

                        System.out.print("Deseja marcar presenças agora? (1 para Sim, 0 para Não): ");
                        int markPresence = getValidOption(scanner, 0, 1);
                        if (markPresence == 1) {
                            System.out.println("\n==================================");
                            System.out.println("<<<     Marcando Presenças     >>>");
                            System.out.println("==================================");
                            for (Aluno a : selectedTurma.getAlunos()) {
                                System.out.println("Aluno: " + a.getName());
                                System.out.print("Presente? (1 para Sim, 0 para Não): ");
                                int present = getValidOption(scanner, 0, 1);
                                aula.addPresenca(a, present == 1);
                            }
                            System.out.println("==================================");
                            System.out.println("=== Presenças marcadas com sucesso! ===");
                            System.out.println("==================================");
                        }

                        // Entrar automaticamente na aula criada
                        Aula selectedAula = aula;
                        manageAula(selectedTurma, selectedAula, scanner);
                    } else if (turmaChoice == 3) {
                        System.out.println("\n==================================");
                        System.out.println("=== Aulas da Turma ===");
                        System.out.println("==================================");
                        if (selectedTurma.getAulas().isEmpty()) {
                            System.out.println("  - Nenhuma aula encontrada para esta turma.");
                        } else {
                            for (Aula a : selectedTurma.getAulas()) {
                                System.out.println("  - " + a);
                            }
                        }
                        System.out.println("==================================");
                        System.out.print("Por favor, insira o ID da aula para entrar: ");
                        int aulaId = scanner.nextInt();
                        Aula selectedAula = null;
                        for (Aula a : selectedTurma.getAulas()) {
                            if (a.getId() == aulaId) {
                                selectedAula = a;
                                break;
                            }
                        }
                        if (selectedAula == null) {
                            System.out.println("==================================");
                            System.out.println("=== Aula não encontrada! Tente novamente. ===");
                            System.out.println("==================================");
                            continue;
                        }

                        manageAula(selectedTurma, selectedAula, scanner);
                    } else if (turmaChoice == 4) {
                        break;
                    }
                }
            } else if (choice == 2) {
                System.out.println("==================================");
                System.out.println("=== Logout realizado com sucesso! ===");
                System.out.println("==================================");
                break;
            }
        }
    }

    // Outros
    private static void manageAula(Turma selectedTurma, Aula selectedAula, Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("=== Menu da Aula: " + selectedAula + " ===");
            System.out.println("==================================");
            System.out.println("  1. Ver Lista de Presença");
            System.out.println("  2. Atualizar Lista de Presença");
            System.out.println("  3. Voltar");
            System.out.println("==================================");
            System.out.print("Por favor, insira a opção desejada: ");
            int aulaChoice = getValidOption(scanner, 1, 3);

            if (aulaChoice == 1) {
                System.out.println("\n==================================");
                System.out.println("=== Lista de Presenças ===");
                System.out.println("==================================");
                if (selectedAula.getPresencas().isEmpty()) {
                    System.out.println("  - Nenhuma presença registrada para esta aula.");
                } else {
                    for (Presenca p : selectedAula.getPresencas()) {
                        System.out.println("  - " + p);
                    }
                }
                System.out.println("==================================");
            } else if (aulaChoice == 2) {
                System.out.println("\n==================================");
                System.out.println("=== Atualizando Lista de Presenças ===");
                System.out.println("==================================");
                selectedAula.getPresencas().clear();
                for (Aluno a : selectedTurma.getAlunos()) {
                    System.out.println("Aluno: " + a.getName());
                    System.out.print("Presente? (1 para Sim, 0 para Não): ");
                    int present = getValidOption(scanner, 0, 1);
                    selectedAula.addPresenca(a, present == 1);
                }
                System.out.println("==================================");
                System.out.println("=== Presenças atualizadas com sucesso! ===");
                System.out.println("==================================");
            } else if (aulaChoice == 3) {
                break;
            }
        }
    }

    // Função auxiliar para validação de opções
    private static int getValidOption(Scanner scanner, int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Opção inválida. Escolha entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
    }

    // Estilização ANSI
    private static final Random random = new Random();

    public static void printBanner() {
        String[] banner = {
                "             ███████╗ ███████╗ ██╗  ██╗                   ",
                "             ██╔════╝ ██╔════╝ ╚██╗██╔╝                   ",
                "             ███████╗ ███████╗  ╚███╔╝                    ",
                "             ╚════██║ ╚════██║  ██╔██╗                    ",
                "             ███████║ ███████║ ██╔╝ ██╗                   ",
                "             ╚System╝ ╚School╝ ╚═Express                   "
        };

        for (int i = 0; i < banner.length; i++) {
            String line = banner[i];
            String color = COLORS[i % COLORS.length];

            for (char c : line.toCharArray()) {
                System.out.print(color + c + RESET);
                sleep(random.nextInt(10) + 3);
            }
            System.out.println();
            sleep(80);
        }

        System.out.println();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}