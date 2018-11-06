package com.example.tccl9.polypicker;

public class Question {
    public String mQuestion[]={
            "1. A movie you\'re most likely to watch again",
            "2. A superhero you would like to be",
            "3. Mobile games that you would enjoy",
            "4. Your favourite weekend activity?",
            "5. Your favourite application?",
            "6. Traits that describe you?",
            "7. Your faourite subjects in school",
            "8. Things that you want to learn when studying in a polytechnic",
            "9. Your future career goals?",
            "10. When i need to complete a task, I look forward to: ",
            "11. I get inspired when: ",
            "12. I feel motivated to work when: ",
            "13. What matter the most for your future job: ",
            "14. I learnt best when: "
    };

    public String mChoices[][]={
            {"Zootopia", "The Intern", "Interstellar", "Lalaland"},
            {"Tony Stark the Philantropist (Iron man)", "Bruce Wayne the Businessamn (Batman)", "Dr Bruce Banner the Scientist (Hulk)", "Peter Parker the Photographer (Spiderman)"},
            {"Cooking dash, Smurfs\' village, Pokemon GO", "Monopoly Deal, Clash Royale", "Minecraft, Plants vs Zombies", "Monument Valley, Colouring Book, Dots"},
            {"Volunteer at a hospital, nursing or old folks\' home", "Sell items on Carousell, or at SCAPE and flea market", "Explore ArtScience Museum, Gardens by the Bay and Science Centre", "Get inspiration at National Gallery or watch a play at the Esplanade"},
            {"Telegram, WhatsApp, Messenger", "News, Health & Fitness Apps", "Instagram, Snapchat, Pinterest", "AutoCAD, Adobe, Procreate"},
            {"Friendly, caring and sociable", "Competitive, driven and enjoys taking intiative", "Logical, precise and detailed", "Artistic and expressive"},
            {"Language, Character & Citizenship Education and Values in action", "Mathematics, Business Studies", "Mathematics, Sciences, Science & Technology", "Humanities and the Arts such as Literature, Geography and History"},
            {"I want to learn different ways to help and care for people", "I want to learn how a business operates and how to manage teams", "I want to learn how to create gadgets and make scientific discoveries", "I want to learn the different ways to communicate to the masses and design products"},
            {"Help others from the young to the old and make a difference to society", "Lead a team to win awards, achieve success or own a profit-driven business", "Invent and create new innovations, gadgets and discoveries to benefit society", "Tell stories that people will remember and design beautiful things"},
            {"Finishing it on my own", "Earning from it", "Being able to help others", "Encouraging myself for a job well done"},
            {"I have the freedom to choose what I want to do", "I can buy things for myself", "People compliment me for my work", "I get to contribute to a greater cause"},
            {"I gain tangible rewards for it", "I am the best at what I do", "There is flexibility in time for finishing something", "I know other people will benefit from what I do"},
            {"Others know that I have given my fullest effort", "I have impacted another person\'s life", "I can balance my work and personal life", "I can receive a satisfactory income"},
            {"I can do it at my own pace", "There is money incentive", "It fits my moral standards", "It gives me expertise and knowledge"},
    };

    public String getQuestions(int a){
        return mQuestion[a];
    }
    public String getChoices1(int a){
        return mChoices[a][0];
    }
    public String getChoices2(int a){
        return mChoices[a][1];
    }
    public String getChoices3(int a){
        return mChoices[a][2];
    }
    public String getChoices4(int a){
        return mChoices[a][3];
    }
}
