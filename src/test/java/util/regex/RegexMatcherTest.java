package util.regex;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import entity.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import enums.Strategy;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RegexMatcherTest {
    public RegexMatcher matcher;

    @Before
    public void setup(){
        matcher = new RegexMatcher();
    }

    @Test
    public void matchDateSuccess(){
        String [] dates = new String[]{
                "01-11-1999",
                "1999-01-11",
                "01/11/1999",
                "1999/01/11",
                "Jan 11, 1999",
                "Jan 11,1999",
                "Jan. 11, 1999",
                "Jan. 11,1999",
                "January 11, 1999",
                "January 11,1999",
                "1999, January 11"
        };
        for(String date : dates){
            assertTrue(matcher.isValidFormat(date,Strategy.DATE));
        }
    }

    @Test
    public void matchDateFail(){
        String [] dates = new String[]{
                "",
                "01/11-1999",
                "01-Jan-1999",
                "01/11-1999",
                "Jan.. 11, 1999",
                "January, 11, 1999",
                "January, 11 1999",
        };
        for(String date : dates){
            assertFalse(matcher.isValidFormat(date,Strategy.DATE));
        }
    }

    @Test
    public void matchPhoneNumberSuccess(){
        String [] numbers = new String[]{
                "(469) 487 - 4318",
                "(469) - 487 - 4318",
                "469 - 487 - 4318",
                "(469)487-4318",
                "(469)-487-4318",
                "469-487-4318",
        };
        for(String number : numbers){
            assertTrue(matcher.isValidFormat(number, Strategy.PHONE));
        }
    }

    @Test
    public void matchPhoneNumberFail(){
        String [] numbers = new String[]{
                "",
                "(469) 487 - 431",
                "(46) 487 - 4318",
                "(469) 48 - 4318",
                "(469) 487 - 43189",
                "(4699) 487 - 4318",
                "(469) 4879 - 4318",
                "(469) -- 487 - 4318",
                "(469) 487 -- 4318",
                "(487) - 4318"
        };
        for(String number : numbers){
            assertFalse(matcher.isValidFormat(number, Strategy.PHONE));
        }
    }

    @Test
    public void testAccountNewInstance_WrongNumberArgsException(){
        String address = "2310 Bahamas dr.,Garland,Texas,75044";
        try{
            Address.newInstance(address);
        } catch (Exception e) {
            assertEquals(e.getClass(),WrongNumberArgsException.class);
        }
    }

    @Test
    public void matchAddressSuccess(){
        List<String> addresses = new LinkedList<>();
        addresses.add("2310 Bahamas dr.,,Garland,Texas,75044");
        addresses.add("2310 Bahamas,,Garland,Texas,75044");
        addresses.add("2310 bahamas Dr.,,Garland,Texas,75044");
        addresses.add("2310 Bahamas Drive.,,Garland,Texas,75044");
        addresses.add("2310 Bahamas Island dr.,,Garland,Texas,75044");
        addresses.add("2310 Bahamas-Island dr.,,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,,garland,Texas,75044");
        addresses.add("2315 Bahamas dr.,,City-Line 2,Texas,75044");
        addresses.add("2310 Bahamas dr.,,Garland,texas,75044");
        addresses.add("2310 Bahamas dr.,,Garland,Tx,75044");
        addresses.add("2310 Bahamas dr.,,Garland,Texas,75044-6788");
        addresses.add("2310 Bahamas dr.,Suite 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,# 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,#49,Garland,Texas,75044");
        for(String address : addresses){
            assertTrue(matcher.isValidFormat(address, Strategy.ADDRESS));
        }
    }

    @Test
    public void matchAddressFail(){
        List<String> addresses = new LinkedList<>();
        addresses.add("");
        addresses.add("Bahamas dr.,# 49,Garland,Texas,75044");
        addresses.add(",# 49,Garland,Texas,75044");
        addresses.add("-Bahamas dr.,# 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas .,# 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr..,# 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas .dr,# 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,## 49,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,49 #,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,gay,Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,# 49,#Garland,Texas,75044");
        addresses.add("2310 Bahamas dr.,# 49,Garland,north texas,75044");
        addresses.add("2310 Bahamas dr.,# 49,Garland,23432,75044");
        addresses.add("2310 Bahamas dr.,# 49,Garland,Texas,Texas");
        addresses.add("2310 Bahamas dr.,# 49,Garland,Texas,-75044");
        for(String address : addresses){
            assertFalse(matcher.isValidFormat(address, Strategy.ADDRESS));
        }
    }

    @Test
    public void matchNumberSuccess(){
        String numbers [] = new String[]{
                "0",
                ".1",
                "1",
                "100",
                "1.0",
                "1.10",
                "1.1111111111",
                "100.10",
                "1000",
                "1000.01",
                "1,000",
                "10,000",
                "100,000",
                "1,000,000",
                "1,000,000.01",
                "1,000,000,000.01",
                "1000000000.01"
        };
        for(String number : numbers){
            assertTrue(matcher.isValidFormat(number,Strategy.NUMBER));
        }
    }

    @Test
    public void matchNumberFail(){
        String numbers [] = new String[]{
                "",
                "01",
                "1.",
                "1..",
                "1,",
                "1,,",
                "1.000,000",
                ",1",
                ".0",
                "1.1.1",
                "1,1",
                "1000,000",
                "1.000.000",
                "$1",
        };
        for(String number : numbers){
             assertFalse(matcher.isValidFormat(number,Strategy.NUMBER));
        }
    }

    @After
    public void finish(){
        // garbage clean up
        // close ports
    }
}
