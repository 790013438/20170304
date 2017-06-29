package javagame.intersection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Day3 {
    
    public static void main (String[] args) {
        First first = new First();
        first.method();
    }
    
    static class First {
        public void method () {
            int sum = 0;
            int i = 0;
            while (sum < 20) {
                i++;
                sum += i;
            }
            System.out.println(i);
        }
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
