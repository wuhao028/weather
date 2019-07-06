package com.wuhao.weather.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class UtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHighLowTemperature() {
        Assert.assertEquals(Util.getHighLowTemperature(25,22),"25℃/22℃");
    }

    @Test
    public void getTime() {
        Assert.assertEquals(Util.getTime(1562425200), "03:00:00");
    }

    @Test
    public void getCurrentTime() {
        Assert.assertNotNull(Util.getCurrentTime());
    }

    @Test
    public void getWindDirection() {
        Assert.assertEquals(Util.getWindDirection(122), "SE");
        Assert.assertEquals(Util.getWindDirection(10), "N");
        Assert.assertEquals(Util.getWindDirection(22.5), "NE");
        Assert.assertEquals(Util.getWindDirection(68), "E");
        Assert.assertEquals(Util.getWindDirection(122), "SE");
        Assert.assertEquals(Util.getWindDirection(169), "S");
        Assert.assertEquals(Util.getWindDirection(230), "SW");
        Assert.assertEquals(Util.getWindDirection(122), "SE");
        Assert.assertEquals(Util.getWindDirection(250), "W");
        Assert.assertEquals(Util.getWindDirection(300), "NW");
    }

    @Test
    public void getWeekOfDate() {
        Assert.assertNotNull(Util.getWeekOfDate(new Date(),0));
    }

    @Test
    public void getLatAndLon() {

    }

    @Test
    public void getLngAndLatWithNetwork() {
    }

    @Test
    public void getDateFromBean() {
        Assert.assertEquals(Util.getDateFromBean("2019-07-06 06:00:00"), "2019-07-06");
        Assert.assertEquals(Util.getDateFromBean(" "), "");
    }

    @Test
    public void getTimeZoneOffSet() {
        Date date = new Date(1562392800);
        Assert.assertEquals(Util.getTimeZoneOffSet(date, 3600),3600);
    }

}