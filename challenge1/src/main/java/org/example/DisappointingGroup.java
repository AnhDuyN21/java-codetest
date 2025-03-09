package org.example;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

//Annotation thông báo class này an toàn trong môi trường đa luồng (thread-safe)
@ThreadSafe
public class DisappointingGroup
        implements Closeable
{
    String groupId;
    HashSet<Member> members;
    boolean isRunning;
    boolean shouldStop;

    class Member
    {
        String memberId;
        int age;

        Member(String memberId, int age)
        {
            this.memberId = memberId;
            this.age = age;
        }

        public String getMemberId()
        {
            return memberId;
        }

        public int getAge()
        {
            return age;
        }

        public boolean equals(Object o)
        {
            Member member = (Member) o;
            //sử dụng equals() thay cho ==
            //equals() sẽ so sánh nội dung thay vì địa chỉ ô nhớ
            return this.memberId == member.memberId;
        }
    }

    class AdminMember extends Member
    {
        AdminMember(String memberId, int age)
        {
            super(memberId, age);
        }
    }

    public DisappointingGroup(String groupId)
    {
        this.groupId = groupId;
        //Thay HashSet bằng ConcurrentHashMap.newKeySet()
        //HashSet không an toàn trong môi trường đa luồng

        this.members = new HashSet<>();
    }

    public void addMember(Member member)
    {
        members.add(member);
    }

    private String getDecoratedMemberId(Member member)
    {
        // nên đảo ngược check AdminMember trước vì điều kiện AdminMember sẽ không được chạy tới
        if (member instanceof Member) {
            return member.getMemberId() + "(normal)";
        }
        else if (member instanceof AdminMember) {
            return member.getMemberId() + "(admin)";
        }
        return null;
    }

    private String getMembersAsStringFlooringAge()
    {
        //Sử dụng StringBuilder thay vì String -> tối ưu bộ nhớ, hiệu suất
        //Nối chuỗi += mỗi lần nối sẽ tạo ra 1 object mới
        String buf = "";
        for (Member member : members)
        {

            Integer flooredAge = (member.getAge() / 10) * 10;
            String decoratedMemberId = getDecoratedMemberId(member);
            buf += String.format("memberId=%s, age=%d \n", decoratedMemberId, flooredAge);
        }
        return buf;
    }

    @Override
    public void close()
            throws IOException
    {
    }

    public void startLoggingMemberList10Times(final String outputFilePrimary, final String outputFileSecondary)
    {
        // dùng để chỉ có 1 thread truy cập vào 1 lần nhưng chưa có code set isRunning = false;
        if (isRunning) {
            return;
        }
        isRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                int i = 0;
                while (!shouldStop)
                {
                    if (i++ >= 10)
                        break;
                    //Sử dụng BufferedWriter sẽ tốt hơn -> ghi vào bộ nhớ đệm trước, tránh gọi hệ thống nhiều lần
                    FileWriter writer0 = null;
                    FileWriter writer1 = null;
                    try {
                        String membersStr = DisappointingGroup.this.getMembersAsStringFlooringAge();

                        writer0 = new FileWriter(new File(outputFilePrimary));
                        //append đang ghi đè lại cái cũ chứ không thực sự thêm mới, muốn thêm phải có ..,true ở phía trên
                        writer0.append(membersStr);

                        writer1 = new FileWriter(new File(outputFileSecondary));
                        //append đang ghi đè lại cái cũ chứ không thực sự thêm mới, muốn thêm phải có ..,true ở phía trên
                        writer1.append(membersStr);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(
                                "Unexpected error occurred. Please check these file names. outputFilePrimary="
                                        + outputFilePrimary + ", outputFileSecondary=" + outputFileSecondary);
                    }
                    finally {
                        try {
                            if (writer0 != null)
                                writer0.close();

                            if (writer1 != null)
                                writer1.close();
                        }
                        catch (Exception ignored) {

                        }
                    }

                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stopPrintingMemberList()
    {
        shouldStop = true;
    }
}
