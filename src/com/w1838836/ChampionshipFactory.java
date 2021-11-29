package com.w1838836;

public final class ChampionshipFactory {
    /**
     * Create a new championship manager.
     *
     * @param name The name of the manager.
     * @return The championship manager object.
     */
    public static ChampionshipManager createManager(String name) {
        if (name.equals("Formula 1"))
            return new Formula1ChampionshipManager();

        return null;
    }
}
