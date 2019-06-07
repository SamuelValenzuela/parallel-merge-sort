/**
 * Class {@code ParallelMergeSort} provides the functionality of sorting an array with multiple
 * threads at once
 */
public class ParallelMergeSort implements Runnable {

  private static int threadAmount = 1;

  public static int getThreadAmount() {
    return threadAmount;
  }

  public static void setThreadAmount(int threadAmount) {
    ParallelMergeSort.threadAmount = threadAmount;
  }


  private static int threadsCreated = 1;
  private static Object threadCreatorLock = new Object();

  /**
   * Creates {@code threadAmount} threads and uses the methods implemented in
   * {@code SingleMergeSort} to sort an array
   * 
   * @param arr array to be sorted
   */
  public static void mergeSort(int[] arr) {
    threadsCreated = 1; // main thread is thread #1
    mergeSort(arr, 0, arr.length - 1);
  }

  /**
   * Recursively creates up to {@code threadAmount} threads and uses the methods implemented in
   * {@code SingleMergeSort} to sort an array
   * 
   * @param arr   array containing the subarray to be sorted
   * @param left  the smallest index of the subarray to sort
   * @param right the highest index of the subarray to sort
   */
  private static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      Thread createdThread = null;
      synchronized (threadCreatorLock) { // make sure only one thread can be created at once
        if (threadsCreated < threadAmount) { // only create new thread if allowed
          createdThread = new Thread(new ParallelMergeSort(arr, left, mid));
          threadsCreated++;
          createdThread.start(); // new thread sorts first half in ParallelMergeSort
        }
      }
      if (createdThread != null) {
        mergeSort(arr, mid + 1, right); // this thread sorts second half in ParallelMergeSort
        try {
          createdThread.join(); // wait for other thread to finish sorting
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else { // no new threads can be created -> both halves are sorted in NormalMergeSort
        SingleMergeSort.mergeSort(arr, left, mid);
        SingleMergeSort.mergeSort(arr, mid + 1, right);
      }
      SingleMergeSort.merge(arr, left, mid, right); // merge both sorted halves
    }
  }


  private int[] arr;
  private int left;
  private int right;

  /**
   * Creates a runnable object of ParallelMergeSort
   * 
   * @param arr   array containing the subarray to be sorted
   * @param left  the smallest index of the subarray to sort
   * @param right the highest index of the subarray to sort
   */
  public ParallelMergeSort(int[] arr, int left, int right) {
    this.arr = arr;
    this.left = left;
    this.right = right;
  }

  @Override
  public void run() {
    mergeSort(arr, left, right); // sort part of array in ParallelMergeSort
  }
}
