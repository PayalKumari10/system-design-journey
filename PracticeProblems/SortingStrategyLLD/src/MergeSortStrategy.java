public class MergeSortStrategy implements SortStrategy {
    private boolean inPlace;

    public MergeSortStrategy(boolean inPlace) {
        this.inPlace = inPlace;
    }

    @Override
    public void sort(int[] array, boolean ascending) {
        if (inPlace) {
            System.out.println("In-Place Merge Sort (not implemented)");
            // Optional: Add in-place merge sort logic
        } else {
            System.out.println("Standard Merge Sort");
            mergeSort(array, 0, array.length - 1, ascending);
        }
    }

    private void mergeSort(int[] arr, int left, int right, boolean ascending) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, ascending);
            mergeSort(arr, mid + 1, right, ascending);
            merge(arr, left, mid, right, ascending);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if ((ascending && L[i] <= R[j]) || (!ascending && L[i] >= R[j])) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }
}
