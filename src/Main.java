public class Main {

    public static void main(String[] args) {
        MediaConversionFacade converter = new MediaConversionFacade();

        convert(converter, "concert.flac", "mp3");
        convert(converter, "podcast.wav", "ogg");
        convert(converter, "lecture.mp3", "wav");
        convert(converter, "track.flac", "aac");
        convert(converter, "notes.txt", "mp3");
    }

    private static void convert(MediaConversionFacade converter, String fileName, String targetFormat) {
        try {
            System.out.println("OK:     " + converter.convert(fileName, targetFormat));
        } catch (IllegalArgumentException e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}