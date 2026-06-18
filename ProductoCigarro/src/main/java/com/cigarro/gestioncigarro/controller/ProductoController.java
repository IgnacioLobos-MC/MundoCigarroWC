package com.cigarro.gestioncigarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cigarro.gestioncigarro.model.Producto;
import com.cigarro.gestioncigarro.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos",
    description = "Endpoints para gestionar los productos de Mundo Cigarro"
)
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar Productos",
        description = "Obtiene una lista de todos los productos disponibles en Mundo Cigarro"
    )

    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoService.mostrarProducto();
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Producto por su ID",
        description = "Obtiene los detalles de un producto específico utilizando su ID"
    )

    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
        try{
            Producto producto = productoService.buscaProductoId(id);
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo Producto",
        description = "Agrega un nuevo producto a la base de datos de Mundo Cigarro"
    )
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto unProducto){
        Producto productoNuevo = productoService.creaProducto(unProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un Producto existente",
        description = "Actualiza los detalles de un producto existente en la base de datos de Mundo Cigarro"
    )

    public ResponseEntity<Producto> modificar(@PathVariable Long id, @RequestBody Producto unProducto){
        try{
            Producto pro = productoService.buscaProductoId(id);
            pro.setIdProducto(id);
            pro.setNombre(unProducto.getNombre());
            pro.setMarca(unProducto.getMarca());
            pro.setTipo(unProducto.getTipo());
            pro.setPrecio(unProducto.getPrecio());
            productoService.creaProducto(pro);
            return ResponseEntity.ok(pro);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Producto",
        description = "Elimina un producto específico de la base de datos de Mundo Cigarro utilizando su ID"
    )
    
    public String eliminarProducto(@PathVariable Long id){
        try{
            productoService.eliminarProducto(id);
            return "El producto ha sido eliminado satisfactoriamente :v";
        }
        catch (Exception e){
            return "El producto no existe";
        }
    }

}
