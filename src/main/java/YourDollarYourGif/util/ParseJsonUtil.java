package YourDollarYourGif.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class ParseJsonUtil {

    private ParseJsonUtil() {
    }

    public static String parseJsonString(String JsonData, String firstKey, String secondKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(JsonData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String value = Objects.requireNonNull(jsonNode).get(firstKey).get(secondKey).asText();

        return value;
    }
}
