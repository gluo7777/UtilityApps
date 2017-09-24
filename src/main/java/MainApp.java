import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainApp {
    public static void main(String [] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMMdd,yyyy");
        LocalDate parsedDate = LocalDate.parse("May01,2017", formatter);
        System.out.println(parsedDate.toString());
    }
}
