public class MediaConversionFacade {

    private final MetadataReader metadataReader = new MetadataReader();
    private final CodecFactory codecFactory = new CodecFactory();
    private final BitrateReader bitrateReader = new BitrateReader();
    private final AudioMixer audioMixer = new AudioMixer();

    public String convert(String fileName, String targetFormat) {
        try {
            MetadataReader.Metadata meta = metadataReader.read(fileName);
            CodecFactory.Codec codec = codecFactory.create(targetFormat);
            int source = bitrateReader.readBitrate(meta);
            int bitrate = bitrateReader.pickBitrate(source, codec);
            float[] samples = audioMixer.mix(meta);
            byte[] encoded = codec.encode(samples, bitrate);
            return meta.name() + "." + codec.format()
                    + " (" + bitrate + " kbps, " + encoded.length + " bytes)";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Cannot convert '" + fileName + "' to '" + targetFormat + "': " + e.getMessage(), e);
        }
    }
}