import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Javafx1 extends Application 
{  
	@Override
	public void start (Stage primaryStage) 
	{
        BorderPane border = new BorderPane ();
        border.setCenter (createGridPane ());

        Scene scene = new Scene (border, 400, 200);
		primaryStage.setTitle ("ver 1.0"); 
		primaryStage.setScene (scene);
        primaryStage.setResizable (false);
        primaryStage.show ();
    }
    private GridPane createGridPane ()
    {
        GridPane grid = new GridPane();
        grid.setHgap (10);
        grid.setVgap (12);
        grid.setPadding (new Insets (50, 25, 50, 25));
        
        Label inputLabel = new Label ("Input:");
        grid.add (inputLabel, 0, 0);

        final TextField input = new TextField ();
        input.getText ();
        grid.add (input, 1, 0);

        Button button = new Button("Copy");
        grid.add (button, 2, 0);
        
        Label outputLabel = new Label ("Output:");
        grid.add (outputLabel, 0, 1);

        final TextField output = new TextField ();
        output.setDisable (true);
        grid.add (output, 1, 1);

        ////Label errors = new Label ("Error");
        ////grid.add (errors, 0, 2);
            
        button.setOnAction (new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent e)
            {
                output.setDisable (false);
                try
                {
                    checkInput (input.getText ());
                }
                catch (NumException ex)  
                {
                    output.setText (ex.str);
                }
                ///output.setText (getNumbers (input.getText ()));
            }   
        });

        return grid;
    }

    private static void checkInput (String input) throws NumException
    {
        StringBuffer numbers = new StringBuffer ();
        Pattern pattern = Pattern.compile ("\\d+");
        Matcher matcher = pattern.matcher (input);
        while (matcher.find ())
        {
            numbers.append (matcher.group ());
        }
        if (numbers.length () > 0) 
            throw new NumException (numbers.toString());
    }

    static class NumException extends Exception
    {
        public String str;
        public NumException (String s)
        {
            super (s);
            str = s;
        }
    }

	public static void main (String[] args) 
	{   
		launch (args);
    }
}
