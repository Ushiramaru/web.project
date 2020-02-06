package com.epam.conference.controller.command;

import com.epam.conference.controller.command.impl.*;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.service.*;
import com.epam.conference.service.exception.ServiceException;

public class CommandFactory {

    public static Command create(String command) throws ServiceException {
        switch (command) {
            case "askPage":
                return new ShowPageCommand("/WEB-INF/ask.jsp");
            case "loginPage":
                return new ShowPageCommand("/WEB-INF/login.jsp");
            case "success":
                return new ShowPageCommand("/WEB-INF/success.jsp");
            case "mainPage":
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case "login":
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "ask":
                return new AskCommand(new QuestionService(new DaoHelperFactory()));
            case "answerAdmin":
                return new AnswerAdminCommand(new AnswerService(new DaoHelperFactory()));
            case "answerPage":
                return new AnswerPageCommand(new QuestionService(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
            case "applyPage":
                return new ApplyPageCommand(new SectionService(new DaoHelperFactory()));
            case "apply":
                return new ApplyCommand(new RequestService(new DaoHelperFactory()));
            case "request":
                return new RequestCommand(new RequestService(new DaoHelperFactory()));
            case "cancelRequest":
                return new CancelRequestCommand(new RequestService(new DaoHelperFactory()));
            case "conference":
                return new ConferenceCommand(new ConferenceService(new DaoHelperFactory()));
            case "conferenceInfo":
                return new ConferenceInfoCommand(new SectionService(new DaoHelperFactory()), new ConferenceService(new DaoHelperFactory()));
            case "question":
                return new QuestionCommand(new QuestionService(new DaoHelperFactory()));
            case "questionAdmin":
                return new QuestionAdminCommand(new QuestionService(new DaoHelperFactory()));
            case "answer":
                return new AnswerCommand(new AnswerService(new DaoHelperFactory()), new QuestionService(new DaoHelperFactory()));
            default:
                throw new ServiceException("Unknown command " + command);
        }
    }

}