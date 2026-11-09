package ec.edu.espe.tienda.exception;

/** Cuerpo JSON que devuelve la API cuando ocurre un error. */
public record ErrorRespuesta(String mensaje, int estado) {
}
