import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class Ledger {
    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        return new LedgerEntry(LocalDate.parse(d), desc, c);
    }
    public String format(String cur, String loc, LedgerEntry[] entries) {
        if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        }else if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        }
        StringBuilder header = new StringBuilder(loc.equals("en-US") ? "Date       | Description               | Change       " : "Datum      | Omschrijving              | Verandering  ");
        String curSymbol = cur.equals("USD") ? "$" : "€";
        String datPat = loc.equals("en-US") ? "MM/dd/yyyy" : "dd/MM/yyyy";
        char decSep = loc.equals("en-US") ? '.' : ',';
        char thSep = loc.equals("en-US") ? ',' : '.';
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(decSep);
        dfs.setGroupingSeparator(thSep);
        DecimalFormat decimalFormat= new DecimalFormat("#,##0.00",dfs);
        if (entries.length > 0) {
            List<LedgerEntry> all = new ArrayList<>(Arrays.asList(entries));
            all.sort(Comparator.comparingDouble(LedgerEntry::change).thenComparing(LedgerEntry::localDate));
            for (LedgerEntry e : all) {
                String date = e.localDate.format(DateTimeFormatter.ofPattern(datPat));
                String desc = e.description;
                desc = desc.length() > 25 ? desc.substring(0, 22) + "..." : desc;
                double change = e.change() / 100.0;
                String amount = curSymbol+decimalFormat.format(change);
                if (loc.equals("en-US")){
                    amount = e.change() < 0 ? "("+amount.replace("-","") +")" : amount + " ";
                }else {
                    amount = amount.replace(curSymbol,curSymbol+" ") +" ";
                }
                header.append("\n").append(String.format("%s | %-25s | %13s", date, desc, amount));
            }
        }
        return header.toString();
    }
    public record LedgerEntry (LocalDate localDate, String description, double change){
    }
}