import java.util.*;

public class Cluster {
    private final List<Observation> elements;

    public Cluster(){
        this.elements = new ArrayList<>();
    }
    public List<Observation> getElements() {
        return this.elements;
    }
    public void addElement(Observation element) {
        this.elements.add(element);
    }
    public void removeElement(Observation element) {
        this.elements.remove(element);
    }

    public double[] calculateCentroid() {
        // Check if there are elements in cluster
        if(this.elements.size() == 0) return null;
        else {

            int size = elements.get(0).vector().size();
            double[] centroid = new double[size];
            Arrays.fill(centroid, 0.0);

            // Add all values
            for (Observation element : elements) {
                for (int i = 0; i < size; i++) {
                    centroid[i] += element.vector().get(i);
                }
            }
            // Divide by num of elements in cluster
            for (int i = 0; i < size; i++) {
                centroid[i] = centroid[i] / elements.size();
            }
            return centroid;
        }
    }

    public double calculateDistance(Observation observation) {
        double distance = 0;
        double[] centroid = this.calculateCentroid();

        // Check if centroid exist, if cluster is not empty
        if(centroid == null) return Double.MAX_VALUE;

        // Euler formula
        for (int i = 0; i < elements.get(0).vector().size(); i++) {
            distance += Math.pow(observation.vector().get(i) - centroid[i], 2);
        }
        distance = Math.sqrt(distance);

        return distance;
    }

    public double calculateSumOfDistances() {
        double sumOfDistances = 0;
        for (Observation observation : elements) {
            sumOfDistances += calculateDistance(observation);
        }
        return sumOfDistances;
    }

    public void printPurity() {
        // Check if there are elements in cluster
        if (this.elements.size() == 0) System.out.println("This Cluster is Empty!");
        else {

            double labelCount;
            double purity;

            // Get all labels
            Set<String> labels = new HashSet<>();
            for (Observation element : elements) {
                labels.add(element.label());
            }

            // Check purity for each label
            for (String label : labels) {
                labelCount = 0;
                for (Observation element : elements) {
                    if (element.label().equals(label)) {
                        labelCount++;
                    }
                }
                purity = labelCount / elements.size() * 100;

                System.out.print(label + " " + (int) purity + "% ");
            }
            System.out.println();
        }
  }
}
