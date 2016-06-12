package com.ijzepeda;
import java.lang.String;
import java.util.Random;

public class JavaLibJokes {
    public String getJoke(){
        String [] jokes={
                "I submitted 10 puns to a pun contest hoping one would win but no pun in ten did",
                "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away",
                "I used to like my neighbors, until they put a password on their Wi-Fi.",
                "How do you seduce a fat woman? Piece of cake.",
                "The early bird might get the worm, but the second mouse gets the cheese.",
                "If at first you don't succeed, destroy all evidence that you tried."

        };


        return jokes[new Random().nextInt(jokes.length)];
    }
}
