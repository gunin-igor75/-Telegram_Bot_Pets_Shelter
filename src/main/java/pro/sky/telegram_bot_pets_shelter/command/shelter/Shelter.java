package pro.sky.telegram_bot_pets_shelter.command.shelter;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import pro.sky.telegram_bot_pets_shelter.command.Command;
import pro.sky.telegram_bot_pets_shelter.component.BuilderInlineKeyboard;
import pro.sky.telegram_bot_pets_shelter.utils.MessageUtils;
/**
 * Данный класс формрует сообщения исходя из выбора shelter
 */
@Component
public class Shelter implements Command {
    private final BuilderInlineKeyboard keyboard;
    private final MessageUtils messageUtils;

    public Shelter(BuilderInlineKeyboard keyboard, MessageUtils messageUtils) {
        this.keyboard = keyboard;
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute (Update update){
        InlineKeyboardMarkup markup = keyboard.createInlineKeyShelter();
        String text = "Select the information you are interested in:";
        return messageUtils.generationSendMessage(update,markup,text);

    }
}
