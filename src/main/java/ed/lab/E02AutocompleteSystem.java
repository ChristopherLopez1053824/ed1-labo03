package ed.lab;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class E02AutocompleteSystem {
    HashMap<String, Integer>[]map;
    String current = "";

    class  Frequency{
        String word;
        int frequency;
        public Frequency(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
    public E02AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap[26];
        for (int i = 0; i < 26; i++) {
            map[i] = new HashMap<String, Integer>();
        }
        for (int i = 0; i < sentences.length; i++) {
            map[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#') {
            map[current.charAt(0) - 'a'].put(current, map[current.charAt(0) - 'a'].getOrDefault(current, 0) + 1);
            current = "";
        }
        else {
            current += c;
            List<Frequency> list = new ArrayList<>();
            for (String s : map[current.charAt(0) - 'a'].keySet()) {
                if (s.indexOf(current) == 0) {
                    list.add(new Frequency(s, map[current.charAt(0) - 'a'].get(s)));
                }
            }
            Collections.sort(list, (a, b) -> a.frequency == b.frequency ? a.word.compareTo(b.word) : b.frequency - a.frequency);
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                result.add(list.get(i).word);
            }
        }
        return result;

    }
}


