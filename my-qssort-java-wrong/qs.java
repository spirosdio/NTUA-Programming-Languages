import java.io.*;
import java.util.*;

public class qs {



    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String line = in.readLine();

            String line2 = in.readLine();
            Scanner scanner = new Scanner(line2);
            ArrayList<Integer> holy_que = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                holy_que.add(scanner.nextInt());
            }

            ArrayList<Integer> sorted_que = new ArrayList<>();
            ArrayList<Integer> empty_stac = new ArrayList<>();
            sorted_que.addAll(holy_que);

            Collections.sort(sorted_que);

            in.close();
            scanner.close();

            System.out.println(holy_que);




            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
