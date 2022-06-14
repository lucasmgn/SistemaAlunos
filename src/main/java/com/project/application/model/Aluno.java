package com.project.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
public class Aluno implements Comparable<Aluno> {

    private Long matricula;
    private String nome;
    private Integer frequencia;
    private List<Double> notas;
    private int serie;
    private SexoAluno sexo;

    public Aluno(Long matricula, String nome, Integer frequencia, List<Double> notas, int serie, SexoAluno sexo){
        this.matricula = matricula;
        this.nome = nome;
        this.frequencia = frequencia;
        this.notas = notas;
        this.serie = serie;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        String formatacaoNotas = Arrays.toString(notas.toArray()).replace("[", "").replace("]", "");
        return "Matricula = " + matricula +
                "\nNome = " + nome +
                "\nNotas = " + formatacaoNotas;
    }

    public Double getMedia(){
        double soma = 0.0;

        for(Double nota : this.notas){
            soma = soma + nota;
        }

        return soma/this.notas.size();
    }

    @Override
    public int compareTo(Aluno o) {
        if (this.matricula > o.matricula) {
            return 1;
        }else if(this.matricula < o.matricula){
            return -1;
        }
        return this.getMatricula().compareTo(o.getMatricula());
    }
}
