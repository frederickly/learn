package arraysmanipulation;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/float-combination-sum/description
 * 1800. Float Combination Sum
 *
 * Given an array of A, a non-negative integer target. Each decimal in A needs to be operated by ceil or floor, and finally an integer array is obtained, requiring the sum of all integers that are in the array to be equal to target, and requiring adjustments sum of the decimal array is minimum.
 * Such as ceil(1.2),adjustment is 0.8,floor(1.2),adjustment is 0.2. return the integer array.
 * Example
 *
 * Example 1:
 *
 * Input：A=[1.2,1.3,2.3,4.2],target=9
 * Output：[1,1,3,4]
 * Explanation：1.2->1,1.3->1,2.3->3,4.2->4.
 *
 * Example 2:
 *
 * Input：A=[2.5,2.5],target=5
 * Output：[2,3]
 * Explanation：2.5->2,2.5->3.
 *
 * Notice
 *
 * 1.1<=A.length<=300
 *
 * 2.0<=target<=15000
 *
 * 3.0.0<=A[i]<=100.0
 *
 * 4.Datas guarantees the existence of answers.
 *
 * 把数组按照小数部分排序，先求出处所有数字floor后的和sum，然后我们需要往上ceil的值就是diff = target-sum。
 * 从排序后的数组n-1开始（也就是小数部分最大的一个数开始），依次往上ceil，同时diff--，直到diff = 0.
 * 很简单证明这种解法是正确的。因为所有数字不是floor down就是ceil up，
 * diff是所有数字floor down后的和target的差值，
 * 然后从小数部分最大的数字开始ceil up直到diff = 0，
 * 这样变化的值必然是最小的。因为取其它任何一个数字去ceil up，最后变化的总值一定不会小于使用小数部分最大的那个数。
 */
public class FloatCombinationSum {
    public int[] getArray(double[] A, int target) {
        int sum=0;
        Element[] diffs= new Element[A.length];
        for(int i=0;i<A.length;i++) {
            diffs[i]= new Element(A[i]- Math.floor(A[i]), i);
            sum+= (int)Math.floor(A[i]);
        }
        Arrays.sort(diffs, (v1, v2)->
                {
                    if(v1.diff - v2.diff< 0) return -1;
                    else if(v1.diff- v2.diff> 0 ) return 1;
                    else return 0;
                }
        );

        int diff= target - sum;
        int[] res=new int[A.length];
        int turn = A.length - 1- diff;

        for(int i=0;i<A.length;i++) {
            if(i<= turn){
                res[diffs[i].index]= (int)Math.floor(A[diffs[i].index]);
            }else{
                res[diffs[i].index]= (int)Math.ceil(A[diffs[i].index]);
            }
        }
        return res;
    }

    class Element {
        public double diff;
        public int index;

        public Element(double diff, int index){
            this.diff= diff;
            this.index= index;
        }
    }

}
