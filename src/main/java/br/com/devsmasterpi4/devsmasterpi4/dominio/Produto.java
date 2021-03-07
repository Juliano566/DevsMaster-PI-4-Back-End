/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devsmasterpi4.devsmasterpi4.dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nails
 */
@Getter
@Setter

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private int estrelas;
    private boolean status;
    private int quantidade;
    private double preco;
    

    public Produto() {
    }

    public Produto(String nome) {
        this.nome = nome;
    }
    


    public Produto(int id, String nome, String descricao, int estrelas,
            boolean status, int quantidade, double preco) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estrelas = estrelas;
        this.status = status;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    
    public Produto( String nome, String descricao, int estrelas,
            boolean status, int quantidade, double preco) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estrelas = estrelas;
        this.status = status;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", estrelas=" + estrelas + ", status=" + status + ", quantidade=" + quantidade + ", preco=" + preco + '}';
    }
    
    

}
