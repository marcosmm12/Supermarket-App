package main.domain.libs;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import java.io.IOException;

/**
 * Aquesta classe permet deserialitzar una clau de tipus Pair<Integer, Integer> a partir d'un String
 * @author keinthdc
 */
public class PairKeyDeserializer extends KeyDeserializer {
    /**
     * Deserialitza una clau de tipus Pair<Integer, Integer> a partir d'un String
     * @param key String que representa la clau
     * @param ctxt Context de deserialització
     * @return Clau deserialitzada
     * @throws IOException Si la clau no es pot deserialitzar
     */
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        // Remove the curly braces and split the key by comma
        String[] parts = key.replace("{", "").replace("}", "").split(",");
        if (parts.length != 2) {
            throw new IOException("Invalid key format");
        }
        // Extract the values from the JSON key
        Integer first = Integer.parseInt(parts[0].split(":")[1].trim());
        Integer second = Integer.parseInt(parts[1].split(":")[1].trim());
        return new Pair<>(first, second);
    }
}