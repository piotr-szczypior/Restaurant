package service.api;

import domain.eto.Meal;
import service.repo.Storage;

public interface StorageService {
    void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage);
}
