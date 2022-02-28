package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    private final List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            while (reader.ready()) {
                channels.add(parseLine(reader.readLine()));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot open file for read!", e);
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getNumberOfVideos)
                .sum();
    }

    private Channel parseLine(String line) {
        String[] fields = line.split(";");
        String channelName = fields[0];
        int subscriptions = Integer.parseInt(fields[1]);
        int numberOfVideos = Integer.parseInt(fields[2]);
        return new Channel(channelName, subscriptions, numberOfVideos);
    }
}
