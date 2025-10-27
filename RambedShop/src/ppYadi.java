import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.List;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import conexion.StatementsClientes;
import model.Cliente;

import com.google.gson.Gson;

public class ppYadi {
    public static void main(String[] args) throws IOException {
// Usaremos el puerto 8081 para evitar conflictos
        HttpServer server = HttpServer.create(new
                InetSocketAddress(8081), 0);
// Endpoints para cada entidad
        //server.createContext("/api/cliente", new AutoresHandler());
        //server.createContext("/api/categorias", new CategoriasHandler());
        server.createContext("/api/cliente", new ArticulosHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor CRUD iniciado en el puerto 8081...");
    }

    /**
     * Función de ayuda para enviar respuestas HTTP y no repetir código.
     */
    private static void enviarRespuesta(HttpExchange exchange, String
            respuesta, int codigoHttp) throws IOException {
// Configurar cabeceras CORS para permitir la comunicación con el frontend
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin",
                "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers",
                "Content-Type, X-Requested-With");
        exchange.getResponseHeaders().set("Content-Type",
                "application/json");
        exchange.sendResponseHeaders(codigoHttp,
                respuesta.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(respuesta.getBytes());
        }
    }
/**
 * Manejador para todas las operaciones CRUD de Autores.

static class AutoresHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
// Manejo de la petición pre-vuelo de CORS

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod()))
        {
            enviarRespuesta(exchange, "", 204);
            return;
        }
        Gson gson = new Gson();
        AutorDAO dao = new AutorDAO();
        String metodo = exchange.getRequestMethod();
        String respuesta = "";
        int codigoHttp = 200;
        try {
            switch (metodo) {
                case "GET":
                    List<Autor> autores = dao.obtenerTodos();
                    respuesta = gson.toJson(autores);
                    break;
                case "POST":
                    Autor autorNuevo = gson.fromJson(new
                            InputStreamReader(exchange.getRequestBody()), Autor.class);
                    boolean exitoCrear = dao.guardar(autorNuevo);
                    System.out.println("Autor recibido: " +
                            autorNuevo.getNombre() + ", " + autorNuevo.getEmail());
                    respuesta = exitoCrear ? "{\"mensaje\": \"Autor creado\"}" : "{\"mensaje\": \"Error al crear\"}";
                    codigoHttp = exitoCrear ? 201 : 500;
                    break;
                case "PUT":
                    Autor autorActualizar = gson.fromJson(new
                            InputStreamReader(exchange.getRequestBody()), Autor.class);
                    boolean exitoActualizar =
                            dao.actualizar(autorActualizar);
                    respuesta = exitoActualizar ? "{\"mensaje\":\"Autor actualizado\"}" : "{\"mensaje\": \"Error al actualizar\"}";
                    break;
                case "DELETE":
                    String path = exchange.getRequestURI().getPath();
                    int idParaBorrar =
                            Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
                    boolean exitoBorrar = dao.eliminar(idParaBorrar);
                    respuesta = exitoBorrar ? "{\"mensaje\": \"Autor eliminado\"}" : "{\"mensaje\": \"Error al eliminar\"}";
                    break;
                default:respuesta = "{\"mensaje\": \"Método no soportado\"}";
                    codigoHttp = 405;
                    break;
            }
        } catch (SQLException e) {
            respuesta = "{\"mensaje\": \"Error en la base de datos: "
                    + e.getMessage() + "\"}";
            codigoHttp = 500;
            e.printStackTrace();
        }
        enviarRespuesta(exchange, respuesta, codigoHttp);
    }
}
/**
 * Manejador para todas las operaciones CRUD de Categorías.


static class CategoriasHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod()))
        {
            enviarRespuesta(exchange, "", 204);
            return;
        }
        Gson gson = new Gson();
        CategoriaDAO dao = new CategoriaDAO(); // <--- Usa
        CategoriaDAO
        String metodo = exchange.getRequestMethod();
        String respuesta = "";
        int codigoHttp = 200;
        try {
            switch (metodo) {
                case "GET":
                    List<Categoria> categorias = dao.obtenerTodos();
// <--- Llama a los métodos de CategoriaDAO
                    respuesta = gson.toJson(categorias);
                    break;
                case "POST":
                    Categoria catNueva = gson.fromJson(new
                            InputStreamReader(exchange.getRequestBody()), Categoria.class);
                    boolean exitoCrear = dao.guardar(catNueva);
                    respuesta = exitoCrear ? "{\"mensaje\":\"Categoría creada\"}" : "{\"mensaje\": \"Error al crear\"}";
                    codigoHttp = exitoCrear ? 201 : 500;
                    break;
                case "PUT":
                    Categoria catActualizar = gson.fromJson(new
                            InputStreamReader(exchange.getRequestBody()), Categoria.class);
                    boolean exitoActualizar =
                            dao.actualizar(catActualizar);
                    respuesta = exitoActualizar ? "{\"mensaje\":\"Categoría actualizada\"}" : "{\"mensaje\": \"Error al actualizar\"}";
                    break;
                case "DELETE":
                    String path = exchange.getRequestURI().getPath();
                    int idParaBorrar =
                            Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
                    boolean exitoBorrar = dao.eliminar(idParaBorrar);
                    respuesta = exitoBorrar ? "{\"mensaje\":\"Categoría eliminada\"}" : "{\"mensaje\": \"Error al eliminar\"}";
                    break;
                default:
                    respuesta = "{\"mensaje\": \"Método no soportado\"}";
                    codigoHttp = 405;
                    break;
            }
        } catch (SQLException e) {
            respuesta = "{\"mensaje\": \"Error en la base de datos: "
                    + e.getMessage() + "\"}";
            codigoHttp = 500;
            e.printStackTrace();
        }
        enviarRespuesta(exchange, respuesta, codigoHttp);
    }
}
*/
/**
 * Manejador para todas las operaciones CRUD de Artículos.
 */

static class ArticulosHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod()))
        {
            enviarRespuesta(exchange, "", 204);
            return;
        }
        Gson gson = new Gson();
        StatementsClientes cl = new StatementsClientes(); // <--- Usa Cliente
        String metodo = exchange.getRequestMethod();
        String respuesta = "";
        int codigoHttp = 200;
        try {
            switch (metodo) {
                case "GET":

// ArticuloDAO debe tener un método que devuelvala vista completa

                    List<Cliente> cliente =
                            cl.obtenerTodosClientes();
                    respuesta = gson.toJson(cliente);
                    break;
                case "POST":
                    Cliente clNuevo = gson.fromJson(new
                            InputStreamReader(exchange.getRequestBody()), Cliente.class);
                    boolean exitoCrear = cl.guardar(clNuevo);
                    respuesta = exitoCrear ? "{\"mensaje\":\"Artículo creado\"}" : "{\"mensaje\": \"Error al crear\"}";
                    codigoHttp = exitoCrear ? 201 : 500;
                    break;
                case "PUT":
                    Cliente clActualizar = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Cliente.class);
                    boolean exitoActualizar =
                            cl.actualizar(clActualizar);
                    respuesta = exitoActualizar ? "{\"mensaje\":\"Artículo actualizado\"}" : "{\"mensaje\": \"Error al actualizar\"}";
                    break;
                case "DELETE":
                    String path = exchange.getRequestURI().getPath();
                    int idParaBorrar =
                            Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
                    boolean exitoBorrar = cl.eliminar(idParaBorrar);
                    respuesta = exitoBorrar ? "{\"mensaje\":\"Artículo eliminado\"}" : "{\"mensaje\": \"Error al eliminar\"}";
                    break;
                default:
                    respuesta = "{\"mensaje\": \"Método no soportado\"}";
                    codigoHttp = 405;
                    break;
            }
        } catch (SQLException e) {
            respuesta = "{\"mensaje\": \"Error en la base de datos: "
                    + e.getMessage() + "\"}";
            codigoHttp = 500;
            e.printStackTrace();
        }
        enviarRespuesta(exchange, respuesta, codigoHttp);
    }
}
    
}
