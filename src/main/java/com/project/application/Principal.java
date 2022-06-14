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
        System.out.println("        2. Buscar Aluno               ");
        System.out.println("        3. Listar Alunos                   ");
        System.out.println("        4. Remover Aluno                   ");
        System.out.println("        5. A maior e a menor nota da turma ");
        System.out.println("        6. O total de alunos reprovados ");
        System.out.println("        7. A percentagem de alunos reprovados por frequência abaixo da mínima necessária");
        System.out.println("        9. Exit                      ");
        System.out.println("===========================================================================================");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Aluno> alunos = new ArrayList<>();
        alunosFixos(alunos);

        int option = 0;

        while (option != 9) {
            System.out.print("Selecione uma opcao: ");
            String opcao = reader.readLine();
            option = Integer.parseInt(opcao);

            switch (option) {
                case 1 -> cadastrandoAluno(reader, alunos);
                case 2 -> buscarAluno(reader, alunos);
                case 3 -> listarAlunos(alunos);
                case 4 -> removerAluno(reader,alunos);
                case 5 -> maiorMenorNota(alunos);
                case 6 -> alunosRepovrados(alunos);
                case 7 -> alunosReprovadosPorFrequencia(alunos);
            }
    }

    }

    private static void alunosFixos(List<Aluno> alunos) {
        List<Double> notasFixo1 = new ArrayList<>();
        notasFixo1.add(10.0);
        notasFixo1.add(7.8);
        notasFixo1.add(8.5);

        List<Double> notasFixo2 = new ArrayList<>();
        notasFixo2.add(10.0);
        notasFixo2.add(10.0);
        notasFixo2.add(9.9);

        List<Double> notasFixo3 = new ArrayList<>();
        notasFixo3.add(6.0);
        notasFixo3.add(9.8);
        notasFixo3.add(5.8);

        List<Double> notasFixo4 = new ArrayList<>();
        notasFixo4.add(5.5);
        notasFixo4.add(4.9);
        notasFixo4.add(6.7);

        List<Double> notasFixo5 = new ArrayList<>();
        notasFixo5.add(5.0);
        notasFixo5.add(9.9);
        notasFixo5.add(9.0);

        List<Double> notasFixo6 = new ArrayList<>();
        notasFixo6.add(7.87);
        notasFixo6.add(10.0);
        notasFixo6.add(6.54);

        List<Double> notasFixo7 = new ArrayList<>();
        notasFixo7.add(6.0);
        notasFixo7.add(7.4);
        notasFixo7.add(8.1);

        Aluno aluno1 = new Aluno(1L, "Carlos Magno Alberto Queiroz", 12, notasFixo1, 1, SexoAluno.MASCULINO);
        Aluno aluno2 = new Aluno(2L, "Leiticia Queiroz Viana", 20, notasFixo2, 1, SexoAluno.FEMENINO);
        Aluno aluno3 = new Aluno(3L, "Lucas Magno Peixoto Lima", 16, notasFixo3, 1, SexoAluno.MASCULINO);
        Aluno aluno4 = new Aluno(4L, "Laura Cardozo Lima", 10, notasFixo4, 1, SexoAluno.FEMENINO);
        Aluno aluno5 = new Aluno(5L, "Vitoria Augusta Santos de Oliveira", 20, notasFixo5, 1, SexoAluno.FEMENINO);
        Aluno aluno6 = new Aluno(6L, "Isadora Di Caprio Vieira", 18, notasFixo6, 1, SexoAluno.FEMENINO);
        Aluno aluno7 = new Aluno(7L, "Iago Novais de Souza", 19, notasFixo7, 1, SexoAluno.MASCULINO);

        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        alunos.add(aluno5);
        alunos.add(aluno6);
        alunos.add(aluno7);
    }

    public static void cadastrandoAluno(BufferedReader reader, List<Aluno> alunos) throws Exception {
        System.out.println(" ");
        System.out.println("------- Cadastrando um novo Aluno -------");
        System.out.println(" ");

        String nome;
        long matricula;
        int frequencia;
        int serie;
        int sexoAlunoN;
        SexoAluno sexoAluno;

        try {
            System.out.print("Informe a matricula do aluno: ");
            matricula = Long.parseLong(reader.readLine());

            if(matricula > 0){
                for (Aluno aluno : alunos) {
                    if (matricula == aluno.getMatricula()) {
                        throw new Exception();
                    }
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("A matricula do aluno precisar ser um número válido, ou uma matricula diferente das demais. ");
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
            if(frequencia > 25 || frequencia < 0){
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("A frequencia do aluno precisar ser número válido");
        }

        List<Double> notas = new ArrayList<>();
        try {
            for (int i = 1; i < 4; i++) {
                System.out.print("Informe a " + i + "° nota do aluno: ");
                double nota = Double.parseDouble(reader.readLine());
                if (nota > 10 || nota < 0) {
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
            if(serie < 0 || serie > 3){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("A série do aluno precisar ser número válido.");
        }

        try {
            System.out.print("Informe o sexo do aluno, 1.(Masculino) 2.(Femenino): ");
            sexoAlunoN = Integer.parseInt(reader.readLine());

            if (sexoAlunoN == 1) {
                sexoAluno = SexoAluno.MASCULINO;
            } else {
                sexoAluno = SexoAluno.FEMENINO;
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

    public static void buscarAluno(BufferedReader reader, List<Aluno> alunos) throws Exception {
        System.out.println(" ");
        System.out.println("------- Buscando Aluno -------");
        System.out.println(" ");

        long matriculaBusca;

        try {
            System.out.print("Digite o número da matricula que você deseja buscar:");
            matriculaBusca = Long.parseLong(reader.readLine());

            if (matriculaBusca > 0) {
                for (Aluno aluno : alunos) {
                    if (matriculaBusca == aluno.getMatricula()) {
                        System.out.println(" ");
                        System.out.println(aluno);
                        System.out.println("Serie: " + aluno.getSerie() + "º ano");
                        System.out.println("Sexo: " + aluno.getSexo());
                        System.out.println("Media: " + Math.round(aluno.getMedia()));
                        System.out.println("Frequência: " + aluno.getFrequencia());
                        System.out.println(" ");
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("A matricula do aluno precisar ser um número válido, ou uma matricula existente. ");
        }
    }

    public static void listarAlunos(List<Aluno> alunos) {
        System.out.println(" ");
        System.out.println("------- Lita de alunos matriculados -------");

        Collections.sort(alunos);



        for (Aluno aluno : alunos) {
            System.out.println(aluno);
            System.out.println(" ");
        }
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
            if (aluno.getMedia() < 6) {
                cont++;
                System.out.println("Nome: " + aluno.getNome() + ", Matricula: " + aluno.getMatricula());
            }
        }

        System.out.println("O total de alunos reprovados é: " + cont);
    }

    private static void alunosReprovadosPorFrequencia(List<Aluno> alunos) {
        int cont = 0;
        for (Aluno aluno: alunos) {
            if (aluno.getFrequencia() < 15) {
                System.out.println("Nome: " + aluno.getNome() + ", Matricula: " + aluno.getMatricula());
                cont++;
            }
        }

        float porcFrenquencia = (float) ((cont * 100)/alunos.size());
        System.out.println("O total de alunos reprovados por frequência é: " + Math.round(porcFrenquencia) + "%");
    }

    public static void removerAluno(BufferedReader reader, List<Aluno> alunos) throws Exception {
        System.out.println(" ");
        System.out.println("------- Remover Aluno -------");
        System.out.println(" ");

        long matriculaRemocao;

        try {
            System.out.print("Digite o número da matricula que você deseja remover:");
            matriculaRemocao = Long.parseLong(reader.readLine());

            if (matriculaRemocao > 0) {
                for (int i = 0; i < alunos.size(); i++) {
                    if (matriculaRemocao == alunos.get(i).getMatricula()) {
                        alunos.remove(alunos.get(i));
                        System.out.println(" ");
                        System.out.println("Aluno Removido com sucesso!!");
                        System.out.println(" ");
                    }
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception("A matricula do aluno precisar ser um número válido, ou uma matricula existente. ");
        }
    }
}