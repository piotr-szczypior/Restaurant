package service.repo;

import domain.eto.Produce;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private Map<Produce, Integer> producePerAmount = new HashMap<>();

    public Map<Produce, Integer> getProducePerAmount() {
        return producePerAmount;
    }

    public void addProduce(Produce produce) {
        if (producePerAmount.containsKey(produce)) {
            producePerAmount.computeIfPresent(produce,(Produce, amount) -> ++amount);
        } else {
            producePerAmount.put(produce, 1);
        }
    }

    public void removeProduce(Produce produce) {
        producePerAmount.computeIfPresent(produce, (k, v) -> --v);
    }

    public void setProducePerAmount(Map<Produce, Integer> producePerAmount) {
        this.producePerAmount = producePerAmount;
    }
}

