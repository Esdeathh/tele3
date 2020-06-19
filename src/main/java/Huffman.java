import java.util.*;

class HuffmanNode {
    int data;
    char code;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {
        return x.data - y.data;
    }
}

public class Huffman {
    static Map<String, Character> decodeMap = new HashMap<>();
    static Map<Character, String> encodeMap = new HashMap<>();

    public static void generateTree(HuffmanNode root, String s)
    {
        if (root.left == null && root.right == null && (Character.isLetter(root.code) || Character.isWhitespace(root.code))) {
            decodeMap.put(s, root.code);
            encodeMap.put(root.code, s);
            return;
        }
        generateTree(root.left, s + "0");
        generateTree(root.right, s + "1");
    }

    public static Map<String, Character> generateCodes(String text)
    {
        Map<Character, Integer> distMap = getDistinct(text);

        PriorityQueue<HuffmanNode> q
                = new PriorityQueue<>(distMap.size(), new MyComparator());

        for (Character a : distMap.keySet()) {

            HuffmanNode hn = new HuffmanNode();

            hn.code = a;
            hn.data = distMap.get(a);

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.code = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        generateTree(root, "");

        return decodeMap;
    }

    static Map<Character, Integer> getDistinct(String str)
    {
        Map<Character, Integer> map = new HashMap<>();

        int[] count = new int[256];

        int i;
        for (i = 0; i < str.length(); i++)
            count[(int)str.charAt(i)]++;
        int n = i;

        for (i = 0; i < n; i++)
            map.put(str.charAt(i), count[(int)str.charAt(i)]);

        return map;
    }

    public static String encode(String text) {
        generateCodes(text);
        String encoded = "";
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            encoded = encoded.concat(encodeMap.get(c));
        }
        return encoded;
    }

    public static String decode(String encoded, Map<String, Character> map) {
        String tmp2 = "";
        String decoded = "";
        for (int i = 0; i < encoded.length(); i++){
            char c = encoded.charAt(i);
            tmp2 += c;
            if(map.get(tmp2) != null) {
                decoded += map.get(tmp2);
                tmp2 = "";
            }
        }

        return decoded;
    }
}
