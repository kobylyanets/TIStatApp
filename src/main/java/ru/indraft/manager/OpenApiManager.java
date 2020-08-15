package ru.indraft.manager;

import ru.indraft.service.OpenApiService;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory;

import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class OpenApiManager {

    private static final String TOKEN_KEY = "tinkoff.invest.openapi.token";
    private static final String SANDBOX_TOKEN_KEY = "tinkoff.invest.openapi.sandbox.token";

    private static final Logger logger = Logger.getLogger(OpenApiService.class.getName());
    private static OpenApi api;

    private static ResourceBundle getSecretResourceBundle() {
        return ResourceBundle.getBundle("bundles.secret");
    }

    private static String getToken() {
        return getSecretResourceBundle().getString(TOKEN_KEY);
    }

    private static String getSandboxToken() {
        return getSecretResourceBundle().getString(SANDBOX_TOKEN_KEY);
    }

    public static OpenApi getOpenApi() {
        if (api == null) {
            api = createOpenApi();
        }
        return api;
    }

    private static OpenApi createOpenApi() {
        var factory = new OkHttpOpenApiFactory(getToken(), logger);
        return factory.createOpenApiClient(Executors.newSingleThreadExecutor());
    }

    public static void closeOpenApi() {
        if (api != null) {
            try {
                api.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
