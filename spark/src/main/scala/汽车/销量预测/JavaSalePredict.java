package 汽车.销量预测;

import java.util.Arrays;

public class JavaSalePredict {
    /**
     * 预测下月销量
     *    算法模型
     *   加权移动平均（1000*1+2000*2+3000*3+...12000*12）/(1+2+3...+12)
     * @param args
     */
    public static void main(String[] args) {
      //数据库数据样式：12:1373,8:1391,4:1392,11:1372,9:1381,5:1437,10:1313,6:1415,1:1434,2:1352,7:1404,3:1388,
        String perMonthSale ="12:1373,8:1391,4:1392,11:1372,9:1381,5:1437,10:1313,6:1415,1:1434,2:1352,7:1404,3:1388,";
        //split[0]=12:1373  ...
        String[] split = perMonthSale.split(",");
        //用于计算加权平均算法的分母
        int sum=0;
        for (int i=0;i<split.length;i++){
            sum+=(i+1);  //1+2+3...  //分母和
        }
//        System.out.println(split.length);
//        //用于计算加权平均算法的分母
//        int sum=0;
//        //用于计算加权平均算法的分子
//        int sumPerCarSaleAmount=0;
//        for (int i=0;i<split.length;i++){
//            sum+=(i+1);  //1+2+3...
//            //monthAndSales[0]表示月，monthAndSales[1]表示月销量
//            String[] monthAndSales = split[i].split(":");
//            sumPerCarSaleAmount+=(Integer.parseInt(monthAndSales[1])*(i+1));
//        }
    }
}
