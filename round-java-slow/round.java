
import java.io.*;

public class round {
    // The main function.
    public static void main(String args[]) {
        final long startTime = System.currentTimeMillis();
        try {

            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String line1 = in.readLine();

            String line2 = in.readLine();
            in.close();

            String[] words2 = line2.split(" ");// splits the string based on whitespace
            String[] words1 = line1.split(" ");// splits the string based on whitespace

            short cities = Short.parseShort(words1[0]);
            short cars = Short.parseShort(words1[1]);

            Short[] locations = new Short[cars];
            int hiha = 0;
            for (String w : words2) {
                short number = Short.parseShort(w);
                locations[hiha] = number;
                hiha = hiha + 1;
            }

            Integer max = 0;
            Integer sum = 0;
            Integer ulti_sum = Integer.MAX_VALUE;
            Short ulti_cit = 90;

            for (short j = 0; j < cities; j++) {
                for (short i = 0; i < cars; i++) {

                    // short location = locations[i];
                    int temp;

                    temp = j - locations[i];
                    if (temp < 0)
                        temp += cities;

                    sum += temp;
                    if (temp > max)
                        max = temp;

                }

                if (sum < ulti_sum) {
                    if (sum - max - max >= -1) {

                        ulti_sum = sum;
                        ulti_cit = j;
                    }
                }
                max = 0;
                sum = 0;
            }

            System.out.print(ulti_sum);

            System.out.print(" ");
            System.out.println(ulti_cit);

            final long endTime = System.currentTimeMillis();
            System.out.println("\nTotal execution time: " + (endTime - startTime));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}