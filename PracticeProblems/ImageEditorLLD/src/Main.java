public class Main {
    public static void main(String[] args) {
        Camera camera = new Camera();
        Image image = camera.clickImage();

        ImageEditor editor = new ImageEditor();
        editor.applyFilter(image, new CropFilter());
        editor.applyFilter(image, new SharpenFilter());
        editor.applyFilter(image, new ColorFilter("Blue"));

        Storage storage = new SQLStorage();
        storage.save(image);
    }
}
