package com.wuhao.weather.model;

import java.util.List;

/**
 * Created by WuHao028 on 2019-07-07
 */
public class CitySearchResult {


    /**
     * message : like
     * cod : 200
     * count : 5
     * list : [{"id":2643743,"name":"London","coord":{"lat":51.5073,"lon":-0.1277},"main":{"temp":289.37,"pressure":1020,"humidity":59,"temp_min":287.04,"temp_max":292.15},"dt":1562307338,"wind":{"speed":1.5},"sys":{"country":"GB"},"rain":null,"snow":null,"clouds":{"all":90},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}]},{"id":6058560,"name":"London","coord":{"lat":42.9886,"lon":-81.2467},"main":{"temp":295.59,"pressure":1017,"humidity":94,"temp_min":294.82,"temp_max":296.15},"dt":1562303369,"wind":{"speed":1.5,"deg":170},"sys":{"country":"CA"},"rain":null,"snow":null,"clouds":{"all":40},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}]},{"id":4298960,"name":"London","coord":{"lat":37.129,"lon":-84.0833},"main":{"temp":294.03,"pressure":1020,"humidity":100,"temp_min":293.15,"temp_max":295.15},"dt":1562303378,"wind":{"speed":0.89,"deg":163.233},"sys":{"country":"US"},"rain":null,"snow":null,"clouds":{"all":1},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}]},{"id":4517009,"name":"London","coord":{"lat":39.8864,"lon":-83.4483},"main":{"temp":296.35,"pressure":1018,"humidity":94,"temp_min":294.82,"temp_max":298.15},"dt":1562303378,"wind":{"speed":1.48,"deg":214.798},"sys":{"country":"US"},"rain":null,"snow":null,"clouds":{"all":1},"weather":[{"id":701,"main":"Mist","description":"mist","icon":"50n"}]},{"id":4119617,"name":"London","coord":{"lat":35.329,"lon":-93.253},"main":{"temp":297.87,"pressure":1016,"humidity":94,"temp_min":297.04,"temp_max":298.71},"dt":1562303480,"wind":{"speed":1.01,"deg":51.456},"sys":{"country":"US"},"rain":null,"snow":null,"clouds":{"all":1},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}]}]
     */

    private String message;
    private String cod;
    private int count;
    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2643743
         * name : London
         * coord : {"lat":51.5073,"lon":-0.1277}
         * main : {"temp":289.37,"pressure":1020,"humidity":59,"temp_min":287.04,"temp_max":292.15}
         * dt : 1562307338
         * wind : {"speed":1.5}
         * sys : {"country":"GB"}
         * rain : null
         * snow : null
         * clouds : {"all":90}
         * weather : [{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}]
         */

        private int id;
        private String name;
        private CoordBean coord;
        private MainBean main;
        private int dt;
        private WindBean wind;
        private SysBean sys;
        private Object rain;
        private Object snow;
        private CloudsBean clouds;
        private List<WeatherBean> weather;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CoordBean getCoord() {
            return coord;
        }

        public void setCoord(CoordBean coord) {
            this.coord = coord;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public Object getRain() {
            return rain;
        }

        public void setRain(Object rain) {
            this.rain = rain;
        }

        public Object getSnow() {
            return snow;
        }

        public void setSnow(Object snow) {
            this.snow = snow;
        }

        public CloudsBean getClouds() {
            return clouds;
        }

        public void setClouds(CloudsBean clouds) {
            this.clouds = clouds;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class CoordBean {
            /**
             * lat : 51.5073
             * lon : -0.1277
             */

            private double lat;
            private double lon;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }
        }

        public static class MainBean {
            /**
             * temp : 289.37
             * pressure : 1020
             * humidity : 59
             * temp_min : 287.04
             * temp_max : 292.15
             */

            private double temp;
            private int pressure;
            private int humidity;
            private double temp_min;
            private double temp_max;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public int getPressure() {
                return pressure;
            }

            public void setPressure(int pressure) {
                this.pressure = pressure;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }
        }

        public static class WindBean {
            /**
             * speed : 1.5
             */

            private double speed;

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }
        }

        public static class SysBean {
            /**
             * country : GB
             */

            private String country;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }

        public static class CloudsBean {
            /**
             * all : 90
             */

            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

        public static class WeatherBean {
            /**
             * id : 804
             * main : Clouds
             * description : overcast clouds
             * icon : 04d
             */

            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
