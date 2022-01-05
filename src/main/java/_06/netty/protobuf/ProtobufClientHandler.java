package _06.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Random;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    int idx = new Random().nextInt(3);
    Object obj = switch (idx) {
      case 0 -> DataInfo.Member.newBuilder()
          .setRole(DataInfo.Member.Role.STUDENT)
          .setStudent(DataInfo.Student.newBuilder()
              .setName("Alice")
              .setAge(27)
              .setAddress("Shanghai").build())
          .build();
      case 1 -> DataInfo.Member.newBuilder()
          .setRole(DataInfo.Member.Role.TEACHER)
          .setTeacher(
              DataInfo.Teacher.newBuilder().setTitle(DataInfo.Teacher.Title.PROFESSOR)
                  .setProfessor(DataInfo.Professor.newBuilder()
                      .setName("David")
                      .build())
                  .build())
          .build();

      default -> {
        DataInfo.Lecturer lecturer = DataInfo.Lecturer.newBuilder().setName("Bob").build();
        DataInfo.Teacher teacher = DataInfo.Teacher.newBuilder()
            .setTitle(DataInfo.Teacher.Title.LECTURER)
            .setLecturer(lecturer)
            .build();

        yield DataInfo.Member.newBuilder()
            .setRole(DataInfo.Member.Role.TEACHER)
            .setTeacher(teacher)
            .build();
      }
    };
    ctx.writeAndFlush(obj);
  }
}
