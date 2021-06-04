package cuoi_ky;

public class HePhuongTrinh {

  static double[] giaiHe(double[][] arr) { // tiến hành giải hệ
    int d1 = arr.length;
    int d2 = arr[0].length;
    double rs[] = new double[d1];
    if (d2 > d1 + 1)
      System.out.println("vo so nghiem.");
    else {
      double[] d = new double[d1];
      for (int i = 0; i < d1; i++)
        d[i] = arr[i][d2 - 1];
      double[][] matrix = copy(arr);
      double res = det(matrix);
      double[] dt = new double[d1];
      for (int k = 0; k < d1; k++) {
        double[][] matrix1 = copy(arr);
        for (int i = 0; i < d1; i++)
          matrix1[i][k] = d[i];
        dt[k] = det(matrix1);
      }

      boolean b = false;
      for (int i = 0; i < d1; i++) {
        if (dt[i] != 0) {
          b = true;
          break;
        }
      }

      if (b && res == 0)
        System.out.println("he vo nghiem");
      else if (!b && res == 0)
        System.out.println("vo nghiem hoac vo so nghiem");
      else {
        for (int i = 0; i < d1; i++) {
          rs[i] = (double) dt[i] / res;

        }
      }
    }
    return rs;
  }

  static double[][] copy(double[][] arr) {
    double[][] a = new double[arr.length][arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        a[i][j] = arr[i][j];
      }
    }
    return a;
  }

  static double det(double[][] arr) { // tính và trả về định thức
    if (arr.length == 1)
      return arr[0][0];
    if (arr.length == 2)
      return arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
    else {

      int res = 0;
      for (int k = 0; k < arr.length; k++) {
        double[][] smaller = new double[arr.length - 1][arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
          for (int j = 1; j < arr.length; j++) {
            if (i < k)
              smaller[i][j - 1] = arr[i][j];
            else if (i > k)
              smaller[i - 1][j - 1] = arr[i][j];
          }
        }
        int s = -1;
        if (k % 2 == 0)
          s = 1;
        res += arr[k][0] * s * det(smaller);
      }
      return res;
    }
  }
}