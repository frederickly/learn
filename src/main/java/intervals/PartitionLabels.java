package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 1045. Partition Labels
 * https://www.lintcode.com/problem/partition-labels/description
 *
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * Example
 *
 * Example 1:
 * 	Input:  S = "ababcbacadefegdehijhklij"
 * 	Output:  [9,7,8]
 *
 * 	Explanation:
 * 	The partitions are "ababcbaca", "defegde", "hijhklij".
 * 	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Example 2:
 * 	Input: S = "abcab"
 * 	Output:  [5]
 *
 * 	Explanation:
 * 	We can not split it.
 *
 *
 * Notice
 *
 * 1.S will have length in range [1, 500].
 * 2.S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {
    /**
     * @param S: a string
     * @return: a list of integers representing the size of these parts
     */
    public List<Integer> partitionLabels(String S) {
        int[][] intervals= new int[26][2];
        for(int i=0;i<26;i++)
            intervals[i][0]=-1;
        int len = S.length();
        char c=' ';
        int index=0;
        for(int i=0;i<len;i++) {
            c= S.charAt(i);
            index= c - 'a';
            if(intervals[index][0]==-1) {
                intervals[index][0]= i;
                intervals[index][1]= i;
            }else{
                intervals[index][1]= i;
            }
        }

        List<Interval> ranges = new ArrayList<>();
        for(int i=0;i<26;i++)
            if(intervals[i][0]!=-1)
                ranges.add(new Interval(intervals[i][0],intervals[i][1]));
        Collections.sort(ranges, new RangeComparator());
        //System.out.println("before="+ ranges);
        for(int i=ranges.size()-1;i>0;i--){
            while(i< ranges.size() && ranges.get(i).start<ranges.get(i-1).end){
                ranges.get(i-1).end= Math.max(ranges.get(i).end, ranges.get(i-1).end);
                ranges.remove(i);
            }
        }
        //System.out.println("after="+ranges);
        List<Integer> res= new ArrayList<>();
        for(Interval range: ranges) {
            res.add(range.end- range.start+1);
        }
        return res;

    }

    class RangeComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b)  {
            int res= a.start - b.start;
            if(res!=0 ) return res;
            return a.end-b.end;
        }
    }

    class Interval{
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start= start;
            this.end= end;
        }

        public String toString(){
            return start+"-"+ end;
        }
    }
}
