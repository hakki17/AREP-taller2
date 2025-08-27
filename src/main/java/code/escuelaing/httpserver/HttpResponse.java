/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.escuelaing.httpserver;

import java.io.PrintWriter;

/**
 *
 * @author maria.sanchez-m
 */
public class HttpResponse {
    private final PrintWriter out;

    public HttpResponse(PrintWriter out) {
        this.out = out;
    }

    /**
     * Envía una respuesta al cliente.
     * @param response La respuesta a enviar.
     */
    public void send(String response) {
        out.println(response);
    }
}
