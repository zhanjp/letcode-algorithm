package top.hrzgj.dp.n392;

import java.util.Objects;

/**
 * description
 *
 * @author zhan jp
 * @date 2021-04-24 11:21
 */
public class StringSubSequence {

    /**
     * 双指针
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (Objects.isNull(s) || Objects.isNull(t) || s.length() > t.length()) {
            return false;
        }

        int j = 0;
        int plen = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < t.length()) {
                if (s.charAt(i) == t.charAt(j++)) {
                    plen ++;
                    break;
                }
            }
        }
        return s.length() == plen;
    }

    /**
     * 普通动态规划
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        boolean[][] arr = new boolean[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= m; i++) {
            arr[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j-1]) {
                    arr[i][j] = true;
                } else if (s.charAt(i) == t.charAt(j) && arr[i - 1][j - 1]) {
                    arr[i][j] = true;
                }
            }
        }
        return arr[n][m];
    }


    /**
     * 动态规划官方解答，通过一轮预处理，适合处理大量的子字符串的判断
     */
    public boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();

        // 建立动态规划表格，行是字符串t的字符，列是26为英文字符
        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        //
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    //
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        final StringSubSequence f = new StringSubSequence();
        System.out.println(f.isSubsequence("abc", "ahbgdc"));
        System.out.println(f.isSubsequence("axc", "ahbgdc"));

        System.out.println(f.isSubsequence("aaa", "abbbaaaaa"));
        System.out.println(f.isSubsequence("", ""));
        System.out.println(f.isSubsequence("aaa", ""));
        System.out.println(f.isSubsequence("aaaaaa", "bbaaaa"));

        System.out.println(f.isSubsequence2("abc", "abwwasasabc"));
    }
}
