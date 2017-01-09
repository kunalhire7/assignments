import java.util.ArrayList;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        Solution codility = new Solution();
        int[] A = {0, 3, 3, 7, 5, 3, 11, 1};
        System.out.println(codility.solution(A));
    }

    public int solution(int[] A) {
        int distance = Integer.MIN_VALUE;
        int d = 0;
        List<Integer> elements = new ArrayList<>(A.length);
        for(int i : A) {
            elements.add(i);
        }
        int p, q;
        for(int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                p = A[i];
                q = A[j];
                if(p != q) {
                    if(p < q) {
                        if(!check(elements, p, q)) {
                            d = Math.abs(i - j);
                        }
                    } else {
                        if(!check(elements, q, p)){
                            d = Math.abs(i - j);
                        }
                    }
                    if(d > distance) {
                        distance = d;
                    }
                }
            }
        }
        return distance;
    }

    private boolean check(List<Integer> list, int p, int q) {
        for(int i = p + 1; i < q; i++) {
            if(list.contains(i)) {
                return true;
            }
        }
        return false;
    }

}