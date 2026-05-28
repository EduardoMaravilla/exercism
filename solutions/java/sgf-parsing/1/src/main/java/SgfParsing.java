import java.util.*;
import java.util.regex.*;

public class SgfParsing {
    public SgfNode parse(String input) throws SgfParsingException {
        if (input.isBlank() || input.equals(";")) throw new SgfParsingException("tree missing");
        if (Pattern.compile("^\\(\\)$").matcher(input).matches()) throw new SgfParsingException("tree with no nodes");
        if (Pattern.compile("^\\(;[A-Z]+\\)$").matcher(input).matches()) throw new SgfParsingException("properties without delimiter");
        if (Pattern.compile("^\\(;[A-Z]*[a-z]+[A-Z]*\\[.+]\\)$").matcher(input).matches()) throw new SgfParsingException("property must be in uppercase");
        if (Pattern.compile("^\\(;\\)$").matcher(input).matches()) return new SgfNode();
        if (Pattern.compile("^\\(;\\w\\[\\w](|\\(;\\w\\[\\w]|\\))*\\)$").matcher(input).matches() ||
                Pattern.compile("^\\(;\\w\\[\\w](;\\w\\[\\w])*\\)$").matcher(input).matches()) {
            String patron = ";\\w\\[\\w]";
            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(input);
            List<String> vals = new ArrayList<>();
            while (matcher.find()) {
                vals.add(matcher.group());
            }
            if (vals.size() > 1){
                SgfNode sgfNode = createSfgNode(vals.get(0));
                vals.remove(0);
                for (String val : vals) {
                    sgfNode.appendChild(createSfgNode(val));
                }
                return sgfNode;
            }else {
                return createSfgNode(vals.get(0));
            }
        }
        return createSfgNode(input);
    }

    private SgfNode createSfgNode(String input) {
        input = input.replaceAll("^\\(|;|\\)$", "");
        input = input.replaceAll("\\\\]","£");
        List<String> keys2 = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z]+(?![^\\[]*])");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            keys2.add(matcher.group());
        }
        String[] values = input.split("(?=[A-Z]+\\[)");
        Map<String, List<String>> properties = new HashMap<>();
        for (int i = 0; i < keys2.size(); i++) {
            List<String> vals = new ArrayList<>();
            Pattern pattern2 = Pattern.compile("\\[([^\\[\\]]*)]");
            Matcher matcher2 = pattern2.matcher(values[i]);
            while (matcher2.find()) {
                vals.add(matcher2.group(1).replace("£","]"));
            }
            properties.put(keys2.get(i), vals);
        }
        return new SgfNode(properties);
    }
}