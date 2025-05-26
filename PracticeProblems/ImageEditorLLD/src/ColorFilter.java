public class ColorFilter implements Filter {
    private String color;

    public ColorFilter(String color) {
        this.color = color;
    }

    public void apply(Image image) {
        System.out.println("Applying color: " + color);
    }
}
