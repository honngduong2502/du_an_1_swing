package helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MsgBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "C&T", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "C&T", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "C&T", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void exit(Component parent) {

        if (MsgBox.confirm(parent, "Bạn chắc chắn muốn thoát khỏi chương trình")) {
            System.exit(0);
        }
    }
}
