package AlgorithmStudy;

/**
 * @title  : [1차]프렌즈 4블록
 * @author : SI YOUNG
 */

public class 프렌즈4블록 {
    class Solution {
        public static int height;
        public static int width;
        public static int count;
        public static int answer;
        public static char[][] board;

        public int solution(int m, int n, String[] boardInput) {
            answer = 0;
            height = m;
            width = n;
            count = 0;
            board = new char[height][width];

            // board 배열 초기화
            for (int i = 0; i < height; i++) {
                board[i] = boardInput[i].toCharArray();
            }

            while (true) {
                boolean[][] same = findBlocksToRemove();
                if (count == 0) {
                    break;
                }
                answer += count;
                removeBlocks(same);
            }

            return answer;
        }

        private static boolean[][] findBlocksToRemove() {
            boolean[][] same = new boolean[height][width];
            count = 0;

            // 2x2 블록을 찾아서 same 배열에 기록
            for (int i = 0; i < height - 1; i++) {
                for (int j = 0; j < width - 1; j++) {
                    char current = board[i][j];
                    if (current != ' ' &&
                            current == board[i + 1][j] &&
                            current == board[i][j + 1] &&
                            current == board[i + 1][j + 1]) {
                        same[i][j] = true;
                        same[i + 1][j] = true;
                        same[i][j + 1] = true;
                        same[i + 1][j + 1] = true;
                    }
                }
            }

            count = countRemovedBlocks(same);
            return same;
        }

        private static int countRemovedBlocks(boolean[][] same) {
            int totalRemoved = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (same[i][j]) {
                        totalRemoved++;
                    }
                }
            }
            return totalRemoved;
        }

        private static void removeBlocks(boolean[][] same) {
            for (int j = 0; j < width; j++) {
                int writeIndex = height - 1;

                // 블록을 제거하고 문자를 아래로 이동
                for (int i = height - 1; i >= 0; i--) {
                    if (!same[i][j]) {
                        board[writeIndex--][j] = board[i][j];
                    }
                }

                // 빈 공간 채우기
                while (writeIndex >= 0) {
                    board[writeIndex--][j] = ' ';
                }
            }
        }
    }
}
