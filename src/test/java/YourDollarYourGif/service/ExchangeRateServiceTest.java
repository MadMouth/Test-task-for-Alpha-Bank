package YourDollarYourGif.service;

import YourDollarYourGif.config.WireMockConfig;
import YourDollarYourGif.feignClient.OpenExchangeRatesMocks;
import YourDollarYourGif.feignClient.OpenExchangeRatesProxy;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-test.yml")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = {WireMockConfig.class})
class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;
    @Autowired
    private OpenExchangeRatesProxy openExchangeRatesProxy;
    @Autowired
    private WireMockServer wireExchangeRateService;

    @BeforeEach
    void setUp() throws IOException {
        OpenExchangeRatesMocks.setupMockOpenExchagneRatesResponse(wireExchangeRateService);
        exchangeRateService = new ExchangeRateService("app_id", openExchangeRatesProxy);
    }

    @Test
    void whenCallIsTheRateHigherTodayThanYesterday_thenShouldReturnFalse() {
        //given
        String currentDate = Instant.now().toString().split("T")[0];
        Boolean expectedValue = false;
        //when
        Boolean resultingValue = exchangeRateService.isTheRateHigherTodayThanYesterday("RUB");
        //then
        assertThat(expectedValue).isEqualTo(resultingValue);
    }

}