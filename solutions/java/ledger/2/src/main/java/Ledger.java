import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ledger {
    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        return new LedgerEntry(LocalDate.parse(d), desc, c);
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        validateCurrency(cur);
        validateLocale(loc);
        String s;
        String header = loc.equals("en-US") ? "Date       | Description               | Change       " : "Datum      | Omschrijving              | Verandering  ";
        String curSymbol = cur.equals("USD") ? "$" : "€";
        String datPat = loc.equals("en-US") ? "MM/dd/yyyy" : "dd/MM/yyyy";
        String decSep = loc.equals("en-US") ? "." : ",";
        String thSep = loc.equals("en-US") ? "," : ".";
        s = header;
        if (entries.length > 0) {
            List<LedgerEntry> neg = Arrays.stream(entries).filter(e -> e.getChange() < 0).collect(Collectors.toList());
            List<LedgerEntry> pos = Arrays.stream(entries).filter(e -> e.getChange() >= 0).collect(Collectors.toList());
            neg.sort(Comparator.comparing(LedgerEntry::getLocalDate));
            pos.sort(Comparator.comparing(LedgerEntry::getLocalDate));
            List<LedgerEntry> all = new ArrayList<>();
            all.addAll(neg);
            all.addAll(pos);
            for (LedgerEntry e : all) {
                String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));
                String desc = e.getDescription();
                if (desc.length() > 25) {
                    desc = desc.substring(0, 22) + "...";
                }
                String converted;
                double chance = e.getChange() / 100;
                if (chance < 0) {
                    chance *= -1.0;
                }
                converted = String.format("%.02f", chance);
                String[] parts = converted.split("\\.");
                String amount = "";
                int count = 1;
                for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
                    if (((count % 3) == 0) && ind > 0) {
                        amount = thSep + parts[0].charAt(ind) + amount;
                    } else {
                        amount = parts[0].charAt(ind) + amount;
                    }
                    count++;
                }
                if (loc.equals("nl-NL")) {
                    amount = curSymbol + " " + amount + decSep + parts[1];
                } else {
                    amount = curSymbol + amount + decSep + parts[1];
                }
                if (e.getChange() < 0 && loc.equals("en-US")) {
                    amount = "(" + amount + ")";
                } else if (e.getChange() < 0 && loc.equals("nl-NL")) {
                    amount = curSymbol + " -" + amount.replace(curSymbol, "").trim() + " ";
                } else if (loc.equals("nl-NL")) {
                    amount = " " + amount + " ";
                } else {
                    amount = amount + " ";
                }
                s = s + "\n" + String.format("%s | %-25s | %13s",
                        date,
                        desc,
                        amount);
            }
        }
        return s;
    }

    private static void validateLocale(String loc) {
        if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        }
    }

    private static void validateCurrency(String cur) {
        if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        }
    }

    public static class LedgerEntry {
        private LocalDate localDate;
        private String description;
        private double change;

        public LedgerEntry(LocalDate localDate, String description, double change) {
            this.localDate = localDate;
            this.description = description;
            this.change = change;
        }

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }
}