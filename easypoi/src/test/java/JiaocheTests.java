import com.study.poi.POIApplication;
import com.study.poi.service.InsertJiaoChe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyan on 2017/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = POIApplication.class)
public class JiaocheTests {

    @Autowired
    InsertJiaoChe insertS2;

    @Test
    public void city() throws Exception {
        insertS2.insertCity();
    }

    @Test
    public void pro() throws Exception {
        insertS2.insertPro();
    }

}

