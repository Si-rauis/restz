package AlgorithmStudy;

public class 카운트다운 {
    //④ 메모하기
    public static int[][] memo;

    public int[] solution(int target) {
        //① DP로 풀 수 있는 문제인지 확인
        //1) Overlapping Subproblems(겹치는 부분 문제)
        //2) Optimal Substructure(최적 부분 구조)
        //점수가 target일 때 다트는 최대한 적게, "싱글"과 "불"은 최대한 많이 맞추는 것이 목표
        //목표 점수를 만들기 위해, 먼저 작은 점수들을 만드는 방법을 최적화할 수 있습니다.
        //예를 들어, 100점을 만들기 위해 50점, 30점, 20점을 만드는 방법을 최적화하는 과정을 거쳐 전체 목표 점수를 만드는 방법을 최적화할 수 있습니다.

        //② 문제의 변수 파악
        // 목표 점수 (target) / 던진 다트 수 (dart_count) / 싱글 또는 불을 맞춘 횟수 (single_or_bull_count)

        //③ 변수 간 관계식 만들기
        //memo[i][0] = min(memo[i - j][0] + 1), 여기서 j는 가능한 던진 점수입니다. 이 경우 최소 던진 횟수를 업데이트합니다.
        //memo[i][1] = max(memo[i - j][1] + f), 여기서 f는 싱글/불의 횟수입니다. 이 경우 싱글/불의 최대 횟수를 업데이트합니다.

        // memo[i][0] == i점을 획득하기 위해 최소 던진 횟수
        // memo[i][1] == i점을 획득하기 위해 최소 던지는 중 던진 싱글/불 횟수

        memo = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            // 초기값
            memo[i][0] = Integer.MAX_VALUE;
            // 불 던지기
            shoot(i, 50, 1);
            // 1 ~ 20점에 대해서
            for (int j = 1; j <= 20; j++) {
                // 싱글 던지기
                shoot(i, j, 1);
                // 더블 던지기
                shoot(i, 2 * j, 0);
                // 트리플 던지기
                shoot(i, 3 * j, 0);
            }
        }
        return new int[]{memo[target][0], memo[target][1]};

    }
    // 던지기 로직
    // f == 0 -> 더블/트리플
    // f == 1 -> 싱글/불

    public void shoot(int i, int j, int f) {
        // 남은 점수가 던진 점수보다 작으면 return
        if (i < j) return;
        // 기존 던진 횟수가 더 크면
        if (memo[i][0] > memo[i - j][0] + 1) {
            // 던진 횟수 갱신
            memo[i][0] = memo[i - j][0] + 1;
            // 싱글/불 횟수 갱신
            memo[i][1] = memo[i - j][1] + f;
        }
        // 같다면
        else if (memo[i][0] == memo[i - j][0] + 1) {
            // 싱글/불 횟수 최댓값 갱신
            memo[i][1] = Math.max(memo[i][1], memo[i - j][1] + f);
        }
    }
}
