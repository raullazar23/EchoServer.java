import java.io.*;
import java.net.*;

/**
 * A simple echo server that listens for client connections on a specified
 * port, and echoes back whatever the client sends to the server.
 *
 * To use the server, start it running on a particular port, and then
 * use a client to connect to the server and send it data. The server
 * will print out the data it receives from the client, and then echo
 * it back to the client.
 *
 * The server is designed to be run from the command line, with the port
 * number specified as a command-line argument. For example:
 *
 *      java EchoServer 4444
 *
 * This would start the server running on port 4444.
 *
 * The server will continue running until the user types `Ctrl+C` to
 * interrupt the program.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        // Create a ServerSocket to listen for client connections on the specified port
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            // If the specified port is already in use, or if the server cannot bind to the
            // port for any other reason, print an error message and exit
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        // Accept the first client connection that is requested
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            // If the server is unable to accept the client connection for any reason,
            // print an error message and exit
            System.err.println("Accept failed.");
            System.exit(1);
        }
        // Create a PrintWriter and BufferedReader to send and receive data from the client
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        // Read data from the client and send it back until the client closes the connection
        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
        }
        // Close the input and output streams, and the client and server sockets
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
