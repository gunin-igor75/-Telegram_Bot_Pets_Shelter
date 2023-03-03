package pro.sky.telegram_bot_pets_shelter.exception;

import lombok.Getter;

@Getter
public class PetNotFoundException extends RuntimeException {
    private final Long id;

    public PetNotFoundException(Long id) {
        this.id = id;
    }

}
