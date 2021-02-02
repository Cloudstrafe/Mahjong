package mahjong;

public class Cost {
    private int main = 0;
    private int additional = 0;
    private int mainBonus = 0;
    private int additionalBonus = 0;
    private int kyoutakuBonus = 0;
    private int total = 0;
    private String yakuLevel = "";

    public void setTotal() {
        total = main + mainBonus + 2 * (additional + additionalBonus) + kyoutakuBonus;
    }

    public void setKyoutakuBonus(int riichiSticks) {
        kyoutakuBonus = riichiSticks * 1000;
    }

    public void setTsumiBonuses(int tsumiSticks) {
        if (additional == 0) {
            mainBonus = tsumiSticks * 300;
            additionalBonus = 0;
        } else {
            mainBonus = tsumiSticks * 100;
            additionalBonus = tsumiSticks * 100;
        }
    }

    public int getMainPayment() {
        return main + mainBonus;
    }

    public int getAdditionalPayment() {
        return additional + additionalBonus;
    }

    public int getMain() {
        return main;
    }

    public int getAdditional() {
        return additional;
    }

    public int getMainBonus() {
        return mainBonus;
    }

    public int getAdditionalBonus() {
        return additionalBonus;
    }

    public int getKyoutakuBonus() {
        return kyoutakuBonus;
    }

    public int getTotal() {
        return total;
    }

    public String getYakuLevel() {
        return yakuLevel;
    }
}
