import java.text.NumberFormat;
import java.text.ParseException;
 
public class NumberFormatExample {
 
  public static void main(String args[]) {
    // get a object for your locale
    NumberFormat numberFormat = NumberFormat.getInstance();
    numberFormat.setMinimumFractionDigits(2);
    numberFormat.setMaximumFractionDigits(2);
 
    // you can also define the length of integer
    // that is the count of digits before the decimal point
    numberFormat.setMinimumIntegerDigits(1);
    numberFormat.setMaximumIntegerDigits(10);
 
    // if you want the number format to have commas
    // to separate the decimals the set as true
    numberFormat.setGroupingUsed(true);
 
    // convert from integer to String
    String formattedNr = numberFormat.format(12345678L);
    // note that the output will have 00 in decimal place
    System.out.println("12345678L number formatted to " + formattedNr);
 
    // convert from decimal to String
    formattedNr = numberFormat.format(12345.671D);
    System.out.println("12345.671D number formatted to " + formattedNr);
 
    // format a String to number
    Number n1 = null;
    Number n2 = null;
 
    try {
      n1 = numberFormat.parse("1,234");
      n2 = numberFormat.parse("1.234");
    } catch (ParseException e) {
      System.out.println("I couldn't parse your string!");
    }
    System.out.println(n1);
    System.out.println(n2);
 
    // show percentage
    numberFormat = NumberFormat.getPercentInstance();
    System.out.println(numberFormat.format(0.98));
  }
}