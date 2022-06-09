package YourDollarYourGif.service;

import YourDollarYourGif.feignClient.GiphyProxy;
import YourDollarYourGif.util.ParseJsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifUrlSenderService {

    private final GiphyProxy giphyProxy;
    private final String APP_KEY;

    public GifUrlSenderService(@Value("${giphy.app_key}") String APP_KEY,
                               GiphyProxy giphyProxy) {
        this.APP_KEY = APP_KEY;
        this.giphyProxy = giphyProxy;
    }

    public String getGifUrl(boolean isTheRateHigherTodayThanYesterday) {
        String embed_url;
        if (isTheRateHigherTodayThanYesterday) {
            embed_url = giphyProxy.getGif(APP_KEY, "rich");
        } else {
            embed_url = giphyProxy.getGif(APP_KEY, "broke");
        }
        String gifUrl = ParseJsonUtil.parseJsonString(embed_url, "data", "embed_url");
        return gifUrl;
    }
}
