import java.io.*;
import java.net.*;

/**
 * A simple echo client that connects to a server and sends it data,
 * and then prints out the data that the server echoes back to the client.
 *
 * To use the client, specify the server hostname and port number as
 * command-line arguments. For example:
 *
 *      java EchoClient localhost 4444
 *
 * This would start the client running on the local machine, connecting
 * to a server running on the same machine on port 4444.
 *
 * The client will continue running until the user types `Ctrl+C` to
 * interrupt the program.
 */

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 4444);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //citeste ce scri in consola de client

        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
