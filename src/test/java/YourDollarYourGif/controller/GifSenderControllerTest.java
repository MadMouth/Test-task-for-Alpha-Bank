package YourDollarYourGif.controller;

import YourDollarYourGif.service.ExchangeRateService;
import YourDollarYourGif.service.GifUrlSenderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GifSenderController.class)
@AutoConfigureMockMvc
class GifSenderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @MockBean
    private GifUrlSenderService gifUrlSenderService;

    @Test
    void whenGivenDefaultExchangeRate_thenReturnURL() throws Exception {
        given(exchangeRateService.isTheRateHigherTodayThanYesterday("symbol")).isNotNull();
        mockMvc.perform(get("/default-currency"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void whenGivenExchangeRateWithParam_thenReturnURL() throws Exception {
        given(exchangeRateService.isTheRateHigherTodayThanYesterday("symbol")).isNotNull();
        mockMvc.perform(get("/")
                        .param("symbol", "symbol"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}