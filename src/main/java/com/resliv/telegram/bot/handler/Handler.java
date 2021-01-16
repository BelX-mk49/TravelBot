package com.resliv.telegram.bot.handler;

import com.resliv.telegram.bot.State;
import com.resliv.telegram.model.City;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.Serializable;
import java.util.List;

public interface Handler {
    List<PartialBotApiMethod<? extends Serializable>> handle(City city, String message);

    State operatedBotState();

    List<String> operatedCallBackQuery();
}
