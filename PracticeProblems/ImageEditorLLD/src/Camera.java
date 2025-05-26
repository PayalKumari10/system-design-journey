public class Camera {
    public Image clickImage() {
        System.out.println("Clicking image...");
        return new Image("Image1", "JPEG", "binary-data");
    }
}
