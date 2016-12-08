/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf.model;


import javax.xml.transform.Result;
import java.sql.*;
import java.util.Date;

/**
 * @author aviva
 */
public class Score {
    private Integer score = null;
    private Integer id = null;
    private Integer questionId = null;
    private Date date = null;

    public void addScore() {

        DBConnectionBuilder myBuilder = new DBConnectionBuilder();
        Connection conn = myBuilder.getConnection();

        if (conn != null) {
            try {

                // Insert-Statement erzeugen (Fragezeichen werden später ersetzt).
                String sql = "INSERT INTO score(score, question_id, date) " +
                        "VALUES(?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, getScore());

                preparedStatement.setInt(2, getQuestionId());

                // Get current datetime
                Date date = new Date();
                // Get it in timestamp format
                long timestamp = date.getTime();
                // Use timestamp to tell sql.Date which time it is
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(timestamp);

                preparedStatement.setTimestamp(3, sqlDate);
                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()){
                    id=rs.getInt(1);
                }
                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        myBuilder.close();
    }

    public void updateScore()
    {
        if (this.id == null) return;

        DBConnectionBuilder myBuilder = new DBConnectionBuilder();
        Connection conn = myBuilder.getConnection();

        if (conn != null) {
            try {

                String sql = "UPDATE  score SET score = ?, date = ? WHERE id = ?  ";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, getScore());

                preparedStatement.setInt(3, getId());

                Date date = new Date();

                long timestamp = date.getTime();

                java.sql.Timestamp sqlDate = new java.sql.Timestamp(timestamp);

                preparedStatement.setTimestamp(2, sqlDate);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        myBuilder.close();
    }

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the questionId
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }


}

