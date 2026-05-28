import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SgfParsing {

    public SgfNode parse(String input) throws SgfParsingException {
        if (input.isBlank() || input.charAt(0) != '(' || input.charAt(input.length() - 1) != ')') {
            throw new SgfParsingException("tree missing");
        }
        ParserCursor cursor = new ParserCursor();
        return parseTree(input, cursor);
    }

    private SgfNode parseTree(String input, ParserCursor cursor) throws SgfParsingException {
        if (input.charAt(cursor.index) != '(') {
            throw new SgfParsingException("expected '(' at position " + cursor.index);
        }
        cursor.index++;

        List<SgfNode> sequence = new ArrayList<>();
        while (cursor.index < input.length() && input.charAt(cursor.index) != ')') {
            char c = input.charAt(cursor.index);
            if (c == ';') {
                cursor.index++;
                sequence.add(parseNode(input, cursor));
            } else if (c == '(') {
                SgfNode variation = parseTree(input, cursor);
                if (!sequence.isEmpty()) {
                    sequence.get(sequence.size() - 1).appendChild(variation);
                }
            } else {
                cursor.index++; 
            }
        }

        if (cursor.index >= input.length() || input.charAt(cursor.index) != ')') {
            throw new SgfParsingException("expected ')' at position " + cursor.index);
        }
        cursor.index++;

        if (sequence.isEmpty()) {
            throw new SgfParsingException("tree with no nodes");
        }

        SgfNode root = sequence.get(0);
        SgfNode current = root;
        for (int i = 1; i < sequence.size(); i++) {
            current.appendChild(sequence.get(i));
            current = sequence.get(i);
        }

        return root;
    }

    private SgfNode parseNode(String input, ParserCursor cursor) throws SgfParsingException {
        StringBuilder nodeBuilder = new StringBuilder();
        boolean inBracket = false;
        boolean escaping = false;

        while (cursor.index < input.length()) {
            char c = input.charAt(cursor.index);
            if (!inBracket && (c == ';' || c == '(' || c == ')')) {
                break;
            }

            if (escaping) {
                nodeBuilder.append(c);
                escaping = false;
            } else if (c == '\\') {
                nodeBuilder.append(c);
                escaping = true;
            } else {
                nodeBuilder.append(c);
                if (c == '[') inBracket = true;
                if (c == ']') inBracket = false;
            }

            cursor.index++;
        }

        List<String> properties = getPropertiesNode(nodeBuilder.toString());
        return new SgfNode(parsePropertiesMap(properties));
    }

    private List<String> getPropertiesNode(String node) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inBracket = false;
        boolean escaping = false;

        for (int i = 0; i < node.length(); i++) {
            char c = node.charAt(i);

            if (escaping) {
                current.append(c);
                escaping = false;
                continue;
            }

            if (c == '\\') {
                current.append(c);
                escaping = true;
                continue;
            }

            if (c == '[') {
                inBracket = true;
                current.append(c);
                continue;
            }

            if (c == ']') {
                inBracket = false;
                current.append(c);
                continue;
            }

            if (!inBracket && Character.isUpperCase(c)) {
                if (!current.isEmpty()) {
                    result.add(current.toString());
                    current.setLength(0);
                }
            }

            current.append(c);
        }

        if (!current.isEmpty()) {
            result.add(current.toString());
        }

        return result;
    }

    public Map<String, List<String>> parsePropertiesMap(List<String> nodes) throws SgfParsingException {
        Map<String, List<String>> properties = new LinkedHashMap<>();
        for (String node : nodes) {
            if (node.isEmpty()) continue;
            int i = 0;
            while (i < node.length() && node.charAt(i) != '[') {
                i++;
            }
            String key = node.substring(0, i);
            if (!key.equals(key.toUpperCase())) {
                throw new SgfParsingException("property must be in uppercase");
            }
            if (key.length() == node.length()) {
                throw new SgfParsingException("properties without delimiter");
            }

            List<String> values = new ArrayList<>();

            while (i < node.length()) {
                if (node.charAt(i) == '[') {
                    i++;
                    StringBuilder value = new StringBuilder();
                    boolean escaping = false;

                    while (i < node.length()) {
                        char c = node.charAt(i);
                        if (escaping) {
                            if (c == '\n' || c == '\r') {
                            } else {
                                value.append(c);
                            }
                            escaping = false;
                        } else if (c == '\\') {
                            escaping = true;
                        } else if (c == ']') {
                            break;
                        } else {
                            value.append(c);
                        }
                        i++;
                    }
                    values.add(value.toString().replace("\t"," "));
                }
                i++;
            }
            properties.put(key, values);
        }
        return properties;
    }

    private static class ParserCursor {
        int index = 0;
    }
}
