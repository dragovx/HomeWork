// Применение join() для ожидания завершения потоков.
class NewThread3 implements Runnable {
    String name; // имя потока
    Thread t;
    int millis;
    NewThread3(String threadname, int millis) {
        name = threadname;
        this.millis=millis;
        t = new Thread(this, name);
        System.out.println("Новый поток: " + t);
        t.start(); // Запуск потока
    }
    // Входная точка потока.
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i) ;
                Thread.sleep(millis);
            }
        }
        catch (InterruptedException e) {
            System.out.println(name + " прерван.");
        }
        System.out.println(name + " завершен.");
    }
}
class DemoJoin {
    public static void main(String args[]) {
        NewThread3 ob1 = new NewThread3("Один",1000);
        NewThread3 ob2 = new NewThread3("Два",2000);
        NewThread3 ob3 = new NewThread3("Три",3000);
        System.out.println("Поток Один запущен: " + ob1.t.isAlive());
        System.out.println("Поток Два запущен: " + ob2.t.isAlive());
        System.out.println("Поток Три запущен: " + ob3.t.isAlive());
// ожидать завершения потоков
        try {
            System.out.println("Ожидание завершения потоков.");
            ob1.t.join () ;
            ob2.t.join () ;
            ob3.t.join();
        }
        catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }
        System.out.println("Поток Один запущен: " + ob1.t.isAlive());
        System.out.println("Поток Два запущен: " + ob2.t.isAlive());
        System.out.println("Поток Три запущен: " + ob3.t.isAlive());
        System.out.println("Главный поток завершен.");
    }
}

