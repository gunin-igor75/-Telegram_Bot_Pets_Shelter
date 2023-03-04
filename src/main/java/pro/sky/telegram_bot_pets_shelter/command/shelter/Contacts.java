package pro.sky.telegram_bot_pets_shelter.command.shelter;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import pro.sky.telegram_bot_pets_shelter.command.Command;
import pro.sky.telegram_bot_pets_shelter.component.BuilderInlineKeyboard;
import pro.sky.telegram_bot_pets_shelter.utils.MessageUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс формрует сообщения исходя из выбора shelter
 */
@Component
public class Contacts implements Command {
    private final MessageUtils messageUtils;
    private final BuilderInlineKeyboard keyboard;

    public Contacts(MessageUtils messageUtils, BuilderInlineKeyboard keyboard) {
        this.messageUtils = messageUtils;
        this.keyboard = keyboard;
    }
    @Override
    public SendMessage execute(Update update) {
        String text;
        Map<String,String> mapCommand = new HashMap<>();
        mapCommand.put("shelter", "Back");
        InlineKeyboardMarkup markup = keyboard.createInlineKey(mapCommand);
        text = "Отправить контакты";
        return messageUtils.generationSendMessage(update,markup,text);
    }
}

