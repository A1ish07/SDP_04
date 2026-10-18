public class AudioMixer {

    private static final int FRAMES = 1000;

    public float[] mix(MetadataReader.Metadata meta) {
        float[] source = load(meta.channels());
        float[] stereo = downmix(source, meta.channels());
        normalize(stereo);
        return stereo;
    }

    private float[] load(int channels) {
        float[] samples = new float[FRAMES * channels];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = (i % 20 - 10) / 10f;
        }
        return samples;
    }

    private float[] downmix(float[] source, int channels) {
        float[] stereo = new float[FRAMES * 2];
        for (int frame = 0; frame < FRAMES; frame++) {
            float sum = 0;
            for (int channel = 0; channel < channels; channel++) {
                sum += source[frame * channels + channel];
            }
            float average = sum / channels;
            stereo[2 * frame] = average;
            stereo[2 * frame + 1] = average;
        }
        return stereo;
    }

    private void normalize(float[] samples) {
        float peak = 0;
        for (float sample : samples) {
            peak = Math.max(peak, Math.abs(sample));
        }
        if (peak == 0) {
            return;
        }
        for (int i = 0; i < samples.length; i++) {
            samples[i] /= peak;
        }
    }
}