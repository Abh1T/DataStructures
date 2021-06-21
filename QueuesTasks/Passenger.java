import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Passenger implements Comparable<Passenger> {

    String first;
    String last;
    String city;
    String time;

    public Passenger(String fname, String lname, String c, String t) {
        this.first = fname;
        this.last = lname;
        this.city = c;
        this.time = t;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public String flightCity() {
        return city;
    }

    public String flightTime() {
        return time;
    }

    public String etdCalc(){
    	try{
            Date date = new SimpleDateFormat("hh:mm aa").parse("9:03 AM");
            Date sate = new SimpleDateFormat("hh:mm aa").parse(flightTime());
            long mil = (Math.abs(sate.getTime() - date.getTime()));
            return String.format("%d:%d", TimeUnit.MILLISECONDS.toHours(mil),(TimeUnit.MILLISECONDS.toMinutes(mil) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mil))));
        }catch(ParseException e) {}return "this is bad";
    }
    
    @Override
    public int compareTo(Passenger o) {
    	if(Integer.parseInt(etdCalc().substring(0, etdCalc().indexOf(":"))) == 0)
        return etdCalc().compareTo(o.etdCalc());
    	else return 75;
    }

}
