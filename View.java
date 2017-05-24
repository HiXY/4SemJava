import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class View extends Application
{
    Controller controller = new Controller ();
    Data data = new Data ();
	@Override
	public void start (Stage stage)
	{
        BorderPane border = new BorderPane ();
        border.setCenter (addGraph ());
        border.setRight (addGridPane ());
        /*border.setCenter (addAnchorPane (addGridPane (), addGraph ()));*/

        Scene scene = new Scene (border, 950, 410);
        stage.setScene (scene);
        stage.setResizable (false);
        stage.setTitle ("ModLab");
        stage.show ();
    }
    
    private GridPane addGridPane () 
    {
        GridPane grid = new GridPane ();
        grid.setHgap (10);
        grid.setVgap (10);
        grid.setPadding (new Insets (10, 10, 0, 0));

        Text velX = new Text ("X-axis speed (m/s) :");
        velX.setFont (Font.font ("Arial", FontWeight.BOLD, 16));
        grid.add (velX, 0, 1); 
        final TextField inputVx = new TextField ();
        grid.add (inputVx, 1, 1);


        Text velY = new Text ("Y-axis speed (m/s) :");
        velY.setFont (Font.font ("Arial", FontWeight.BOLD, 16));
        grid.add (velY, 0, 2);
        final TextField inputVy = new TextField ();
        grid.add (inputVy, 1, 2);

        Text points = new Text ("Amount of points :");
        points.setFont (Font.font ("Arial", FontWeight.BOLD, 16));
        grid.add (points, 0, 3);
        final TextField inputPts = new TextField ();
        grid.add (inputPts, 1, 3); 

        Text parameters = new Text ("Enter parameters :");
        parameters.setFont(Font.font ("Arial", FontWeight.BOLD, 20));
        grid.add (parameters, 0, 0);

        Text height = new Text ("Max Height :");
        height.setFont (Font.font ("Arial", FontWeight.BOLD, 16));
        grid.add (height, 0, 4);
        
        Label maxHeight = new Label ("-");
        grid.add (maxHeight, 1, 4);

        Text time = new Text ("Time :");
        time.setFont (Font.font ("Arial", FontWeight.BOLD, 16));
        grid.add (time, 0, 5);
        
        Label totalTime = new Label ("-");
        grid.add (totalTime, 1, 5);

        Label parseErrorLabel = new Label ("");
        grid.add (parseErrorLabel, 0, 8);

        Button resultButton = new Button ("Solve");
        grid.add (resultButton, 0, 15);
        resultButton.setOnAction (new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent e)
            {
                try 
                {
                    data.vx_s = inputVx.getText ();
                    data.vy_s = inputVy.getText ();
                    data.pts_s = inputPts.getText ();
                    controller.parabola (data);

                    parseErrorLabel.setText("");
                } 
                catch (NumberFormatException ex) 
                {
                    parseErrorLabel.setText("Numbers, please!");
                }
                
                maxHeight.setText(data.height_s);
                totalTime.setText(data.time_s);
            }
        });

        return grid;
    }

    private GridPane addGraph ()
    {
        GridPane gridd = new GridPane ();
        gridd.setHgap (10);
        gridd.setVgap (10);
        gridd.setPadding (new Insets (0, 10, 0, 10));
        NumberAxis x = new NumberAxis ();
        NumberAxis y = new NumberAxis ();
                                        
        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number> (x,y);
        numberLineChart.setTitle ("Graph");
        XYChart.Series series = new XYChart.Series ();
        series.setName ("Chart");
        series.setData (data.points);
        numberLineChart.getData ().add (series);
        gridd.add (numberLineChart, 0, 0);
        return gridd;

    }


    /*private AnchorPane addAnchorPane (GridPane grid, GridPane gridd) 
    {
        AnchorPane anchorpane = new AnchorPane ();
        anchorpane.getChildren ().addAll (gridd);
        AnchorPane.setTopAnchor (gridd, 8.0);
        AnchorPane.setLeftAnchor (gridd, 5.0);
        AnchorPane.setRightAnchor (grid, 5.0);
        AnchorPane.setTopAnchor (grid, 10.0);
        return anchorpane;
    }
    */
	
	public static void main (String[] args)
	{
        launch (View.class, args);
	}
}
