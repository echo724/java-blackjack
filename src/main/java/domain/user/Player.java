package domain.user;

import domain.Card.Card;
import domain.Card.CardCollection;
import java.util.Objects;

public class Player implements Playable {
    
    public static final String FIRST_HAND_STATUS_ERROR_MESSAGE = "처음에는 2장의 카드만 가질 수 있습니다.";
    public static final String EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";
    public static final int PLAYER_DRAWABLE_BOUNDARY = 21;
    private final String name;
    
    private CardCollection hand = new CardCollection();
    
    public Player(final String name) {
        this.validateName(name);
        this.name = name;
    }
    
    private void validateName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public CardCollection getReadyCards() {
        if (this.hand.size() != 2) {
            throw new IllegalStateException(FIRST_HAND_STATUS_ERROR_MESSAGE);
        }
        return this.hand;
    }
    
    @Override
    public CardCollection getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < PLAYER_DRAWABLE_BOUNDARY;
    }
    
    @Override
    public ParticipantStatus getStatus() {
        return ParticipantStatus.of(this.hand.calculateScore());
    }
    
    @Override
    public int getScore() {
        return this.hand.calculateScore();
    }
    
    @Override
    public boolean isBust() {
        return this.getStatus().isBust();
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.hand);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return this.name.equals(player.name) && this.hand.equals(player.hand);
    }
}
