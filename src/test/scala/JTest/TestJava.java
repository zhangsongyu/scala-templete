package JTest;

import org.junit.Test;

public class TestJava {
    @Test
    public void t1() {
        String userInfo = "userInfoL:s";
        String r= userInfo != null ? userInfo.split(":", 2)[1] : null;
        System.out.println(r);
       String res= userInfo.split(":", 2)[1];
    }
}
