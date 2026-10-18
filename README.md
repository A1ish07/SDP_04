# SDP_04

## Alimbayev Alisher

## Structure

```
src/
├── Main.java                    client, uses only the facade
├── MediaConversionFacade.java   the facade
├── MetadataReader.java          reads file metadata
├── CodecFactory.java            creates a codec for the target format
├── BitrateReader.java           source bitrate and target bitrate selection
└── AudioMixer.java              downmix to stereo and normalization
```

Subsystem classes do not know about the facade and can be used directly.

# UML 
<img width="1315" height="766" alt="umlsdp_04" src="https://github.com/user-attachments/assets/e3efc76b-047a-4aa2-981a-2deeb3377979" />
