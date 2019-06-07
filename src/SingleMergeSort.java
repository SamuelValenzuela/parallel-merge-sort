/**
 * Class {@code SingleMergeSort} provides the functionality of sorting an array
 */
public class SingleMergeSort {

  /**
   * Sorts an array with merge sort
   * 
   * @param arr array to be sorted
   */
  public static void mergeSort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  /**
   * Sorts a given part of an array with merge sort
   * 
   * @param arr   array containing the subarray to be sorted
   * @param left  the smallest index of the subarray to sort
   * @param right the highest index of the subarray to sort
   */
  public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSort(arr, left, mid);
      mergeSort(arr, mid + 1, right);
      merge(arr, left, mid, right);
    }
  }

  /**
   * Merges two subarrays within an array
   * 
   * @param arr   array containing both subarrays
   * @param left  the smallest index of the first subarray
   * @param mid   the highest index of the first subarray ending right before the second one begins
   * @param right the highest index of the second subarray
   */
  public static void merge(int[] arr, int left, int mid, int right) {
    int i = left;
    int l = 0;
    int r = 0;
    int[] leftArr = new int[mid - left + 1];
    int[] rightArr = new int[right - mid];
    System.arraycopy(arr, left, leftArr, 0, leftArr.length);
    System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);
    while(l < leftArr.length && r < rightArr.length) { // merge until done with one of the subarrays
      arr[i++] = leftArr[l] < rightArr[r] ? leftArr[l++] : rightArr[r++];
    }
    while(l < leftArr.length) {
      arr[i++] = leftArr[l++];
    }
    while(r < rightArr.length) {
      arr[i++] = rightArr[r++];
    }
  }
}
