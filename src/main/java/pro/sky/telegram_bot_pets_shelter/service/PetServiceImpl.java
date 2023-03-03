package pro.sky.telegram_bot_pets_shelter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.telegram_bot_pets_shelter.entity.Pet;
import pro.sky.telegram_bot_pets_shelter.exception.PetNotFoundException;
import pro.sky.telegram_bot_pets_shelter.repositories.PetRepository;

import java.util.List;
/**
 *  Pet Service Implement - Класс, который содержит переопределенные CRUD операции
 **/
@Slf4j
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository repository) {
        this.petRepository = repository;
    }

    /**
     * Данный метод для создания нового питомца
     * @param pet питомец
     * @return - сохраняет нового питомца в БД и возвращает его
     **/
    @Override
    public Pet createPet(Pet pet) {
        log.info("Create pet");
        pet.setId(null);
        return petRepository.save(pet);
    }

    /**
     * Данный метод для поиска питомца по ID
     * @param id питомца
     * @return - ищет питомца по ID и возвращает его
     * @exception PetNotFoundException если питомца с таким Id нет, выбрасывает исключение
     **/
    @Override
    public Pet findPet(Long id) {
        log.info("Find pet by ID: {}", id );
        return petRepository.findById(id).orElseThrow(()-> new PetNotFoundException(id));
    }

    /**
     * Данный метод для редактирования питомца
     * @param pet питомец
     * @return - сохраняет питомца в БД и возвращает его
     **/
    @Override
    public Pet editPet(Pet pet) {
        log.info("Edit pet ID: {}", pet.getId());
        if (petRepository.findById(pet.getId()).orElse(null) == null) {
            return null;
        }
        return petRepository.save(pet);
    }

    /**
     * Данный метод для удаления питомца по ID
     * @param id  питомца
     * @return - возвращает удаленного питомца
     * @exception PetNotFoundException если питомца с таким Id нет, выбрасывает исключение
     **/
    @Override
    public Pet deletePet(Long id) {
        log.info("Delete pet ID: {}", id);
        Pet pet=petRepository.findById(id).orElseThrow(()-> new PetNotFoundException(id));
        petRepository.delete(pet);
        return pet;
    }

    /**
     * Данный метод ищет всех питомцев
     * @return - возвращает список питомцев
     **/
    @Override
    public List<Pet> getAllPets() {
        log.info("Get all pets");
        return petRepository.findAll();
    }
}
