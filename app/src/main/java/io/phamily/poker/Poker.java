package io.phamily.poker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/**
 * Created by nhatp on 9/15/2017.
 */

public class Poker {

    Queue<Card> cards;
    ArrayList<Card> dealtCards = new ArrayList<Card>(5);


    public class Card
    {
        char rank;
        char suit;

        public Card(char r, char s)
        {
            rank = r;
            suit = s;
        }

        public String toString() {
            return "card_" +String.valueOf(rank) + String.valueOf((suit));
        }
    }

    public void ShuffleCards()
    {

        char[] suits = new char[] {'C', 'D', 'S', 'H'};
        char[] ranks = new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

        ArrayList<Card> deck = new ArrayList<Card>();

        if(cards == null) {

            cards = new LinkedList<>();
            for (char s : suits) {
                for (char r : ranks) {
                    deck.add(new Card(r, s));
                }
            }
            Collections.shuffle(deck);
        }
        else
        {
            while(!cards.isEmpty())
            {
                deck.add(cards.remove());
            }


            while(!dealtCards.isEmpty())
            {
                deck.add(dealtCards.remove(0));
            }
            Collections.shuffle(deck);
        }

        for(Card c : deck)
        {
            cards.add(c);
        }


    }

    public ArrayList<Card> GetPokerHand()
    {
        for(int i = 0; i < 5; i++)
        {
            dealtCards.add(cards.remove());
        }

        return dealtCards;

    }

    public boolean isFlush(ArrayList<Card> cards)
    {
        ArrayList<Character> suits = new ArrayList<Character>(5);
        for(Card card: cards)
        {
            suits.add(card.suit);
        }

        return new HashSet<Character>(suits).size()  == 1 ? true : false;
    }


    public boolean isStraightFlush(ArrayList<Card> cards)
    {
        return isStraight(cards) && isFlush(cards);
    }


    public boolean isFourOfAKind(ArrayList<Card> cards)
    {
        Map<Character, Integer> pair = new HashMap<Character, Integer>();

        for(Card card: cards)
        {
            if(pair.containsKey(card.rank))
            {
                pair.put(card.rank, pair.get(card.rank) + 1);
            }
            else
            {
                pair.put(card.rank, 1);
            }

        }

        int setCount = Collections.frequency(pair.values(), 4);
        return setCount == 1;

    }


    public boolean isFullHouse(ArrayList<Card> cards)
    {
        return isThreeOfAKind(cards) && isPair(cards);
    }

    public boolean isStraight(ArrayList<Card> cards)
    {
        int[] rank = new int[14]; //ace to king or king to ace
        boolean straight = false;


        for(Card card: cards)
        {
            if(card.rank == 'A')
            {
                rank[0] = 1;
                rank[13] = 1;
            }
            else if(card.rank == 'T')
            {
                rank[9] = 1;
            }
            else if(card.rank == 'J')
            {
                rank[10] = 1;
            }
            else if(card.rank == 'Q')
            {
                rank[11] = 1;
            }
            else if(card.rank == 'K')
            {
                rank[12] = 1;
            }
            else
            {
                rank[Character.getNumericValue(card.rank) - 1] = 1;
            }


        }


        for(int r = 0 ; r < rank.length; r++)
        {
            int count = 0;
            for(int i = 0; i < 5; i++)
            {

                if(r + 5 > rank.length)
                    break;

                if(rank[r + i] == 0)
                    break;

                count++;

            }

            if(count == 5)
                straight = true;
        }

        return straight;
    }


    public boolean isThreeOfAKind(ArrayList<Card> cards)
    {
        Map<Character, Integer> pair = new HashMap<Character, Integer>();

        for(Card card: cards)
        {
            if(pair.containsKey(card.rank))
            {
                pair.put(card.rank, pair.get(card.rank) + 1);
            }
            else
            {
                pair.put(card.rank, 1);
            }

        }

        int setCount = Collections.frequency(pair.values(), 3);
        int pairCount= Collections.frequency(pair.values(), 2);

        return setCount == 1 && pairCount == 0;
    }



    public boolean isTwoPairs(ArrayList<Card> cards)
    {
        Map<Character, Integer> pair = new HashMap<Character, Integer>();

        for(Card card: cards)
        {
            if(pair.containsKey(card.rank))
            {
                pair.put(card.rank, pair.get(card.rank) + 1);
            }
            else
            {
                pair.put(card.rank, 1);
            }

        }

        int count = Collections.frequency(pair.values(), 2);

        return count == 2;
    }


    public boolean isPair(ArrayList<Card> cards)
    {
        Map<Character, Integer> pair = new HashMap<Character, Integer>();

        for(Card card: cards)
        {
            if(pair.containsKey(card.rank))
            {
                pair.put(card.rank, pair.get(card.rank) + 1);
            }
            else
            {
                pair.put(card.rank, 1);
            }

        }

        int count = Collections.frequency(pair.values(), 2);

        return count == 1;
    }

    public String GetPokerHands()
    {

//        dealtCards.clear();
//        dealtCards.add(new Card('T','C'));
//        dealtCards.add(new Card('T','C'));
//        dealtCards.add(new Card('T','C'));
//        dealtCards.add(new Card('Q','S'));
//        dealtCards.add(new Card('A','C'));

        if(isStraightFlush(dealtCards))
            return "Straight Flush!";
        else if(isFourOfAKind(dealtCards))
            return "Four of a Kind!";
        else if(isFullHouse(dealtCards))
            return "Full House!";
        else if(isFlush(dealtCards))
            return "Flush!";
        else if(isStraight(dealtCards))
            return "Straight!";
        else if(isThreeOfAKind(dealtCards))
            return "Three of a Kind";
        else if(isTwoPairs(dealtCards))
            return "Two Pairs";
        else if(isPair(dealtCards))
            return "PAIR";


        else
            return "High Card =(";


    }


}

