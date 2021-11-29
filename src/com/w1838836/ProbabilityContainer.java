package com.w1838836;

public class ProbabilityContainer implements Comparable<ProbabilityContainer> {
    private Driver mDriver;
    private int mIndex;
    private double mProbability;

    /**
     * Default constructor.
     */
    public ProbabilityContainer() {
        mDriver = null;
        mIndex = 0;
        mProbability = 0.0;
    }

    /**
     * Constructor.
     *
     * @param driver      The driver object.
     * @param index       The driver index.
     * @param probability The winning probability.
     */
    public ProbabilityContainer(Driver driver, int index, double probability) {
        mDriver = driver;
        mIndex = index;
        mProbability = probability;
    }

    /**
     * Get the driver.
     *
     * @return The driver stored inside.
     */
    public Driver getDriver() {
        return mDriver;
    }

    /**
     * Get the driver index.
     *
     * @return The index.
     */
    public int getIndex() {
        return mIndex;
    }

    /**
     * Get the winning probability.
     *
     * @return Get the winning probability of the driver.
     */
    public double getProbability() {
        return mProbability;
    }

    /**
     * Compare to function override.
     *
     * @param o The other object.
     * @return The compared result.
     */
    @Override
    public int compareTo(ProbabilityContainer o) {
        if (mProbability > o.mProbability)
            return 1;
        else if (mProbability < o.mProbability)
            return -1;

        return 0;
    }
}
