 package cuoi_ky;

import java.util.ArrayList;

public class Vat extends HinhHop {

  public Vat(ArrayList<Diem> toaDo) {
    super(toaDo);
  }

  public static void main(String[] args) {
  }

  public boolean isThuocPhong(Phong p) {
    for (Diem d : toaDo) {
      if (d.isThuocTrongHinhHop(p) || d.isThuocMatPhangHinhHop(p)) {
        return true;
      }
    }
    return false;
  }

  public boolean isHopLe(Phong p) {

    if (this.isThuocPhong(p)) {
      if (p.cac_vat.size() > 0) {
        for (Vat vat : p.cac_vat) {
          // kiem tra 8 diem cua vat can them co thuoc vat nao khong
          for (Diem d : this.toaDo) {
            if (d.isThuocTrongHinhHop(vat)) {
              return false;
            }
          }
          // kiem tra 8 diem cua vat da them co thuoc vat can them khong
          for (Diem d : vat.toaDo) {
            // System.out.println(d.isThuocTrongHinhHop(this));
            if (d.isThuocTrongHinhHop(this)) {
              return false;
            }
          }
        }
      }

      if (p.toaDo.get(0).z == this.toaDo.get(0).z) {
        return true;
      }
      if (p.cac_vat.size() > 0) {
        for (Vat vat : p.cac_vat) {
          if (vat.toaDo.get(4).z == this.toaDo.get(0).z) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
