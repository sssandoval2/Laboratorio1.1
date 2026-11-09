package ec.edu.espe.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ec.edu.espe.tienda.exception.ProductoInvalidoException;
import ec.edu.espe.tienda.exception.ProductoNoEncontradoException;
import ec.edu.espe.tienda.model.Producto;

/** Reglas de negocio de productos. Los datos viven en una coleccion en memoria. */
@Service
public class ProductoService {

    private final Map<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>(productos.values());
        lista.sort((a, b) -> Long.compare(a.getId(), b.getId()));
        return lista;
    }

    public Producto buscarPorId(Long id) {
        Producto producto = productos.get(id);
        if (producto == null) {
            throw new ProductoNoEncontradoException(id);
        }
        return producto;
    }

    public Producto crear(Producto producto) {
        if (producto == null) {
            throw new ProductoInvalidoException("El cuerpo de la solicitud es obligatorio");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoInvalidoException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }
        producto.setId(secuencia.incrementAndGet());
        productos.put(producto.getId(), producto);
        return producto;
    }

    public void eliminar(Long id) {
        if (productos.remove(id) == null) {
            throw new ProductoNoEncontradoException(id);
        }
    }
}
