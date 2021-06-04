package cuoi_ky;

public class Diem {
    public double x, y, z;

    public Diem() {
    }

    public Diem(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double khoangCach(Diem g) {
        return Math.pow((x - g.x), 2) + Math.pow(y - g.y, 2) + Math.pow(z - g.z, 2);
    }

    public boolean isThangDung(Diem g) {
        return x == g.x && y == g.y;
    }

    public boolean isThuocTrongHinhHop(HinhHop hh) {
        if (this.x >= hh.toaDo.get(0).x && this.y >= hh.toaDo.get(0).y && this.z >= hh.toaDo.get(0).z
                && Math.pow(this.x - hh.toaDo.get(0).x, 2) < hh.width
                && Math.pow(this.y - hh.toaDo.get(0).y, 2) < hh.length
                && Math.pow(this.z - hh.toaDo.get(0).z, 2) < hh.height) {
            return true;
        }
        return false;
    }

    public boolean isThuocMatPhangHinhHop(HinhHop hh) {

    if ((Math.pow(this.x - hh.toaDo.get(0).x, 2) == hh.width
                || this.x - hh.toaDo.get(0).x == 0 || Math.pow(this.y - hh.toaDo.get(0).y, 2) == hh.length
                || this.y - hh.toaDo.get(0).y == 0 || Math.pow(this.z - hh.toaDo.get(0).z, 2) == hh.height
                || this.z - hh.toaDo.get(0).z == 0) && this.z <= hh.toaDo.get(4).z && this.x <= hh.toaDo.get(2).x && this.y <= hh.toaDo.get(2).y) {
            return true;
        }
        return false;
    }
}
