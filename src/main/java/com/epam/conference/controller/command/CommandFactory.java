package com.epam.conference.controller.command;

import com.epam.conference.controller.command.impl.*;
import com.epam.conference.controller.command.impl.admin.*;
import com.epam.conference.controller.command.impl.user.*;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractorImpl;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidatorImpl;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.service.exception.ServiceException;
import com.epam.conference.service.impl.*;

public class CommandFactory {

    private final static String ASK_JSP = "/WEB-INF/ask.jsp";
    private final static String LOGIN_JSP = "/WEB-INF/login.jsp";
    private final static String SUCCESS_JSP = "/WEB-INF/success.jsp";
    private final static String MAIN_JSP = "/WEB-INF/main.jsp";
    private final static String CREATE_CONFERENCE_PAGE_JSP = "/WEB-INF/createConferencePage.jsp";
    private final static String UNKNOWN_COMMAND = "Unknown command ";

    public static Command create(String command) throws ServiceException {
        DaoHelperFactory helperFactory = new DaoHelperFactory();
        RequestParameterExtractor parameterExtractor = new RequestParameterExtractorImpl();
        RequestParameterValidator parameterValidator = new RequestParameterValidatorImpl();
        switch (command) {
            case "language":
                return new LanguageCommand(parameterExtractor, parameterValidator);
            case "logout":
                return new LogoutCommand();
            case "askPage":
                return new ShowPageCommand(ASK_JSP);
            case "loginPage":
                return new ShowPageCommand(LOGIN_JSP);
            case "success":
                return new ShowPageCommand(SUCCESS_JSP);
            case "mainPage":
                return new ShowPageCommand(MAIN_JSP);
            case "createConferencePage":
                return new ShowPageCommand(CREATE_CONFERENCE_PAGE_JSP);
            case "userAdmin":
                return new UserAdminCommand(new UserServiceImpl(helperFactory));
            case "login":
                return new LoginCommand(parameterExtractor, parameterValidator, new UserServiceImpl(helperFactory));
            case "ask":
                return new AskCommand(parameterExtractor, parameterValidator, new QuestionServiceImpl(helperFactory));
            case "block":
                return new BlockCommand(parameterExtractor, parameterValidator, new UserServiceImpl(helperFactory));
            case "unblock":
                return new UnblockCommand(parameterExtractor, parameterValidator, new UserServiceImpl(helperFactory));
            case "answerAdmin":
                return new AnswerAdminCommand(parameterExtractor, parameterValidator,
                        new AnswerServiceImpl(helperFactory));
            case "answerPage":
                return new AnswerPageCommand(parameterExtractor, parameterValidator,
                        new QuestionServiceImpl(helperFactory));
            case "requestAdmin":
                return new RequestAdminCommand(new RequestServiceImpl(helperFactory));
            case "requestReject":
                return new RequestRejectCommand(parameterExtractor, parameterValidator,
                        new RequestServiceImpl(helperFactory));
            case "requestAccept":
                return new RequestAcceptCommand(parameterExtractor, parameterValidator,
                        new RequestServiceImpl(helperFactory));
            case "conferenceAdmin":
                return new ConferenceAdminCommand(new ConferenceServiceImpl(helperFactory));
            case "conferenceUnblock":
                return new ConferenceUnblockCommand(parameterExtractor, parameterValidator,
                        new ConferenceServiceImpl(helperFactory));
            case "conferenceBlock":
                return new ConferenceBlockCommand(parameterExtractor, parameterValidator,
                        new ConferenceServiceImpl(helperFactory));
            case "sectionEdit":
                return new SectionEditCommand(parameterExtractor, parameterValidator,
                        new SectionServiceImpl(helperFactory));
            case "applyPage":
                return new ApplyPageCommand(parameterExtractor, parameterValidator,
                        new SectionServiceImpl(helperFactory));
            case "request":
                return new RequestCommand(new RequestServiceImpl(helperFactory));
            case "cancelRequest":
                return new CancelRequestCommand(parameterExtractor, parameterValidator,
                        new RequestServiceImpl(helperFactory));
            case "createConference":
                return new CreateConferenceCommand(parameterExtractor, parameterValidator,
                        new ConferenceServiceImpl(helperFactory));
            case "conference":
                return new ConferenceCommand(new ConferenceServiceImpl(helperFactory));
            case "question":
                return new QuestionCommand(new QuestionServiceImpl(helperFactory));
            case "questionAdmin":
                return new QuestionAdminCommand(new QuestionServiceImpl(helperFactory));
            case "answer":
                return new AnswerCommand(parameterExtractor, parameterValidator, new AnswerServiceImpl(helperFactory));
            case "conferenceInfoAdmin":
                return new ConferenceInfoAdminCommand(parameterExtractor, parameterValidator,
                        new ConferenceServiceImpl(helperFactory), new SectionServiceImpl(helperFactory));
            case "apply":
                return new ApplyCommand(parameterExtractor, parameterValidator, new RequestServiceImpl(helperFactory),
                        new SectionServiceImpl(helperFactory));
            case "conferenceInfo":
                return new ConferenceInfoCommand(parameterExtractor, parameterValidator,
                        new SectionServiceImpl(helperFactory), new ConferenceServiceImpl(helperFactory));
            default:
                throw new ServiceException(UNKNOWN_COMMAND + command);
        }
    }

}