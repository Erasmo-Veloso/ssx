# 🎓 System School Express - Sistema Escolar

## 📝 Descrição
System School Express é um sistema de gerenciamento escolar desenvolvido em Java, projetado para facilitar a administração de turmas, alunos, professores e diretores. Com uma interface de linha de comando (CLI) estilizada e interativa, o sistema permite ao usuário gerenciar informações de forma eficiente. Atualmente, o foco está em validações robustas de entrada de dados e consistência nos registros, preparando o terreno para futuras melhorias como geração automática de IDs e persistência de dados.

---

## 🚀 Funcionalidades

### 👥 Gerenciamento de Usuários:
- Login como Director ou Professor.
- Cadastro, listagem, atualização e eliminação de diretores, professores e alunos.

### 🏫 Gerenciamento de Turmas:
- Criação, listagem, atualização e eliminação de turmas.
- Adição e remoção de alunos em turmas.

### 📚 Gerenciamento de Aulas:
- Criação de aulas e registro de presenças.
- Visualização da lista de presenças.

### 🎨 Estilização:
- Interface CLI com banner animado e cores ANSI para uma experiência visual atraente.

---

## ⚙️ Pré-requisitos

- Java Development Kit (JDK) 11 ou superior.
- Um terminal compatível com códigos ANSI (ex.: Windows Terminal, iTerm2, ou qualquer terminal Linux/macOS moderno).

---

## 📦 Instalação

```bash
# Clone o repositório:
git clone https://github.com/Erasmo-Veloso/ssx.git

# Navegue até o diretório do projeto:
cd src

# Compile o projeto:
javac -d bin src/main/*.java src/model/*.java src/repository/*.java

# Execute o programa:
java -cp bin main.Main
```

---

## 💻 Uso

### Início:
Ao executar, um banner animado será exibido. Escolha entre as opções 1 (Director), 2 (Professor) ou 3 (Sair).

### Como Director:
- Entre com o ID 1 (padrão: Erasmo).
- Gerencie professores (listar, criar, eliminar, atualizar), alunos e turmas.
- Use as opções do menu para adicionar ou remover alunos de turmas.

### Como Professor:
- Entre com o ID 1 (padrão: Carlos).
- Liste turmas, crie aulas e marque presenças.

### Validações:
- Tente criar um registro com um ID já existente (ex.: 1) para ver o aviso de duplicata.
- Insira valores inválidos (letras ou números fora do intervalo) para testar as validações.

---

## 🗂️ Estrutura do Projeto

src/main/: Contém a classe principal (Main.java) e a lógica de interação.  
src/model/: Define as classes de entidades (ex.: Aluno, Turma, Aula, Professor, Director).  
src/repository/: Gerencia coleções de dados (ex.: ProfessorsRepository, AlunosRepository).

---

## 🤝 Contribuições

Sinta-se à vontade para sugerir melhorias ou relatar problemas.  
Para contribuir, faça um fork do repositório, crie uma branch e envie um pull request.

---

## 🔮 Plano Futuro

- Geração Automática de IDs: Substituir a entrada manual de IDs por uma geração automática.  
- Persistência de Dados: Salvar e carregar informações em arquivos ou banco de dados.  
- Associação de Professores a Turmas: Vincular professores a turmas para controle de acesso.  
- Sumário de Aulas: Adicionar funcionalidade para registrar resumos de aulas.

---

## 📄 Licença
Este projeto está sob a licença MIT (a definir formalmente).

---

## 🙏 Agradecimentos
Desenvolvido com muito amor e carinho do grupo 04 do curso de informática do ITEL da Turma A3 11ª.
