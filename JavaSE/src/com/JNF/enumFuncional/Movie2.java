package com.JNF.enumFuncional;

import java.util.function.BiFunction;

public class Movie2 {

    enum Type {
        REGULAR(PriceService::getRegular),
        HOT(PriceService::getHot),
        CHILDREN(PriceService::getChildren);

        // 用作提醒, 如果增加新enum成员
        public final BiFunction<PriceService, Integer, Integer> priceAlgo;

        private Type(BiFunction<PriceService, Integer, Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
    }

    private final Type type;

    public Movie2(Movie2.Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
    
}



class PriceService {

    int getRegular(int days) {
        return days + 1;
    }

    int getHot(int days) {
        return days * 2;
    }

    int getChildren(int days) {
        return 5;
    }

    public int getPrice(Movie2.Type type, int days) {
        return type.priceAlgo.apply(this, days);
    }
}