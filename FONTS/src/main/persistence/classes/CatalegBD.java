package main.persistence.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
import main.domain.classes.Producte;

/**
 * Classe que implementa la interfície {@link CatalegAd}.
 * Aquesta classe gestiona la persistència dels catàlegs de productes.
 * Utilitza fitxers en format JSON per a carregar i guardar la informació.
 * @author DavidComino
 * @author keinthdc
 */
public class CatalegBD implements CatalegAd {
    /**
     * Atributs de la classe:
     * catalegs: Mapa dels catàlegs de productes guardats al programa.
     * filePath: Ruta on es guarden els fitxers.
     * objectMapper: Objecte per a la lectura i escriptura de fitxers JSON.
     */
    private Map<String, Map<Integer, Producte>> catalegs;
    private static final String filePath = "data/";
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructora per defecte que inicialitza el mapa de catàlegs.
     */
    public CatalegBD() {
        catalegs = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Producte> getCataleg(String id) {
        return catalegs.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarCataleg(String id, Map<Integer, Producte> cataleg) {
        catalegs.put(id, cataleg);
        //System.out.println(catalegs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existCataleg(String id) {
        return catalegs.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void carregarCatalegs(String nomFitxer) throws IOException {
        //System.out.println("filePath: " + filePath + nomFitxer + "Cat.dat");
        catalegs = objectMapper.readValue(
            new File(filePath + nomFitxer + "CatSistema.json"),
                objectMapper.getTypeFactory().constructMapType(
                        Map.class,
                        objectMapper.getTypeFactory().constructType(String.class),
                        objectMapper.getTypeFactory().constructMapType(
                                Map.class,
                                objectMapper.getTypeFactory().constructType(Integer.class),
                                objectMapper.getTypeFactory().constructType(Producte.class))));
        //System.out.println(catalegs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarCatalegs(String nomFitxer) throws IOException {
        //System.out.println(catalegs);
        //System.out.println(objectMapper.writeValueAsString(catalegs));
        objectMapper.writeValue(new File(filePath + nomFitxer + "CatSistema.json"), catalegs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Producte> getCatalegFitxer(String nomFitxer) throws IOException {
        return objectMapper.readValue(
                new File(filePath + nomFitxer + "Cat.json"),
                objectMapper.getTypeFactory().constructMapType(
                        Map.class,
                        objectMapper.getTypeFactory().constructType(Integer.class),
                        objectMapper.getTypeFactory().constructType(Producte.class)
                )
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarCatalegFitxer(String nomFitxer, Map<Integer, Producte> cataleg) throws IOException {
        objectMapper.writeValue(new File(filePath + nomFitxer + "Cat.json"), cataleg);
    }
} 