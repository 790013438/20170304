package javagame.intersection;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Fragment {
    public static void main (String[] args) {
    }

    static class Average {
        public void method () {
            SwingUtilities.invokeLater(new Runnable() {
                public void run () {
                    double[][] scoreArray = new double[3][4];
                    double sum = 0;
                    double averageScore = 0;
                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 4; ++j) {
                            String enterString = JOptionPane.showInputDialog("请输入" + (i + 1) + "班，第" + (j + 1) + "个学生的成绩：");
                            if (enterString != null) {
                                try {
                                    scoreArray[i][j] = Double.parseDouble(enterString);
                                } catch(Exception e) {}
                            }
                            sum += scoreArray[i][j];
                        }
                    }
                    averageScore = sum / (scoreArray[0].length * scoreArray.length);
                    JOptionPane.showMessageDialog(null, "平均成绩：" + averageScore);
                    System.out.println("平均分" + averageScore);
                }
            });
        }
    }
}
