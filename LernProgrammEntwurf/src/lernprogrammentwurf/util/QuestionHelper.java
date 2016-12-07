/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lernprogrammentwurf.model.DBConnectionBuilder;
import lernprogrammentwurf.model.Question;


/**
 *
 * @author aviva
 */
public class QuestionHelper 
{
    private static List<Integer> getQuestionIdsByCategoryAndLevel(String category, int level)
    {
        List<Integer> questionIds = new ArrayList<>();
        try{
            
            DBConnectionBuilder myBuilder = new DBConnectionBuilder();
            Connection conn = myBuilder.getConnection();
            
            String query ="SELECT id FROM questions WHERE category=? AND level=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,category);
            preparedStatement.setInt(2,level);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                questionIds.add(rs.getInt("id"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(QuestionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questionIds;

    }
    
    private static int selectRandomQuestionId(List<Integer> ids)
    {
        int index = (int) (Math.random()* ids.size());
        
        return ids.get(index);
    }
    
    public static Question getRandomQuestion(String category, int level)
    {
        List<Integer> questionIds = getQuestionIdsByCategoryAndLevel(category, level);
        int randomId = selectRandomQuestionId(questionIds);
        
        Question randomQuestion = new Question();
        randomQuestion.getById(randomId);
        
        return randomQuestion;
        
    }
    
}
