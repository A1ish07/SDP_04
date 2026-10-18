public class CodecFactory {

    public record Codec(String format, int maxBitrate) {

        private static final int BASELINE = 1411;

        public byte[] encode(float[] samples, int bitrate) {
            int length = samples.length * Math.min(bitrate, BASELINE) / BASELINE;
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = (byte) Math.round(samples[i] * 127);
            }
            return data;
        }
    }

    public Codec create(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Target format is not specified");
        }
        return switch (format.toLowerCase()) {
            case "wav" -> new Codec("wav", 1411);
            case "mp3" -> new Codec("mp3", 320);
            case "ogg" -> new Codec("ogg", 500);
            default -> throw new IllegalArgumentException("Unsupported target format: " + format);
        };
    }
}