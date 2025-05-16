package com.tecdesoftware.market_app.persistance.entity;

import jakarta.persistence.*; // o javax.persistence.* dependiendo de tu configuración

@Entity
@Table(name = "compras_productos")
public class CompraProducto {

    @EmbeddedId
    private CompraProductoPk id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Boolean estado;

    // Getters y Setters
    public CompraProductoPk getId() {
        return id;
    }

    public void setId(CompraProductoPk id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
