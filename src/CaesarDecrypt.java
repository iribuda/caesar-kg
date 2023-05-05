import java.util.*;

public class CaesarDecrypt {

    private String encryptedText;
    private ArrayList<Character> lowerCase;
    private ArrayList<Character> upperCase;
    private LinkedList<Character> mostFrequentInLang;

    public CaesarDecrypt(String encryptedText) {
        this.encryptedText = encryptedText.toLowerCase();
        lowerCase = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'ң', 'о', 'ө', 'п', 'р', 'с', 'т', 'у', 'ү', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'));
        setMostFrequentInLang();
        setUpperCase();
    }

    public HashMap<Character, Integer> findFrequencies(){
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (int i=0; i<encryptedText.length(); i++){
            char letter = encryptedText.charAt(i);
            if (!frequencies.containsKey(letter)){
                frequencies.put(letter, 0);
            }
            frequencies.replace(letter, frequencies.get(letter)+1);
        }
        frequencies.remove(' ');
        return frequencies;
    }

    public ArrayList<Character> getFrequencyList(){
        List<Map.Entry<Character, Integer> > list = new ArrayList<>(findFrequencies().entrySet());
        list.sort(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        ArrayList<Character> frequencies = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : list){
            frequencies.add(e.getKey());
        }
        return frequencies;
    }

    public int findKey(char mostFrequentLetterInLang){
        ArrayList<Character> frequencies = getFrequencyList();
        char mostFrequentInText = frequencies.get(frequencies.size()-1);
        int key = (36 + lowerCase.indexOf(mostFrequentInText) - lowerCase.indexOf(mostFrequentLetterInLang)) % 36;
        return key;
    }

    public String shift(int key){
        StringBuilder res = new StringBuilder();
        char current;
        for (int i=0; i<encryptedText.length(); i++){
            current = encryptedText.charAt(i);
            if (current == ' ') res.append(' ');
            else res.append(shiftLetter(key, current));
        }
        return res.toString();
    }

    public char shiftLetter(int key, char current){
        int index;
        char decrypted;
        if (Character.toUpperCase(current)==current){
            index = upperCase.indexOf(current);
            if (index<key) index += 36;
            decrypted = upperCase.get(index-key);
        }
        else{
            index = lowerCase.indexOf(current);
            if (index<key) index += 36;
            decrypted = lowerCase.get(index-key);
        }
        return decrypted;
    }

    public String decrypt(){
        int key = findKey(mostFrequentInLang.poll());
        String decrypted = shift(key);
        return decrypted;
    }

    private void setUpperCase(){
        upperCase = new ArrayList<>();
        for (int i=0; i<36; i++){
            upperCase.add(Character.toUpperCase(lowerCase.get(i)));
        }
    }

    public void setMostFrequentInLang() {
        mostFrequentInLang = new LinkedList<>();
        mostFrequentInLang.add('а');
        mostFrequentInLang.add('э');
        mostFrequentInLang.add('н');
        mostFrequentInLang.add('о');
        mostFrequentInLang.add('т');
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }
}
