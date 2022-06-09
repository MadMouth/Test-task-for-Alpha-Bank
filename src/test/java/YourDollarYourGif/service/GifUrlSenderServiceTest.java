package YourDollarYourGif.service;

import YourDollarYourGif.config.WireMockConfig;
import YourDollarYourGif.feignClient.GiphyMocks;
import YourDollarYourGif.feignClient.GiphyProxy;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ContextConfiguration(classes = {WireMockConfig.class})
class GifUrlSenderServiceTest {

    private GifUrlSenderService gifUrlSenderService;
    @Autowired
    private GiphyProxy giphyProxy;
    @Autowired
    private WireMockServer wireMockGiphyProxy;

    @BeforeEach
    void setUp() throws IOException {
        GiphyMocks.setupMockGiphyResponse(wireMockGiphyProxy);
        gifUrlSenderService = new GifUrlSenderService("api_key", giphyProxy);
    }

    @Test
    void whenGetGifUrl_thenReturnURL() {
        //given
        String expectedValue = "https://giphy.com/embed/H7xNGb7tWrtjvy9gNi";
        //when
        String resultingValue = gifUrlSenderService.getGifUrl(true);
        //then
        assertThat(resultingValue).isEqualTo(expectedValue);
    }
}