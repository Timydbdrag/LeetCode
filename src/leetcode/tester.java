package leetcode;

import java.util.*;

public class tester {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int[][] matrix2 = new int[][]{{1,2},{3,4}};
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
       // permute(nums);
/*       rotate(matrix);*/
/*        String[] str = new String[]{"ak","ka"};
        groupAnagrams(str);*/
       // System.out.println(pow(-1,-2147483648));
       // spiralOrder(matrix);
        //generateMatrix(3);
        //setZeroes(matrix);
        //System.out.println(searchMatrix(matrix2, 1));
       // System.out.println(removeDuplicates(nums));

        System.out.println(search(nums, 0));
    }

    public static boolean search(int[] nums, int target){
        if (nums == null || nums.length == 0) return false;

        int start = 0;
        int end = nums.length-1;
        int mid;
        while (start <= end) {
            mid =start + (end - start) / 2;
            if(nums[mid] == target) return true;
            else if(nums[mid]>nums[start]){
                if (target>=nums[start] && target<=nums[mid]) end = mid-1;
                 else start = mid+1;
            } else if (nums[mid]<nums[start]){
                if(target>=nums[mid]&&target<=nums[end]) start=mid+1;
                else end=mid-1;
            } else start++;
        }
        return false;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int next = 1;
        int current = 0;

        while (next < nums.length){
            if (current == 0){
                current++;
                next++;
            }
            if (nums[current] == nums[next] && nums[current-1] == nums[next]){
                next++;
            } else if ((nums[current] != nums[next] && nums[current-1] != nums[next])||
                    (nums[current] == nums[next] && nums[current-1] != nums[next])){
                current++;
                nums[current] = nums[next];
                next++;
            }
        }
        return current+1;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        //task 74
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
        //Memory Usage: 39.4 MB, less than 22.73% of Java online submissions for Search a 2D Matrix.
        if (matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0] ||
                target > matrix[matrix.length-1][matrix[matrix.length-1].length-1])return false;
        int start = 0;
        int end = matrix.length;
        int middle = 0;
        while (true){
            if(matrix.length < 2) break;
            if(matrix[1][0] > target) break;
            middle = (int) (start + Math.floor((end - start)/2));
            System.out.println(middle);
            if(middle == 0)break;
            if(middle == matrix.length-1)break;
            if(target > matrix[middle-1][matrix[middle-1].length-1] && target < matrix[middle+1][0])break;
            if(matrix[middle][0] < target){ //если искомый элемент больше значения из матрицы
                start = middle+1;
            } else end = middle -1;
        }

        for (int i = 0; i < matrix[middle].length ; i++) {
            if(target == matrix[middle][i]) return true;
        }
        return false;
    }

    public static void setZeroes(int[][] matrix) {
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> col = new HashSet<>();

        for (int i = 0; i <  matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]==0){
                    col.add(i);
                    row.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(col.contains(i) || row.contains(j)) matrix[i][j]=0;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static int[][] generateMatrix(int n) {
        //task 59
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
        //Memory Usage: 37.4 MB, less than 8.33% of Java online submissions for Spiral Matrix II.
        if(n==0)return new int[n][n];
        int[][] matrix = new int[n][n];
        int minW = 0;
        int maxW = matrix[0].length;
        int minD = 0;
        int maxD = matrix.length;
        int direction = 0; //0 -r 1-d 2- go left 3- go
        int i = 0, j = 0;
        int val = 1;
        while (true){
            if(minD>=maxD || minW>=maxW) break;
            if(direction==0){
                matrix[i][j] = val;
                j++;
                if(j>=maxW){
                    minD++;//убираем строку старта
                    direction = 1;//идем вниз
                    i = minD;
                    j = maxW-1;
                }
            } else if(direction == 1) {
                matrix[i][j] = val;
                i++;
                if (i >= maxD) {
                    maxW--;//уменьшаем длинну проходов
                    direction = 2;//go left
                    i = maxD - 1;//последняя строка
                    j = maxW - 1;//паослений символ
                }
            } else if(direction == 2) {
                matrix[i][j] = val;
                j--;
                if(j < minW){
                    maxD--;
                    direction=3;///go up
                    i = maxD-1;
                    j = minW;
                }
            } else {
                matrix[i][j] = val;
                i--;
                if(i < minD){
                    minW++;
                    direction=0;//go right
                    i = minD;
                    j = minW;
                }
            }
            val++;
        }

        System.out.println(Arrays.toString(matrix[1]));
        return matrix;
    }

    public static boolean canJump(int[] nums) {
        if(nums.length==1)return true;
        int s =0;
        for(int i=0;i<nums.length;i++){
            if(s<i) return false;
            s = Math.max(i+nums[i],s);
            if(s>=nums.length)return true;
        }
        return true;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new ArrayList<>();
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
        //Memory Usage: 37.1 MB, less than 5.21% of Java online submissions for Spiral Matrix.
        int minW = 0;
        int maxW = matrix[0].length;
        int minD = 0;
        int maxD = matrix.length;
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        boolean leftOrRight = false; //true go left
        boolean upOrDown = false; //true go Up
        int naprav = 0; //0 -r 1-d 2- go left 3- go

        while (true){
            if(minD>=maxD || minW>=maxW) break;
            if(naprav == 0){
                res.add(matrix[i][j]);
                j++;
                if(j>=maxW){
                    minD++;//убираем строку старта
                    naprav = 1;//идем вниз
                    i = minD;
                    j = maxW-1;
                }
            } else if(naprav == 1) {
                System.out.println("minD >> " + minD + " <> " + maxD);
                System.out.println("minW >> " + minW + " <> " + maxW);
                System.out.println(i + " " + j);
                res.add(matrix[i][j]);
                i++;
                if(i>=maxD){
                    maxW--;//уменьшаем длинну проходов
                    naprav=2;//go left
                    i=maxD-1;//последняя строка
                    j=maxW-1;//паослений символ
                }
            } else if(naprav == 2){
                res.add(matrix[i][j]);
                j--;
                if(j < minW){
                    maxD--;
                    naprav=3;///go up
                    i = maxD-1;
                    j = minW;
                }
            } else {
                res.add(matrix[i][j]);
                i--;
                if(i < minD){
                    minW++;
                    naprav=0;//go right
                    i = minD;
                    j = minW;
                }
            }
            System.out.println(res);
        }

        return res;
    }

    public int maxSubArray(int[] nums) {
        //task 53
        int tmp = nums[nums.length-1];
        int res = tmp;
        for(int i=nums.length-2; i>=0; i--) {
            tmp = Math.max(nums[i], nums[i] + tmp);
            res = Math.max(res, tmp);
        }
        return res;
    }

    public static double pow(double x, int n) {
        //task 50 //100%
        if(n == 0 || x == 1.0)return 1.0;
        if(x == -1.0)return (n%2 == 0) ? 1.0 : -1.0;
        if(n == 1)return x;
        if(n == Integer.MIN_VALUE) return 0.0;
        if(n < 0){
            x = 1/x;
            n = -1 * n;
        }

        double res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }

     //   System.out.println(res);
        return res;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String item: strs){
            char[] c1 = item.toCharArray();
            Arrays.sort(c1);
            String s1 = new String(c1);
            if(map.containsKey(s1)){
                List<String> temp = map.get(s1);
                temp.add(item);
                map.put(s1,temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(item);
                map.put(s1, temp);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> item: map.values()) {
            res.add(item);
        }


/*

        List<String> temp = new ArrayList<>();
        temp.add(strs[0]);
        res.add(new ArrayList<>(temp));
        temp.clear();
        for (int i = 1; i < strs.length ; i++) { // исходный
            for (int j = 0; j < res.size(); j++) { //по всем подмассивам
                temp = res.get(j); //подмассив
                System.out.println(">>>>>>>>>>>  " + j);
                boolean anagram = false;
                for (int k = 0; k < temp.size(); k++) { //по элементам
                    anagram = isAnagram(strs[i], temp.get(k));
                    if(anagram) break;
                }
                if(anagram) {
                    System.out.println("anagram <<< " + strs[i]);
                    temp.add(strs[i]);
                    System.out.println(temp);
                    res.set(j, new ArrayList<>(temp));
                    break;
                } else if(j == res.size()-1){

                    List<String> notA = new ArrayList<>();
                    notA.add(strs[i]);
                    res.add(new ArrayList<>(notA));
                    break;
                }
            }
        }*/
     //   System.out.println(res);
        return res;
    }

    public static boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int m = 0; m < c1.length; m++) {
            if(c1[m] != c2[m]) return false;
        }
        return true;
    }

    public static void rotate(int[][] matrix) {
        //task 48
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
        //Memory Usage: 39.7 MB, less than 5.77% of Java online submissions for Rotate Image.
        int[][] test = matrix.clone();
        for (int i = 0; i < matrix.length; i++) {

            int[] temp = new int[test[i].length];

            for (int j = 0; j < test.length; j++) {
                temp[j] = test[test.length -1 - j][i];
            }
            matrix[i] = temp;
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static List<List<Integer>> permute(int[] nums) {
        //task 46

        LinkedList<List<Integer>> result = new LinkedList<>();

        result.add(new ArrayList<>());

        for (int n: nums) {
            int len = result.size();
            for (int size = len; size > 0; size--) {
                List<Integer> bucket = result.poll();

                System.out.println(bucket);
                for (int i = 0; i <= bucket.size(); i++) {
                    List<Integer> temp = new ArrayList<>(bucket);
                    System.out.println(temp);
                    temp.add(i, n);
                    result.add(temp);

                }
            }
        }

        return result;
 ///////////////////////////////////
       // return new ArrayList(new HashSet<List<Integer>>(res));




/*        for (int i = 1; i < nums.length; i++) {
            System.out.println(nums[i]);
            nums[i-1] ^= nums[i];
            nums[i-1] ^= (nums[i] ^= nums[i-1]);

        }*/

     //   return null;
    }

}
