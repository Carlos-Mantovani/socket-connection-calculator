import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    private Socket socket = null;
    public Server(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
                String inp = dis.readUTF();
                if (inp.equals("bye"))
                    break;
                System.out.println("Equation received");
                int result = 0;

                StringTokenizer st = new StringTokenizer(inp);
                int oprnd1 = Integer.parseInt(st.nextToken());
                String operator = st.nextToken();
                int oprnd2 = Integer.parseInt(st.nextToken());

                boolean validOperator = (operator != null);
                switch (operator) {
                    case "+" -> result = oprnd1 + oprnd2;
                    case "-" -> result = oprnd1 - oprnd2;
                    case "*" -> result = oprnd1 * oprnd2;
                    case "/" -> result = oprnd1 / oprnd2;
                    default -> {
                        validOperator = false;
                        System.out.println("Invalid operator");
                        dos.writeUTF("Invalid operator");
                    }
                }
                if (validOperator) {
                    System.out.println("Sending the result");
                    dos.writeUTF(Integer.toString(result));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
