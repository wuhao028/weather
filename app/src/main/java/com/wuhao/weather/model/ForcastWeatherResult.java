package com.wuhao.weather.model;

import java.util.List;

public class ForcastWeatherResult {


    /**
     * cod : 200
     * message : 0.0119
     * cnt : 5
     * list : [{"dt":1562144400,"main":{"temp":287.97,"temp_min":287.381,"temp_max":287.97,"pressure":1018.05,"sea_level":1018.05,"grnd_level":1017.59,"humidity":92,"temp_kf":0.59},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":13.85,"deg":339.577},"sys":{"pod":"n"},"dt_txt":"2019-07-06 09:00:00"},{"dt":1562155200,"main":{"temp":287.36,"temp_min":286.919,"temp_max":287.36,"pressure":1018.1,"sea_level":1018.1,"grnd_level":1017.63,"humidity":89,"temp_kf":0.44},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":11.61,"deg":339.887},"sys":{"pod":"n"},"dt_txt":"2019-07-06 12:00:00"},{"dt":1562166000,"main":{"temp":286.88,"temp_min":286.581,"temp_max":286.88,"pressure":1017.7,"sea_level":1017.7,"grnd_level":1017.1,"humidity":85,"temp_kf":0.3},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":11.07,"deg":342.804},"sys":{"pod":"n"},"dt_txt":"2019-07-03 15:00:00"},{"dt":1562176800,"main":{"temp":286.18,"temp_min":286.03,"temp_max":286.18,"pressure":1017.82,"sea_level":1017.82,"grnd_level":1016.96,"humidity":87,"temp_kf":0.15},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":6.01,"deg":326.006},"sys":{"pod":"n"},"dt_txt":"2019-07-03 18:00:00"},{"dt":1562187600,"main":{"temp":286.319,"temp_min":286.319,"temp_max":286.319,"pressure":1020.79,"sea_level":1020.79,"grnd_level":1020.75,"humidity":74,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":8.47,"deg":167.162},"sys":{"pod":"d"},"dt_txt":"2019-07-06 21:00:00"}]
     * city : {"id":2179537,"name":"Wellington","coord":{"lat":-41.2888,"lon":174.7772},"country":"NZ","population":1000000,"timezone":43200}
     */

    private String cod;
    private double message;
    private int cnt;
    private CityBean city;
    private List<ListBean> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CityBean {
        /**
         * id : 2179537
         * name : Wellington
         * coord : {"lat":-41.2888,"lon":174.7772}
         * country : NZ
         * population : 1000000
         * timezone : 43200
         */

        private int id;
        private String name;
        private CoordBean coord;
        private String country;
        private int population;
        private int timezone;

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public int getTimezone() {
            return timezone;
        }

        public void setTimezone(int timezone) {
            this.timezone = timezone;
        }

        public static class CoordBean {
            /**
             * lat : -41.2888
             * lon : 174.7772
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
    }

    public static class ListBean {
        /**
         * dt : 1562144400
         * main : {"temp":287.97,"temp_min":287.381,"temp_max":287.97,"pressure":1018.05,"sea_level":1018.05,"grnd_level":1017.59,"humidity":92,"temp_kf":0.59}
         * weather : [{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}]
         * clouds : {"all":100}
         * wind : {"speed":13.85,"deg":339.577}
         * sys : {"pod":"n"}
         * dt_txt : 2019-07-06 09:00:00
         */

        private int dt;
        private MainBean main;
        private CloudsBean clouds;
        private WindBean wind;
        private SysBean sys;
        private String dt_txt;
        private List<WeatherBean> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }

        public CloudsBean getClouds() {
            return clouds;
        }

        public void setClouds(CloudsBean clouds) {
            this.clouds = clouds;
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

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class MainBean {
            /**
             * temp : 287.97
             * temp_min : 287.381
             * temp_max : 287.97
             * pressure : 1018.05
             * sea_level : 1018.05
             * grnd_level : 1017.59
             * humidity : 92
             * temp_kf : 0.59
             */

            private double temp;
            private double temp_min;
            private double temp_max;
            private double pressure;
            private double sea_level;
            private double grnd_level;
            private int humidity;
            private double temp_kf;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
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

            public double getPressure() {
                return pressure;
            }

            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public double getSea_level() {
                return sea_level;
            }

            public void setSea_level(double sea_level) {
                this.sea_level = sea_level;
            }

            public double getGrnd_level() {
                return grnd_level;
            }

            public void setGrnd_level(double grnd_level) {
                this.grnd_level = grnd_level;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public double getTemp_kf() {
                return temp_kf;
            }

            public void setTemp_kf(double temp_kf) {
                this.temp_kf = temp_kf;
            }
        }

        public static class CloudsBean {
            /**
             * all : 100
             */

            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

        public static class WindBean {
            /**
             * speed : 13.85
             * deg : 339.577
             */

            private double speed;
            private double deg;

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public double getDeg() {
                return deg;
            }

            public void setDeg(double deg) {
                this.deg = deg;
            }
        }

        public static class SysBean {
            /**
             * pod : n
             */

            private String pod;

            public String getPod() {
                return pod;
            }

            public void setPod(String pod) {
                this.pod = pod;
            }
        }

        public static class WeatherBean {
            /**
             * id : 804
             * main : Clouds
             * description : overcast clouds
             * icon : 04n
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
