package com.handpay.coupon.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class ProvincEBean implements IPickerViewData {


    private List<ProvinceBean> province;

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.province.get(0).name;
    }

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"province\":")
                .append(province);
        sb.append(",\"pickerViewText\":\"")
                .append(getPickerViewText()).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public static class ProvinceBean implements IPickerViewData{
        /**
         * name : 北京市
         * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]}]
         */

        private String name;
        private List<CityBean> city;
        @Override
        public String getPickerViewText() {
            return this.name;
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append(",\"city\":")
                    .append(city);
            sb.append('}');
            return sb.toString();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 北京市
             * area : ["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]
             */

            private String name;
            private List<String> area;

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"name\":\"")
                        .append(name).append('\"');
                sb.append(",\"area\":")
                        .append(area);
                sb.append('}');
                return sb.toString();
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getArea() {
                return area;
            }

            public void setArea(List<String> area) {
                this.area = area;
            }
        }
    }
}
