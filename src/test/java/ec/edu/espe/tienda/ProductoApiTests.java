package ec.edu.espe.tienda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoApiTests {

    @Autowired
    private MockMvc mvc;

    private static final String VALIDO = "{\"nombre\":\"Laptop\",\"precio\":850.5,\"stock\":10}";

    @Test
    void crearConsultarYEliminar() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON).content(VALIDO))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Laptop"));
        mvc.perform(get("/api/productos")).andExpect(status().isOk());
        mvc.perform(delete("/api/productos/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/productos/1")).andExpect(status().isNotFound());
    }

    @Test
    void precioInvalidoDevuelve400() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"X\",\"precio\":0,\"stock\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.estado").value(400));
    }

    @Test
    void stockNegativoDevuelve400() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"X\",\"precio\":5,\"stock\":-1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void inexistenteDevuelve404() throws Exception {
        mvc.perform(get("/api/productos/999")).andExpect(status().isNotFound());
        mvc.perform(delete("/api/productos/999")).andExpect(status().isNotFound());
    }
}
