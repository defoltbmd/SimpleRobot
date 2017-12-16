import controller.MainFormController;
import view.MainForm;
import complexnumber.СomplexNumber;

public class Main {

    public static void main(String[] args) {
        MainForm menuForm = new MainForm();
        menuForm.delegate = new MainFormController();

        СomplexNumber a = new СomplexNumber(1, 1);
        СomplexNumber b = new СomplexNumber(1, 1);
        СomplexNumber c = new СomplexNumber(1, 2);

// a.equals(b) must return true
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));

// a.hashCode() must be equal to b.hashCode()
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }
}
