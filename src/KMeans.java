import java.util.*;

public class KMeans {
    private int k;
    private final List<Observation> observations;
    private final List<Cluster> clusters;
    public KMeans(String filePath) {
        this.observations = FileReader.readFile(filePath);
        this.clusters = new ArrayList<>();
        setK();
        setClusters();
    }

    public void setK() {
        String strK;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Provide k: ");
            strK = scanner.nextLine();
            try {
                this.k = Integer.parseInt(strK);
                if (k > 0) {
                    break;
                } else System.out.println("K must be positive, try again.");
            } catch (Exception e) {
                System.out.println("K must be integer, try again.");
            }
        }
    }

    public void setClusters() {
        // Create k num of clusters
        for(int i =0; i < k; i++) {
            clusters.add(new Cluster());
        }
        Random random = new Random();

        // while there is empty cluster (anyMatch returns true if condition is meet)
        while(clusters.stream().anyMatch(e -> e.getElements().size() == 0)) {
            for (Observation observation : observations) {
                int clusterIndex = random.nextInt(k);
                clusters.get(clusterIndex).addElement(observation);
            }
        }

    }

    public int calculateSumOfDistances() {
        int SumOfDistances = 0;

        for (Cluster cluster : clusters) {
            SumOfDistances += cluster.calculateSumOfDistances();
        }
        return SumOfDistances;
    }

    private void reassignmentOfClusters() {

        for (int j = 0; j < clusters.size(); j++) {

            // Map of changed elements that need to be reassigned (Observation - element, Integer - index of the nearest luster)
            Map<Observation, Integer> reassigned = new HashMap<>();

            for (Observation element : clusters.get(j).getElements()) {
                double distance = clusters.get(j).calculateDistance(element);
                double shortestDistance = distance;
                int indexOfNearestCluster = j;

                // Find the closest cluster
                for (int i = 0; i < clusters.size(); i++) {
                    double distance1 = clusters.get(i).calculateDistance(element);
                    if (shortestDistance >= distance1) {
                        shortestDistance = distance1;
                        indexOfNearestCluster = i;
                    }
                }
                // Check if element needs to be reassigned
                if (shortestDistance != distance) {
                    reassigned.put(element, indexOfNearestCluster);
                }
            }
            int finalJ = j;
            // Reassign elements, remove each changed element from current cluster and add it to the nearest cluster
            reassigned.keySet().forEach(entry -> {
                clusters.get(finalJ).removeElement(entry);
                clusters.get(reassigned.get(entry)).addElement(entry);
            });
        }
    }

    public void loop() {

        int sumDist;
        int iteration = 1;
        int indexOfCluster = 1;

        do {
            sumDist = calculateSumOfDistances();

            System.out.println("Iteration " + iteration);
            // Print sum of distances
            System.out.println("Sum of distances ~ " + sumDist);

            // Print purity
            for (Cluster cluster : clusters) {
                System.out.println("Cluster " + indexOfCluster + ": ");
                cluster.printPurity();
                indexOfCluster += 1;
            }
            System.out.println();
            iteration += 1;
            indexOfCluster = 1;

            // Reassign Clusters
            reassignmentOfClusters();

        // Check if sum of distances changed, if no the stop
        } while(sumDist != calculateSumOfDistances());
    }

    public static void main(String[] args) {

        String filePath = "iris";

        KMeans kMeans = new KMeans(filePath);
        kMeans.loop();
    }
}
