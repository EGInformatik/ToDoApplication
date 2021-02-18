/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package todoapplication;

/**
 *
 * @author alex
 */
public class Todo {
    
    public String todoString;
    public String username;
    public Object timestamp;
    public boolean isDone;

    @Override
    public String toString() {
        return "Todo{" + "todoString=" + todoString + ", username=" + username + ", timestamp=" + timestamp + ", isDone=" + isDone + '}';
    }
    
}
