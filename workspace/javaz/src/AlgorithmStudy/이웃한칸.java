package AlgorithmStudy;

/**
 * @title  : 이웃한 칸
 * @author : SI YOUNG
 */

public class 이웃한칸 {
    class Solution {
        public int solution(String[][] board, int h, int w) {
            int count = 0;
            int length = board.length;
            int[] dh = {0, -1, 1, 0};
            int[] dw = {1, 0, 0, -1};
            for(int i = 0; i < 4; i++){
                int h_check = h + dh[i];
                int w_check = w + dw[i];
                if(h_check < length && h_check >= 0 && w_check < length && w_check >= 0)
                    if(board[h][w].equals(board[h_check][w_check])){
                        count++;
                    }
            }
            return count;
        }
    }
}
