package AlgorithmStudy;

/**
 * @title  : 조이스틱
 * @author : SI YOUNG
 */

public class 조이스틱 {
    class Solution {
        private static char[] nameAry;

        public int solution(String name) {
            nameAry = name.toCharArray();
            int totalMove = 0;

            // 알파벳 상하 이동 횟수 계산
            for (int i = 0; i < nameAry.length; i++) {
                totalMove += getMinMove(nameAry[i]);
            }

            // 좌우 이동 횟수 계산
            int minMove = nameAry.length - 1; // 끝까지 가는 경우로 초기화

            for (int i = 0; i < nameAry.length; i++) {
                int nextNonA = i + 1;

                // 'A'가 아닌 다음 문자를 찾음
                while (nextNonA < nameAry.length && nameAry[nextNonA] == 'A') {
                    nextNonA++;
                }

                // 앞으로 이동 후 뒤로 돌아가는 최적 경로 계산
                int backAndForthMove = i + nameAry.length - nextNonA + Math.min(i, nameAry.length - nextNonA);

                // 최소 이동 경로 갱신
                minMove = Math.min(minMove, backAndForthMove);
            }

            totalMove += minMove;
            return totalMove;
        }

        // 해당 문자를 'A'로 변경하기 위한 최소 조작 횟수 계산
        private static int getMinMove(char targetChar) {
            return Math.min(targetChar - 'A', 'Z' - targetChar + 1);
        }
    }

}
