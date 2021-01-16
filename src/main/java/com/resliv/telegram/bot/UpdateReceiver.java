package com.resliv.telegram.bot;

import com.resliv.telegram.bot.handler.Handler;
import com.resliv.telegram.model.City;
import com.resliv.telegram.repository.JpaCitiesRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
public class UpdateReceiver {
    private final List<Handler> handlers;
    private final JpaCitiesRepository jpaCitiesRepository;

    public UpdateReceiver(List<Handler> handlers, JpaCitiesRepository jpaCitiesRepository) {
        this.handlers = handlers;
        this.jpaCitiesRepository = jpaCitiesRepository;
    }

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update) {
        try {
            if (isMessageWithText(update)) {
                final Message message = update.getMessage();
                final String cityName = message.getFrom().getUserName();
                final City city = jpaCitiesRepository.getByName(cityName)
                        .orElseGet(() -> jpaCitiesRepository.save(new City(cityName)));
                return getHandlerByState(city.getBotState()).handle(city, message.getText());
            } else if (update.hasCallbackQuery()) {
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                final String cityName = callbackQuery.getFrom().getUserName();
                final City city = jpaCitiesRepository.getByName(cityName)
                        .orElseGet(() -> jpaCitiesRepository.save(new City(cityName)));

                return getHandlerByCallBackQuery(callbackQuery.getData()).handle(city, callbackQuery.getData());
            }

            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }

    private Handler getHandlerByState(State state) {
        return handlers.stream()
                .filter(h -> h.operatedBotState() != null)
                .filter(h -> h.operatedBotState().equals(state))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private Handler getHandlerByCallBackQuery(String query) {
        return handlers.stream()
                .filter(h -> h.operatedCallBackQuery().stream()
                        .anyMatch(query::startsWith))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }
}
