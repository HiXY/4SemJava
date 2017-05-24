class Controller
{
    Model model = new Model ();
    public void parabola (Data data)
    {
        data.to_nums ();
        model.apply (data);
        data.to_strings ();
    }
}
