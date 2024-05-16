package com.brandbank.transactions.domain.model.entity.enums;

public enum MovimentationType {
    DEBT("debt"),
    CREDT("credt");

    private String mov;

    MovimentationType(String mov) {
        this.mov = mov;
    }

    public String getMovimentation(){
        return this.mov;
    }

    public static boolean isDEBT(MovimentationType move){
        return move.equals(MovimentationType.DEBT);
    }
}
