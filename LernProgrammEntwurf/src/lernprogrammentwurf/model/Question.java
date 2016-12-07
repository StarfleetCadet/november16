/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import lernprogrammentwurf.util.QuestionHelper;
import java.util.logging.Logger;
import java.lang.Comparable;
import java.util.Arrays;

/**
 *
 * @author aviva
 */
public class Question implements IDBModel
{
    
    private int id;
    private String category = "";
    private String question ="";
    private String correctAnswer = "";
    private int level;
    public static String[] categories = {"Sozialkunde", "Java", "AWL", "LAN", 
                                        "PC-Grundlagen", "SWL", "KLR", "Datenbanken",
                                        "SQL", "E-Tech", "Linux", "HTML/CSS", "HAPCS"};
  
    
    
    
    public void addNewQuestion() 
    {
        DBConnectionBuilder myBuilder = new DBConnectionBuilder();
        // todo: use singleton instead, with method "getInstance()"
        Connection conn = myBuilder.getConnection();
        
        if(conn != null)
        {
            try
            {
                String sql = "INSERT INTO questions(category, question, correct_answer, level) " +
                             "VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, category);
                preparedStatement.setString(2, question);
                preparedStatement.setString(3, correctAnswer);
                preparedStatement.setInt(4, level);

                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        myBuilder.close();
    }
    
    public void getById(int questionId)
    {
        try{
            
            DBConnectionBuilder myBuilder = new DBConnectionBuilder();
            Connection conn = myBuilder.getConnection();
            
            String query = "SELECT id, category, question, correct_answer, level FROM questions WHERE id = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, questionId);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            rs.next();
            
            this.id = rs.getInt("id");
            this.category = rs.getString("category");
            this.correctAnswer = rs.getString("correct_answer");
            this.level = rs.getInt("level");
            this.question = rs.getString("question");

        } catch (SQLException ex) {
            Logger.getLogger(QuestionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() 
    {
       
    }

    @Override
    public void update() 
    {
        
    }

    @Override
    public void find() 
    {
        
    }

    /**
     * @return the id
     */
    public int getId() 
    {
        return id;
    }

    /**
     * @return the category
     */
    public String getCategory() 
    {
        return category;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    /**
     * @return the question
     */
    public String getQuestion() 
    {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() 
    {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) 
    {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the level
     */
    public int getLevel() 
    {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) 
    {
        this.level = level;
    }

    @Override
    public void addScore() 
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
