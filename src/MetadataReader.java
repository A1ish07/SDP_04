public class MetadataReader {

    public record Metadata(String name, String format, int rate, int depth, int channels) {
    }

    public Metadata read(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            throw new IllegalArgumentException("File has no extension: " + fileName);
        }
        int dot = fileName.lastIndexOf('.');
        String name = fileName.substring(0, dot);
        String format = fileName.substring(dot + 1).toLowerCase();

        return switch (format) {
            case "wav", "mp3" -> new Metadata(name, format, 44100, 16, 2);
            case "ogg" -> new Metadata(name, format, 48000, 16, 2);
            case "flac" -> new Metadata(name, format, 96000, 24, 6);
            default -> throw new IllegalArgumentException("Unsupported source format: " + format);
        };
    }
}