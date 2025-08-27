/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.escuelaing.httpserver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maria.sanchez-m
 */
public class HttpRequest {
    
    private String path;// Almacena la ruta de la solicitud (antes del signo '?')
    private String query; // Almacena la cadena de consulta de la solicitud (después del '?')
    private Map<String, String> queryParams; // Mapa que contiene los parámetros de la consulta clave-valor


    /**
     * Constructor de la clase HttpRequest.
     * Toma una cadena `fullPath` que representa la URL completa, con la ruta y los parámetros de la consulta.
     * @param fullPath La URL completa de la solicitud, que puede contener ruta y parámetros de consulta.
     */
    public HttpRequest(String fullPath) {
        String[] parts = fullPath.split("\\?", 2);
        this.path = parts[0];
        this.query = (parts.length > 1) ? parts[1] : "";
        this.queryParams = parseQueryParams(this.query);
    }

    /**
     * Analiza los parámetros de la consulta en formato clave=valor.
     * @param query La cadena de consulta de la URL (después del '?')
     * @return Un mapa de los parámetros de la consulta donde la clave es el nombre del parámetro
     * y el valor es el valor del parámetro.
     */
    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    /**
     * Obtiene la ruta de la solicitud HTTP.
     * @return La ruta de la solicitud.
     */
    public String getPath() {
        return path;
    }


    /**
     * Obtiene el valor de un parámetro de la consulta dado su nombre (clave).
     * Si el parámetro no existe, retorna `null`.
     * @param key El nombre del parámetro de la consulta.
     * @return El valor del parámetro si existe, o `null` si no se encuentra.
     */
    public String getValues(String key) {
        return queryParams.getOrDefault(key, null);
    }
}
