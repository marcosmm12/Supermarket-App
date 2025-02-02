package main.persistence.classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;

import java.util.Map;

/**
 * Classe que implementa la interfície {@link PrestatgeriaAd}.
 * Aquesta classe gestiona la persistència de les altures i distribucions de les prestatgeries.
 * Utilitza fitxers en format JSON per a carregar i guardar la informació.
 * @author keinthdc
 */
public class PrestatgeriaBD implements PrestatgeriaAd {
    /**
     * Atributs de la classe:
     * altures: Mapa que conté les altures de les prestatgeries.
     * distribucions: Mapa que conté les distribucions de les prestatgeries.
     * filePath: Ruta on es guarden els fitxers JSON.
     */
    private Map<String, Integer> altures;
    private Map<String, Vector<Integer>> distribucions;
    private static final String filePath = "data/";

    /**
     * Constructor per defecte que inicialitza els mapes d'altures i distribucions.
     */
    public PrestatgeriaBD() {
        altures = new TreeMap<>();
        distribucions = new TreeMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getAltura(String id) {
        return altures.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existAltura(String id) {
        return altures.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<Integer> getDistribucio(String id) {
        return distribucions.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existDistribucio(String id) {
        return distribucions.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarAltura(String id, Integer altura) {
        altures.put(id, altura);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarDistribucio(String id, Vector<Integer> distribucio) {
        distribucions.put(id, distribucio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void carregarAltures(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        altures = objectMapper.readValue(new File(filePath + nomFitxer + "AltSistema.json"), new TypeReference<Map<String, Integer>>() {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void carregarDistribucions(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        distribucions = objectMapper.readValue(new File(filePath + nomFitxer + "DistSistema.json"), new TypeReference<Map<String, Vector<Integer>>>() {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarAltures(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath + nomFitxer + "AltSistema.json"), altures);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarDistribucions(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath + nomFitxer + "DistSistema.json"), distribucions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getAlturaFitxer(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath + nomFitxer + "Alt.json"), Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<Integer> getDistribucioFitxer(String nomFitxer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath + nomFitxer + "Dist.json"), Vector.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarAlturaFitxer(String nomFitxer, Integer altura) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath + nomFitxer + "Alt.json"), altura);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardarDistribucioFitxer(String nomFitxer, Vector<Integer> distribucio) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath + nomFitxer + "Dist.json"), distribucio);
    }
}