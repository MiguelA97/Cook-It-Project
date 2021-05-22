package isel.leic.ps.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeadersUtils {

    private final static String SIREN_MEDIA_TYPE = "application/vnd.siren+json";

    /**
     * Set content type header of HTTP protocol to siren hypermedia
     *
     * @param headers instance of HttpHeaders to add the header content type
     * @return the headers instance
     */
    public static HttpHeaders setSirenContentType(HttpHeaders headers) {
        headers.setContentType(org.springframework.http.MediaType.valueOf(SIREN_MEDIA_TYPE));
        return headers;
    }

    /**
     * Set content type header of HTTP protocol to problem+json hypermedia
     *
     * @param headers instance of HttpHeaders to add the header content type
     * @return the headers instance
     */
    public static HttpHeaders setProblemDetailContentType(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return headers;
    }
}