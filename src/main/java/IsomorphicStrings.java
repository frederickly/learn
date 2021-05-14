import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    public static void main(String[] args) {
        //create int arrays
        int[] intArray1 = new int[]{78,27, 1023};
        int[] intArray2 = new int[]{27,78,1023};

    /*
      To compare two int arrays use,
      static boolean equals(int array1[], int array2[]) method of Arrays class.

      It returns true if both arrays are equal. Arrays are considered as equal
      if they contain same elements in same order.
    */

        boolean blnResult = Arrays.equals(intArray1,intArray2);
        System.out.println("Are two int arrays equal ? : " + blnResult);

    /*
      Please note that two int array references pointing to null are
      considered as equal.
    */

    }
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // write your code here
        Map<Character, Integer> mapS= new HashMap<>();
        Map<Character, Integer> mapT= new HashMap<>();
        char key;
        for(int i=0;i< s.length(); i++){
            key = s.charAt(i);
            if(!mapS.containsKey(key)){
                mapS.putIfAbsent(key, 1);
            }
            mapS.put(key, mapS.get(key)+1);

            key = t.charAt(i);
            if(!mapT.containsKey(key)){
                mapT.putIfAbsent(key, 1);
            }
            mapT.put(key, mapT.get(key)+1);
        }

        return mapS.values().containsAll(mapT.values())
                && mapT.values().containsAll(mapS.values());

    }
}
