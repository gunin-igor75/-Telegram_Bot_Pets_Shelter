package pro.sky.telegram_bot_pets_shelter.command.volunteer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.telegram_bot_pets_shelter.command.Command;
import pro.sky.telegram_bot_pets_shelter.entity.Dog;
import pro.sky.telegram_bot_pets_shelter.entity.Owner;
import pro.sky.telegram_bot_pets_shelter.exception_handling.OwnerNotFoundException;
import pro.sky.telegram_bot_pets_shelter.service.DogService;
import pro.sky.telegram_bot_pets_shelter.service.imp.OwnerServiceImpl;
import pro.sky.telegram_bot_pets_shelter.utils.MessageUtils;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AdoptionDog implements Command {
    private final OwnerServiceImpl ownerService;
    private final DogService dogService;
    private final MessageUtils messageUtils;

    public AdoptionDog(OwnerServiceImpl ownerService, DogService dogService, MessageUtils messageUtils) {
        this.ownerService = ownerService;
        this.dogService = dogService;
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        var idDog = Long.parseLong(update.getCallbackQuery().getData().split("\\s+")[0]);
        var chatIdOwner = update.getCallbackQuery().getMessage().getFrom().getId();
        Owner persistentOwner = ownerService.findOwnerByChatId(chatIdOwner);
        Dog persistentDog = dogService.findDog(idDog);
        if (persistentOwner == null) {
            log.error("persistentOwner is null registration");
            throw new OwnerNotFoundException();
        }
        if (!persistentOwner.getRegistration()) {
            return messageUtils.generationSendMessage(update,"You are not registered");
        }
        if (persistentDog == null) {
            return messageUtils.generationSendMessage(update, "No such dog");
        }
        boolean adoption = ownerService.checkAdoptionDog(persistentOwner);
        if (!adoption) {
            return messageUtils.generationSendMessage(update, "Do you have one dog on probation");
        }
        persistentDog.setAdopted(false);
        persistentDog.setDateAdoption(LocalDateTime.now());
        persistentOwner.setDog(persistentDog);
        dogService.editDog(persistentDog);
        ownerService.editOwner(persistentOwner);
        var text = "Congratulations. The dog " + persistentDog.getName() + " belongs to you.";
        return messageUtils.generationSendMessage(update, text);
    }
}
