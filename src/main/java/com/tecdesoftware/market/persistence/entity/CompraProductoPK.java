package com.tecdesoftware.market.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column(name= "id_compra")
    private int idCompra;

    @Column(name="id_producto")
    private int idProducto;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompraProductoPK)) return false;
        CompraProductoPK that = (CompraProductoPK) o;
        return idCompra == that.idCompra &&
                idProducto == that.idProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }
}
