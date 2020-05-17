import java.util.Scanner;

/**
 * 프로그래밍 대회에서 배우는 알고리즘 문제해결전략(저자: 구종만)
 * 6.3 소풍 (문제ID: PICNIC, 난이도: 하)
 */

public class Picnic {
    // 친구 관계를 담을 변수
    private static boolean[][] friendsInfo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("몇번 반복하시겠습니까?");
        int loop = sc.nextInt();

        // 짝이 맺어졌는지 확인할 변수
        boolean[] paired;
        int[] result = new int[loop];

        while(loop --> 0) {
            // 학생수
            System.out.println("학생수를 입력해주세요.");
            int cntStudents = sc.nextInt();
            // 친구 쌍의 수
            System.out.println("친구 쌍의 수를 입력해주세요.");
            int cntFriends = sc.nextInt();


            // 학생 수로 초기화
            paired = new boolean[cntStudents];
            friendsInfo = new boolean[cntStudents][cntStudents];

            // 친구 관계 여부 삽입.
            for (int i = 0; i < cntFriends; i++) {
                System.out.println("친구 관계 설정해주세요(ex. 1 3)");
                int first = sc.nextInt();
                int second = sc.nextInt();
                friendsInfo[first][second] = true;
                friendsInfo[second][first] = true;
            }

            result[loop] = countParings(paired);
        }

        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(result[i]);
        }
    }

    private static int countParings(boolean[] paired) {
        // 학생 수
        int cntStudents = paired.length;
        // 짝이 지어지지 않은 학생 중 가장 빠른 번호의 학생을 담을 변수.
        int remainFirst = -1;

        // 짝이 맞춰지지 않은 학생중 출석번호가 가장 빠른 학생을 찾는다.
        for (int i = 0; i < cntStudents; i++) {
            if (!paired[i]) {
                remainFirst = i;
                break;
            }
        }

        // 모든 학생이 짝이 맞춰졌다면 짝을 맞추는 하나의 경우를 만족했으므로 1을 반환한다.
        if (remainFirst == -1) {
            return 1;
        }

        // 경우의 수를 반환할 변수
        int result = 0;

        for (int friend = remainFirst + 1; friend < cntStudents; friend++) {
            if (!paired[friend] && friendsInfo[remainFirst][friend]) {
                paired[remainFirst] = paired[friend] = true;
                result += countParings(paired);
                paired[remainFirst] = paired[friend] = false;
            }
        }

        return result;
    }
}
