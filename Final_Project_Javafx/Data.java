import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

class Data
{
    String pts_s, vx_s, vy_s, time_s, height_s;
    Integer pts;
    Float vx, vy, time, height;
    ObservableList<XYChart.Data> points = FXCollections.observableArrayList();
    public void to_nums ()
    {
        pts = Integer.parseInt (pts_s);
        vx = Float.parseFloat (vx_s);
        vy = Float.parseFloat (vy_s);
    }
    public void to_strings ()
    {   
        time_s = null;
        height_s = null;
        time_s = Float.toString (time);
        height_s = Float.toString (height);
    }
}
