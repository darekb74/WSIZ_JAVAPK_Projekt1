/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

/**
 *
 * @author Darek Xperia
 * Interfejs kart
 */
public interface Karta {

    public void init(Object[] args);

    public void logout();

    public void takeAction(int type);
}
