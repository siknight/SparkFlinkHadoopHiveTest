package 汽车.销量预测;

import java.util.Arrays;
/**
 * 数据格式 1:1000 2:2000
 * 预测下月销量
 *    算法模型 1 2 3 4 5 6 7 8 9 10 11 12
 *   加权移动平均（1000*1+2000*2+3000*3+...12000*12）/(1+2+3...+12)
 *   y=ax+b
 */
public  class JavaSalePredict3 {

    //perMonthSale数据样式：1:1434,2:1352,3:1388,4:1392,
    public static int extMonthSalePridict(String perMonthSale) {

        //split[0]=12:1373  1:1000...
        String[] split = perMonthSale.split(",");
        //用于计算加权平均算法的分母
//        System.out.println(split.length);
        //用于计算加权平均算法的分母
        int sum=0;
        //用于计算加权平均算法的分子
        int sumPerCarSaleAmount=0;
        for (int i=0;i<split.length;i++){
            //monthAndSales[0]表示月，monthAndSales[1]表示月销量
            String[] monthAndSales = split[i].split(":");
            sum+=Integer.parseInt(monthAndSales[0]);
//            System.out.println("sum="+sum);
            sumPerCarSaleAmount+=(Integer.parseInt(monthAndSales[1])*(i+1));
        }

        int result =sumPerCarSaleAmount / sum ;
        return  result;
    }
}
