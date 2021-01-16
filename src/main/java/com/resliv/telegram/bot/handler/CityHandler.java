package com.resliv.telegram.bot.handler;

import com.resliv.telegram.bot.State;
import com.resliv.telegram.model.City;
import com.resliv.telegram.repository.JpaCitiesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static com.resliv.telegram.util.TelegramUtil.createMessageTemplate;

@Component
public class CityHandler implements Handler{
    @Value("${bot.name}")
    private String botUsername;

    private final JpaCitiesRepository citiesRepository;

    public CityHandler(JpaCitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(City city, String message) {
        SendMessage welcomeMessage = createMessageTemplate(city)
                .setText(String.format("Hola! I'm *%s*%nI am here to help you learn Java", botUsername));
        SendMessage registrationMessage = createMessageTemplate(city)
                .setText("In order to start our journey tell me your name");

        citiesRepository.save(city);

        return List.of(welcomeMessage, registrationMessage);
    }

    @Override
    public State operatedBotState() {
        return State.START;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}
