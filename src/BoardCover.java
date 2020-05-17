import java.util.Scanner;

public class BoardCover {

    /**
     * board를 덮을 block의 경우를 4가지로 분류
     *   1   2   3   4
     *   #   ##  ##   #
     *   ##   #  #   ##
     */
    private static int[][][] coverType = {
        {{0, 0}, {1, 0}, {1, 1}},
        {{0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, -1}, {1, 0}},
    };

    // board 정의
    private static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("높이(H)를 입력해주세요.");
        int H = sc.nextInt();
        System.out.println("너비(W)를 입력해주세요.");
        int W = sc.nextInt();
        String[] blockLine = new String[H];

        for (int i = 0; i < H; i++) {
            System.out.println(String.format("너비에 맞게 검은색 블록은 '#', 흰색 블록은 '.'로 입력해주세요. (%d번째 라인)", i+1));
            String input = "";
            for (int j = 0; j < W; j++) {
                System.out.println(String.format("%d번째 기호를 입력하세요.", j+1));
                char ch = sc.next().charAt(0);
                input += ch;
            }
            blockLine[i] = input;
        }
        // 입력받은 높이, 너비수에 맞게 board 초기화
        board = new int[blockLine.length][blockLine[0].length()];
        // board 생성
        createBoard(blockLine);
        // 생성된 board에 들어갈 블록의 경우의 수 출력
        System.out.println(countCovered(board));

    }

    private static void createBoard(String[] blockLine) {
        for (int i = 0; i < blockLine.length; i++) {
            for (int j = 0; j < blockLine[i].length(); j++) {
                char sign = blockLine[i].charAt(j);
                if (sign == '#') {
                    board[i][j] = 1;
                }
                else if (sign == '.') {
                    board[i][j] = 0;
                } else {
                    throw new IllegalArgumentException("'#' 또는 '.' 기호만을 입력해주세요.");
                }
            }
        }
    }

    private static int countCovered(int[][] board) {
        int bx = -1;
        int by = -1;
        // base case
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    by = i;
                    bx = j;
                    break;
                }
            }
            if (by != -1) break;
        }

        if (by == -1) {
            return 1;
        }

        int result = 0;
        for (int coverTypeIndex = 0; coverTypeIndex < coverType.length; coverTypeIndex++) {
            if (setBlock(bx, by, coverTypeIndex, 1)) {
                result += countCovered(board);
            }
            setBlock(bx, by, coverTypeIndex, -1);
        }

        return result;
    }

    private static boolean setBlock(int bx, int by, int coverTypeIndex, int delta) {
        boolean result = true;
        int[][] selectedCoverType = coverType[coverTypeIndex];
        for (int i = 0; i < selectedCoverType.length; i ++) {
            int y = by + selectedCoverType[i][0];
            int x = bx + selectedCoverType[i][1];

            if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
                return false;
            } else {
                board[y][x] += delta;
                if (board[y][x] > 1) {
                    result = false;
                }
            }
        }
        return result;
    }
}
