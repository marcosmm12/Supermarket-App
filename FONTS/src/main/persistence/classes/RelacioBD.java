package main.persistence.classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import main.domain.libs.*;

import java.io.*;
import java.util.*;

/**
 * Classe que implementa la interfície {@link RelacioAd}.
 * Aquesta classe gestiona la persistència de les relacions entre productes.
 * Utilitza fitxers en format JSON per a carregar i guardar la informació.
 * @author erikGauchia
 */

 public class RelacioBD implements RelacioAd{
    private Map<String, Map<Pair<Integer,Integer>,Integer>> relacions;
    private static final String filePath = "data/";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static SimpleModule module = new SimpleModule();

    /**
     * Constructora per defecte que inicialitza el mapa de relacions.
     */

    public RelacioBD() {
        relacions = new HashMap<>();
        module.addKeyDeserializer(Pair.class, new PairKeyDeserializer());
        objectMapper.registerModule(module);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Pair<Integer, Integer>, Integer> getRelacio(String id) {
        return relacions.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existRelacio(String id) {
        return relacions.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarRelacio(String id, Map<Pair<Integer, Integer>, Integer> relacio) {
        relacions.put(id, relacio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void carregarRelacions(String nomFitxer) throws IOException {
        relacions = objectMapper.readValue(new File(filePath + nomFitxer + "RelSistema.json"),
                objectMapper.getTypeFactory().constructMapType(
                        Map.class,
                        objectMapper.getTypeFactory().constructType(String.class),
                        objectMapper.getTypeFactory().constructMapType(
                                Map.class,
                                objectMapper.getTypeFactory().constructType(Pair.class),
                                objectMapper.getTypeFactory().constructType(Integer.class))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarRelacions(String nomFitxer) throws IOException{
        objectMapper.writeValue(new File(filePath + nomFitxer + "RelSistema.json"), relacions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Pair<Integer, Integer>, Integer> getRelacioFitxer(String nomFitxer) throws IOException{
        return objectMapper.readValue(new File(filePath + nomFitxer + "Rel.json"),
                new TypeReference<Map<Pair<Integer,Integer>,Integer>>() {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarRelacioFitxer(String nomFitxer, Map<Pair<Integer, Integer>, Integer> relacio) throws IOException{
        objectMapper.writeValue(new File(filePath + nomFitxer + "Rel.json"), relacio);
    }
}