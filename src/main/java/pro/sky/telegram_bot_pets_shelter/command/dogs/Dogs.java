package pro.sky.telegram_bot_pets_shelter.command.dogs;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import pro.sky.telegram_bot_pets_shelter.command.Command;
import pro.sky.telegram_bot_pets_shelter.component.BuilderKeyboard;
import pro.sky.telegram_bot_pets_shelter.utils.MessageUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Dogs implements Command {
    private final BuilderKeyboard keyboard;
    private final MessageUtils messageUtils;

    public Dogs(BuilderKeyboard keyboard, MessageUtils messageUtils) {
        this.keyboard = keyboard;
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        Map<String, String> mapCommand = new LinkedHashMap<>();
        mapCommand.put( "shelterDogsInfo", "shelter");
        mapCommand.put("shelterDogsAdoption", "adoption");
        mapCommand.put("dogReport", "report");
        mapCommand.put("volunteerDogs", "volunteer");
        InlineKeyboardMarkup markup = keyboard.createInlineKey(mapCommand);
        String text = "Welcome to the dogs shelter";
        return messageUtils.generationSendMessage(update,markup,text);
    }
}
