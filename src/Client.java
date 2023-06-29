import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client(String address, int port) {
        try {
            Scanner sc = new Scanner(System.in);
            Socket s = new Socket(address, port);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
                System.out.println("Enter the operation");
                System.out.println("Example: 2 + 5");
                String inp = sc.nextLine();

                if (inp.equals("Over"))
                    break;
                dos.writeUTF(inp);

                String ans = dis.readUTF();
                System.out.println("Answer: " + ans);
            }
        }
        catch (Exception e) {
            System.out.println("Error in connection");
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }
}
