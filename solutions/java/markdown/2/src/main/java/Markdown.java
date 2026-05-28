public class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean activeList = false;

        for (String line : lines) {
            String theLine = parseHeader(line);
            if (theLine == null) {
                theLine = parseListItem(line);
            }
            if (theLine == null) {
                theLine = parseParagraph(line);
            }
            if (theLine.matches("(<li>).*") && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*") && !activeList) {
                activeList = true;
                result.append("<ul>");
                result.append(theLine);
            } else if (!theLine.matches("(<li>).*") && activeList) {
                activeList = false;
                result.append("</ul>");
                result.append(theLine);
            } else {
                result.append(theLine);
            }
        }
        if (activeList) {
            result.append("</ul>");
        }
        return result.toString();
    }

    private String parseHeader(String markdown) {
        int count = 0;
        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++) count++;
        if (count > 6) { return "<p>" + markdown + "</p>"; }
        if (count == 0) { return null; }
        return "<h" + count + ">" + markdown.substring(count + 1) + "</h" + count + ">";
    }

    private String parseListItem(String markdown) {
        if (markdown.startsWith("*")) {
            return "<li>" + parseSomeSymbols(markdown.substring(2)) + "</li>";
        }
        return null;
    }

    private String parseParagraph(String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }

    private String parseSomeSymbols(String markdown) {
        String workingOn = markdown.replaceAll("__(.+)__", "<strong>$1</strong>");
        return workingOn.replaceAll("_(.+)_", "<em>$1</em>");
    }
}