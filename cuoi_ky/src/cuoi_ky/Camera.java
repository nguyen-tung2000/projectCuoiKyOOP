package cuoi_ky;

public class Camera {
    public Diem td;
    public double goc_cao;
    public double goc_rong;

    public Camera(Diem td, double goc_rong, double goc_cao) {
        this.td = td;
        this.goc_rong = goc_rong;
        this.goc_cao = goc_cao;
    }

    public boolean isHopLe(Phong p) {
        for (Diem d : p.toaDo) {
            if (d.x == this.td.x && d.y == this.td.y && d.z == this.td.z)
                return false;
        }
        if ((goc_cao <= 90 && goc_cao > 0) && (goc_rong <= 90 && goc_rong > 0) && td.isThuocMatPhangHinhHop(p)) {
            return true;
        }
        return false;
    }
}
