package come.liuyan.study.base.equals.domain;

import lombok.Data;

/**
 * Created by liuyan on 2017/9/30.
 */
@Data
public class City {
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}
