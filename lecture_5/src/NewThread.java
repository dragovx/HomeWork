// Создание второго потока.
class NewThread implements Runnable {
    Thread t;
    NewThread () {
// Создать новый, второй поток 
        t = new Thread(this, "Демонстрационный поток");
        System.out.println("Дочерний поток создан: " + t) ;
        t.start(); // Запустить поток
    }
    // Точка входа второго потока.
    public void run () {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дочерний поток: " + i) ;
                Thread.sleep (500);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Дочерний поток прерван.");
        }
        System.out.println("Дочерний поток завершен");
    }
}
class ThreadDemo {
    public static void main(String args[]) {
        new NewThread(); // создать новый поток
        try {
            for (int i = 5; i > 0; i--) {
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