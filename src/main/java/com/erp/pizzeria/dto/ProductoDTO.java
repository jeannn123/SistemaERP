package com.erp.pizzeria.dto;

import com.erp.pizzeria.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


public record ProductoDTO (
    Integer idProducto,
    String codigo,
    String nombre,
    BigDecimal precio,
    Integer stock,
    Boolean disponible,
    Integer idCategoria,
    String categoria,
    boolean preparado
){
    public static ProductoDTO from(Producto p) {
        return new ProductoDTO(
                p.getIdProducto(),
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getDisponible(),
                p.getCategoria()!=null?p.getCategoria().getIdCategoria():null,
                p.getCategoria()!=null?p.getCategoria().getNombre():null,
                p.getStock()==null
        );
    }
}
