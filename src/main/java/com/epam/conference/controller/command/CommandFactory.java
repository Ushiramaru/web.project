package com.epam.conference.controller.command;

import com.epam.conference.controller.command.impl.*;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.service.*;
import com.epam.conference.service.exception.ServiceException;

public class CommandFactory {

    public static Command create(String command) throws ServiceException {
        switch (command) {
            case "language":
                return new LanguageCommand();
            case "logout":
                return new LogoutCommand();
            case "askPage":
                return new ShowPageCommand("/WEB-INF/ask.jsp");
            case "loginPage":
                return new ShowPageCommand("/WEB-INF/login.jsp");
            case "success":
                return new ShowPageCommand("/WEB-INF/success.jsp");
            case "mainPage":
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case "createConferencePage":
                return new ShowPageCommand("/WEB-INF/createConferencePage.jsp");
            case "userAdmin":
                return new UserAdminCommand(new UserService(new DaoHelperFactory()));
            case "login":
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "ask":
                return new AskCommand(new QuestionService(new DaoHelperFactory()));
            case "block":
                return new BlockCommand(new UserService(new DaoHelperFactory()));
            case "unblock":
                return new UnblockCommand(new UserService(new DaoHelperFactory()));
            case "answerAdmin":
                return new AnswerAdminCommand(new AnswerService(new DaoHelperFactory()));
            case "answerPage":
                return new AnswerPageCommand(new QuestionService(new DaoHelperFactory()));
            case "requestAdmin":
                return new RequestAdminCommand(new RequestService(new DaoHelperFactory()));
            case "requestReject":
                return new RequestRejectCommand(new RequestService(new DaoHelperFactory()));
            case "requestAccept":
                return new RequestAcceptCommand(new RequestService(new DaoHelperFactory()));
            case "conferenceAdmin":
                return new ConferenceAdminCommand(new ConferenceService(new DaoHelperFactory()));
            case "conferenceUnblock":
                return new ConferenceUnblockCommand(new ConferenceService(new DaoHelperFactory()));
            case "conferenceBlock":
                return new ConferenceBlockCommand(new ConferenceService(new DaoHelperFactory()));
            case "sectionEdit":
                return new SectionEditCommand(new SectionService(new DaoHelperFactory()));
            case "applyPage":
                return new ApplyPageCommand(new SectionService(new DaoHelperFactory()));
            case "request":
                return new RequestCommand(new RequestService(new DaoHelperFactory()));
            case "cancelRequest":
                return new CancelRequestCommand(new RequestService(new DaoHelperFactory()));
            case "createConference":
                return new CreateConferenceCommand(new ConferenceService(new DaoHelperFactory()));
            case "conference":
                return new ConferenceCommand(new ConferenceService(new DaoHelperFactory()));
            case "question":
                return new QuestionCommand(new QuestionService(new DaoHelperFactory()));
            case "questionAdmin":
                return new QuestionAdminCommand(new QuestionService(new DaoHelperFactory()));
            case "conferenceInfoAdmin":
                return new ConferenceInfoAdminCommand(new ConferenceService(new DaoHelperFactory()), new SectionService(new DaoHelperFactory()));
            case "apply":
                return new ApplyCommand(new RequestService(new DaoHelperFactory()), new SectionService(new DaoHelperFactory()));
            case "conferenceInfo":
                return new ConferenceInfoCommand(new SectionService(new DaoHelperFactory()), new ConferenceService(new DaoHelperFactory()));
            case "answer":
                return new AnswerCommand(new AnswerService(new DaoHelperFactory()));
            default:
                throw new ServiceException("Unknown command " + command);
        }
    }

}