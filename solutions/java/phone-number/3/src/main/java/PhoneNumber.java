public class PhoneNumber {
    private final String number;
    public PhoneNumber(String number) {
        this.number = number.replaceAll("[^0-9]", "");
        int d = this.number.length();
        if (number.matches(".*[a-zA-Z].*")) throw new IllegalArgumentException("letters not permitted");
        if (number.matches(".*[@:!].*")) throw new IllegalArgumentException("punctuations not permitted");
        if (d < 10) throw new IllegalArgumentException("must not be fewer than 10 digits");
        if (d > 11) throw new IllegalArgumentException("must not be greater than 11 digits");
        if (d == 11 && this.number.charAt(0) != '1') throw new IllegalArgumentException("11 digits must start with 1");
        if (getNumber().charAt(0) == '0') throw new IllegalArgumentException("area code cannot start with zero");
        if (getNumber().charAt(0) == '1') throw new IllegalArgumentException("area code cannot start with one");
        if (getNumber().charAt(3) == '0') throw new IllegalArgumentException("exchange code cannot start with zero");
        if (getNumber().charAt(3) == '1') throw new IllegalArgumentException("exchange code cannot start with one");
    }
    public String getNumber() {
        return number.length() == 10 ? number : number.substring(1);
    }
}