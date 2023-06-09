package com.ts.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @Author: tsai
 * @Date: 2022-04-18 10:11
 **/
public class SystemConstant {

    @Getter
    @AllArgsConstructor
    public enum SessionStatusEnum {

        ON(1, "进行中"),
        OFF(2, "已关闭");

        private Integer code;
        private String msg;

        public static SessionStatusEnum toType(int value) {
            return Stream.of(SessionStatusEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (SessionStatusEnum c : SessionStatusEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum OrderStatusEnum {

        WAIT(1, "待支付"),
        SUCCESS(2, "支付成功"),
        REFUND(3, "已退款");

        private Integer code;
        private String msg;

        public static OrderStatusEnum toType(int value) {
            return Stream.of(OrderStatusEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (OrderStatusEnum c : OrderStatusEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }


    @Getter
    @AllArgsConstructor
    public enum NumberTypeEnum {

        ONE(1, "普通套餐"),
        TWO(2, "新人套餐"),
        THREE(3, "邀请赠送套餐");

        private Integer code;
        private String msg;

        public static NumberTypeEnum toType(int value) {
            return Stream.of(NumberTypeEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (NumberTypeEnum c : NumberTypeEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 存储类型
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum StorageTypeEnum {

        ALI_OSS("ALI_OSS", "阿里oss"),
        LOCAL_STORAGE("LOCAL_STORAGE", "本地存储");

        private String code;
        private String msg;

        public static StorageTypeEnum toType(String value) {
            return Stream.of(StorageTypeEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(String status) {
            for (StorageTypeEnum c : StorageTypeEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 存储类型
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum SMSTypeEnum {

        YUN_JI("YUN_JI", "云集"),
        ALI_DA_YU("ALI_DA_YU", "阿里大鱼");

        private String code;
        private String msg;

        public static SMSTypeEnum toType(String value) {
            return Stream.of(SMSTypeEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(String status) {
            for (SMSTypeEnum c : SMSTypeEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 支付类型
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum TranTypeEnum {

        AliPay(2004, "支付宝支付"),
        WechatPay(1006, "微信支付"),
        UnionPay(9004, "银联");

        private Integer code;
        private String msg;

        public static TranTypeEnum toType(int value) {
            return Stream.of(TranTypeEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (TranTypeEnum c : TranTypeEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 众筹状态
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum GameEnum {

        NOT(1, "未开始"),
        ON(2, "进行中/预约中"),
        OFF(3, "已完成");

        private Integer code;
        private String msg;

        public static GameEnum toType(int value) {
            return Stream.of(GameEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (GameEnum c : GameEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 是否为vip
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum VipEnum {

        YES(1, "是"),
        NO(-1, "否");

        private Integer code;
        private String msg;

        public static VipEnum toType(int value) {
            return Stream.of(VipEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (VipEnum c : VipEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 是否认证
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum RealEnum {

        YES(1, "已认证"),
        NO(0, "未认证");

        private Integer code;
        private String msg;

        public static RealEnum toType(int value) {
            return Stream.of(RealEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (RealEnum c : RealEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 团队等级
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum TeamLevelEnum {

        ZERO(0, "零级"),
        ONE(1, "一级"),
        TWO(2, "二级"),
        THREE(3, "三级"),
        FOUR(4, "四级");

        private Integer code;
        private String msg;

        public static TeamLevelEnum toType(int value) {
            return Stream.of(TeamLevelEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (TeamLevelEnum c : TeamLevelEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 性别
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum SexEnum {

        NON(0, "未知"),
        MAN(1, "男"),
        WOMAN(2, "女");

        private Integer code;
        private String msg;

        public static SexEnum toType(int value) {
            return Stream.of(SexEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (SexEnum c : SexEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 用户状态
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum UserEnum {

        YES(0, "正常"),
        NO(1, "封禁");

        private Integer code;
        private String msg;

        public static UserEnum toType(int value) {
            return Stream.of(UserEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (UserEnum c : UserEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 订单状态
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum OrderEnum {

        /**
         * 待付款
         */
        WAIT_PAY(2, "待付款"),
        /**
         * 待收货
         */
        WAIT_GET(3, "待收货"),
        /**
         * 已完成
         */
        FINISHED(4, "已完成"),
        /**
         * 已取消
         */
        WAIT_REFUND(1, "已取消");

        private Integer code;
        private String msg;

        public static OrderEnum toType(int value) {
            return Stream.of(OrderEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (OrderEnum c : OrderEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 是否默认收货地址
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum DefaultEnum {

        YES(1, "是"),
        NO(2, "否");

        private Integer code;
        private String msg;

        public static DefaultEnum toType(int value) {
            return Stream.of(DefaultEnum.values())
                    .filter(p -> p.code == value)
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(int status) {
            for (DefaultEnum c : DefaultEnum.values()) {
                if (c.code.compareTo(status) == 0) {
                    return c.msg;
                }
            }
            return null;
        }
    }

    /**
     * @Description: 是否默认收货地址
     * @Param:
     * @return:
     * @Author: tsai
     * @Date: 2022/4/19 10:42
     */
    @Getter
    @AllArgsConstructor
    public enum ExpressEnum {

        SF("SF", "顺丰速运"),
        HTKY("HTKY", "百世快递"),
        ZTO("ZTO", "中通快递"),
        STO("STO", "申通快递"),
        YTO("YTO", "圆通速递"),
        YD("YD", "韵达速递"),
        YZPY("YZPY", "邮政快递包裹"),
        JD("JD", "京东快递"),
        UC("UC", "优速快递"),
        DBL("DBL", "德邦快递"),
        JTSD("JTSD", "极兔速递"),
        ZYE("ZYE", "众邮快递"),
        ZJS("ZJS", "宅急送");


        private String code;
        private String msg;

        public static DefaultEnum toType(String value) {
            return Stream.of(DefaultEnum.values())
                    .filter(p -> p.code.equals(value))
                    .findAny()
                    .orElse(null);
        }

        public static String getMsg(String status) {
            for (DefaultEnum c : DefaultEnum.values()) {
                if (c.code.equals(status)) {
                    return c.msg;
                }
            }
            return null;
        }
    }
}
