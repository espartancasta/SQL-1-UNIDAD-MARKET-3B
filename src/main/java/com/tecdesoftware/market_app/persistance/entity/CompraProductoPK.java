package com.tecdesoftware.market_app.persistance.entity;

public class CompraProductoPK {
}
//ESTA CLASE ES PARA CREAR LA LLAVE COMPUESTA
@Embeddable

public class CompraProductoPK{
    @Column (name = "id_compra")
    private  Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;
}

    public Interger getIdCompra