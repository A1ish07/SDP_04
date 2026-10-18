public class BitrateReader {

    public int readBitrate(MetadataReader.Metadata meta) {
        return meta.rate() * meta.depth() * meta.channels() / 1000;
    }

    public int pickBitrate(int source, CodecFactory.Codec codec) {
        return Math.min(source, codec.maxBitrate());
    }
}