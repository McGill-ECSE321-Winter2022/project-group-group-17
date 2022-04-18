package ca.mcgill.ecse321.grocerystoresystem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Helper class that is used for all the HTTP requests of the application.
 */
public class HttpUtils {

    //URL of the backend.
    public static final String DEFAULT_BASE_URL = "https://grocerystoresys-backend-22ws.herokuapp.com/";
    public static final String DEFAULT_DEBUG_URL = "http://localhost:8080/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        baseUrl = DEFAULT_BASE_URL;
    }

    /**
     * Returns the baseURL
     * @return baseURL
     */
    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
    }

    /**
     * GET HTTP Request.
     * @param url relative path we want to access.
     * @param params params to add to the request.
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * POST HTTP Request.
     * @param url relative path we want to access.
     * @param params params to add to the request.
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * GET HTTP Request.
     * @param url abosolute path we want to access.
     * @param params params to add to the request.
     * @param responseHandler
     */
    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    /**
     * POST HTTP Request.
     * @param url absolute path we want to access.
     * @param params params to add to the request.
     * @param responseHandler
     */
    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    /**
     * @param relativeUrl relative path we want to access
     * @return the absolute path we want to access (baseURL + relativeURL)
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }
}
