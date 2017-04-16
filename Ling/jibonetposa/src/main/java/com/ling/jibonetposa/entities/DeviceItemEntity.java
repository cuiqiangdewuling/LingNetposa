package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/14.
 */

public class DeviceItemEntity extends BaseEntity {

    private List<Brand> brand_list;

    public class Brand {
        private String brand_id;
        private String brand_name;
        private List<Device> device_list;
    }

    public class Device {
        private int id;
        private String name;
        private String device_type;
        private String device_type_name;
    }
}
