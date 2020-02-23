/**
 * 3-x мерный класс точки.
 **/
class Point3d {
    /**
     * координаты X,Y,Z
     **/
    private double xCoord;
    private double yCoord;
    private double zCoord;

    /**
     * Конструктор инициализации
     **/
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    /**
     * Конструктор по умолчанию.
     **/
    public Point3d() {
        this(0, 0, 0);
    }

    /**
     * Возвращение координаты X,Y,Z
     **/
    public double getX() {
        return xCoord;
    }
    public double getY() {
        return yCoord;
    }
    public double getZ() {
        return zCoord;
    }

    /**
     * Установка значения координаты X,Y,Z
     **/
    public void setX(double val) {
        xCoord = val;
    }
    public void setY(double val) {
        yCoord = val;
    }
    public void setZ(double val) {
        zCoord = val;
    }

    /**
     * Проверяет совпадение точек
     **/
    public static boolean eq(Point3d a, Point3d b) {
        return (a.getX()==b.getX()) && (a.getY()==b.getY()) && (a.getZ()==b.getZ());
    }
    /**
     * Высчитывает расстояние
     **/
    public static double distanceTo(Point3d a, Point3d b) {
        double dist = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getY() - b.getY(), 2));
        return Math.abs(dist);
    }
}
