package com.study.poi.mine;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by liuyan on 2017/10/17.
 */
@Data
public class Jiqi {
    String districtode;
    String districtname;
    String citycode;
    String cityname;
    String procode;
    String proname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Jiqi jiqi = (Jiqi) o;

        if (districtode != null ? !districtode.equals(jiqi.districtode) : jiqi.districtode != null) return false;
        if (districtname != null ? !districtname.equals(jiqi.districtname) : jiqi.districtname != null) return false;
        if (citycode != null ? !citycode.equals(jiqi.citycode) : jiqi.citycode != null) return false;
        if (cityname != null ? !cityname.equals(jiqi.cityname) : jiqi.cityname != null) return false;
        if (procode != null ? !procode.equals(jiqi.procode) : jiqi.procode != null) return false;
        return proname != null ? proname.equals(jiqi.proname) : jiqi.proname == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (districtode != null ? districtode.hashCode() : 0);
        result = 31 * result + (districtname != null ? districtname.hashCode() : 0);
        result = 31 * result + (citycode != null ? citycode.hashCode() : 0);
        result = 31 * result + (cityname != null ? cityname.hashCode() : 0);
        result = 31 * result + (procode != null ? procode.hashCode() : 0);
        result = 31 * result + (proname != null ? proname.hashCode() : 0);
        return result;
    }
}
