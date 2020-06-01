package com.nancy.ddz.run;

import com.nancy.ddz.model.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author chen
 * @date 2020/5/28 23:58
 */
public class CleanCard {
    public ArrayList<Card> cards = new ArrayList<Card>();

    public void initCards() {
        Card ad = new Card();
        int sc = 11;
        for (int i = 3; i <= 10; i++) {
            for (int j = 0; j < 4; j++) {
                ad = new Card(i + "", ad.colors[j], sc);
                cards.add(ad);
                sc++;
            }
            sc = sc + 6;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    ad = new Card("J", ad.colors[j], sc);
                    cards.add(ad);
                } else if (i == 1) {
                    ad = new Card("Q", ad.colors[j], sc);
                    cards.add(ad);
                } else if (i == 2) {
                    ad = new Card("K", ad.colors[j], sc);
                    cards.add(ad);
                } else if (i == 3) {
                    ad = new Card("A", ad.colors[j], sc);
                    cards.add(ad);
                } else {
                    ad = new Card("2", ad.colors[j], sc);
                    cards.add(ad);
                }
                sc++;
            }
            sc = sc + 6;
        }
        ad = new Card("小王", "", sc);
        cards.add(ad);
        ad = new Card("大王", "", sc + 10);
        cards.add(ad);
    }

    public void clean(int j) {
        Card ad = null;
        for (int i = 0; i < cards.size() - 1 - j; i++) {
            if (cards.get(i).score > cards.get(i + 1).score) {
                ad = cards.get(i);
                cards.set(i, cards.get(i + 1));
                cards.set(i + 1, ad);
            }
        }
    }

    public static void main(String[] args) {
        CleanCard cc = new CleanCard();
        Card card = new Card();
        System.out.println(Arrays.toString(card.colors));
        cc.initCards();
        Collections.shuffle(cc.cards);
        System.out.println(cc.cards);
        for (int i = 0; i < cc.cards.size(); i++) {
            cc.clean(i);
        }
        System.out.println(cc.cards);
    }
}
