import java.io.*;
import java.util.*;

public class qssort {

    public static ArrayList<ArrayList<Integer>> move_q(ArrayList<ArrayList<Integer>> state) {

        state.get(1).add(0, state.get(0).get(0));
        state.get(0).remove(0);

        return state;
    }

    public static ArrayList<ArrayList<Integer>> move_s(ArrayList<ArrayList<Integer>> state) {

        state.get(0).add(state.get(1).get(0));
        state.get(1).remove(0);

        return state;
    }

    public static Deque<ArrayList<ArrayList<Integer>>> next(ArrayList<ArrayList<Integer>> state) {
        
        Deque<ArrayList<ArrayList<Integer>>> katifor_next = new LinkedList<>();

        ArrayList<Integer> stac_minus1 = new ArrayList<>();
        stac_minus1.add(-1);
        ArrayList<ArrayList<Integer>> failed_state = new ArrayList<>();
        failed_state.add(stac_minus1);
        failed_state.add(stac_minus1);

        ArrayList<Integer> new_que = new ArrayList<>();
        ArrayList<Integer> new_stac = new ArrayList<>();
        new_que.addAll(state.get(0));
        new_stac.addAll(state.get(1));

        ArrayList<ArrayList<Integer>> state1 = new ArrayList<>();
        state1.add(new_que);
        state1.add(new_stac);

        if (state.get(1).isEmpty()) {
            katifor_next.add(move_q(state1));
            katifor_next.add(failed_state);
            return katifor_next;
        }
        if (state.get(0).isEmpty()) {

            katifor_next.add(failed_state);
            katifor_next.add(move_s(state1));
            return katifor_next;
        }
        if (new_que.get(0) == new_stac.get(new_stac.size() - 1)) {
            katifor_next.add(move_q(state1));
            katifor_next.add(failed_state);
            return katifor_next;
        }

        katifor_next.add(move_q(state1));
        katifor_next.add(move_s(state1));
        return katifor_next;
    }

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

            ArrayList<ArrayList<Integer>> state = new ArrayList<>();
            state.add(holy_que);
            state.add(empty_stac);

            ArrayList<Integer> stac_minus1 = new ArrayList<>();
            stac_minus1.add(-1);
            ArrayList<ArrayList<Integer>> failed_state = new ArrayList<>();
            failed_state.add(stac_minus1);
            failed_state.add(stac_minus1);

            ArrayList<ArrayList<Integer>> state_m = new ArrayList<>();

            if (sorted_que.equals(holy_que)) {
                System.out.println("empty");
            } else {

                boolean solved = false;
                HashSet<String> prev = new HashSet<>();
                String temp = new String();
                temp = state.toString();
                prev.add(temp);

                Deque<ArrayList<ArrayList<Integer>>> d2lis = new LinkedList<>();
                Deque<String> road_list = new LinkedList<>();
                road_list.add("");
                d2lis.add(state);
                String road = new String();
                String road1 = new String();



                
                while (true) {
                    state_m.addAll(d2lis.pop());
                    road = road_list.pop();

                    if (state_m.get(0).equals(sorted_que)) {
                        solved = true;
                        break;
                    }
                    Deque<ArrayList<ArrayList<Integer>>> t = new LinkedList<>();
                    t = next(state_m);

                    if (t.peek().equals(failed_state)) {
                        t.pop();
                    } else { // q
                        if (!prev.contains(t.peek().toString())) {
                            d2lis.addLast(t.peek());
                            prev.add(state_m.toString());
                            t.pop();
                            road1 = road + 'Q';
                            road_list.addLast(road1);

                        }
                    }
                    if (t.peek().equals(failed_state)) {
                        t.pop();
                    } else { // s
                        if (!prev.contains(t.peek().toString())) {
                            d2lis.addLast(t.peek());
                            prev.add(state_m.toString());
                            t.pop();
                            road1 = road + 'S';
                            road_list.addLast(road1);

                        }
                    }
                    state_m.clear();
                    
                }
                if (solved) {
                    System.out.println(road);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
