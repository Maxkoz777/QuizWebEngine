package com.example.quizwebengine.service.impl;

import com.example.quizwebengine.exceptions.QuizException;
import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.opentdb.service.DailyQuizScheduler;
import com.example.quizwebengine.repository.QuizRepository;
import com.example.quizwebengine.repository.UserRepository;
import com.example.quizwebengine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.quizwebengine.constants.ExceptionsTextConstants.NO_QUIZ_WITH_SUCH_ID;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final DailyQuizScheduler dailyQuizScheduler;

    @Override
    public long createQuiz(Quiz quiz, Long userId) throws QuizException {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new QuizException(NO_QUIZ_WITH_SUCH_ID));
        quiz.setUser(user);
        return quizRepository.save(quiz).getId();
    }

    @Override
    public Quiz getDataAboutQuiz(long quizId) throws QuizException {
        return quizRepository.findById(quizId).orElseThrow(() -> new QuizException(NO_QUIZ_WITH_SUCH_ID));
    }

    @Override
    public void updateQuizData(long quizId, String name) throws QuizException {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizException(NO_QUIZ_WITH_SUCH_ID));
        quiz.setName(name);
        quizRepository.save(quiz);
    }

    @Override
    public void deleteQuizData(long quizId) throws QuizException {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizException(NO_QUIZ_WITH_SUCH_ID));
        quizRepository.delete(quiz);
    }

    @Override
    public List<Quiz> getListOfQuizzes(long userId) throws QuizException {
        return quizRepository.findAllByUserId(userId).orElseThrow(() -> new QuizException(NO_QUIZ_WITH_SUCH_ID));
    }

    @Override
    public Quiz getDailyQuiz() {
        return dailyQuizScheduler.getDailyQuiz();
    }

}
