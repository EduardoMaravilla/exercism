public class FoodChain {
    private final String[] animal = {"fly.\n", "spider.\n", "bird.\n", "cat.\n", "dog.\n", "goat.\n", "cow.\n", "horse.\n"};
    private final String[] desAnimal = {"It wriggled and jiggled and tickled inside her.\n", "How absurd to swallow a bird!\n", "Imagine that, to swallow a cat!\n", "What a hog, to swallow a dog!\n", "Just opened her throat and swallowed a goat!\n", "I don't know how she swallowed a cow!\n"};
    private final String[] chainFood = {"She swallowed the spider to catch the fly.\n", "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n", "She swallowed the cat to catch the bird.\n", "She swallowed the dog to catch the cat.\n", "She swallowed the goat to catch the dog.\n", "She swallowed the cow to catch the goat.\n"};
    public String verses(int startverse, int endverse) {
        StringBuilder verses = new StringBuilder();
        for (int j = startverse; j <= endverse; j++) {
            verses.append(verse(j));
            if (j != endverse) {
                verses.append("\n\n");
            }
        }
        return verses.toString();
    }

    public String verse(int ver) {
        StringBuilder temp = new StringBuilder();
        String verse1 = "I don't know why she swallowed the fly. Perhaps she'll die.";
        String verse2 = "I know an old lady who swallowed a ";
        if (ver == 1) {
            return verse2 + animal[ver - 1] + verse1;
        } else if (ver == 8) {
            return verse2 + animal[ver - 1] + "She's dead, of course!";
        } else {
            for (int i = 0; i <= (ver - 2); i++) {
                temp.insert(0, chainFood[i]);
            }
            return verse2 + animal[ver - 1] + desAnimal[ver - 2] + temp + verse1;
        }
    }
}