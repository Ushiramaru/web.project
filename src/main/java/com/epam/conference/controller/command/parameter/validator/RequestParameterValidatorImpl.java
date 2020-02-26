package com.epam.conference.controller.command.parameter.validator;

import com.epam.conference.controller.command.parameter.validator.parameter.predicate.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class RequestParameterValidatorImpl implements RequestParameterValidator {

    private final static String UNKNOWN_PARAMETER_NAME_MESSAGE = "Unknown parameter ?";
    private final static String MIN_DATE = "1970-01-01T00:00";
    private final static String MAX_DATE = "2038-01-09T03:14";
    private final static int MAX_CONTENT_LENGTH = 50;

    private final static Map<String, Predicate<Object>> VALIDATORS;


    static {
        VALIDATORS = new HashMap<>();
        VALIDATORS.put("l", new UncheckedParameterPredicate());
        VALIDATORS.put("back", new UncheckedParameterPredicate());
        VALIDATORS.put("login", new UncheckedParameterPredicate());
        VALIDATORS.put("password", new UncheckedParameterPredicate());

        VALIDATORS.put("user_id", new IdParameterPredicate());
        VALIDATORS.put("conference_id", new IdParameterPredicate());
        VALIDATORS.put("question_id", new IdParameterPredicate());
        VALIDATORS.put("request_id", new IdParameterPredicate());
        VALIDATORS.put("section_id", new IdParameterPredicate());

        VALIDATORS.put("content", new StringParameterLengthPredicate(MAX_CONTENT_LENGTH));
        VALIDATORS.put("topic", new StringParameterLengthPredicate(MAX_CONTENT_LENGTH));
        VALIDATORS.put("section_topic", new StringParameterLengthPredicate(MAX_CONTENT_LENGTH));
        VALIDATORS.put("conference_name", new StringParameterLengthPredicate(MAX_CONTENT_LENGTH));
        VALIDATORS.put("section-topic[]", new StringParameterLengthArrayPredicate(MAX_CONTENT_LENGTH));

        VALIDATORS.put("start_date", new DateRangePredicate(LocalDateTime.parse(MIN_DATE), LocalDateTime.parse(MAX_DATE)));
        VALIDATORS.put("end_date", new DateRangePredicate(LocalDateTime.parse(MIN_DATE), LocalDateTime.parse(MAX_DATE)));
    }

    @Override
    public boolean isValid(String name, Object value) {
        if (!VALIDATORS.containsKey(name)) {
            throw new IllegalArgumentException(UNKNOWN_PARAMETER_NAME_MESSAGE.replace("?", name));
        }
        Predicate<Object> predicate = VALIDATORS.get(name);

        return predicate.test(value);
    }

}