package shipping;

public class InternationalPackage implements Transportable {

    private static final int DEFAULT_PRICE = 1200;
    private static final int BREAKABLE_PRICE_MULTIPLIER = 2;
    private static final int PRICE_PER_KM = 10;

    private final int weight;
    private final boolean breakable;
    private final String destinationCountry;
    private final int distance;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int calculateShippingPrice() {
        int basePrice = DEFAULT_PRICE * (breakable ? BREAKABLE_PRICE_MULTIPLIER : 1);
        return basePrice + (distance * PRICE_PER_KM);
    }
}
