/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import lernprogrammentwurf.model.Question;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import lernprogrammentwurf.model.Score;
import lernprogrammentwurf.util.QuestionHelper;

/**
 *
 * @author aviva
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private Label label;

    @FXML
    private ComboBox<String> categoriesComboBox;
    
    @FXML
    private ComboBox<String> chooseCategoryComboBox;
    
    @FXML
    private ComboBox<Integer> chooseLevelComboBox;

    @FXML
    private TextArea questionField;

    @FXML
    private TextArea answerField;
    
    @FXML
    private TextArea questionLearnField;
        
    @FXML
    private TextArea answerLearnField;
    
    @FXML
    private TextArea notesField;

    @FXML
    private Slider levelSlider;
            
    @FXML
    private Slider scoreSlider;
    
    private Question newQuestion = null;
      
    // If the score for the current question has been saved, this is it
    private Score currentScore = null;
    
    // The currently loaded question
    private Question currentQuestion = null;

    @FXML
    private void handleBtnAddQuestionAction(ActionEvent event)
    {
        Question toAdd = new Question();

        toAdd.setCategory(categoriesComboBox.getValue());
        toAdd.setCorrectAnswer(answerField.getText());

        toAdd.setLevel((int) (Math.round(levelSlider.getValue())));
        toAdd.setQuestion(questionField.getText());

        toAdd.addNewQuestion();

        questionField.clear();
        answerField.clear();
    }
    
    @FXML
    private void handleBtnNewQuestionAction(ActionEvent event) 
    {
        if(chooseLevelComboBox.getValue() == null || chooseCategoryComboBox.getValue() == null)
        {
            JOptionPane.showMessageDialog(null,"Bitte setze Level und Kategorie");
        }
        else
        { 
            currentQuestion = QuestionHelper.getRandomQuestion(chooseCategoryComboBox.getValue(), chooseLevelComboBox.getValue());
            questionLearnField.setText(currentQuestion.getQuestion());

            answerLearnField.clear(); 
            notesField.clear();
            
            // Set current score null so the next time someone hits the "save score" button,
            // the score will be added, not updated
            currentScore = null;
        }
    }
    @FXML
    private void handleBtnDiscoverAnswerAction(ActionEvent event) 
    {
              
        if (currentQuestion != null) 
        {        
            answerLearnField.setText(currentQuestion.getCorrectAnswer());
        }
     
    }
    
   
    @FXML
    private void handleBtnSafeScoreAction(ActionEvent event)
    {
        if(currentQuestion == null) return;
        
        if (currentScore == null) {
            // currentScore is null, that means no score has been saved for this question,
            Score toSet = new Score();
            toSet.setScore((int)(Math.round(scoreSlider.getValue())));
            toSet.setQuestionId(currentQuestion.getId());
            toSet.addScore();
            JOptionPane.showMessageDialog(null, "Score wurde gespeichert");
        } else {
            
        }

        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Arrays.sort(Question.categories);
        categoriesComboBox.getItems().addAll(Question.categories);
        chooseCategoryComboBox.getItems().addAll(Question.categories);
        chooseLevelComboBox.getItems().addAll(1, 2, 3, 4, 5);

    }


}
