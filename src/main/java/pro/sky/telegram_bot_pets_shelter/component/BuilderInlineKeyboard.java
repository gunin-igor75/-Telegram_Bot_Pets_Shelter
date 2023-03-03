package pro.sky.telegram_bot_pets_shelter.component;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class BuilderInlineKeyboard {
    public InlineKeyboardMarkup createInlineKey(LinkedHashMap<String, String> mapCommand) {
        if (mapCommand.isEmpty()) {
            log.warn("mapCommand is Empty");
            throw new IllegalArgumentException();
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> listButton = new ArrayList<>();
        mapCommand.forEach((key, value) -> {
            List<InlineKeyboardButton> list = new ArrayList<>();
            InlineKeyboardButton elemKeyboard = new InlineKeyboardButton();
            elemKeyboard.setText(value);
            elemKeyboard.setCallbackData(key);
            list.add(elemKeyboard);
            listButton.add(list);
        });
        markup.setKeyboard(listButton);
        return markup;
    }
    public InlineKeyboardMarkup createInlineKeyboard(LinkedHashMap<String, String> mapCommand) {
        if (mapCommand.isEmpty()) {
            log.warn("mapCommand is Empty");
            throw new IllegalArgumentException();
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> listButton = new ArrayList<>();
        mapCommand.forEach((key, value) -> {
            List<InlineKeyboardButton> list = new ArrayList<>();
            InlineKeyboardButton shelterKeyboard = new InlineKeyboardButton();
            shelterKeyboard.setText(EmojiParser.parseToUnicode(":information_source:" + "Информация о приюте"));
            shelterKeyboard.setCallbackData("SHELTER_BUTTON");
            list.add(shelterKeyboard);
            InlineKeyboardButton adoptionKeyboard = new InlineKeyboardButton();
            shelterKeyboard.setText(EmojiParser.parseToUnicode(":dog:" + ":cat:" + "Как взять питомца из приюта"));
            shelterKeyboard.setCallbackData("ADOPTION_BUTTON");
            list.add(shelterKeyboard);
            InlineKeyboardButton applicationKeyboard = new InlineKeyboardButton();
            shelterKeyboard.setText(EmojiParser.parseToUnicode(":white_check_mark:" + "Регистрация, усыновление"));
            shelterKeyboard.setCallbackData("APP_BUTTON");
            list.add(shelterKeyboard);
            InlineKeyboardButton reportKeyboard = new InlineKeyboardButton();
            shelterKeyboard.setText(EmojiParser.parseToUnicode(":e-mail:" + "Прислать отчет о питомце"));
            shelterKeyboard.setCallbackData("REPORT_BUTTON");
            list.add(shelterKeyboard);
            InlineKeyboardButton volunteerKeyboard = new InlineKeyboardButton();
            shelterKeyboard.setText(EmojiParser.parseToUnicode(":loudspeaker:" + "Позвать волонтера"));
            shelterKeyboard.setCallbackData("VOLUNTEER_BUTTON");
            list.add(shelterKeyboard);
            listButton.add(list);
        });
        markup.setKeyboard(listButton);
        return markup;
        }
    }























