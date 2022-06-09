package YourDollarYourGif.feignClient;

import YourDollarYourGif.config.WireMockConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ContextConfiguration(classes = {WireMockConfig.class})
class GiphyProxyTest {

    @Autowired
    private WireMockServer mockGiphyService;
    @Autowired
    private GiphyProxy giphyProxy;

    @BeforeEach
    void setUp() throws IOException {
        GiphyMocks.setupMockGiphyResponse(mockGiphyService);
    }

    @Test
    void whenGetGif_thenTheGifShouldBeReturned() {
        assertFalse(giphyProxy.getGif("api_key", "rich").isEmpty());
    }
}