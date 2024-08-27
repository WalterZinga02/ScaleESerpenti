package box;

import player.Player;

import java.util.Random;

public class DrawACardBox extends AbstractBox{
    private BoxFactory boxFactory;

    public DrawACardBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Player player){
        AbstractBox box = draw();
        box.act(player);
    }

    @Override
    public String getBoxType() {
        return "DrawACard";
    }

    //draws a random special box simulating the draw of a card from a deck of cards in which the probability
    //of drawing a particular type of card is the same as that of any other type of card in the deck
    private AbstractBox draw(){
        int max=8;
        int min=4;
        Random random = new Random();
        int boxTypeNumber = random.nextInt(max-min)+min;
        return boxFactory.createBox(boxNumber, boxTypeNumber,0); //creates a new box using the factory method
    }
}
