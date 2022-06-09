package YourDollarYourGif.util;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class ParseJsonUtilTest {

    @Test
    void ItShouldParseJsonString() {
        // given
        String jsonData = """
                { "data" : {"username" : "Antoshka_2009"}}""";
        String firstKey = "data";
        String secondKey = "username";
        String expectedValue = "Antoshka_2009";
        //when-then
        try (MockedStatic<ParseJsonUtil> utilities = Mockito.mockStatic(ParseJsonUtil.class)) {
            utilities.when(() -> ParseJsonUtil.parseJsonString(jsonData, firstKey, secondKey))
                    .thenReturn(expectedValue);
        }
    }
}