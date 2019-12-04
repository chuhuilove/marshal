package com.chuhui.marshal.examplserver.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderController
 * <p>
 *
 * @author: 纯阳子
 * @date: 2019/12/3
 */
@RestController
public class OrderController {

    @GetMapping("/orderService/{userName}")
    public List<UserOrderModel> orderService(@PathVariable String userName) {
        List<UserOrderModel> models = new ArrayList<>();
        models.add(modelGenerator(userName, 0));
        models.add(modelGenerator(userName, 1));
        models.add(modelGenerator(userName, 2));
        models.add(modelGenerator(userName, 3));
        models.add(modelGenerator(userName, 4));

        return models;
    }

    static UserOrderModel modelGenerator(String userName, int index) {
        UserOrderModel model = new UserOrderModel();
        model.setUserName(userName);
        model.setUserId((long) index);
        model.setCommodityName(commodityNames[index]);
        model.setShopId(shopIds[index]);
        model.setOrderCreateTime(System.nanoTime());

        return model;
    }


    private static String[] commodityNames = new String[]{"杜蕾斯", "洗头膏", "剃须泡", "香皂", "纸巾"};
    private static Long[] shopIds = new Long[]{1L, 2L, 3L, 4L, 5L};


    @Getter
    @Setter
    private static class UserOrderModel {

        /**
         * 用户名
         */
        private String userName;
        /**
         * 用户id
         */
        private Long userId;
        /**
         * 商品名
         */
        private String commodityName;
        /**
         * 订单创建时间
         */
        private Long orderCreateTime;
        /**
         * 店铺id
         */
        private Long shopId;

    }

}
