package techgig.iotProblem;

public class Codility {
    public static void main(String[] args) {
        Codility codility = new Codility();
        int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
        System.out.println(codility.solution(A));
    }

    public int solution(int[] A) {
        int p = 0;
        int leftSum = 0;
        int rightSum = 0;
        while(p < A.length) {
            leftSum = getSum(A, 0, p);
            rightSum = getSum(A, p + 1, A.length);
            if(leftSum == rightSum){
                break;
            }
            p++;
        }
        if(leftSum != rightSum) {
            p = -1;
        }
        return p;
    }

    private int getSum(int[] a, int start, int end) {
        int sum = 0;
        for(int i = start; i < end; i++) {
            sum += a[i];
        }
        return sum;
    }
}

// perfect solution
//int equi(int arr[], int n) {
//    if (n==0) return -1;
//    long long sum = 0;
//    int i;
//    for(i=0;i<n;i++) sum+=(long long) arr[i];
//
//    long long sum_left = 0;
//    for(i=0;i<n;i++) {
//        long long sum_right = sum - sum_left - (long long) arr[i];
//        if (sum_left == sum_right) return i;
//        sum_left += (long long) arr[i];
//    }
//    return -1;
//}
