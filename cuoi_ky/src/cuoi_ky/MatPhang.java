package cuoi_ky;

public class MatPhang {

    /**
     * @param args the command line arguments
     */
    public static double[] phuongTrinhMatPhang(Diem a, Diem b, Diem c) {
        double[] mt = new double[5];
        mt[0] = ((a.y - b.y) * (a.z - c.z) - (a.z - b.z) * (a.y - c.y));
        mt[1] = ((a.z - b.z) * (a.x - c.x) - (a.x - b.x) * (a.z - c.z));
        mt[2] = ((a.x - b.x) * (a.y - c.y) - (a.y - b.y) * (a.x - c.x));
        mt[3] = (0.0);
        mt[4] = (((a.y - b.y) * (a.z - c.z) - (a.z - b.z) * (a.y - c.y)) * a.x
                + ((a.z - b.z) * (a.x - c.x) - (a.x - b.x) * (a.z - c.z)) * a.y
                + ((a.x - b.x) * (a.y - c.y) - (a.y - b.y) * (a.x - c.x)) * a.z);
        return mt;
    }

    public static double[][] phuongTrinhDuongThang(Diem a, Diem b) {
        double[][] rs = new double[4][5];
        double[] pt1 = new double[5];
        double[] pt2 = new double[5];
        double[] pt3 = new double[5];

        pt1[0] = 1.0;
        pt1[1] = 0.0;
        pt1[2] = 0.0;
        pt1[3] = (a.x - b.x);
        pt1[4] = (a.x);
        rs[0] = (pt1);

        pt2[0] = (0.0);
        pt2[1] = (1.0);
        pt2[2] = (0.0);
        pt2[3] = (a.y - b.y);
        pt2[4] = (a.y);
        rs[1] = (pt2);

        pt3[0] = (0.0);
        pt3[1] = (0.0);
        pt3[2] = (1.0);
        pt3[3] = (a.z - b.z);
        pt3[4] = (a.z);
        rs[2] = (pt3);

        return rs;
    }

    public static double absCosGocGiuaHaiVecto(Vector a, Vector b) {
        double cos = Math.abs((a.x * b.x + a.y * b.y + a.z * b.z)
                / (Math.sqrt((a.x * a.x + a.y * a.y + a.z * a.z)) * Math.sqrt((b.x * b.x + b.y * b.y + b.z * b.z))));

        return cos;
    }

    public static Vector vectoPhapTuyen(Diem a, Diem b, Diem c) {
        Vector vt = new Vector(((a.y - b.y) * (a.z - c.z) - (a.z - b.z) * (a.y - c.y)),
                ((a.z - b.z) * (a.x - c.x) - (a.x - b.x) * (a.z - c.z)),
                ((a.x - b.x) * (a.y - c.y) - (a.y - b.y) * (a.x - c.x)));
        return vt;
    }

    public static boolean diemSangToi(Diem d, Camera cam, Phong p) {
        if ((cam.td.x == 0 && cam.td.y != 0 && cam.td.z != 0)) {
            // mat 1
            Diem x1 = new Diem(0, 0, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(0, cam.td.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(0, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }

            }

        } else if (cam.td.x == p.width && cam.td.y != 0 && cam.td.z != 0) { // mat3
            Diem x1 = new Diem(p.width, 0, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(p.width, cam.td.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(0, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        } else if ((cam.td.x != 0 && cam.td.y == 0 && cam.td.z != 0)) { // mat2
            Diem x1 = new Diem(0, 0, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(cam.td.x, 0, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 0, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        } else if ((cam.td.y == p.length && cam.td.x != 0 && cam.td.z != 0 && cam.td.x < p.width // mat4
                && cam.td.z < p.height)) {
            Diem x1 = new Diem(0, p.length, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(cam.td.x, p.length, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 0, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }
        } else if (cam.td.z == p.height && cam.td.x != 0 && cam.td.y != 0) { // mat5
            Diem x1 = new Diem(0, cam.td.x, p.height);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 1, 0);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(cam.td.x, p.length, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 0, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        } else if (cam.td.x == 0 && cam.td.y == 0) { // Cam dat tren Oz
            Diem x1 = new Diem(-1, 1, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(d.x, d.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(-1, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }
        } else if (cam.td.x == p.width && cam.td.y == p.length) { // Cam dat tren doan thang co x= phong.width va y
                                                                  // =phong.length 0<z<phong.height

            Diem x1 = new Diem(cam.td.x - 1, cam.td.y + 1, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(d.x, d.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(-1, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }
        } else if (cam.td.x == 0 && cam.td.y == p.length) {
            Diem x1 = new Diem(cam.td.x + 1, cam.td.y + 1, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(d.x, d.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        } else if (cam.td.x == p.width && cam.td.y == 0) {
            Diem x1 = new Diem(cam.td.x + 1, cam.td.y + 1, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(d.x, d.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        } else if (cam.td.z == p.height && cam.td.y == 0) {
            Diem x1 = new Diem(cam.td.x + 1, cam.td.y + 1, cam.td.z);
            Vector vt1 = vectoPhapTuyen(d, x1, cam.td);
            Vector vt11 = new Vector(0, 0, 1);
            if (Math.cos(cam.goc_cao / 2) < absCosGocGiuaHaiVecto(vt1, vt11)) {
                Diem x2 = new Diem(d.x, d.y, 0);
                Vector vt2 = vectoPhapTuyen(d, x2, cam.td);
                Vector vt12 = new Vector(1, 1, 0);
                if (Math.cos(cam.goc_rong / 2) < absCosGocGiuaHaiVecto(vt2, vt12)) {
                    return true;
                }
            }

        }
        return false;
    }

}