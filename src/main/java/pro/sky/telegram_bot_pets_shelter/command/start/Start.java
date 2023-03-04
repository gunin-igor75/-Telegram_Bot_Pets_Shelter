package pro.sky.telegram_bot_pets_shelter.command.start;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.telegram_bot_pets_shelter.command.Command;
import pro.sky.telegram_bot_pets_shelter.utils.MessageUtils;
/**
 * Данный класс формрует сообщения исходя из выбора start
 */
@Component
public class Start implements Command {

    private final MessageUtils messageUtils;

    public Start(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        String userName = update.getMessage().getChat().getUserName();
        return messageUtils.generationSendMessage(update, EmojiParser.parseToUnicode("Здравствуйте " + userName + ":heart_eyes:" + " Вас приветствует бот приюта!"));
    }
}
