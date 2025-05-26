import java.util.Random;

public class QuickSortStrategy implements SortStrategy {
    private boolean isRandomized;
    private Random random;

    public QuickSortStrategy(boolean isRandomized) {
        this.isRandomized = isRandomized;
        this.random = new Random();
    }

    @Override
    public void sort(int[] array, boolean ascending) {
        if (isRandomized) {
            System.out.println("Using Randomized QuickSort");
            randomizedQuickSort(array, 0, array.length - 1, ascending);
        } else {
            System.out.println("Using Standard QuickSort");
            quickSort(array, 0, array.length - 1, ascending);
        }
    }

    private void quickSort(int[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(arr, low, high, ascending);
            quickSort(arr, low, pi - 1, ascending);
            quickSort(arr, pi + 1, high, ascending);
        }
    }

    private void randomizedQuickSort(int[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            int pivotIndex = low + random.nextInt(high - low + 1);
            swap(arr, pivotIndex, high); // Move random pivot to end

            int pi = partition(arr, low, high, ascending);
            randomizedQuickSort(arr, low, pi - 1, ascending);
            randomizedQuickSort(arr, pi + 1, high, ascending);
        }
    }

    private int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] > pivot)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
