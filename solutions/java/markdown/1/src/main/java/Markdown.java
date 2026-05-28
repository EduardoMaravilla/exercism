class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        String result = "";
        boolean activeList = false;
         //for to evaluate if there is more than one line, if not it is only executed once
        for (int i = 0; i < lines.length; i++) {
            String theLine = parseHeader(lines[i]); //evaluates if the line is a title or header
            if (theLine == null) {
              theLine = parseListItem(lines[i]); //evaluates if the line of text belongs to a list
            }
            if (theLine == null) 
            {
                theLine = parseParagraph(lines[i]); //returns the line in paragraph format
            }
            //condition that passes if the line of text belongs to a list
            if (theLine.matches("(<li>).*") && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*") && !activeList) {
                activeList = true;
              result = result + "<ul>";
                result = result + theLine;
            }
            //ends the list in case there is another type of element next
            else if (!theLine.matches("(<li>).*") && activeList) {
                activeList = false;
                result = result + "</ul>";
                result = result + theLine;
            } else {
              result = result + theLine;
            }
        }
        //end list if last value was a list
        if (activeList) {
            result = result + "</ul>";
        }

        return result;
    }
    //returns the header type text and its respective level
    private String parseHeader(String markdown) {
        int count = 0;
        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++) 
        {
            count++;
        }

        if (count == 0) { return null; }

        return "<h" + Integer.toString(count) + ">" + markdown.substring(count + 1) + "</h" + Integer.toString(count)+ ">";
    }
    //returns if it is a list element
    private String parseListItem(String markdown) {
        if (markdown.startsWith("*")) {
            String skipAsterisk = markdown.substring(2);
            String listItemString = parseSomeSymbols(skipAsterisk);
            return "<li>" + listItemString + "</li>";
        }

        return null;
    }
    //returns if it is a paragraph
    private String parseParagraph(String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }
    //returns adding the corresponding tags to the text encoding
    private String parseSomeSymbols(String markdown) {
        String lookingFor = "__(.+)__";
        String update = "<strong>$1</strong>";
        String workingOn = markdown.replaceAll(lookingFor, update);

        lookingFor = "_(.+)_";
        update = "<em>$1</em>";
        return workingOn.replaceAll(lookingFor, update);
    }
}
