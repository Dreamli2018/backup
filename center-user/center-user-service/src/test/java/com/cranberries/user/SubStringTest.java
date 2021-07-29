package com.cranberries.user;

import org.junit.jupiter.api.Test;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：store
 * @date ：Created in 2020/07/21 10:03
 * @description ：测试分隔字符串
 */
public class SubStringTest {
    @Test
    void subStringTest() {

        String categoryName = "庭院（Patio）";

//        String replace1 = categoryName.replace("（", "(");
//        String replace2 = replace1.replace("）", ")");

        String newCategoryName = categoryName.replace("（", "(").replace("）", ")");


        System.out.println(newCategoryName);

        int i = newCategoryName.indexOf("(");
        int i1 = newCategoryName.indexOf(")");

        String substring = newCategoryName.substring(i + 1, i1);

        System.out.println(substring);

    }


    @Test
    void testWhile() {
        int i = 0;
        while (i < 3) {
            try {
                System.out.println(i);
                int k = i / 0;
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Test
    void testFor() {

        for (int i = 0; i < 3; i++) {
            try {

                System.out.println(i);
                int k = i / 0;
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void replaceTest() {
        String carNo = "沪沪,:AA9999,";

//       String newCarNo = carNo.replace("沪", "");
       String newCarNo = carNo.replaceAll("[\\p{P}]", "");

        System.out.println("新车牌号:" + newCarNo);
    }
}
