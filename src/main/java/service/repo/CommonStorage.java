package service.repo;

import domain.eto.Produce;

import java.util.HashMap;
import java.util.Map;

public class CommonStorage {
    private static Map<Produce, Integer> producePerAmount = new HashMap<>();

    public static boolean checkInStorage(Produce produce) {
        return ((producePerAmount.containsKey(produce)) && (producePerAmount.get(produce) > 0));
    }

    public static void addToStorage(Produce product, Integer quantity) {
        if (!producePerAmount.containsKey(product)) {
            producePerAmount.put(product, quantity);
            return;
        }
        producePerAmount.computeIfPresent(product, (Produce, amount) -> amount + quantity);
    }

    public static void removeFromStorage(Produce product, Integer quantity) {
        if(!producePerAmount.containsKey(product)) {
            return;
        }

        Integer amount = producePerAmount.get(product);
        amount = amount >= quantity ? amount - quantity : 0;
        producePerAmount.put(product, amount);
    }

    public static void clearStorage() {
        producePerAmount.clear();
    }
}
