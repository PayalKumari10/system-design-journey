public class Image {
    private String name;
    private String metadata;
    private String content;

    public Image(String name, String metadata, String content) {
        this.name = name;
        this.metadata = metadata;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Image{name='" + name + "', metadata='" + metadata + "', content='" + content + "'}";
    }
}
