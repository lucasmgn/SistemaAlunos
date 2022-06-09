package com.project.application;

import com.project.application.model.Aluno;
import com.project.application.model.SexoAluno;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Principal {

    public static void main(String[] args) throws Exception {

        System.out.println(" ");
        System.out.println("======================= Bem Vindo o Colégio FacsInternational ========================");
        System.out.println(" Olá professor(a), digite o que o senhor(a) deseja buscar.");
        System.out.println(" Opcoes :                                  ");
        System.out.println("        1. Cadastrar Aluno               ");
        System.out.println("        2. Listar Alunos                   ");
        System.out.println("        3. A maior e a menor nota da turma ");
        System.out.println("        4. O total de alunos reprovados ");
        System.out.println("        5. A percentagem de alunos reprovados por frequência abaixo da mínima necessária");
        System.out.println("        9. Exit                      ");
        System.out.println("===========================================================================================");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Aluno> alunos = new ArrayList<>();

        int option = 0;

        while (option != 9) {
            System.out.print("Selecione uma opcao: ");
            String opcao = reader.readLine();
            option = Integer.parseInt(opcao);

            switch (option) {
                case 1 -> cadastrandoAluno(reader, alunos);
                case 2 -> listarAlunos(alunos);
                case 3 -> maiorMenorNota(alunos);
            }
    }

    }

    public static void cadastrandoAluno(BufferedReader reader, List<Aluno> alunos) throws Exception {

        System.out.println(" ");
        System.out.println("------- Cadastrando um novo Aluno -------");
        System.out.println(" ");

        String nome;
        Long matricula;
        Integer frequencia;
        Integer serie;
        Integer sexoAlunoN;
        SexoAluno sexoAluno;

        try {
            System.out.print("Informe a matricula do aluno: ");
            matricula = Long.parseLong(reader.readLine());

            if(matricula != null){
                for (Aluno aluno : alunos) {
                    if (matricula == aluno.getMatricula()) {
                        throw new Exception();
                    }
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("A matricula do aluno precisar ser número válido. ");
        }

        try {
            System.out.print("Informe o nome completo do aluno: ");
            nome = reader.readLine();

            if(!nome.isBlank()) {
                for (Aluno aluno : alunos) {
                    if (nome.equalsIgnoreCase(aluno.getNome())) {
                        throw new Exception();
                    }
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("O campo precisa ser preenchido de maneira corretamente e não pode ser repetido.");
        }

        try {
            System.out.print("Informe a frequencia do aluno: ");
            frequencia = Integer.parseInt(reader.readLine());

            if(frequencia == null){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("A frequencia do aluno precisar ser número válido");
        }

        List<Double> notas = new ArrayList<>();
        try {
            for (int i = 1; i < 4; i++) {
                System.out.print("Informe a " + i + "° nota do aluno: ");
                Double nota = Double.parseDouble(reader.readLine());
                if (nota > 10 || nota < 0 || nota == null) {
                    throw new Exception();
                }
                notas.add(nota);
            }
        } catch (Exception e) {
            throw new Exception("A nota do aluno precisar ser número válido, menor ou igual a 10");
        }

        try {
            System.out.print("Informe a série do aluno(apenas números): ");
            serie = Integer.parseInt(reader.readLine());
            if(serie == null || serie < 0 || serie > 3){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("A série do aluno precisar ser número válido.");
        }

        try {
            System.out.print("Informe o sexo do aluno, 1.(Masculino) 2.(Femenino): ");
            sexoAlunoN = Integer.parseInt(reader.readLine());

            if(sexoAlunoN != null){
                if (sexoAlunoN == 1) {
                    sexoAluno = SexoAluno.MASCULINO;
                } else {
                    sexoAluno = SexoAluno.FEMENINO;
                }

            }else{
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("O sexo do aluno precisar ser número válido e não pode estar vázio.");
        }

        Aluno aluno = new Aluno(matricula, nome, frequencia, notas, serie, sexoAluno);
        alunos.add(aluno);
        System.out.println(" ");
        System.out.println("Aluno cadastrado com sucesso!");
        System.out.println(" ");
    }

    public static void listarAlunos(List<Aluno> alunos){
        System.out.println(" ");
        System.out.println("------- Lita de alunos -------");

        for(Aluno aluno:alunos){
            System.out.println(aluno);
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void maiorMenorNota(List<Aluno> alunos){
        Set<Double> medias = new HashSet<>();

        for(Aluno aluno : alunos){
            medias.add(aluno.getMedia());
        }

        System.out.println("Maior Nota é: " +  Collections.max(medias));
        System.out.println("Menor Nota é: " +  Collections.min(medias));
    }

    public static void alunosRepovrados(List<Aluno> alunos){
        int cont = 0;
        for (Aluno aluno: alunos) {
            if (aluno.getMedia() < 6 || aluno.getFrequencia() < 10) {
                cont++;
                System.out.println("Nome: " + aluno.getNome() + ", Matricula: " + aluno.getMatricula());
            }
        }

        System.out.println("O total de alunos reprovados é: " + cont);
    }
}