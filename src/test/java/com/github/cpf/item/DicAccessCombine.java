package com.github.cpf.item;


import com.github.codedict.core.IDictItem;
import com.github.codedict.core.StaticDictPool;
import com.github.codedict.util.StringUtils;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 11:42
 **/
public interface DicAccessCombine {


    /**
     * 投资范围
     */
    enum InvestmentRange implements IDictItem {
        Bonds("1", "债券"),
        ABS("2", "ABS"),
        funds("4", "基金"),
        trusts("8", "信托"),
        bank_deposits("16", "银行存款"),
        bank_deposit_certificates("32", "银行存单"),
        options("64", "期权"),
        equity("128", "股权"),
        other("1024", "其它");

        public static String castCode(Integer integer) {
            StringBuilder stringBuilder = new StringBuilder();
            InvestmentRange[] values = InvestmentRange.values();
            for (InvestmentRange value : values) {
                if (((Integer.parseInt(value.value()) & integer) > 0)) {
                    stringBuilder.append(",");
                    stringBuilder.append(value.value());
                }
            }
            if (stringBuilder.length() == 0) {
                return "";
            }
            return stringBuilder.substring(1);
        }

        public static String castInvestmentRadio(String investmentRadio) {
            if (StringUtils.isBlank(investmentRadio)) {
                return "";
            }
            String[] split = investmentRadio.split(";");
            String s;
            StringBuilder strBdr = new StringBuilder();
            for (int i = 0, len = split.length, tp, mi; i < len; i++) {
                if (i != 0) {
                    strBdr.append("; ");
                }
                s = split[i];
                tp = s.indexOf(':');
                String textByCode = getTextByCode(Integer.parseInt(s.substring(0, tp)));
                strBdr.append(textByCode);
                strBdr.append(s.substring(tp));
                strBdr.append("%");
            }
            return strBdr.toString();
        }

        public static Integer castCodeByText(String text) {
            if (StringUtils.isBlank(text)) {
                return 0;
            }
            String[] split = text.split(",");
            int value = 0;
            for (String s : split) {
                s = s.trim();
                if ("".equals(s)) {
                    continue;
                }
                String code = IDictItem.getLabelByValue(InvestmentRange.class, s);
                value += Integer.parseInt(code);
            }
            return value;
        }

        public static String getTextByCode(Integer integer) {
            if (integer == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            InvestmentRange[] values = InvestmentRange.values();
            for (InvestmentRange value : values) {
                if (((Integer.parseInt(value.value()) & integer) > 0)) {
                    stringBuilder.append(",");
                    stringBuilder.append(value.label());
                }
            }
            if (stringBuilder.length() == 0) {
                return "";
            }
            return stringBuilder.substring(1);
        }

        InvestmentRange(String code, String text) {
            StaticDictPool.putDictItem(this, code, text);
        }
    }


    enum RolePermState implements IDictItem {
        /**
         * 授予权限
         */
        authorize("y", "启用"),
        /**
         * 权限无效状态
         */
        invalid("i", "失效"),
        /**
         * 剥夺权限
         */
        disable("n", "禁用");

        RolePermState(String code, String text) {
            StaticDictPool.putDictItem(this, code, text);
        }
    }

}
