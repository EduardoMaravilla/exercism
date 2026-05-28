import java.util.ArrayList;
import java.util.List;

public class LanguageList {
    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
     return languages.isEmpty();   
    }

    public void addLanguage(String language) {
        languages.add(language); 
    }

    public void removeLanguage(String language) {
        languages.remove(language);   
    }

    public String firstLanguage() {
        return languages.get(0);
    }

    public int count() {
        return languages.size();
    }

    public boolean containsLanguage(String language) {
        return languages.contains(language);
    }

    public boolean isExciting() {
        String palabra1="Java";
        String palabra2="Kotlin";
        if(languages.contains(palabra1)){
            return true;
        }else if(languages.contains(palabra2)){
            return true;
        }else{
            return false;
        }
    }
}
