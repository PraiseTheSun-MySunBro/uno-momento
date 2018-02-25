package ee.ttu.unomomento.models;

import java.util.Date;

public class Person {
    public long personId;
    public short gradeCode;
    public String firstname;
    public String lastname;
    public short personStateCode;
    public String uniid;
    public String email;
    public String password;
    public Date regTime;

    // Just for testing method
    @Override
    public String toString() {
        return String.format("{%d} {%s} {%s} {%s}", personId, uniid, email, regTime);
    }
}
