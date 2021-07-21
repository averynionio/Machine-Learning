package CS4802;
import java.io.*;
import java.util.ArrayList;
public class ML_IO {
    public static void step1(String fileName) {
        ArrayList<Double> x = new ArrayList();
        ArrayList<Double> y = new ArrayList();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            int n;
            while ((n = fis.available()) > 0) {
                byte[] b = new byte[n];
                int result = fis.read(b);
                if (result == -1) break;
                String s = new String(b);
                s = s.replaceAll("\n"," ");
                String[] s2 = s.split(" ");
                for(int j = 0; j < s2.length ; j+=2){
                    x.add(Double.parseDouble(s2[j]));
                    y.add(Double.parseDouble(s2[j+1]));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not find file " + fileName);
        }
        catch (IOException e) {
            System.err.println(e);
        }
        step2(x,y,x.size());
    }
    public static void step2(ArrayList<Double> x, ArrayList<Double> y,int n){
        ArrayList<Double> xy = new ArrayList();
        ArrayList<Double> xx = new ArrayList();
        for(int i = 0; i < n;i++){
            xy.add(x.get(i)*y.get(i));
            xx.add(x.get(i)*x.get(i));
        }
        step3(x,y,xx,xy,n);
    }
    public static void step3(ArrayList<Double> x, ArrayList<Double> y,
                             ArrayList<Double> xx,ArrayList<Double> xy, int n){
        Double sum_x = 0.0, sum_y = 0.0, sum_xx = 0.0, sum_xy = 0.0;
        for(int i = 0; i < n;i++){
            sum_x += x.get(i);
            sum_y += y.get(i);
            sum_xx += xx.get(i);
            sum_xy += xy.get(i);
        }
        step4(sum_x,sum_y,sum_xx,sum_xy,n);
    }
    public static void step4(Double sum_x, Double sum_y,
                             Double sum_xx,Double sum_xy, int n){
        Double b = (n*sum_xy-(sum_x*sum_y))/(n*sum_xx-(sum_x*sum_x));
        step5(sum_x,sum_y,b,n);
    }
    public static void step5(Double sum_x, Double sum_y,Double b, int n){
        Double a = (sum_y-(b*sum_x))/n;
        step6(a,b,n);
    }
    public static void step6(Double a,Double b,int n){
        Double Y = a+b*64;
        System.out.println("X = 64, Y = "+Y);
    }

    public static void main(String args[]) {
        step1("input.txt");
    }
}
