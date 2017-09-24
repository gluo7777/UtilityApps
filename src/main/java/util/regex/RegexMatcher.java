package util.regex;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import entity.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    public boolean isValidFormat(String text, Strategy strategy) {
        switch (strategy){
            case DATE:
                for(CommonPatterns pattern: CommonPatterns.dates){
                    if(text.matches(pattern.getPattern()))
                        return true;
                }
                return false;
            case PHONE:
                if(text.matches(CommonPatterns.PHONE_NUMBER.getPattern()))
                    return true;
                else
                    return false;
            case EMAIL:
                if(text.matches(CommonPatterns.GMAIL.getPattern()))
                    return true;
                else
                    return false;
            case ADDRESS:
                try {
                    Address address = Address.newInstance(text);
                    if(address.getStreet().matches(CommonPatterns.STREET.getPattern())
                            && address.getSuite().matches(CommonPatterns.SUITE.getPattern())
                            && address.getCity().matches(CommonPatterns.CITY.getPattern())
                            && address.getState().matches(CommonPatterns.STATE.getPattern())
                            && address.getZip().matches(CommonPatterns.ZIP.getPattern())
                    )
                        return true;
                    else
                        return false;
                } catch (WrongNumberArgsException e) {
                    return false;
                }
            case NUMBER:
                if(text.matches(CommonPatterns.NUMBER.getPattern()))
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

    @Deprecated
    public LocalDate getDate(String text) throws InvalidStrategyException {
        Pattern pat;
        Matcher matcher;
        if(text.matches(CommonPatterns.NUMERIC_DATE.getPattern())){
            pat = Pattern.compile(CommonPatterns.NUMERIC_DATE.getPattern());
            matcher = pat.matcher(text);
            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            return LocalDate.of(year,month,day);
        }else if(text.matches(CommonPatterns.NUMERIC_DATE_REVERSED.getPattern())){
            pat = Pattern.compile(CommonPatterns.NUMERIC_DATE_REVERSED.getPattern());
            matcher = pat.matcher(text);
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            int year = Integer.parseInt(matcher.group(1));
            return LocalDate.of(year,month,day);
        } else if(text.matches(CommonPatterns.NUMERIC_DATE_TEXT.getPattern())){
            text = text.replaceAll(" ","");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMdd,yyyy");
            try{
                return LocalDate.parse(text,formatter);
            }catch (DateTimeParseException e){
                formatter = DateTimeFormatter.ofPattern("MMMMdd,yyyy");
                return LocalDate.parse(text,formatter);
            }
        } else if(text.matches(CommonPatterns.NUMERIC_DATE_TEXT_REVERSED.getPattern())){
            text = text.replaceAll(" ","");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MMMdd");
            try{
                return LocalDate.parse(text,formatter);
            }catch (DateTimeParseException e){
                formatter = DateTimeFormatter.ofPattern("yyyy,MMMMdd");
                return LocalDate.parse(text,formatter);
            }
        }
        return null;
    }
}
