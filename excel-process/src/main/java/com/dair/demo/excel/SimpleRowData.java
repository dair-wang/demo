package com.dair.demo.excel;


import com.dair.demo.excel.filter.Base;
import lombok.Data;

import java.util.Date;


/**
 * SimpleRowData
 *
 * @author hutao
 * @date 2020年09月13日 16:12:44
 */
@Data
public class SimpleRowData extends Base {
    /**
     * Sku
     */
    private Long sku;
    /**
     * Sku name
     */
    private String skuName;
    /**
     * Price
     */
    private Double price;
    /**
     * Stock num
     */
    private Long stockNum;
    /**
     * Stock date
     */
    private Date stockDate;

}
