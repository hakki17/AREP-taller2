/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.escuelaing.httpserver;

import static code.escuelaing.httpserver.Httpserver.get;
import static code.escuelaing.httpserver.Httpserver.staticfiles;
import static code.escuelaing.httpserver.Httpserver.startServer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
/**
 *
 * @author maria.sanchez-m
 */
public class WebApplication {
    private static String directory = "webroot";
    
    public static void main(String[] args) throws IOException, URISyntaxException{
        staticfiles("/webroot");
        get("/app/hello", (req, resp) -> {
            String name = req.getValues("name");
            if (name == null || name.isEmpty()) {
                name = "usuario";
            }
            //return "{\"name\": \"" + name + "\"}";
            return "Hola " + name ;
        });
        get("/app/pi", (req, resp) -> {
            return String.valueOf(Math.PI); 
        });
        get("/app/mundo",(req,resp)-> "Hola Mundo");
        startServer(args);
    }
    
    private static void findWebRoot() {
        String currentDir = System.getProperty("user.dir");
        System.out.println("📂 Directorio actual: " + currentDir);
        
        String[] possiblePaths = {
            "webroot",
            "../webroot",
            "../../webroot", 
            "./webroot",
            currentDir + "/webroot",
            "src/main/resources",
        };
        
        for (String path : possiblePaths) {
            File webRootDir = new File(path);
            if (webRootDir.exists() && webRootDir.isDirectory()) {
                directory = path;
                System.out.println("✅ Webroot encontrado en: " + webRootDir.getAbsolutePath());
                return;
            }
        }
        
        System.out.println("⚠️ No se encontró webroot, usando: " + directory);
    }
    
    public static void staticfiles(String directories) {
        directory = directories;
    }

    public static String getStaticFilesDirectory() {
        return directory;
    }
}
