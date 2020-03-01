package com.epam.conference.service.impl;

import com.epam.conference.dao.AnswerDao;
import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Question;
import com.epam.conference.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class AnswerServiceImplTest {

    private final static Long ANSWER_ID = 1L;
    private final static Long NON_ANSWER_ID_VALUE = null;
    private final static Long WITH_ANSWER_QUESTION_ID = 1L;
    private final static Long WITHOUT_ANSWER_QUESTION_ID = 2L;
    private final static Long QUESTION_NOT_EXIST_ID = 2L;
    private final static Long WITH_PERMISSION_USER_ID = 1L;
    private final static Long WITHOUT_PERMISSION_USER_ID = 2L;

    private static AnswerServiceImpl service;

    @BeforeClass
    public static void setUp() throws DaoException {
        DaoHelperFactory factory = Mockito.mock(DaoHelperFactory.class);

        DaoHelper helper = mockDaoHelper();
        Mockito.when(factory.create()).thenReturn(helper);

        service = new AnswerServiceImpl(factory);
    }

    private static DaoHelper mockDaoHelper() throws DaoException {
        DaoHelper mock = Mockito.mock(DaoHelper.class);

        QuestionDao questionDao = mockQuestionDao();
        Mockito.when(mock.createQuestionDao()).thenReturn(questionDao);

        AnswerDao answerDao = mockAnswerDao();
        Mockito.when(mock.createAnswerDao()).thenReturn(answerDao);

        return mock;
    }

    private static QuestionDao mockQuestionDao() throws DaoException {
        QuestionDao mock = Mockito.mock(QuestionDao.class);

        Optional<Question> optionalQuestion = Optional.of(new Question(WITH_ANSWER_QUESTION_ID, WITH_PERMISSION_USER_ID,
                ANSWER_ID, null));
        Mockito.when(mock.getById(WITH_ANSWER_QUESTION_ID)).thenReturn(optionalQuestion);
        optionalQuestion = Optional.of(new Question(WITHOUT_ANSWER_QUESTION_ID, WITH_PERMISSION_USER_ID,
                NON_ANSWER_ID_VALUE, null));
        Mockito.when(mock.getById(WITHOUT_ANSWER_QUESTION_ID)).thenReturn(optionalQuestion);
        Mockito.when(mock.getById(QUESTION_NOT_EXIST_ID)).thenReturn(Optional.empty());

        return mock;
    }

    private static AnswerDao mockAnswerDao() throws DaoException {
        AnswerDao mock = Mockito.mock(AnswerDao.class);

        Optional<AnswerDto> optionalAnswerDto = Optional.of(new AnswerDto(ANSWER_ID, null, null));
        Mockito.when(mock.getFullInfoById(ANSWER_ID)).thenReturn(optionalAnswerDto);
        Mockito.when(mock.getFullInfoById(NON_ANSWER_ID_VALUE)).thenReturn(Optional.empty());

        return mock;
    }

    @Test
    public void getByQuestionAndUserIdShouldBeReturnNonEmptyOptionalWhenQuestionAndAnswerExist() throws ServiceException {
        Optional<AnswerDto> optional = service.getByQuestionAndUserId(WITH_ANSWER_QUESTION_ID, WITH_PERMISSION_USER_ID);
        boolean actual = optional.isPresent();
        Assert.assertTrue(actual);
    }

    @Test
    public void getByQuestionAndUserIdShouldBeReturnEmptyOptionalWhenQuestionExistAndAnswerNotExist() throws ServiceException {
        Optional<AnswerDto> optional = service.getByQuestionAndUserId(WITHOUT_ANSWER_QUESTION_ID, WITH_PERMISSION_USER_ID);
        boolean actual = optional.isPresent();
        Assert.assertFalse(actual);
    }

    @Test
    public void getByQuestionAndUserIdShouldBeReturnEmptyOptionalWhenQuestionNotExist() throws ServiceException {
        Optional<AnswerDto> optional = service.getByQuestionAndUserId(QUESTION_NOT_EXIST_ID, WITH_PERMISSION_USER_ID);
        boolean actual = optional.isPresent();
        Assert.assertFalse(actual);
    }

    @Test
    public void getByQuestionAndUserIdShouldBeReturnEmptyOptionalWhenUserNotHavePermission() throws ServiceException {
        Optional<AnswerDto> optional = service.getByQuestionAndUserId(WITH_ANSWER_QUESTION_ID, WITHOUT_PERMISSION_USER_ID);
        boolean actual = optional.isPresent();
        Assert.assertFalse(actual);
    }

}