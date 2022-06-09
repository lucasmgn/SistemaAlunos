package com.project.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Aluno {

    private Long matricula;
    private String nome;
    private Integer frequencia;
    private List<Double> notas = new ArrayList<Double>();
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
        return "Matricula = " + matricula +
                "\nNome = " + nome +
                "\nFrequência = " + frequencia + " aulas" +
                "\nNotas = " + notas +
                "\nSérie = " + serie + "ºano" +
                "\nSexo = " + sexo;
    }

    public Double getMedia(){
        double soma = 0.0;

        for(Double nota : this.notas){
            soma = soma + nota;
        }

        return soma/this.notas.size();
    }
}
