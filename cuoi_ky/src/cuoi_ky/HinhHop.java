package cuoi_ky;

import java.util.ArrayList;

public class HinhHop {
    public ArrayList<Diem> toaDo = new ArrayList<Diem>();
    public double width, height, length;

    public HinhHop() {

    }

    public HinhHop(ArrayList<Diem> toaDo) {
        this.toaDo = toaDo;
        this.width = toaDo.get(0).khoangCach(toaDo.get(1));
        this.length = toaDo.get(0).khoangCach(toaDo.get(3));
        this.height = toaDo.get(0).khoangCach(toaDo.get(4));

    }

    public static boolean isHinhChuNhat(Diem d1, Diem d2, Diem d3, Diem d4) {
        if (!(d1.z == d2.z && d2.z == d3.z && d3.z == d4.z)) {
            return false;
        }
        double C1, C2, C3, C4, C5;
        C1 = d1.khoangCach(d2);
        C2 = d2.khoangCach(d3);
        C3 = d3.khoangCach(d4);
        C4 = d1.khoangCach(d4);

        C5 = d1.khoangCach(d3);

        if (C1 != C3 || C2 != C4)
            return false;

        return C5 == C1 + C2;
    }

    public static boolean isHinhHop(ArrayList<Diem> toaDo) {
        if (!isHinhChuNhat(toaDo.get(0), toaDo.get(1), toaDo.get(2), toaDo.get(3))
                || !isHinhChuNhat(toaDo.get(4), toaDo.get(5), toaDo.get(6), toaDo.get(7))) {
            return false;
        }

        if (!toaDo.get(0).isThangDung(toaDo.get(4)) || !toaDo.get(1).isThangDung(toaDo.get(5))
                || !toaDo.get(2).isThangDung(toaDo.get(6)) || !toaDo.get(3).isThangDung(toaDo.get(7))) {
            return false;
        }
        return true;
    }

}
