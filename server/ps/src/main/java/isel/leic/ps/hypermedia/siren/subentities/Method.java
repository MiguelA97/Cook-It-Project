package isel.leic.ps.hypermedia.siren.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Method {
    GET, PUT, POST, DELETE, PATCH
}
