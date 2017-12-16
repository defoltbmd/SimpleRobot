package controller;

import java.awt.*;

public interface iComandInvokerDelegate {
    public void didCompletedComand();
    public void didFinishedQueue(Point lastComand);
}
