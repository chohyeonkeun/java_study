import java.util.Scanner;

import static java.lang.Math.min;

public class AdjustClocks {
    private static int INF = 99999;
    private static int CLOCKS = 16;
    private static int[][] linked = {
        {0, 1, 2, -1, -1},
        {3, 7, 9, -1, -1},
        {4, 10, 14, 15, -1},
        {0, 4, 5, 6, 7},
        {6, 7, 8, 10, 12},
        {0, 2, 14, 15, -1},
        {3, 14, 15, -1, -1},
        {4, 5, 7, 14, 15},
        {1, 2, 3, 4, 5},
        {3, 4, 5, 9, 13}
    };

    private static boolean check(int[] clocks) {
        boolean result = true;
        for (int i = 0; i < CLOCKS; i++) {
            if (clocks[i] != 12) {
                result = false;
            }
        }
        return result;
    }

    private static void pushSwitch(int[] clocks, int nextSwitch) {
        for (int i = 0; i < 5; i++) {
            if (linked[nextSwitch][i] != -1) {
                clocks[linked[nextSwitch][i]] += 3;
                if (clocks[linked[nextSwitch][i]] == 15) {
                    clocks[linked[nextSwitch][i]] = 3;
                }
            }
        }
    }

    private static int solve(int[] clocks, int nextSwitch) {
        if (nextSwitch == 10) {
            if (check(clocks)) {
                return 0;
            }
            return INF;
        }

        int ret = INF;
        for (int cnt = 0; cnt < 4; cnt++) {
            ret = min(ret, cnt + solve(clocks, nextSwitch + 1));
            pushSwitch(clocks, nextSwitch);
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] clocks = new int[16];

        System.out.println("테스트 케이스의 개수를 입력해주세요.");
        int loop = sc.nextInt();
        int[] result = new int[loop];

        while(loop --> 0) {
            System.out.println("16개의 시계 시침 방향을 입력해주세요. ex) 12 6 6 6 ... 12");
            for (int i = 0; i < 16; i++) {
                clocks[i] = sc.nextInt();
            }

            int minCount = solve(clocks, 0);
            if (minCount == INF) {
                result[loop] = -1;
            } else {
                result[loop] = minCount;
            }
        }

        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(result[i]);
        }
    }
}
