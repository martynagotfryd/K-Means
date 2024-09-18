import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Observation> readFile(String filePath) {
        List<Observation> observations = new ArrayList<>();
        List<String> rawData;
        try {
            rawData = Files.readAllLines(Path.of(filePath));

            rawData.forEach(e -> {
                String[] rawVector = e.split(",");
                List<Double> vector = new ArrayList<>();
                for (int i = 0; i < rawVector.length - 1; i++) {
                    vector.add(Double.parseDouble(rawVector[i].trim()));
                }
                String label = rawVector[rawVector.length - 1];
                observations.add(new Observation(vector, label));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return observations;
    }
}

