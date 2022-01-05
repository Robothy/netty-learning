package _06.netty.protobuf;


import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    DataInfo.Student student = DataInfo.Student.newBuilder()
        .setName("Bob")
        .setAge(26)
        .setAddress("Shanghai")
        .build();

    byte[] bytes = student.toByteArray();

    DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);
    System.out.println(student1);
  }

}
