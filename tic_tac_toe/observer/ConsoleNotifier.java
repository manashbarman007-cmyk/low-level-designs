package tic_tac_toe.observer;

import tic_tac_toe.player.Player;

public class ConsoleNotifier implements Observer{
    private final Player player;
    public ConsoleNotifier (Player player) {
        this.player = player;
    }
    @Override
    public void update(String msg) {
        System.out.println("Message for : " + player.getName() + " : " + msg);
    }



}
