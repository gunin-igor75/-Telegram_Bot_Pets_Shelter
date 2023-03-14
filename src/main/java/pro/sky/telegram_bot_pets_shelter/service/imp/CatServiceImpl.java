package pro.sky.telegram_bot_pets_shelter.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.telegram_bot_pets_shelter.entity.Cat;
import pro.sky.telegram_bot_pets_shelter.exception_handling.CatNotFoundException;
import pro.sky.telegram_bot_pets_shelter.repositories.CatRepository;
import pro.sky.telegram_bot_pets_shelter.service.CatService;

import java.util.List;

@Service
@Slf4j
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public Cat createCat(Cat cat) {
        checkCayNull(cat);
        if (cat.getId() == null) {
            return catRepository.save(cat);
        }
        return cat;
    }

    @Override
    public Cat findCat(Long id) {
        return catRepository.findById(id).orElse(null);
    }

    @Override
    public Cat editCat(Cat cat) {
        checkCayNull(cat);
        Cat persistentCat = findCat(cat.getId());
        if (persistentCat == null) {
            String message = "There is no cat with ID =" + cat.getId() + " in Database";
            log.error(message);
            throw new CatNotFoundException();
        }
        return catRepository.save(persistentCat);
    }

    @Override
    public Cat deleteCat(Long id) {
        Cat cat = findCat(id);
        if (cat == null) {
            String message = "There is no cat with ID =" + id + " in Database";
            log.error(message);
            throw new CatNotFoundException();
        }
        catRepository.delete(cat);
        return cat;
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }
    @Override
    public List<Cat> getAllCatsFree() {
        return catRepository.getCatsByAdoptedIsNull();
    }

    private void checkCayNull(Cat cat) {
        if (cat == null) {
            String message = "cat is null";
            log.error(message);
            throw new NullPointerException();
        }
    }
}
