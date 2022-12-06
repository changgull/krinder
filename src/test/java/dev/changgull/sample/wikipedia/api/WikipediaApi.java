package dev.changgull.sample.wikipedia.api;

import dev.changgull.core.BaseApi;

public class WikipediaApi extends BaseApi {
    public WikipediaApi() {
        buildRequest(getProperty("url.wikipedia.api"));
    }
}
