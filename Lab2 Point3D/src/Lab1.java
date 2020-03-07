import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point3d[] Points = new Point3d[3];
        for (int i=0;i<3;i++){
            System.out.println("Введите координату точки:");
            while (true) {
                int k=0;
                Points[i] = new Point3d();
                Points[i].setX(sc.nextInt());
                Points[i].setY(sc.nextInt());
                Points[i].setZ(sc.nextInt());
                for (int n=0;n<i;n++){
                    if (Points[i].eq(Points[n])){
                        k=1;
                    }
                }
                if (k==1){
                    System.out.println("Данная точка уже существует.");
                    System.out.println("Введите координату точки:");
                } else {
                    break;
                }
            }
            System.out.println("Координата точки по " +"х: " + Points[i].getX() + " у: "+ Points[i].getY() + " z: " + Points[i].getZ());
        }
        System.out.println("******************************************");
        for (int i=0;i<3;i++) {
            System.out.println("Координата точки "+ i + " х: " + Points[i].getX() + " у: " + Points[i].getY() + " z: " + Points[i].getZ());
        }
        System.out.println("******************************************");
        if (Point3d.computeArea(Points[0],Points[1],Points[2])>0){
            System.out.format("Площадь данного треугольнка = %.2f", Point3d.computeArea(Points[0],Points[1],Points[2]));
        } else {
            System.out.println("Данного треугольника не существует");
        }
    }
}