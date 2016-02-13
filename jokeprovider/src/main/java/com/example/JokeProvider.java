package com.example;

import java.util.Random;

public class JokeProvider {


    public JokeProvider() {
    }

    /**
     * Picks out a joke from an array at random.
     * TODO: make it pull from an XML resource file
     * @return String
     */
    public String getJoke() {

        String[] jokes = {
                "Last Christmas, we bought a fake Christmas Tree. The guy behind the counter asked my dad: \n" +
                        "\"Are you going to put it up yourself?\" \n" +
                        "and my Dad said: \n" +
                        "\"Don't be disgusting... I'm going to put it in the living room.\"",
                "How do you sell a deaf man a chicken? \n" +
                        "HEY YOU WANNA BUY A CHICKEN?!",
                "What's the difference between Dubai and Abu Dhabi? \n" +
                        "The people in Dubai don't watch The Flintstones. But the people in Abu Dhabi do!"
        };

        Random random = new Random();
        int randInt = random.nextInt();
        int chooserInt = randInt % jokes.length;

        return jokes[chooserInt];
    }
}
