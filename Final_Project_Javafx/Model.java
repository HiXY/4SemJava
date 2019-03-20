import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

class Model
{
    Float g = new Float (9.81);
    public void apply (Data data)
    {
        data.points.clear ();
        data.height = data.vy * data.vy / (2 * g);
        data.time = 2 * data.vy / g;
        for (int i = 0; i < data.pts; i++)
        {
            float t = (float)i * data.time / data.pts;
            data.points.add (new XYChart.Data (data.vx * t, data.vy * t - g * t * t / 2));
        }
            data.points.add (new XYChart.Data (data.vx * data.time, 0));
        
    }
}
