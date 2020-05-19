// Создание второго потока расширением Thread
 class NewThread1 extends Thread {
    NewThread1 () {
// Создать новый второй поток
        super("Демонстрационный поток");
        System.out.println("Дочерний поток: " + this);
        start(); // Запустить поток
    }
    // Точка входа второго потока.
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дочерний поток: " + i) ;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Дочерний поток прерван.");
        }
        System.out.println("Дочерний поток завершен.");
    }
}
class ExtendThread {
    public static void main(String args[]) {
        new NewThread1(); // Создать новый поток
        try {
            for (int i = 5; i > 0; i --) {
                System.out.println("Главный поток: " + i) ;
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Главный поток прерван.");
        }
        System.out.println("Главный поток завершен.");
    }
}