/*
170. Two Sum III - Data structure design

Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/

public class TwoSum {
    
    Map<Integer, Integer> map;
    
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, 2);
        } else {
            map.put(number, 1);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Iterator<Integer> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            int num1 = itr.next();
            int num2 = value - num1;
            if (map.containsKey(num2)) {
                if (num1 != num2 || map.get(num2) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    /********************************************************************************************************/
    
    Set<Integer> sum;
    Set<Integer> num;
    
    /** Initialize your data structure here. */
    public TwoSum() {
        sum = new HashSet<Integer>();
        num = new HashSet<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (num.contains(number)) {
            sum.add(number * 2);
        } else {
            Iterator<Integer> itr = num.iterator();
            while (itr.hasNext()) {
                sum.add(itr.next() + number);
            }
            num.add(number);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sum.contains(value);
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
