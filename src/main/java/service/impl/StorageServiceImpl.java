package service.impl;

import domain.eto.Meal;
import domain.eto.Produce;
import service.api.StorageService;
import service.exception.NoProduceException;
import service.repo.Storage;

public class StorageServiceImpl implements StorageService {

    @Override
    public void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage) {
        if (meal == null || storage == null) {
            throw new IllegalArgumentException();
        }

        for (Produce produce : meal.getProducts()) {
            if (!storage.getProducePerAmount().containsKey(produce) || storage.getProducePerAmount().get(produce) == 0) {
                throw new NoProduceException("There is no " + produce.getName() + " in storage");
            }
        }
    }
}
