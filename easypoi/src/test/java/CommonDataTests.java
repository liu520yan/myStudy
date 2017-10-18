import com.study.poi.POIApplication;
import com.study.poi.commomdata.City;
import com.study.poi.commomdata.District;
import com.study.poi.commomdata.Province;
import com.study.poi.mine.Jiaoche;
import com.study.poi.mine.Jiqi;
import com.study.poi.dao.GetCommonData;
import com.study.poi.dao.GetMine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by liuyan on 2017/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = POIApplication.class)
public class CommonDataTests {

    @Autowired
    GetCommonData getCommonData;

    @Autowired
    GetMine getMine;

    @Test
    public void a() {
        List<City> cities = getCommonData.getCity();
        assert (cities.size() > 0);
    }

    @Test
    public void b() {
        List<Province> province = getCommonData.getProvince();
        assert (province.size() > 0);
    }

    @Test
    public void c() {
        List<District> district = getCommonData.getDistrict();
        assert (district.size() > 0);
    }

    @Test
    public void d() {
        List<Jiaoche> jiaoche = getMine.getJiaoche();
        assert (jiaoche.size() > 0);
    }

    @Test
    public void e() {
        List<Jiqi> jiqis = getMine.getJiqi();
        assert (jiqis.size() > 0);
    }
}
