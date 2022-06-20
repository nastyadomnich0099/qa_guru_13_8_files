package guru_qa;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;
public class JsonTest {

    ClassLoader classLoader = JsonTest.class.getClassLoader();

    @DisplayName("json with Jackson library test")
    @Test
    void jsonTest() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("testJson.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream));

        assertThat(jsonNode.get("firstName").asText()).isEqualTo("John");
        assertThat(jsonNode.get("lastName").asText()).isEqualTo("Smith");
        assertThat(jsonNode.get("isAlive").asBoolean()).isEqualTo(true);
        assertThat(jsonNode.findValue("age").asInt()).isEqualTo(20);


    }

}
