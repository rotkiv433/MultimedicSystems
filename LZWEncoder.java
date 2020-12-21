import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.CharacterCodingException;
import java.util.*;

public class LZWEncoder {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input string: ");
        String input = br.readLine();
        Map<String, Integer> map = new HashMap<>();
        boolean moreThanOneCharacter = false;
        //input number of starting items in dictionary
        System.out.print("Number of items in dictionary: ");
        String inputNumber = br.readLine();
        int numberStartingItems = Integer.parseInt(inputNumber);
        for(int i=0;i<numberStartingItems;i++) {
            //ReadLine
            //ex S 1
            //   T 2 ...
            System.out.print("Element " + (i+1) + " (ex. S 1): ");
            String line = br.readLine();
            String[] items = line.split(" ");
            int value = Integer.parseInt(items[1]);
            if(items[0].length() > 1) {
                System.out.println("ERROR: " + items[0] + " has more characters than 1");
                moreThanOneCharacter = true;
                break;
            }

            map.put(items[0].toUpperCase(), value);
        }

        if(!moreThanOneCharacter) {
            int maxValueInMap = (Collections.max(map.values()));
            input = input.toUpperCase();

            String extend = "";

            List<Integer> encoded_values = new ArrayList<>();

            for(char symbol : input.toCharArray()) {
                String str_c = extend + symbol;
                if(map.containsKey(str_c)) {
                    extend = str_c;
                }
                else {
                    encoded_values.add(map.get(extend));
                    map.put(str_c, ++maxValueInMap);
                    extend = "" + symbol;
                }
            }

            Iterator<Integer> iterator = encoded_values.iterator();
            System.out.println("Input string is: " + input);
            System.out.print("Encoded string is: ");
            while(iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }



        }

    }


}
