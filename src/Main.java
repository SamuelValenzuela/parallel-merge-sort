/**
 * Class {@code Main} includes the main function to run the program which showcases the
 * ParallelMergeSort.
 */
public class Main {

  public static void main(String[] args) {
    for (int i = 10; i <= 20; i++) {
      sortRandomArray((int) Math.pow(2, i), (int) Math.pow(2, i) * 10, 1);
      sortRandomArray((int) Math.pow(2, i), (int) Math.pow(2, i) * 10, 8);
    }
  }

  /**
   * Creates a random unsorted array and sorts it, documenting the time needed for it to be sorted.
   * 
   * @param arrSize      the size of the array
   * @param maxNumber    all numbers in the array will be in the interval <br>
   *                     [0,maxNumber) if maxNumber > 0 or <br>
   *                     [0,maxNumber] if maxNumber = 0 or <br>
   *                     (maxNumber,0] if maxNumber < 0
   * @param threadAmount amount of threads to create
   */
  public static void sortRandomArray(int arrSize, int maxNumber, int threadAmount) {
    ParallelMergeSort.setThreadAmount(threadAmount);
    int[] arr = new int[arrSize];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * maxNumber);
    }
    long start = System.nanoTime();
    ParallelMergeSort.mergeSort(arr);
    long end = System.nanoTime();
    System.out.println("Sorting an array with " + arrSize + " elements using " + threadAmount
        + " thread" + (threadAmount == 1 ? "" : "s") + " took " + (end - start) + " nanoseconds.");
  }

}
