package 力扣.学习计划.算法.Day15动态规划;

import java.util.Arrays;

public class Solution279 {
    /**
     * 01背包问题:完全背包
     *
     * @param n -->背包  完全平方数-->物品
     * @return 动态规划:dp[i]:和为i的完全平方数的最少数量
     * dp[i]=dp[i-j*j]
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;//为后面dp[i]服务,和为0的时,组合的个数为0
        for (int j = 1; j * j <= n; j++) {//物品
            for (int i = 1; i <= n; i++) {//背包
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);//消减[i-j*j]这种情况
                }
            }
        }
        return dp[n];
    }

    /*
        四平方定理:任何一个正整数都可以表示成不超过四个整数的平方之和。
        推论：满足四数平方和定理的数n（四个整数的情况），必定满足 `n=4ª * (8b+7)`
        因此这里答案只可能是1,2,3,4
        1:如果n本来就是完全平方数,返回1
        4:当n满足n=4ª * (8b+7),返回4
        2:枚举a²+b²=n的情况,出现了就返回2
        3:如果都不满足,则为3
     */
    public int numSquares2(int n) {
        if (isSquare(n)) {//情况1:
            return 1;
        }
        if (isFourSquare(n)) {//情况4
            return 4;
        }
        //情况2
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    //判断是否为平方数
    private boolean isSquare(int n) {
        int t = (int) Math.sqrt(n);
        return t * t == n;
    }

    //判断是否为四平方数`n=4ª * (8b+7)`
    private boolean isFourSquare(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        return n % 8 == 7;
    }
}
