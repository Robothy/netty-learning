package _08.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class TraditionalClient {

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 8899);
    String filename = "build.gradle";

    FileInputStream fileInputStream = new FileInputStream(filename);

    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

    byte[] buf = new byte[4096];
    int len;
    long total = 0;
    long start = System.currentTimeMillis();
    while ((len = fileInputStream.read()) >= 0) {
      dataOutputStream.write(buf, 0, len);
      total += len;
    }
    long end = System.currentTimeMillis();
    System.out.printf("Transfered %d bytes, cost %d ms%n\n", total, end - start);

    fileInputStream.close();
    socket.close();
  }

}
