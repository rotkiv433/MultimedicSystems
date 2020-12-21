import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ArithmeticCoding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter input string (ex. MULTI): ");
        String input_line = br.readLine();
        char[] array_char = input_line.toCharArray();
        List<Character> array_characters = new ArrayList<>(array_char.length);
        List<Float> array_probabilites = new ArrayList<>(array_char.length);

        Map<Character, Float> map = new HashMap<>();
        for(int i=0;i<array_char.length;i++) {
            System.out.print("Enter probability for character " + array_char[i] + ": ");
            float p = Float.parseFloat(br.readLine());
            array_characters.add(array_char[i]);
            array_probabilites.add(p);
        }

        for(int i=0;i<array_char.length-1;i++) {
            for(int j=i+1;j<array_char.length;j++) {
                if(array_probabilites.get(j) > array_probabilites.get(i)) {
                    array_probabilites.add(j, array_probabilites.get(i));
                    array_probabilites.set(i, array_probabilites.get(j+1));
                    array_probabilites.remove(j+1);

                    array_characters.add(j, array_characters.get(i));
                    array_characters.set(i, array_characters.get(j+1));
                    array_characters.remove(j+1);

                }
            }
        }

        float temp = (float) 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<array_char.length;i++) {
//            System.out.print(temp + " - " + array_characters.get(i) + " - ");
            stringBuilder.append(temp).append(" - ").append(array_characters.get(i)).append(" - ");
            temp += array_probabilites.get(i);


        }
        stringBuilder.append(temp);
//        System.out.print(temp);
        System.out.println(stringBuilder+"\n");
        String output = stringBuilder.toString();
        String[] parts = output.split(" - ");
        float pLeft= (float) 0.0, pRight= (float) 0.0, range= (float) 0.0;
        for(int i=0;i<array_char.length-1;i++) {
            for(int j=0;j<parts.length;j++) {
                if(parts[j].equals(String.valueOf(array_char[i]))) {
                    pLeft = Float.parseFloat(parts[j-1]);
                    pRight = Float.parseFloat(parts[j+1]);
                    range = pRight - pLeft;
                    break;
                }

            }
            StringBuilder s = stringBuild(array_char,pLeft,pRight,array_characters,array_probabilites);
            String testt = s.toString();
            parts = testt.split(" - ");
            System.out.print("CODING FOR CHARACTER " + array_char[i] + ": ");
            System.out.println(s);
        }
        char checkChar = array_char[array_char.length-1];
        for(int i=0;i<parts.length;i++) {
            if(parts[i].equals(String.valueOf(checkChar))) {
                System.out.println("\nOpsegot koj se dobiva za " + checkChar + " e: " + parts[i-1] + " - " + parts[i+1]);
                System.out.println(parts[i-1] + " <= koden zbor < " + parts[i+1]);
                break;
            }

        }




    }

    private static StringBuilder stringBuild(char[] array_char, float pLeft, float pRight, List<Character> array_characters, List<Float> array_probabilites) {
        float temp = pLeft;
        float range = pRight - pLeft;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<array_char.length;i++) {
//            System.out.print(temp + " - " + array_characters.get(i) + " - ");
            stringBuilder.append(temp).append(" - ").append(array_characters.get(i)).append(" - ");
            temp += (range * array_probabilites.get(i));


        }
        stringBuilder.append(pRight);
        return stringBuilder;
    }
}
