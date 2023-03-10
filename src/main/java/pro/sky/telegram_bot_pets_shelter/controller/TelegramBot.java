package pro.sky.telegram_bot_pets_shelter.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import pro.sky.telegram_bot_pets_shelter.configuration.BotConfiguration;
import pro.sky.telegram_bot_pets_shelter.utils.CheckingMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * TelegramBot - Клас, который отвечает регистрацию, первичну настройку бота
 * и за взаимодействие бота с пользователем
 */
@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    /**
     * botConfiguration бин конфигурации бота
     */
    private final BotConfiguration botConfiguration;
    private final CheckingMessage checking;
    public SendMessage message;

    public TelegramBot(BotConfiguration botConfiguration, CheckingMessage checking) {
        super(botConfiguration.getToken());
        this.botConfiguration = botConfiguration;
        this.checking = checking;
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getName();
    }

    /**
     * Метод отвечающий за регитсрацию бота и формирования главногоменю
     */
    @PostConstruct
    public void init() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Error register bot", e);
        }
        createMenu();
    }

    /**
     * Основная логика контроллера:
     * рассматривает 2 варианта:
     * - если текстовое сообщение содержится в update.getMessage()
     * - если текстовое сообщение содержиься в реакции на нажатую кнопку
     *
     * @param update - входящи параметр при прослушиванием бота входящих сообщени
     */
    @Override
    public void onUpdateReceived(Update update) {
        message = checking.checkUpdate(update);
        sendAnswerMessage(message);
    }

    /**
     * Метод формирующий главное меню бота
     */
    private void createMenu() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/cats", "Приют для кошек"));
        commandList.add(new BotCommand("/dogs", "Приют для собак"));
        commandList.add(new BotCommand("/registration", "Регистрация"));
        try {
            execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error crete menu", e);
        }
    }

    /**
     * Метод отправляющий сообщение пользователю
     *
     * @param message - объект SendMessage, полученный от определенной команды
     */

    public void sendAnswerMessage(SendMessage message) {
        try {
            if (message != null) {
                execute(message);
            }
        } catch (TelegramApiException e) {
            log.error("Error send message", e);
        }
    }
}