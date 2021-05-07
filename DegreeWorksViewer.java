
//Programmer: Nsikan Umoh
import javax.swing.*;

public class DegreeWorksViewer
{
    public static void main(String[] args)
    {
        //make instance of degree works frame
        DegreeWorksFrame frame = new DegreeWorksFrame();

        //set size to 450 p width, 400 p height
        frame.setSize(450,400);

        //set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the frame title
        frame.setTitle("Umoh DegreeWorks");

        //set the frame visible
        frame.setVisible(true);
    }
}
