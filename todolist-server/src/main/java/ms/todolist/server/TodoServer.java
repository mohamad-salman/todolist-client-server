package ms.todolist.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author MS
 */
public class TodoServer {

    private final static int DEFAULT_PORT = 8_000;

    public static void main(String[] args) {
        int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);

        System.out.println("=== Todo List server ready menggunakan port " + port);        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                new TodoServerThread(serverSocket.accept()).start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
