package com.erp.pizzeria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/** Conjunto completo de datos del panel de reportes, reutilizado por la vista y la exportacion (PDF/Excel). */
public record ReporteDataDTO(
    String generadoEn,
    List<StatDTO> salesStats,
    List<TopProductoDTO> topProductos,
    List<TipoMovReporteDTO> movimientosPorTipo,
    List<ProveedorReporteDTO> comprasPorProveedor,
    List<AnuladoReporteDTO> anulados){
}
