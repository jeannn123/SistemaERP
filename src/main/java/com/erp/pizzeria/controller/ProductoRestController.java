package com.erp.pizzeria.controller;

import com.erp.pizzeria.dto.ProductoDTO;
import com.erp.pizzeria.dto.ProductoFormDTO;
import com.erp.pizzeria.dto.StockAlertDTO;
import com.erp.pizzeria.model.Producto;
import com.erp.pizzeria.service.CatalogService;
import com.erp.pizzeria.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoRestController {

    private final CatalogService catalogService;
    private final InventarioService inventarioService;

    public ProductoRestController(CatalogService catalogService,
                                  InventarioService inventarioService) {
        this.catalogService = catalogService;
        this.inventarioService = inventarioService;
    }

    @GetMapping("/api/productos")
    public List<ProductoDTO> productos(@RequestParam(required = false) Integer categoriaId) {
        List<Producto> productos = (categoriaId == null)
                ? catalogService.listProductosDisponibles()
                : catalogService.listProductosDisponiblesPorCategoria(categoriaId);
        return productos.stream().map(ProductoDTO::from).toList();
    }

    @GetMapping("/api/stock/check")
    public StockAlertDTO checkStock(@RequestParam Integer productoId,
                                    @RequestParam(defaultValue = "1") int cantidad) {
        Producto producto = catalogService.getProducto(productoId);
        return inventarioService.verificarStock(producto, cantidad);
    }
    @GetMapping("/api/productos/{id}")
    public ProductoDTO productoId(@PathVariable Integer id){
        Producto producto = catalogService.getProducto(id);
        return ProductoDTO.from(producto);
    }
    @PostMapping("/api/productos")
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoFormDTO form){
            Producto producto=catalogService.crearProducto(form);
            ProductoDTO productoDTO=ProductoDTO.from(producto);
            return ResponseEntity.ok(productoDTO);
    }
    @PutMapping("/api/productos/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Integer id
            ,@Valid @RequestBody ProductoFormDTO productoform){
            Producto producto=catalogService.actualizarProducto(id,productoform);
            ProductoDTO productoDTO=ProductoDTO.from(producto);
        return ResponseEntity.ok(productoDTO);
    }
    @DeleteMapping("/api/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id){
        catalogService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}