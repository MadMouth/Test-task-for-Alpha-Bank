package YourDollarYourGif.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "ExchangeRatesClient", url = "${open-exchange-rates.url}")
public interface OpenExchangeRatesProxy {
    @GetMapping("/api/historical/{date}.json")
    String getHistoricalExchangeRateBySymbolId
            (@RequestParam("app_id") String app_id, @RequestParam("symbols") String symbols, @PathVariable("date") String date);
}
