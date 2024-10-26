package MonteCarlo.PokemonChecker;

import java.util.ArrayList;
import java.util.Random;

import PokemonCardGame.CardGame;
import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import PokemonCardGame.CardTypes.TrainerCards.Trainer;
import java.util.Collections;


public class PokemonHand 
{
    //Problem, find out how to properly shuffle the pokemon cards in the deck without them being most likely at the start
    //Solution, fill the deck with 60 energy and trainer cards then replace x of them randomly with pokemon cards, maybe in player
    public static void main(String[] args)
    {
        PokemonHand experiment = new PokemonHand();
        Player player = new Player();
        int runs = 1000;
        Random rng = new Random();
        ArrayList<Double> successRates = new ArrayList<>();
        for(int pokemonCount = 0; pokemonCount < 60; pokemonCount++)
        {
            int success = 0;
            for(int j = 0; j < runs; j++)
            {
                ArrayList<Card> deck = new ArrayList<>();
                // Fill deck with 60 cards of either energy or trainer
                for(int k = 0; k < 60; k++)
                {
                    int cardType = rng.nextInt(2);
                    if(cardType == 0)
                    {
                        deck.add(new Energy());
                    }
                    else
                    {
                        deck.add(new Trainer());
                    }
                }
                // Replace pokemonCount cards with pokemon cards, then shuffle the deck
                for(int k = 0; k < pokemonCount; k++)
                {
                    deck.add(new Pokemon());
                    deck.remove(0);
                }
                Collections.shuffle(deck);

                player.setActiveDeck(deck);
                for(int i = 0; i < 7; i++)
                {
                    player.drawCard();
                }
                if(experiment.hasPokemon(player))
                {
                    success++;
                }
            }
            successRates.add((double)success / runs);  
        }

        System.out.println(successRates);

        
    }

    private boolean hasPokemon(Player player)
    {
        for(Card card : player.getHand())
        {
            if(card instanceof Pokemon)
            {
                return true;
            }
        }
        return false;
    }    
}



