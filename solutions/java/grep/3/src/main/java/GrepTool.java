import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class GrepTool {
    String grep(String pattern, List<String> flags, List<String> files) {
        List<String> results = new ArrayList<>();
        String result = "";
        try {
            for (String nameFile : files) {
                List<String> readFile = readFile(nameFile);
                for (int i = 0; i < readFile.size(); i++) {
                    String text = readFile.get(i);
                    if (flags.isEmpty()) {
                        if (text.contains(pattern)) {
                            if (files.size() > 1) {
                                results.add(String.format("%s:%s", nameFile, text));
                            } else {
                                results.add(text);
                            }
                        }
                    } else {
                        if (flags.contains("-l") && text.contains(pattern)) {
                            results.add(nameFile);
                            break;
                        }
                        if (flags.contains("-v") && !matchText(flags, text, pattern)) {
                            result = text;
                        } else if (!flags.contains("-v") && matchText(flags, text, pattern)) {
                            result = text;
                        }
                        if(result.length() > 1){
                            if (flags.contains("-n") && files.size() > 1) {
                                results.add(String.format("%s:%d:%s",nameFile,i+1,result));
                            } else if (flags.contains("-n")) {
                                results.add(String.format("%d:%s",i+1,result));
                            } else if (files.size() > 1){
                                results.add(String.format("%s:%s",nameFile,result));
                            } else {
                                results.add(result);
                            }
                            result="";
                        }
                    }
                }
            }
        } catch (IOException ignored) {
             throw new IllegalArgumentException();
        }
        return String.join("\n", results).trim();
    }
    private List<String> readFile(String nameFile) throws IOException {
        return Files.readAllLines(Paths.get(nameFile));
    }
    private boolean matchText(List<String> condition, String text, String pattern) {
        if (condition.contains("-i") && condition.contains("-x")) {
            return text.equalsIgnoreCase(pattern);
        } else if (condition.contains("-x")) {
            return text.equals(pattern);
        } else if (condition.contains("-i")) {
            return text.toLowerCase().contains(pattern.toLowerCase());
        }else {
            return text.contains(pattern);
        }
    }
}