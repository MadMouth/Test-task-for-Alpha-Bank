package YourDollarYourGif.service;

import YourDollarYourGif.feignClient.OpenExchangeRatesProxy;
import YourDollarYourGif.util.ParseJsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
public class ExchangeRateService {

    private final OpenExchangeRatesProxy openExchangeRatesProxy;
    private final String APP_ID;

    public ExchangeRateService(@Value("${open-exchange-rates.app_id:}") String APP_ID,
                               OpenExchangeRatesProxy openExchangeRatesProxy) {
        this.APP_ID = APP_ID;
        this.openExchangeRatesProxy = openExchangeRatesProxy;
    }

    public Boolean isTheRateHigherTodayThanYesterday(String currencyCode) {
        String currentDate = Instant.now().toString().split("T")[0];
        String yesterdayDate = Instant.now().minus(1, ChronoUnit.DAYS).toString().split("T")[0];
        String exchangeRateBySymbolId;

        exchangeRateBySymbolId = openExchangeRatesProxy.getHistoricalExchangeRateBySymbolId(APP_ID, currencyCode, currentDate);
        String currentValue = ParseJsonUtil.parseJsonString(exchangeRateBySymbolId, "rates", currencyCode);

        exchangeRateBySymbolId = openExchangeRatesProxy.getHistoricalExchangeRateBySymbolId(APP_ID, currencyCode, yesterdayDate);
        String yesterdayValue = ParseJsonUtil.parseJsonString(exchangeRateBySymbolId, "rates", currencyCode);

        return Float.parseFloat(currentValue) > Float.parseFloat(yesterdayValue);
    }
}
