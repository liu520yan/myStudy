import com.study.poi.POIApplication;
import com.study.poi.service.InsertJiqi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyan on 2017/10/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = POIApplication.class)
public class JilinTests {

    @Autowired
    InsertJiqi insertJiqi;

    @Test
    @Transient
    public void dis() throws Exception {
        insertJiqi.insertDis();
    }

    @Test
    @Transient
    public void pro() throws Exception {
        insertJiqi.insertPro();
    }

    @Test
    @Transient
    public void city() throws Exception {
        insertJiqi.insertCity();
    }
}

