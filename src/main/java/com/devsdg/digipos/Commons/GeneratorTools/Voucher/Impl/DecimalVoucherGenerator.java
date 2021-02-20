package com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl;

import com.devsdg.digipos.Commons.GeneratorTools.Voucher.VoucherGenerator;

public class DecimalVoucherGenerator extends VoucherGenerator {
    @Override
    public void generate(int quantity) throws IllegalArgumentException {
        if (quantity > 0) {
            String format = voucherFormat.replace("%s", "");
            for(int i = 0; i<quantity; i++) {
                list.add(String.format(format, i));
            }
        }else{
            throw new IllegalArgumentException("Can't generate vouchers of negative or zero quantities");
        }
    }
}
