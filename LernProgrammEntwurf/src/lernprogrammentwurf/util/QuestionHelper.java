/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf.util;

import lernprogrammentwurf.model.DBConnectionBuilder;
import lernprogrammentwurf.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author aviva
 */
public class QuestionHelper {
    private static List<Integer> getQuestionIdsByCategoryAndLevel(String category, int level) {
        List<Integer> questionIds = new ArrayList<>();
        try {

            DBConnectionBuilder myBuilder = new DBConnectionBuilder();
            Connection conn = myBuilder.getConnection();

            String query = "SELECT id FROM questions WHERE category=? AND level=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, level);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questionIds.add(rs.getInt("id"));
            }


        } catch (SQLException ex) {
            Logger.getLogger(QuestionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionIds;
    }

    private static Integer selectRandomQuestionId(List<Integer> ids) {
        int index = (int) (Math.random() * ids.size());

        if (ids.size() > 0) {
            return ids.get(index);
        }

        return null;
    }

    public static Question getRandomQuestion(String category, int level) {
        List<Integer> questionIds = getQuestionIdsByCategoryAndLevel(category, level);
        Integer randomId = selectRandomQuestionId(questionIds);

        if (randomId == null) return null;

        Question randomQuestion = new Question();
        randomQuestion.getById(randomId);

        return randomQuestion;
    }
}

