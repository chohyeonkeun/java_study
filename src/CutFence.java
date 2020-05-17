import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 프로그래밍 대회에서 배우는 알고리즘 문제해결전략(저자: 구종만)
 * 7.4 울타리 잘라내기 (문제ID: FENCE, 난이도: 중)
 */

public class CutFence {
    private static int[] fence;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ret = "";
        System.out.println("테스트 케이스 (T<=50)");
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            System.out.println("판자 갯수 (1<=N<=20000");
            int num = sc.nextInt();
            fence = new int[num];
            System.out.println("판자 높이를 입력해주세요. ex) 7 1 5 9 6 7 3");
            for (int j = 0; j < num; j++) {
                fence[j] = sc.nextInt();
            }
            int left = 0;
            int right = fence.length - 1;
            ret += solve(left, right) + "\n";
        }
        System.out.println(ret);
    }

    private static int solve(int left, int right) {
        // base case: 판자가 하나밖에 없는 경우
        if (left == right) return fence[left];

        int mid = (left + right) / 2;
        // mid를 기준으로 solve(left, right)가 base case가 되어 값을 return 할 때까지 분할한다.
        int ret = max(solve(left, mid), solve(mid+1, right));
        int low = mid;
        int high = mid + 1;
        // 맞닿은 두개 판자중 작은 판자의 높이로 x2한 값과 기존 ret을 비교해 최대너비로 설정한다.
        int height = min(fence[low], fence[high]);
        ret = max(ret, height * 2);

        // mid를 기준으로 low(=mid), high(mid+1)가 정해지는데, low -> left &&  high -> right가 될때까지 가능한 판자너비 중 최대너비를 구한다.
        while(left < low || high < right) {
            // 맞닿은 판자를 기준으로 양옆 1칸 위치에 있는 판자들을 비교하여 높은 판자가 있는 방향으로 너비를 구해 ret과 비교하여 최대너비로 설정한다.
            if (high < right && (low == left || fence[low-1] < fence[high+1])) {
                high++;
                height = min(height, fence[high]);
            } else {
                low--;
                height = min(height, fence[low]);
            }
            ret = max(ret, height * (high - low + 1));
        }
        return ret;
    }
}
