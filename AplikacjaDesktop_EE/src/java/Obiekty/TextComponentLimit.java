/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Darek Xperia
 */

public class TextComponentLimit extends PlainDocument
{
    private int charactersLimit;

    private TextComponentLimit(int charactersLimit)
    {
        this.charactersLimit = charactersLimit;
    }

    @Override
    public void insertString(int offset, String input, AttributeSet attributeSet) throws BadLocationException
    {
        if (isAllowed(input))
        {
            super.insertString(offset, input, attributeSet);
        } else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean isAllowed(String string)
    {
        return (getLength() + string.length()) <= charactersLimit;
    }

    public static void addTo(JTextComponent textComponent, int charactersLimit)
    {
        TextComponentLimit textFieldLimit = new TextComponentLimit(charactersLimit);
        textComponent.setDocument(textFieldLimit);
    }
}