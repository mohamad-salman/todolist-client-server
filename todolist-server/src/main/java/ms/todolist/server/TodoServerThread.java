package ms.todolist.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author MS
 */
public class TodoServerThread extends Thread {

    private final Socket socket;

    public TodoServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter response = new PrintWriter(socket.getOutputStream(), true); 
                BufferedReader request = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));) {
            
            String requestMessage, responseMessage;
            RequestProcessor processor = new RequestProcessor();

            while ((requestMessage = request.readLine()) != null) {
                logMsg(requestMessage);
                responseMessage = processor.process(requestMessage);

                response.println(responseMessage);
            }
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private void logMsg(String reqMsg) {
        System.out.println("client " + getIpAddr() + " request -> " + reqMsg);
    }

    private String getIpAddr() {
        return socket.getInetAddress().getHostAddress();
    }

}
