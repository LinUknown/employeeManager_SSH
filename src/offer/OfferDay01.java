package offer;

import org.junit.Test;

public class OfferDay01 {
    @Test
    public void test() {

        StringBuffer sb = new StringBuffer("1 2 3");
        System.out.println(replaceSpace(sb));
    }
    public String replaceSpace(StringBuffer str) {
        int cnt = 0;
        for (int i = 0, j = str.length(); i < j; i++) {
            if(str.charAt(i) == ' ')
                 cnt++;
        }
        int j = str.length() - cnt + cnt * 3 - 1;
        int i = str.length() - 1;
        str.setLength(j+1);
        while (i >= 0) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(j--,'0');
                str.setCharAt(j--,'2');
                str.setCharAt(j--,'%');
            }else str.setCharAt(j--,str.charAt(i));
            i--;
        }
        return str.toString();
    }
    public boolean Find(int target, int[][] array) {
        int n = array.length;
        int m = array[0].length;

        int i = n-1,j = 0;
        while (i>=0 && j < m){
            if (array[i][j] == target) {
                return true;
            }
            if (array[i][j] > target) {
                i--;
            }else j++;
        }
        return false;
    }
}
