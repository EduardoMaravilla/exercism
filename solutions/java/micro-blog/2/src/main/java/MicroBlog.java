class MicroBlog {
    public String truncate(String input) {
        int longitud = input.codePointCount(0, input.length());
        if (longitud > 5) {
            input = input.substring(0, input.offsetByCodePoints(0, 5));
        }
        return input;
    }
}