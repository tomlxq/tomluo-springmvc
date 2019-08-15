import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
/**
 * 功能描述
 *
 * @author TomLuo
 * @date 2019/8/15
 */
public class TestRegix {
    @Test
    public void testReg() throws IOException {
        String s = FileUtils.readFileToString(new File("E:\\data\\wwwtest\\tom-springmvc\\tomluo-web\\src\\main\\webapp\\WEB-INF\\jsp\\statics.jsp"), StandardCharsets.UTF_8);
        Pattern p = Pattern.compile("/*.[^.js].*/");
        Matcher m = p.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, ".js?"+ RandomUtils.nextInt());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
