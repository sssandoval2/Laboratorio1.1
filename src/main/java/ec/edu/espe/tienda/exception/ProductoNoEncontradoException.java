package ec.edu.espe.tienda.exception;

/** Se lanza cuando se consulta o elimina un producto que no existe. */
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("No existe un producto con id " + id);
    }
}
