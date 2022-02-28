package sentences;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        validateSentence(sentence);
        String[] words = sentence.split(" ");
        if (words.length < 5) {
            return sentence;
        } else {
            return words[0] + " ... " + words[words.length - 1];
        }
    }

    private void validateSentence(String sentence) {
        validateFirstChar(sentence);
        validateLastChar(sentence);
    }

    private void validateFirstChar(String sentence) {
        char first = sentence.charAt(0);
        if (!Character.isUpperCase(first)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
    }

    private void validateLastChar(String sentence) {
        char last = sentence.charAt(sentence.length() - 1);
        switch (last) {
            case '.', '!', '?' -> {
                // Valid case
            }
            default -> throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
