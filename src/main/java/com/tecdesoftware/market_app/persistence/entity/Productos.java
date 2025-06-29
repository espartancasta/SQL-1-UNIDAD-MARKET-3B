package com.tecdesoftware.market_app.persistance.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "Productos")
public class Productos {


    @Id //llave primaria
    //Hace el id autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products")
    private Integer idProducto;

    private String nombre;

    @Column(name ="id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column (name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //Relación con la entidad Cliente : Muchas compras a un cliente
    @ManyToOne
    //No quiero que se modifique la entidad cliente, solo relacionarla
    @JoinColumn(name ="id_categoria", insertable=false, updatable=false)
    private Categoria Categoria;


//Métodos publicos//

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
