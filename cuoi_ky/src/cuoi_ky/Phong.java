package cuoi_ky;

import java.util.ArrayList;

public class Phong extends HinhHop {

  public ArrayList<Vat> cac_vat = new ArrayList<Vat>();
  public ArrayList<Camera> cac_camera = new ArrayList<Camera>();

  public Phong() {
  }

  public Phong(ArrayList<Diem> toaDo) {
    super(toaDo);
  }

  public static void main(String[] args) {
  }

  public void addVat(Vat vat) {
    this.cac_vat.add(vat);
  }

  public void addCamera(Camera camera) {
    this.cac_camera.add(camera);
  }
}
