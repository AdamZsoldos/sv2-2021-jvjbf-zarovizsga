package shipping;

import java.util.*;

public class ShippingService {

    private final List<Transportable> packages = new ArrayList<>();

    public List<Transportable> getPackages() {
        return packages;
    }

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(p -> p.isBreakable() == breakable && p.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Transportable transportable : packages) {
            result.compute(
                    transportable.getDestinationCountry(),
                    (k, v) -> v == null ? 1 : v + 1
            );
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream()
                .filter(InternationalPackage.class::isInstance)
                .sorted(Comparator.comparing(p -> ((InternationalPackage) p).getDistance()))
                .toList();
    }
}
