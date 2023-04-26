package com.JNF.enumFuncional;

/**
 * Movie
 */
public class Movie {

    enum Type {
        REGULAR {
            @Override
            public int getPrice(int days) {
                return days + 1;
            }
        }, 
        HOT {
            @Override
            public int getPrice(int days) {
                return days * 2;
            }
        }, 
        CHILDREN {
            @Override
            public int getPrice(int days) {
                return 5;
            }
        };
        
        // 用作提醒, 如果增加新enum成员
        public abstract int getPrice(int days);
    }

    private Type type;

     public int getPrice(int days) {
        switch (type) {
            case REGULAR:
                return days + 1;
            case HOT:
                return days * 2;
            case CHILDREN:
                return 5;
            default:
                throw new IllegalArgumentException();
        }
    }
}