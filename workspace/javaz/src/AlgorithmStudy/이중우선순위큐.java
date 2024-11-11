package AlgorithmStudy;
import java.util.*;

/**
 * @title  : 캐시
 * @author : SI YOUNG
 */

public class 이중우선순위큐 {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];
            List<Integer> list = new ArrayList<>();

            for(String oper : operations){
                if(oper.equals("D 1")){
                    Collections.sort(list);
                    if(list.size() != 0) list.remove(list.size() - 1);
                } else if(oper.equals("D -1")){
                    Collections.sort(list);
                    if(list.size() != 0) list.remove(0);
                } else {
                    String num = oper.replace("I ", "");
                    list.add(Integer.parseInt(num));
                }
            }
            Collections.sort(list);
            if(list.size() != 0) {
                answer[0] = list.get(list.size() - 1);
                answer[1] = list.get(0);
            } else {
                answer[0] = 0;
                answer[1] = 0;
            }
            return answer;

        }
    }
