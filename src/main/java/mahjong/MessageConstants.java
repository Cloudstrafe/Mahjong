package mahjong;

public class MessageConstants {
    public static final String MSG_TSUMO = "Player {0}, would you like to tsumo?";
    public static final String MSG_RON = "Player {0}, would you like to ron?";
    public static final String MSG_KAN = "Player {0}, would you like to kan?";
    public static final String MSG_KAN_OR_PON = "Player {0}, would you like to kan or pon?";
    public static final String MSG_PON = "Player {0}, would you like to pon?";
    public static final String MSG_CHI = "Player {0}, would you like to chi?";
    public static final String MSG_WIN = "Player {0} wins!";
    public static final String MSG_EMPTY_DECK = "Deck empty, starting new hand";

    private MessageConstants() {
        throw new IllegalStateException("Utility class");
    }
}
