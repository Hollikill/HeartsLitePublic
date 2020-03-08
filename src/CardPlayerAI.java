/*
/    Author:
/        Hollister Ream
*/

import java.util.*;

public class CardPlayerAI extends CardPlayer {
	// Contructors
	public CardPlayerAI () {
		super("CardPlayerLevel1", 0, new ArrayList<Card>());
	}
	public CardPlayerAI (String name, int score, ArrayList<Card> hand) {
		super(name, score, hand);
	}
	
	// The important one. Card Choosing AI
	public Card chooseCard(ArrayList<Card> playedRound, ArrayList<Card> playedGame, int playersNum) {
		if (playedRound.size() != 0) {
			/* MODE:
			FIRST */
		}
		else {
			//get led suit
			String suit = playedRound.get(0).getSuit();
			
			//get the current winning card's value
			int val = 0;
			for (Card c : playedRound) {
				if (c.getSuit() == suit) {
					if (c.getValue() > val) {
						val = c.getValue();
					}
				}
			}
			
			//check if i have any cards of the led suit
			ArrayList<Card> ofLeadSuit = new ArrayList<Card>();
			for (Card c : hand) {
				if (c.getSuit() == suit) {
					ofLeadSuit.add(c);
				}
			}
			
			if (ofLeadSuit.isEmpty()) {
				/* MODE:
				PANDER */
				if (suit != "spades") {
					int qOSIndex = -1;
					int qOSi = 0;
					for (Card c : hand) {
						if (c.getSuit() == "spades" && c.getValue() == 12) {
							qOSIndex = qOSi;
						}
						qOSi++;
					}
					
					if (qOSIndex != -1) {
						return playCard(qOSIndex, playedRound, playedGame);
					}
				}
				if (suit != "hearts") {
					ArrayList<Card> cardsOfType = new ArrayList<Card>();
					for (Card c : hand) {
						//check if card meet reqs
						if (c.getSuit() == "hearts") {
							cardsOfType.add(c);
						}
					}
					if (!cardsOfType.isEmpty()) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : cardsOfType) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(cardsOfType.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
				if (suit != "spades") {
					ArrayList<Card> cardsOfType = new ArrayList<Card>();
					for (Card c : hand) {
						//check if card meet reqs
						if (c.getSuit() == "spades" && c.getValue() > 12) {
							cardsOfType.add(c);
						}
					}
					if (!cardsOfType.isEmpty()) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : cardsOfType) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(cardsOfType.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
				if (true) {
					ArrayList<Card> cardsOfType = new ArrayList<Card>();
					for (Card c : hand) {
						//check if card meet reqs
						if (c.getSuit() == "diamonds" && suit != "diamonds") {
							cardsOfType.add(c);
						}
						else if (c.getSuit() == "clubs" && suit != "clubs") {
							cardsOfType.add(c);
						}
					}
					if (!cardsOfType.isEmpty()) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : cardsOfType) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(cardsOfType.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
				if (suit != "spades") {
					ArrayList<Card> cardsOfType = new ArrayList<Card>();
					for (Card c : hand) {
						//check if card meet reqs
						if (c.getSuit() == "spades" && c.getValue() < 12) {
							cardsOfType.add(c);
						}
					}
					if (!cardsOfType.isEmpty()) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : cardsOfType) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(cardsOfType.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
			}
			else {
				if (playedRound.size() == playersNum) {
					/* MODE:
					LAST */
					if (val > 12) {
						int qOSIndex = -1;
						int qOSi = 0;
						for (Card c : hand) {
							if (c.getSuit() == "spades" && c.getValue() == 12) {
								qOSIndex = qOSi;
							}
							qOSi++;
						}
						
						if (qOSIndex != -1) {
							return playCard(qOSIndex, playedRound, playedGame);
						}
					}
					if (suit == "hearts") {
						ArrayList<Card> cardsOfType = new ArrayList<Card>();
						for (Card c : ofLeadSuit) {
							//check if card meet reqs
							if (c.getValue() < val) {
								cardsOfType.add(c);
							}
						}
						if (!cardsOfType.isEmpty()) {
							int handval = 0;
							int highest = 0;
							int index = 0;
							for (Card c : cardsOfType) {
								if (c.getValue() > handval) {
									handval = c.getValue();
									highest = index;
								}
								index++;
							}
							int loc = getHandIndex(cardsOfType.get(highest));
							return playCard(loc, playedRound, playedGame);
						}
					}
					boolean danger = false;
					for (Card c : playedRound) {
						if (c.getSuit() == "hearts" || c.getSuit() == "spades" && c.getValue() == 12) {
							danger = true;
						}
					}
					if (!danger) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : ofLeadSuit) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(ofLeadSuit.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
					else {
						ArrayList<Card> cardsOfType = new ArrayList<Card>();
						for (Card c : ofLeadSuit) {
							//check if card meet reqs
							if (c.getValue() < val) {
								cardsOfType.add(c);
							}
						}
						if (!cardsOfType.isEmpty()) {
							int handval = 0;
							int highest = 0;
							int index = 0;
							for (Card c : cardsOfType) {
								if (c.getValue() > handval) {
									handval = c.getValue();
									highest = index;
								}
								index++;
							}
							int loc = getHandIndex(cardsOfType.get(highest));
							return playCard(loc, playedRound, playedGame);
						}
					}
					if (true) {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : ofLeadSuit) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(ofLeadSuit.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
				else {
					/* MODE:
					MID */
					if (val > 12) {
						int qOSIndex = -1;
						int qOSi = 0;
						for (Card c : hand) {
							if (c.getSuit() == "spades" && c.getValue() == 12) {
								qOSIndex = qOSi;
							}
							qOSi++;
						}
						
						if (qOSIndex != -1) {
							return playCard(qOSIndex, playedRound, playedGame);
						}
					}
					if (suit == "hearts") {
						ArrayList<Card> cardsOfType = new ArrayList<Card>();
						for (Card c : ofLeadSuit) {
							//check if card meet reqs
							if (c.getValue() < val) {
								cardsOfType.add(c);
							}
						}
						if (!cardsOfType.isEmpty()) {
							int handval = 0;
							int highest = 0;
							int index = 0;
							for (Card c : cardsOfType) {
								if (c.getValue() > handval) {
									handval = c.getValue();
									highest = index;
								}
								index++;
							}
							int loc = getHandIndex(cardsOfType.get(highest));
							return playCard(loc, playedRound, playedGame);
						}
					}
					if (true) {
						ArrayList<Card> cardsOfType = new ArrayList<Card>();
						for (Card c : ofLeadSuit) {
							//check if card meet reqs
							if (c.getValue() < val) {
								cardsOfType.add(c);
							}
						}
						if (!cardsOfType.isEmpty()) {
							int handval = 0;
							int highest = 0;
							int index = 0;
							for (Card c : cardsOfType) {
								if (c.getValue() > handval) {
									handval = c.getValue();
									highest = index;
								}
								index++;
							}
							int loc = getHandIndex(cardsOfType.get(highest));
							return playCard(loc, playedRound, playedGame);
						}
					}
					//check if any cards bigger than my smallest card that are not in my hand or played
					boolean canBeSaved = true;
					if (true) {
						int lowBound = 15;
						for (Card c : ofLeadSuit) {
							if (c.getValue() < lowBound) {
								lowBound = c.getValue();
							}
						}
						if (lowBound == 14) {
							canBeSaved = false;
						}
						ArrayList<Integer> knownVals = new ArrayList<Integer>();
						for (Card c : playedGame) {
							if (c.getSuit() == suit) {
								knownVals.add(c.getValue());
							}
						}
						for (Card c : ofLeadSuit) {
							knownVals.add(c.getValue());
						}
						int i = lowBound;
						while (i < 15) {
							boolean tempBool = false;
							if (!knownVals.contains(i)) {
								tempBool = true;
							}
							if (!tempBool) {
								canBeSaved = false;
							}
						}
					}
					if (canBeSaved) {
							int handval = 15;
							int highest = 0;
							int index = 0;
							for (Card c : ofLeadSuit) {
								if (c.getValue() < handval) {
									handval = c.getValue();
									highest = index;
								}
								index++;
							}
							int loc = getHandIndex(ofLeadSuit.get(highest));
							return playCard(loc, playedRound, playedGame);
					}
					else {
						int handval = 0;
						int highest = 0;
						int index = 0;
						for (Card c : ofLeadSuit) {
							if (c.getValue() > handval) {
								handval = c.getValue();
								highest = index;
							}
							index++;
						}
						int loc = getHandIndex(ofLeadSuit.get(highest));
						return playCard(loc, playedRound, playedGame);
					}
				}
			}
		}
		
		System.out.println("CardPlayerAI error: no viable options");
		return null;
	}
	
	public int getHandIndex(Card i) {
		int index = 0;
		int desiredI = 0;
		for (Card c : hand) {
			if (c.getValue() == i.getValue() && c.getSuit() == i.getSuit()) {
				desiredI = index;
			}
			index++;
		}
		return desiredI;
	}
}