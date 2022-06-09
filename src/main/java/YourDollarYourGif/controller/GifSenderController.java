package YourDollarYourGif.controller;

import YourDollarYourGif.service.ExchangeRateService;
import YourDollarYourGif.service.GifUrlSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/have-i-become-richer-today")
public class GifSenderController {

    private final ExchangeRateService exchangeRateService;
    private final GifUrlSenderService gifUrlSenderService;
    @Value("${currency-code.default}")
    private String symbol;

    @GetMapping("/default-currency")
    public RedirectView getAGifRelativeToTheExchangeRate() {
        Boolean isTheRateHigherTodayThanYesterday = exchangeRateService.isTheRateHigherTodayThanYesterday(symbol);
        String gifUrl = gifUrlSenderService.getGifUrl(isTheRateHigherTodayThanYesterday);
        return new RedirectView(gifUrl);
    }

    @GetMapping
    public RedirectView getAGifRelativeToTheExchangeRateBySymbol(@RequestParam("symbol") String symbol) {
        Boolean isTheRateHigherTodayThanYesterday = exchangeRateService.isTheRateHigherTodayThanYesterday(symbol);
        String gifUrl = gifUrlSenderService.getGifUrl(isTheRateHigherTodayThanYesterday);
        return new RedirectView(gifUrl);
    }
}