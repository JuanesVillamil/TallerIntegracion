import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.example.ArquiSoft.model.Oferta;
import com.example.ArquiSoft.ArquiSoftApplication;
import com.example.ArquiSoft.model.Postor;
import com.example.ArquiSoft.model.Subasta;
import com.example.ArquiSoft.model.dao.CrearOfertaDAO;
import com.example.ArquiSoft.repository.OfertaRepository;
import com.example.ArquiSoft.repository.SubastaRepository;
import com.example.ArquiSoft.repository.PostorRepository;

// ./mvnw test -Dtest=OfertaControllerIntegrationTest
@ActiveProfiles("integration-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ArquiSoftApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class OfertaControllerIntegrationTest {

    private static final String SERVER_URL = "http://localhost:8081";

    @Autowired
    private PostorRepository postorRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private SubastaRepository subastaRepository;

    @Autowired
    private TestRestTemplate rest;

    @BeforeEach
    void init() {
        generarDatos(5);
    }

    private void generarDatos(int cantidad) {
        List<Postor> postores = new ArrayList<>();
        List<Subasta> subastas = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < cantidad; i++) {
            String nombre = "Usuario " + i;
            String nombreProducto = "Producto " + i;
            int tiempoSubasta = rand.nextInt(2) + 1;
            Postor postor = new Postor(nombre);
            Subasta subasta = new Subasta(nombreProducto, tiempoSubasta);

            subasta = subastaRepository.save(subasta);
            postor = postorRepository.save(postor);

            Oferta oferta = new Oferta(rand.nextDouble(1000));
            oferta.setPostor(postor);
            oferta.setSubasta(subasta);
            postor.agregarOferta(oferta);
            subasta.agregarOferta(oferta);
            ofertaRepository.save(oferta);

            postores.add(postor);
            subastas.add(subasta);
        }
    }

    @Test
    public void hacerOferta() {
        CrearOfertaDAO crearOfertaDAO = new CrearOfertaDAO();
        crearOfertaDAO.setValor(13002);
        crearOfertaDAO.setPostorId(1L);  // Asume que el ID del postor es 1
        crearOfertaDAO.setSubastaId(1L); // Asume que el ID de la subasta es 1

        ResponseEntity<Oferta> response = rest.postForEntity(SERVER_URL + "/api/oferta/ofertar",
                crearOfertaDAO,
                Oferta.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}