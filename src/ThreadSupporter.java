import java.io.*;
import java.net.Socket;

/**
 * @author: Imalka
 * @created_date: 1/31/2021
 */
public class ThreadSupporter extends Thread {
    private Socket socket;

    public ThreadSupporter(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            final InputStream inputStream = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final OutputStream outputStream = socket.getOutputStream();
            final PrintWriter writer = new PrintWriter(outputStream, true);
            String receiver;
            do {
                receiver = reader.readLine();

                double receiveNumberValue = Float.parseFloat(receiver);
                System.out.println("Server Received "+ receiveNumberValue);
                receiveNumberValue = receiveNumberValue*1.5;
                writer.println("Server Response: " + receiveNumberValue);
            } while (!receiver.equals("exit"));
            socket.close();
        } catch (IOException exception) {
            System.out.println("Return Server Exception! " + exception.getMessage());
        }catch (NumberFormatException numberFormatException) {
            System.out.println("Return Number Format Exception! "+ numberFormatException.getMessage());
        }
    }
}
