package com.franquicia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
@Builder(toBuilder = true)
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long productoID;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidad_stock")
    private int cantidadStock;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private SucursalEntity sucursal;
}
