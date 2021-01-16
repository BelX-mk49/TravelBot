package com.resliv.telegram.util;

import com.resliv.telegram.model.City;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class TelegramUtil {
    public static SendMessage createMessageTemplate(City city) {
        return createMessageTemplate(String.valueOf(city.getName()));
    }

    public static SendMessage createMessageTemplate(String cityName) {
        return new SendMessage()
                .setText(cityName)
                .enableMarkdown(true);
    }

    // Creating button
    public static InlineKeyboardButton createInlineKeyboardButton(String text, String command) {
        return new InlineKeyboardButton()
                .setText(text)
                .setCallbackData(command);
    }
}
