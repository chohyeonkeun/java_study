import java.util.Scanner;

/**
 * 프로그래밍 대회에서 배우는 알고리즘 문제해결전략(저자: 구종만)
 * 7.2 쿼드 트리 뒤집기 (문제ID: QUADTREE, 난이도: 하)
 */

public class QuadTree {

    private static int pointer;
    private static String quadTree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ret = "";
        System.out.print("테스트 케이스 (T<50)");

        int num = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < num; i++) {
            System.out.print("값:");
            pointer = 0;
            quadTree = sc.nextLine();
            if(quadTree.length() > 1000) continue;
            ret += reverse() + "\n";
        }

        System.out.print(ret);

        sc.close();
    }

    public static String reverse() {
        // 처음 글자가 x가 아니라면
        if (quadTree.charAt(pointer) != 'x') {
            pointer++;
            return quadTree.charAt(pointer - 1) + ""; //String 형으로 리턴하기 위해 빈 문자열을 더하기.
        } else {
            // 처음 글자가 x 일 경우
            pointer++;
            //왼쪽 위.
            String ul = reverse();
            //오른쪽 위.
            String ur = reverse();
            //왼쪽 아래.
            String ll = reverse();
            //오른쪽 아래.
            String lr = reverse();

            //순서대로 뒤집기 -> 왼쪽 아래, 오른쪽 아래, 왼쪽 위, 오른쪽 위
            return 'x' + ll + lr + ul + ur;
        }
    }

}
/*
출력예:
테스트 케이스 (T<50)3
값:w
값:xbwwb
값:xbwxwbbwb
w
xwbbw
xxbwwbbbw
*/
