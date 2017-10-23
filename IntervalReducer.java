import java.util.*;
 
// Class implements an interval that has an upper and lower bound and can be sorted by it's lower bound
class Interval
{
    public int lower;
    public int upper;
 
    // Constructor
    public Interval(int b1, int b2){
      this.lower = Math.min(b1, b2);
      this.upper= Math.max(b1, b2);
    }

    public int getLower() {
      return this.lower;
    }

    public int getUpper() {
      return this.upper;
    }

    public String toString()
    {
        return "[" +this.lower + "," + this.upper +"]";
    }
}
 
 // Class handles sorting for Intervals
class SortByLower implements Comparator<Interval> {

    public int compare(Interval a, Interval b)
    {
        return a.lower - b.lower;
    }
}

// Class contains the function to reduce intervals
class Main
{
    public static void main (String[] args)
    {
        Interval [] arr = {
          new Interval(94200, 94699),
          new Interval(94133, 94133),
          new Interval(94200, 94299)
        };
        Arrays.sort(arr, new SortByLower());
        ArrayList<Interval> stack = new ArrayList();
        stack.add(arr[0]);

        for(int i = 1; i < arr.length; i++) {
          Interval top = stack.get(stack.size()-1);
          if(top.upper < arr[i].lower + 1) {
            stack.add(arr[i]);
          } else if (top.upper < arr[i].upper) {
            Interval newTop = new Interval(top.lower, arr[i].upper);
            stack.remove(stack.size()-1);
            stack.add(newTop);
          }
        }
      System.out.println(stack);
    }
}