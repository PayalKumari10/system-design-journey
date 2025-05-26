public class SortHandler {
    private SortStrategy sortingStrategy;

    public SortHandler(SortStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public void sort(int[] array, boolean ascending) {
        sortingStrategy.sort(array, ascending);
    }
}
