package com.tecdesoftware.market_app.persistance.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;

public class Categoria {
}

@Entity
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id_categoria")
    private Interger idCategoria;

    private String descripcion;
    @Column (name="descripcion")

}