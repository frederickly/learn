package algorithms.arraysmanipulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.lintcode.com/problem/maximum-average-score/description   1483
 *
 * Give the names of a group of students and their grades, find the highest average score(A student may have multiple scores of different course).
 * Example
 *
 * Example 1:
 *
 * Input:names = ["bob","ted","ted"]
 * grades=[88,100,20]
 * Output:88
 *
 * Example 2:
 *
 * Input: names = ["john","xisa","xisa","liajd","alice","john","xisa","mark","ted","xlisa"]
 * grades = [95,83,33,50,78,91,80,67,85,87]
 * Output:93
 */
public class MaximumAverageScore {
    /**
     * @param names: the name
     * @param grades: the grade
     * @return: the maximum average score
     */
    public double maximumAverageScore(List<String> names, int[] grades) {
        int count =0, index;
        int[] sum = new int[grades.length];
        int[] times = new int[grades.length];
        Map<String, Integer> nameToIndex= new HashMap<>();
        for(int i=0; i< grades.length;i++) {
            if(!nameToIndex.containsKey(names.get(i)))
                nameToIndex.put(names.get(i), count++);

            index = nameToIndex.get(names.get(i));
            sum[index]+= grades[i];
            times[index]+=1;
        }

        double max =0, temp;
        for(int i=0;i< count;i++){
            temp = (double)sum[i]/times[i];
            if(temp> max)
                max= temp;
        }
        return max;
    }
}
