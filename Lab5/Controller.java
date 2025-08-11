    import java.util.List;
    import java.util.ArrayList;
    import java.util.Random;
    import java.io.*;
    public class Controller
    {
        //TODO: Battle Mechanics and Save Character Obj
        final int NUMMENUS = 7;
        protected String filename = "save.ser";
        private Menu_UI[] menus;
    
        public Controller()
        {
            menus = new Menu_UI[NUMMENUS];
            createMenus();
        }
    
        public void execute()
        {
            menus[6].print("Welcome to my game!");
            int choice = menus[6].getInput();
            if (choice == 1)
            {
                //Create new character
                String name = menus[0].getUserInput("What is your name?");
                Character mychar = CreateCharacter(name);
                menus[0].print(mychar.toString());
                menus[0].print("Welcome to the start of your amazing advanture " + name + "!");
                MainMenu(mychar);
            }
            else if (choice == 2)
            {
                //Call method to load save
                Character mychar = Load();
                try 
                {
                    mychar.getName();  
                }
                catch (Exception e)
                {
                    menus[6].print("No character object here!");
                    execute();
                }
                menus[6].print("Welcome back " + mychar.getName() + "!");
                MainMenu(mychar);
            }
            else if (choice == 3)
            {
                menus[6].print("Goodbye!");
            }
        } 
        
        public void MainMenu(Character m)
        {
            //The main menu of the program, allows user to do various actions
            //actions modify character object 
            menus[1].print("What would you like to do?");
            int choice = menus[1].getInput();
            if (choice == 1)
            {
                //Prints information about characteer object
                menus[1].print(m.toString());
                MainMenu(m);
            }
            else if (choice == 2)
            {
                //Loads shop menu
                menus[1].print("You go to the town shop with " + m.getGold() + " gold.");
                GoToShop(m);
            }
            else if (choice == 3)
            {
                //Loads field menu
                GoToField(m);
            }
            else if (choice == 4)
            {
                //Loads Dungeon
                GoToDungeon(m);
            }
            else if (choice == 5)
            {
                //Save
                Save(m);
            }    
            
            else if (choice == 6)
            {
                //Quits the program
                menus[1].print("Thanks for playing! Goodbye!");
            }
        }
    
        private void createMenus()
        {
            //welcome menu 
            menus[0] = new Menu_UI();
            menus[0].addChoice("Enter your name.");
            menus[0].addChoice("Quit.");
    
            //main menu
            menus[1] = new Menu_UI();
            menus[1].addChoice("Character info");
            menus[1].addChoice("Go to Shop");
            menus[1].addChoice("Go to Field");
            menus[1].addChoice("Go to Dungeon");
            menus[1].addChoice("Save");
            menus[1].addChoice("Quit");
    
            //ShopMenu
            menus[2] = new Menu_UI();
            menus[2].addChoice("Upgrade Sword");
            menus[2].addChoice("Upgrade Shield");
            menus[2].addChoice("Buy Potion.");
            menus[2].addChoice("Leave Shop");
    
            //Field Menu
            menus[3] = new Menu_UI();
            menus[3].addChoice("Travel");
            menus[3].addChoice("Return home.");
    
            //Battle Menu
            menus[4] = new Menu_UI();
            menus[4].addChoice("Fight");
            menus[4].addChoice("Use Potion");
            menus[4].addChoice("Flee");
            
            //YesNo Menu
            menus[5] = new Menu_UI();
            menus[5].addChoice("Yes");
            menus[5].addChoice("No");
            
            menus[6] = new Menu_UI();
            menus[6].addChoice("Start New Game");
            menus[6].addChoice("Load Save File");
            menus[6].addChoice("Exit");
        }
        
        public void GoToShop(Character m)
        {
            menus[2].print("Shopkeeper: Welcome to my shop! How may I help you?");
            int price;
            boolean pricecheck;
            int choice = menus[2].getInput();
            if (choice == 1)
            {
                String grade = ((Sword)(m.getInventory().get(0))).getGrade();
                //System.out.println(grade);
                //Executed if statement dependent on grade of sword
                //Shop method modifies objects in the character objects inventory
                //inventory is an array, this array is saved to the character object
                //Each item instantiation inherits attributes from an item superclass
                if(grade.equals("Poor"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your sword? That will be 50 gold.");
                    price = 50;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new sword!");
                            ((Sword)(m.getInventory().get(0))).setGrade("Good");
                            ((Sword)(m.getInventory().get(0))).setDamage(25);
                            menus[2].print("You sword has been upgraded to Grade: " + ((Sword)(m.getInventory().get(0))).getGrade() + 
                            " and does " + ((Sword)(m.getInventory().get(0))).getDamage() + " damage.");
                            m.loseGold(50);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        }
                    }                
                }
                if(grade.equals("Good"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your sword? That will be 100 gold.");
                    price = 100;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new sword!");
                            ((Sword)(m.getInventory().get(0))).setGrade("Great");
                            ((Sword)(m.getInventory().get(0))).setDamage(50);
                            menus[2].print("You sword has been upgraded to Grade: " + ((Sword)(m.getInventory().get(0))).getGrade() + 
                            " and does " + ((Sword)(m.getInventory().get(0))).getDamage() + " damage.");
                            m.loseGold(100);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        } 
                    }                        
                }            
                if(grade.equals("Great"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your sword? That will be 200 gold.");
                    price = 200;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new sword!");
                            ((Sword)(m.getInventory().get(0))).setGrade("Excellent");
                            ((Sword)(m.getInventory().get(0))).setDamage(75);
                            menus[2].print("You sword has been upgraded to Grade: " + ((Sword)(m.getInventory().get(0))).getGrade() + 
                            " and does " + ((Sword)(m.getInventory().get(0))).getDamage() + " damage.");
                            m.loseGold(200);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        } 
                    }                        
                }
                if(grade.equals("Excellent"))
                {
                    menus[2].print("Shopkeeper: I cannot upgrade this sword, it is already max grade.");
                    GoToShop(m);
                }
            }
            if (choice == 2)
            {
                String grade = ((Shield)(m.getInventory().get(1))).getGrade();
                //System.out.println(grade);
                //Executed if statement dependent on grade of shield
                if(grade.equals("Poor"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your shield? That will be 25 gold.");
                    price = 25;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new shield!");
                            ((Shield)(m.getInventory().get(1))).setGrade("Good");
                            ((Shield)(m.getInventory().get(1))).setDefense(20);
                            menus[2].print("You shield has been upgraded to Grade: " + ((Shield)(m.getInventory().get(1))).getGrade() + 
                            " and defends against " + ((Shield)(m.getInventory().get(1))).getDefense() + " damage.");
                            m.loseGold(25);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        }
                        //Modify numpot value in potion object stored in character's inventory 
                    }                
                }
                if(grade.equals("Good"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your shield? That will be 50 gold.");
                    price = 50;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new shield!");
                            ((Shield)(m.getInventory().get(1))).setGrade("Great");
                            ((Shield)(m.getInventory().get(1))).setDefense(40);
                            menus[2].print("You shield has been upgraded to Grade: " + ((Shield)(m.getInventory().get(1))).getGrade() + 
                            " and defends against " + ((Shield)(m.getInventory().get(1))).getDefense() + " damage.");
                            m.loseGold(50);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        }
                    }                        
                }            
                if(grade.equals("Great"))
                {
                    menus[2].print("Shopkeeper: Oh you'd like to upgrade your shield? That will be 100 gold.");
                    price = 100;
                    pricecheck = PriceCheck(m, price);
                    if (pricecheck == false)
                    {
                        menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                        GoToShop(m);
                    }
                    else if (pricecheck == true)
                    {
                        boolean confirm = purchConf();
                        if (confirm == true)
                        {
                            menus[2].print("Shopkeeper: Here's your new shield!");
                            ((Shield)(m.getInventory().get(1))).setGrade("Excellent");
                            ((Shield)(m.getInventory().get(1))).setDefense(80);
                            menus[2].print("You shield has been upgraded to Grade: " + ((Shield)(m.getInventory().get(1))).getGrade() + 
                            " and defends against " + ((Shield)(m.getInventory().get(1))).getDefense() + " damage.");
                            m.loseGold(100);
                            GoToShop(m);
                        }
                        else if (confirm == false)
                        {
                            menus[2].print("Shopkeeper: Changed your mind?");
                            GoToShop(m);
                        }
                    }                        
                }
                if(grade.equals("Excellent"))
                {
                    menus[2].print("Shopkeeper: I cannot upgrade this shield, it is already max grade.");
                    GoToShop(m);
                }
            }
            if (choice == 3)
            {
                //Recovery value does not change, numpot does
                menus[2].print("Shopkeeper: Oh you'd like to buy a potion? That will be 10 gold.");
                price = 10;
                pricecheck = PriceCheck(m, price);
                if (pricecheck == false)
                {
                    menus[2].print("Shopkeeper: Ahh, sorry you don't have enough gold.");
                    GoToShop(m);
                }
                else if (pricecheck == true)
                {
                    boolean confirm = purchConf();
                    if (confirm == true)
                    {
                    menus[2].print("Shopkeeper: Here you go!");
                    ((Potion)(m.getInventory().get(2))).GainPot(1);
                    menus[2].print("You have " + ((Potion)(m.getInventory().get(2))).getNumPot() + " potions.");
                    m.loseGold(10);
                    GoToShop(m);
                    }
                    else if (confirm == false)
                    {
                    menus[2].print("Shopkeeper: Changed your mind?");
                    GoToShop(m);
                    }
                }
            }
            if (choice == 4)
            {
                //Leave the shop
                menus[2].print("Thank you for visiting!");
                MainMenu(m);
            }
        }
        private boolean PriceCheck(Character m, int price)
        {
            //Executes everytime character purchases an item
            boolean condition = false;
            if ((m.getGold() - price) < 0)
            {
            condition = false;
            }
            else if ((m.getGold() - price) > 0 )
            {
            condition = true;
            }
            return condition;
        }
        private boolean purchConf()
        {
            //If the user can purchase an item, this block executes to confirm the purchase
            boolean condition = false;
            int choice = menus[5].getInput();
            if (choice == 1)
            {
                condition = true;
            }
            else if (choice == 2)
            {
                condition = false;
            }
            return condition;
        }
        
        public void GoToField(Character m)
        {
            //This method modifies the character object's cold value, easy way to get gold
            int total = 0;
            int choice = 0;
            menus[3].print("You traveled through the fields not far from town...");
            choice = menus[3].getInput();
            while (choice == 1)
            {
            menus[3].print("You frolic around  in the fields for a little while...");
            int gain = Random(10);
            menus[3].print("You found " + gain + " gold!");
            m.gainGold(gain);
            total += gain;
            choice = menus[3].getInput();
            }
            menus[3].print("You found a total of " + total + " gold!"); 
            MainMenu(m);
        }
        
        public void GoToDungeon(Character m)
        {
            boolean cont;
            menus[4].print("You hear rumors of monsters inhabiting the woods not far from town." + "\n"
            + "You decide to go investigate these rumors and travel deep into the woods." + "\n"
            + "While peering through the trees, you hear something rustling in the shrubbery nearby...");
            //Create some monsters
            Monster mob1 = CreateMob("Goblin", 50, 50);
            Monster mob2 = CreateMob("Warrior Goblin", 100, 100);
            Monster mob3 = CreateMob("King Goblin", 200, 200);

            BattleSeq(m, mob1); //Starts battle sequence
            cont = ContDungeon(m); //This is called everytime the character defeats one of the stages
            if (cont == true)
            {
                menus[4].print("After slaying the " + mob1.getName() + ", you hear something rushing towards you through the trees." + "\n"
                + "You quickly dash to the left to get away from the sound. You're able to catch a glimpse of your pursuer!");
                BattleSeq(m, mob2);                
            }
            else 
            {
                menus[4].print("You realize that you bit off more than you could chew and quickly leave the woods.");
                m.Heal(100);
                MainMenu(m);
                return;
            }
            
            cont = ContDungeon(m);
            if (cont == true)
            {
                menus[4].print("You finished the battle with minor scathing, sword in hand you're about ready to leave the woods." + "\n"
                + "Suddenly, you feel frozen! You get a strong feeling that something is watching you..." + "\n"
                + "You take a deep breathe and raise your sword. You're ready to take on your biggest foe yet!");
                BattleSeq(m, mob3);
                if (mob3.getHP() == 0)
                {
                menus[4].print("After slaying the " + mob3.getName() + " you get a sudden feeling of tranquility." +  "\n"
                + "It's as if all the monsters lurking  nearby dispersed back from whence they came...");
                int reward = 1000;
                menus[4].print("You found " + reward + " gold!");
                m.gainGold(reward);
                }
            }
            else 
            {
                menus[4].print("You realize that you bit off more than you could chew and quickly leave the woods.");
                m.Heal(100);
                MainMenu(m);
                return;
            }
            m.Heal(100);
            MainMenu(m);
        }
        
        private boolean ContDungeon(Character m)
        {
            boolean contbool = false;
            if (m.getHP() > 0 )
            {
                menus[5].print("Would you like to continue?");
                int choice = menus[5].getInput();
                if (choice == 1)
                {
                    contbool = true;
                }
            } 
            else
            {
                contbool = false;
            }
            return contbool;
        }
        
        public void BattleSeq(Character m, Monster n)
        {
            //Battle sequence runs until monster or player is dead
            menus[4].print("A " + n.getName() + " appeared!"); 
            boolean fight = true; 
            while (fight == true)
            {
            menus[4].print(m.getName() + "'s health: " + m.getHP());
            menus[4].print(n.getName() + "'s health: " + n.getHP());
            int choice = menus[4].getInput();
            if (choice == 1)
            {
                CharAttack(m, n);
            }
            else if (choice == 2)
            {
                int recov;
                int potnum;
                recov = ((Potion)(m.getInventory().get(2))).getRecover();
                potnum = ((Potion)(m.getInventory().get(2))).getNumPot();
                if (potnum > 0)
                {
                ((Potion)(m.getInventory().get(2))).UsePot(1);
                m.Heal(recov);
                menus[4].print("You used a potion and recovered " + recov + " health! You have " + potnum + " potions remaining.");
                }
                else 
                {
                menus[4].print("Out of potions!");    
                }
            }
            else if (choice == 3)
            {
                int lose = Random(50);
                m.loseGold(lose);
                menus[4].print("You hastily fled the dungeon dropping " + lose + " gold.");
                fight = false;
            }            
            
            if (n.getHP() == 0)
            {
                int gain = Random(n.getDamage() * 3);
                menus[4].print("You defeated the " + n.getName() + "!");
                menus[4].print("You found " + gain + " gold!"); 
                m.gainGold(gain);
                fight = false;
            }             
            
            if (fight == true)
            {    
                MobAttack(m, n);
            } 
            
            if (m.getHP() == 0)
            {
                menus[4].print("You died and lost 50 gold.");
                m.loseGold(50);
                fight = false;
            }
          
            }            
 
        }
    
        private void CharAttack(Character m, Monster n)
        {
            //Calls the calculated damage then decreases health for monster "n"
            int cdmgf = DamageCalc(m);
            n.takeDamage(cdmgf);
            menus[4].print("You attacked the " + n.getName() + " and dealt " + cdmgf + " damage.");
        }
    
        private int DamageCalc(Character m)
        {
            //Damage is calculated by taking a random number from the sword damage then multiplying it by the grade
            int calcdmg = 0;
            int swdmg = ((Sword)(m.getInventory().get(0))).getDamage();
            //System.out.println(swdmg);
            int swdmgf = Random(swdmg);
            //System.out.println(swdmgf);
            calcdmg = swdmgf;
            String swgrade = ((Sword)(m.getInventory().get(0))).getGrade();
            if (swgrade == "Poor")
            {
                calcdmg *= 1;
            }
            else if (swgrade.equals("Good"))
            {
                calcdmg *= 2;
            }
            else if (swgrade.equals("Great"))
            {
                calcdmg *= 3;
            }
            else if (swgrade.equals("Excellent"))
            {
                calcdmg *= 4;
            }        
            return calcdmg;
        }
    
        private void MobAttack(Character m, Monster n)
        {
            //Mob does random amount of damage to character
            int dmg = n.getDamage();
            int cdef = ((Shield)(m.getInventory().get(1))).getDefense();
            int mdmgf = Random(dmg);
            mdmgf -= cdef;
            if (mdmgf < 0)
            {
                mdmgf = 0;
            }
            m.takeDamage(mdmgf);
            menus[4].print(n.getName() + " attacked, you lost " + mdmgf + " health.");
        }
    
        public Monster CreateMob(String name, int hp, int damage)
        {
            //Method for mob creation
            return new Monster(name, hp, damage);
        }
            
        public Character CreateCharacter(String name)
        {
            //Method for character creation 
            Sword sw1 = new Sword("Sword", "Poor", 10);
            Shield sh1 = new Shield("Shield", "Poor", 5);
            Potion pot1 = new Potion("Potion", 5, 50);
            List<Item> inventory = new ArrayList<Item>();
            inventory.add(sw1);
            inventory.add(sh1);
            inventory.add(pot1);
            int gold = 0;
            int hp = 100;
            
            return new Character(name, gold, hp, inventory);   
        }
        
        private int Random(int upper)
        {
            //random number generator
            int num;
            Random rand = new Random();
            num = rand.nextInt(upper)+1;
            return num;
        }
        
        private void Save(Character m)
        {
            try
            {   
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);
                  
                // Method for serialization of object
                out.writeObject(m);
                  
                out.close();
                file.close();
                  
                menus[1].print("Your character has been saved!");
      
            }
              
            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }
            MainMenu(m);
        }
        
        private Character Load()
        {
            Character m= null;
            try
            {   
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);
                  
                // Method for deserialization of object
                
                m = (Character)in.readObject();
                
                  
                in.close();
                file.close();
                  
                menus[6].print("Your character has been loaded!");
            }
              
            catch(IOException ex)
            {
                menus[1].print("IOException is caught");
            }
              
            catch(ClassNotFoundException ex)
            {
                menus[1].print("ClassNotFoundException is caught");
            }
            return m;
        }
}