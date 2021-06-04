package cuoi_ky;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class NewMain {

    public static ArrayList<Diem> td_phong = new ArrayList<Diem>();

    public static ArrayList<Vat> cac_vat = new ArrayList<Vat>();

    public static ArrayList<Camera> cac_camera = new ArrayList<Camera>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readFile();
        Phong p = new Phong();
        if (Phong.isHinhHop(td_phong)) {
            p = new Phong(td_phong);
        }
        khoiTaoPhong(p, cac_vat, cac_camera);

        int sl = 1;

        while (sl != 3) {
            System.out.println("---------------MENU------------- \n");
            System.out.println("1. Kiem tra 1 diem\n");
            System.out.println("2. In hinh chieu \n");
            System.out.println("3. Thoat \n");
            System.out.println("-------------------------------- \n");
            System.out.println("Nhap lua chon:\n");
            sl = sc.nextInt();

            switch (sl) {
                case 1:
                    double x, y, z;
                    System.out.println("Nhap toa do diem:\n");
                    x = sc.nextFloat();
                    y = sc.nextFloat();
                    z = sc.nextFloat();
                    Diem d = new Diem(x, y, z);
                    kiemTraDiem(p, d);
                    break;
                default:
                    break;
            }
        }

        sc.close();

    }

    public static void readFile() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            String toa_do_phong = myReader.nextLine();
            td_phong = nhapToaDoHinhHop(toa_do_phong);

            int tong_vat = Integer.parseInt(myReader.nextLine());
            while (tong_vat != 0) {
                String toa_do_vat = myReader.nextLine();
                ArrayList<Diem> td_vat = new ArrayList<Diem>();
                td_vat = nhapToaDoHinhHop(toa_do_vat);
                if (Vat.isHinhHop(td_vat)) {
                    Vat vat = new Vat(td_vat);
                    cac_vat.add(vat);
                } else {
                    System.out.println("Vat " + tong_vat + " khong hop le.");
                }
                tong_vat--;
            }

            int tong_camera = Integer.parseInt(myReader.nextLine());
            while (tong_camera != 0) {
                String toa_do_camera = myReader.nextLine();
                cac_camera.add(nhapToaDoCamera(toa_do_camera));
                tong_camera--;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Diem> nhapToaDoHinhHop(String toa_do_hinh_hop) {
        toa_do_hinh_hop = toa_do_hinh_hop.replaceAll("[(),]", "");
        String[] split = toa_do_hinh_hop.split(" ");
        ArrayList<Diem> d = new ArrayList<Diem>();
        for (int i = 0; i < split.length; i += 3) {
            d.add(new Diem(Float.parseFloat(split[i]), Float.parseFloat(split[i + 1]), Float.parseFloat(split[i + 2])));
        }
        return d;
    }

    public static Camera nhapToaDoCamera(String toa_do_camera) {
        toa_do_camera = toa_do_camera.replaceAll("[(),]", "");
        String[] split = toa_do_camera.split(" ");
        Diem td = new Diem(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]));
        Camera camera = new Camera(td, Float.parseFloat(split[3]), Float.parseFloat(split[4]));
        return camera;

    }

    public static void khoiTaoPhong(Phong p, ArrayList<Vat> cac_vat, ArrayList<Camera> cac_camera) {
        for (Vat vat : cac_vat) {
            if (vat.isHopLe(p)) {
                p.addVat(vat);
            }
        }
        for (Camera camera : cac_camera) {
            if (camera.isHopLe(p)) {
                p.addCamera(camera);
            }
        }
    }

    public static void kiemTraDiem(Phong p, Diem d) {
        if (!d.isThuocTrongHinhHop(p) && !d.isThuocMatPhangHinhHop(p)) {
            System.out.println("Diem khong thuoc phong\n");
            return;
        }
        int indexCam = 0;
        for (Camera camera : p.cac_camera) {
            indexCam++;
            if (MatPhang.diemSangToi(d, camera, p)) {
                int indexVat = 0;
                if (p.cac_vat.size() > 0) {
                    for (Vat vat : p.cac_vat) {
                        indexVat++;
                        if (d.isThuocTrongHinhHop(vat)) {
                            System.out.printf("Diem  dang nam trong vat So %d\n", indexVat);
                            return;
                        }
                        if (d.isThuocMatPhangHinhHop(vat)) {
                            System.out.printf("Diem  dang nam tren be mat vat So %d\n", indexVat);
                            return;
                        }
                        double[][] ptdt = MatPhang.phuongTrinhDuongThang(d, camera.td);
                        int[][] temp = { { 0, 1, 4 }, { 0, 2, 4 }, { 3, 2, 7 }, { 3, 4, 7 } };

                        for (int i = 0; i < temp.length; i++) {
                            double[] ptmp = MatPhang.phuongTrinhMatPhang(vat.toaDo.get(temp[i][0]),
                                    vat.toaDo.get(temp[i][1]), vat.toaDo.get(temp[i][2]));
                            ptdt[3] = ptmp;
                            double[] td_giao_diem = HePhuongTrinh.giaiHe(ptdt);

                            Diem giao_diem = new Diem(td_giao_diem[0], td_giao_diem[1], td_giao_diem[2]);

                            if (!giao_diem.isThuocMatPhangHinhHop(vat)) {
                                System.out.printf("Diem thuoc vung nhin thay cua camera %d\n", indexCam);
                                return;
                            }
                            if (giao_diem.khoangCach(camera.td) >= d.khoangCach(camera.td)) {
                                System.out.printf("Diem thuoc vung nhin thay cua camera %d\n", indexCam);
                                return;
                            }

                        }
                    }

                } else {
                    System.out.println("Diem thuoc vung nhin thay\n");
                    return;
                }
            }
        }
        System.out.println("Diem thuoc vung khong nhin thay\n");

    }
}
