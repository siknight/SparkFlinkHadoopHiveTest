package 汽车.销量预测;


import org.junit.Test;

public class PredictTest {
    /**
     *移动平均算法有个缺点，在递增时，预测的总是比最后一个月低
     * 在递减时，预测的总是比最后一个月高
     * 在平稳时差不多
     */
    @Test
    public  void test(){
        //递增奥迪Q2L预测：
        String str01="1:949,2:1029,3:1146,4:1235,5:1348,6:1421,7:1436,8:1508,9:1525,10:1556,11:1676,12:1879";
        System.out.println("奥迪Q2L下月销量="+JavaSalePredict1.extMonthSalePridict(str01));

        //递减奔驰EQC:1:1771,2:1602,3:1553,4:1552,5:1430,6:1438,7:1327,8:1325,9:1316,10:1188,11:1127,12:1031
        String str02="1:1771,2:1602,3:1553,4:1552,5:1430,6:1438,7:1327,8:1325,9:1316,10:1188,11:1127,12:1031";
        System.out.println("奔驰EQC下月销量="+ JavaSalePredict1.extMonthSalePridict(str02));

        //平稳奥迪A6L预测：
        String str03="1:1434,2:1352,3:1388,4:1392,5:1437,6:1415,7:1404,8:1391,9:1381,10:1313,11:1372,12:1373";
        System.out.println("奥迪A6L下月销量="+JavaSalePredict1.extMonthSalePridict(str03));

    }
}
