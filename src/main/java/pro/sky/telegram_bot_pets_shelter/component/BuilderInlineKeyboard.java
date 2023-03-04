package pro.sky.telegram_bot_pets_shelter.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class BuilderInlineKeyboard {
    public InlineKeyboardMarkup createInlineKey(Map<String, String> mapCommand) {
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
    /**
     * Данный метод формрует клавиатуру при выборе пункта меню shelter
     */
    public InlineKeyboardMarkup createInlineKeyShelter() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton button4 = new InlineKeyboardButton();

        button1.setText("information about the shelter");
        button1.setCallbackData("information");
        List<InlineKeyboardButton> list1 = new ArrayList<>();
        list1.add(button1);
        button2.setText("shelter schedule, address and directions");
        button2.setCallbackData("address");
        list1.add(button2);
        List<InlineKeyboardButton> list2 = new ArrayList<>();
        button3.setText("safety tips for the shelter");
        button3.setCallbackData("safety");
        list2.add(button3);
        button4.setText("leave contacts");
        button4.setCallbackData("contacts");
        list2.add(button4);

        List<List<InlineKeyboardButton>> listButton = new ArrayList<>();
        listButton.add(list1);
        listButton.add(list2);
        markup.setKeyboard(listButton);
        return markup;
    }
}























