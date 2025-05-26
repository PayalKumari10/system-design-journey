public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7};

        // Using QuickSort
        SortStrategy quickSort = new QuickSortStrategy(false);
        SortHandler handler = new SortHandler(quickSort);
        handler.sort(arr, true);

        // Switching to MergeSort
        handler.setStrategy(new MergeSortStrategy(false));
        handler.sort(arr, false);
    }
}
