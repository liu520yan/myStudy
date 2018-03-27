import com.study.poi.POIApplication;
import com.study.poi.service.InsertJiaoChe;
import com.study.poi.service.InsertTianqi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyan on 2018/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = POIApplication.class)
public class TianqiTests {

    @Autowired
    InsertTianqi insertS2;

    @Test
    public void city() throws Exception {
        insertS2.insertCity();
    }

    @Test
    public void pro() throws Exception {
        insertS2.insertPro();
    }

}
