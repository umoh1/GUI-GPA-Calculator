
//Programmer: Nsikan Umoh
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class DegreeWorksFrame extends JFrame
{
    //FIELDS

    //labels
    private JLabel code;
    private JLabel name;
    private JLabel credit;
    private JLabel grade;

    //textfields
    private JTextField tfcode;
    private JTextField tfname;
    private JTextField tfcredit;
    private JTextField tfgrade;

    private ArrayList<Course> courseList = new ArrayList();

    private JButton addCourse;
    private JButton gpa;
    private JButton rinput;
    private JButton routput;

    private JTextArea out;
    private JScrollPane sp;
    private JPanel panel;
    private double GPA;
    private Course newCourse;

    /**
     Initializes all label objects and textfields to 30 width
     */
    public void createLabels()
    {
        //initalizes the labels
        code = new JLabel("Course Code: ");
        name = new JLabel("Course Name: ");
        credit = new JLabel("Course Credit: ");
        grade = new JLabel("Course Grade: ");

        //initializes the text field to 30 width
        tfcode = new JTextField(30);
        tfname = new JTextField(30);
        tfcredit = new JTextField(30);
        tfgrade = new JTextField(30);
    }

    /**
     Initializes all buttons and registers them with respective listeners
     */
    public void createButtons()
    {
        //initialize buttons
        addCourse = new JButton("Add Course");
        addCourseListener cl = new addCourseListener();
        addCourse.addActionListener(cl);

        gpa = new JButton("Calculate GPA");
        CalGPAListener cgl = new CalGPAListener();
        gpa.addActionListener(cgl);

        rinput = new JButton("Reset Input");
        ResetInputListener ril = new ResetInputListener();
        rinput.addActionListener(ril);

        routput = new JButton("Reset Output");
        ResetOutputListener rol = new ResetOutputListener();
        routput.addActionListener(rol);
    }

    /**
     ActionListener for the addCourse button
     Reads input, creates a course object, adds course into courseList
     and appends the course to the output area
     */
    class addCourseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //extract text from text fields
            String c = tfcode.getText();
            String n = tfname.getText();
            int cr = Integer.parseInt(tfcredit.getText());
            String g = tfgrade.getText();

            //initialize the Course object
            newCourse = new Course(c,n,cr,g);

            //add the Course to the course arraylist
            courseList.add(newCourse);

            //append to the output area
            out.append(newCourse.toString()+"\n");


        }
    }

    /**
     ActionListener for the Calculate GPA button.
     Reads course credits and grades from course list,
     Adds them up, and computes the overall GPA
     */
    class CalGPAListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double sum = 0; //accumulator for sum

            double creditSum = 0; //accumulator for sum of credits

            for(int i = 0; i<courseList.size();i++)
            {
                //add up all the credits
                creditSum = courseList.get(i).getCredit() + creditSum;

                //calculate sum based off of letter grades
                if(courseList.get(i).getGrade().equals("A")||
                        courseList.get(i).getGrade().equals("A+"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 4);

                }
                else if(courseList.get(i).getGrade().equals("A-"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 3.7);
                }
                else if(courseList.get(i).getGrade().equals("B+"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 3.3);
                }
                else if(courseList.get(i).getGrade().equals("B"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 3);
                }
                else if(courseList.get(i).getGrade().equals("B-"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 2.7);
                }
                else if(courseList.get(i).getGrade().equals("C+"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 2.3);
                }
                else if(courseList.get(i).getGrade().equals("C"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 2);
                }
                else if(courseList.get(i).getGrade().equals("C-"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 1.7);
                }
                else if(courseList.get(i).getGrade().equals("D+"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 1.3);
                }
                else if(courseList.get(i).getGrade().equals("D"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 1);
                }
                else if(courseList.get(i).getGrade().equals("E"))
                {
                    sum = sum + (courseList.get(i).getCredit() * 0);
                }
                else
                {
                    System.out.println("Grade is not in letter format.");
                }
            }

            //calculate the gpa
            GPA = (sum)/(creditSum);

            out.append("\nGPA: " + GPA);
        }

    }

    /**
     ActionListener for the Reset Input button
     Sets all fields to an empty string to reset input
     */
    class ResetInputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //reset all input fields
            tfcode.setText("");
            tfname.setText("");
            tfcredit.setText("");
            tfgrade.setText("");
        }
    }

    /**
     Action Listener for Reset Output button
     Resets everything in output field and leaves the headings
     */
    class ResetOutputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //clear output
            out.setText("Code\tName\tCredit\tGrade\n");
        }
    }

    /**
     Makes the Output Area. Initialies the text area and scroll pane
     */
    public void outputArea()
    {
        //initialize output textarea and scrollbar
        out = new JTextArea(10,35);
        sp = new JScrollPane(out);
        out.setEditable(false);

        //set vertical scrollbar
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //set headings
        out.append("Code\t");
        out.append("Name\t");
        out.append("Credit\t");
        out.append("Grade\n");
    }

    /**
     Constructor, no args
     Calls the createComponents() method to make all components
     */
    public DegreeWorksFrame()
    {
        createComponents();
    }

    /**
     Creates the panel. Adds all components to the panel, then adds panel to
     frame
     */
    public void createPanel()
    {
        //initialize panel
        panel = new JPanel();

        //add labels to panel
        panel.add(code);
        panel.add(tfcode);

        panel.add(name);
        panel.add(tfname);

        panel.add(credit);
        panel.add(tfcredit);

        panel.add(grade);
        panel.add(tfgrade);

        //add buttons to panel
        panel.add(addCourse);
        panel.add(gpa);
        panel.add(rinput);
        panel.add(routput);

        //add output box to panel
        panel.add(sp);
        //panel.add(out);


        //add panel to frame
        add(panel);

    }

    /**
     Convert course to string form.
     */
    private void createComponents()
    {
        //create the labels
        createLabels();

        //create the buttons
        createButtons();

        //create the output area
        outputArea();

        //create the panel
        createPanel();

    }
}
